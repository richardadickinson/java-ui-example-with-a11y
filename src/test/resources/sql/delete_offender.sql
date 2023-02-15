--EXEC DBMS_OUTPUT.enable(NULL);
--set trim on;
--set linesize 300;
--set timing on;
DECLARE
    --
    GC_PARALLEL_FLAG       CONSTANT BOOLEAN := FALSE;
    --
    GC_MASTER_COMPONENT_ID CONSTANT INTEGER := 20000;
    GC_MASTER_MAX_THREADS  NUMBER := PKG_MThread.get_cpu_count * 2;
    --
    LC_USER_ID                   NUMBER := PKG_LOOKUPS.GetUserID(p_distinguished_name => PKG_Common.NVLSTR(SYS_CONTEXT('USERENV', 'CLIENT_IDENTIFIER'), '[Data Maintenance]'));
    LC_UPDATED_DATETIME          DATE   := SYSDATE;
    --
    g_component_name VARCHAR2(30) := 'Z_DEL';
    g_procedure_name VARCHAR2(30);
    --
    l_thread_id            INTEGER;
    l_thread_status        INTEGER;
    l_all_threads_finished BOOLEAN;
    l_thread_label         VARCHAR2(30);
    --
    l_max_threads          INTEGER;
    l_program_action       VARCHAR2(4096);
    l_failed_threads       NUMBER;
    --
    CURSOR cs IS
      SELECT
        O.ROWID AS row_id,
        O.offender_id,
        O.crn
      FROM offender O
      WHERE 1=1
        --AND O.created_datetime > TRUNC(NEXT_DAY(SYSDATE-7, 'MON'))
        --AND O.created_by_user_id IN (SELECT user_id FROM user_ WHERE UPPER(distinguished_name) LIKE UPPER('ndelius30%'))
        AND o.crn =?
        --AND ROWNUM <= 100
      ORDER BY created_datetime;
    --
    l_rec cs%ROWTYPE;
    --
    l_total_processed INTEGER := 0;
    l_offender_id     INTEGER;
    --
    PROCEDURE message(p_msg VARCHAR2) IS BEGIN DBMS_OUTPUT.put_line(p_msg); END message;
    --
    FUNCTION get_max_threads RETURN INTEGER IS
    BEGIN
        RETURN GC_MASTER_MAX_THREADS;
    END get_max_threads;
    --
    PROCEDURE do_del IS
    BEGIN
        --
        LOOP
            l_thread_id := PKG_MTHREAD.get_available_thread(
                                p_component_id => GC_MASTER_COMPONENT_ID,
                                p_wait         => 1,
                                p_timeout      => 30,
                                p_max_threads  => get_max_threads );
            EXIT WHEN l_thread_id > 0;
        END LOOP;
        --
        message('DO_DEL: [offender_id=' || l_offender_id || '][max_threads=' || get_max_threads || '][thread_id=' || l_thread_id || ']');
        --
        IF l_thread_id > 0 THEN
            l_program_action :=
               'DECLARE
                   l_thread_id    NUMBER := ' || l_thread_id || ';
                   l_component_id NUMBER := ' || GC_MASTER_COMPONENT_ID || ';
                   l_offender_id  NUMBER := ' || l_offender_ID || ';
                   l_row_id       ROWID;
                   --
                   PROCEDURE update_thread_status(p_status INTEGER, p_err_msg VARCHAR2 DEFAULT NULL) IS
                   BEGIN
                       UPDATE pdt_thread SET
                         status            = p_status,
                         finish_date       = DECODE(p_status, 1, finish_date, SYSDATE),
                         error_message     = p_err_msg,
                         info_message      = NULL,
                         last_heartbeat_dt = SYSDATE
                       WHERE component_id  = l_component_id
                         AND thread_id = l_thread_id;
--                    PKG_MThread.update_thread_status(
--                           p_component_id   => l_component_id,
--                           p_thread_id      => l_thread_id,
--                           p_status         => p_status,
--                           p_err_msg        => p_err_msg );
                   END update_thread_status;
                   --
                   PROCEDURE update_thread_progress(p_rows INTEGER, p_info_msg VARCHAR2 DEFAULT NULL) IS
                   BEGIN
                       PKG_MThread.update_thread_progress(
                           p_component_id   => l_component_id,
                           p_thread_id      => l_thread_id,
                           p_rows           => p_rows,
                           p_current_id_val => l_offender_id,
                           p_info_msg       => p_info_msg);
                    END update_thread_progress;
                    --
                BEGIN
                    --
                    update_thread_status(1);
                    update_thread_progress(0);
                    --
                    PKG_VPD_CTX.set_client_identifier(userID => ' || NVL(LC_USER_ID, 1) || ');
                    --
                    SELECT ROWID INTO l_row_id
                    FROM offender
                    WHERE offender_id = l_offender_id;
                    --
                    PKG_DPA_DELETION.p_del_rec(
                        p_tab_name      => ''OFFENDER'',
                        p_row_id        => l_row_id,
                        p_debug_message => NULL );
                    --
                    update_thread_progress(1);
                    update_thread_status(3);
                    --
                    --COMMIT;
                    --
                EXCEPTION WHEN OTHERS THEN
                    update_thread_status(2, ''Fatal error exception: '' || SQLERRM);
                END;';
            --
            --PKG_MThread.wait_for_threads_to_finish( p_component_id=>GC_MASTER_COMPONENT_ID, p_thread_id=>l_thread_id, p_wait=>0.5 );
            --
            PKG_MTHREAD.create_thread_process(
                p_component_id      => GC_MASTER_COMPONENT_ID,
                p_thread_id         => l_thread_id,
                p_program_action    => l_program_action,
                p_job_name          => g_component_name || '_' || l_offender_id,
                p_thread_label      => l_thread_label,
                p_instance_number   => 1 );
            --
            --COMMIT;
            --
        END IF;
        --
    END do_del;
    --
BEGIN
    --
    IF GC_PARALLEL_FLAG THEN
        PKG_MThread.reset_threads(GC_MASTER_COMPONENT_ID);
    END IF;
    --
    PKG_VPD_CTX.set_client_identifier(userID => NVL(LC_USER_ID, 1));
    --
    OPEN cs;
    LOOP
        FETCH cs INTO l_rec;
        EXIT WHEN cs%NOTFOUND;
        EXIT WHEN PKG_MTHREAD.check_semaphore( p_component_code=>TO_CHAR(GC_MASTER_COMPONENT_ID), p_signal=>'STOP' ) = 'Y';
        --
        IF GC_PARALLEL_FLAG THEN
            l_failed_threads := PKG_MThread.get_num_of_failed_threads(p_component_id => GC_MASTER_COMPONENT_ID);
            IF l_failed_threads > 0 THEN
                raise_application_error(-20001, 'ERROR: There are ' || l_failed_threads || ' threads that have failed - please check the PDT_THREAD and PDT_DEBUG tables for more details');
                EXIT;
            END IF;
            --
            do_del;
        ELSE
            PKG_DPA_DELETION.p_del_rec(
                p_tab_name      => 'OFFENDER',
                p_row_id        => l_rec.row_id,
                p_debug_message => '' );
            --ROLLBACK;
        END IF;
        --
        l_total_processed := l_total_processed +1;
        --INSERT INTO z_log_rebuild_0241( offender_id, log_date ) VALUES ( l_offender_id, SYSDATE );
        COMMIT;
        --
    END LOOP;
    CLOSE cs;
    COMMIT;
    --
    IF GC_PARALLEL_FLAG THEN
        PKG_MThread.wait_for_threads_to_finish( p_component_id=>GC_MASTER_COMPONENT_ID );
    END IF;
    --
    message('Total offenders processed=' || l_total_processed);
    --

END;
--delete from delius_app_schema.offender_delta where status = 'FAILED';
--alter table delius_app_schema.offender_delta shrink space;
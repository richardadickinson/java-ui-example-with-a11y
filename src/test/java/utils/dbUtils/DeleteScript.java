package utils.dbUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;

import static utils.dbUtils.ConnectionPool.*;

public class DeleteScript {

    private static final Logger logger = LoggerFactory.getLogger(DeleteScript.class);

    public static void runDeleteOffenderScript(String crn) throws Throwable {
        String filename = "delete_offender";
        if (!(crn == null)) {
            PreparedStatement statement = assignStringValueToSqlParam(filename + ".sql", 1, crn);
            logger.info("running " + filename + " script to delete " + crn + "...");
            executeSql(statement);
            checkIfCrnDeleted(crn);
        } else logger.info(filename + " script not run as no crn was created for this test");

    }

    private static void checkIfCrnDeleted(String crn) throws Throwable {
        PreparedStatement statement = assignStringValueToSqlParam("count_crn.sql", 1, crn);
        int dbValue = Integer.parseInt(executeSqlAndReturnValue(statement));
        if (dbValue == 0) {
            logger.info(crn + " successfully deleted");
        } else
            logger.info(crn + " not deleted");
    }
}

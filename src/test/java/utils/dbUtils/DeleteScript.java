package utils.dbUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;

import static utils.dbUtils.ConnectionPool.*;

public class DeleteScript {

    private static final Logger logger = LoggerFactory.getLogger(DeleteScript.class);

    public static void runDeleteOffenderScript(String crn) {
        String filename = "delete_offender";
        PreparedStatement statement = assignStringValueToSqlParam(filename + ".sql", 1, crn);
        logger.info("running " + filename + " script to delete " + crn + "...");
        executeSql(statement);
        checkIfCrnDeleted(crn);
    }

    private static void checkIfCrnDeleted(String crn)  {
        PreparedStatement statement = assignStringValueToSqlParam("count_crn.sql", 1, crn);
        int dbValue = Integer.parseInt(executeSqlAndReturnValue(statement));
        if (dbValue == 0) {
            logger.info(crn + " successfully deleted");
        } else {
            logger.error(crn + " not deleted");
        }
    }
}

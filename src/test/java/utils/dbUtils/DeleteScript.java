package utils.dbUtils;

import data.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.util.ArrayList;

import static utils.dbUtils.ConnectionPool.*;

public class DeleteScript {

    private static final Logger logger = LoggerFactory.getLogger(DeleteScript.class);

    public static void deleteOffendersByPerson(ArrayList<Person> persons) {
        for (int i = 0; i <= persons.size() - 1; i++) {
            deleteOffenderByCRN(persons.get(i).getCrn());
        }
    }

    public static void deleteOffenderByCRN(String crn) {
        String filename = "delete_offender";
        PreparedStatement statement = assignStringValueToSqlParam(filename + ".sql", 1, crn);
        logger.info("running " + filename + " script to delete " + crn + "...");
        executeSql(statement);
        if (crnExists(crn) == false) {
            logger.info(crn + " successfully deleted");
        } else {
            logger.error(crn + " not deleted");
        }
    }

    public static boolean crnExists(String crn) {
        PreparedStatement countStmt = assignStringValueToSqlParam("count_crn.sql", 1, crn);
        int dbValue = Integer.parseInt(executeSqlAndReturnValue(countStmt));
        if (dbValue == 0) {
            return false;
        } else {
            return true;
        }
    }
}

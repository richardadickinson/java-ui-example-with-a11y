package utils.dbUtils;

import data.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.util.ArrayList;

import static utils.dbUtils.ConnectionPool.*;

public class DeleteScript {

    private static final Logger logger = LoggerFactory.getLogger(DeleteScript.class);

    public static void runDeleteOffenderScript(ArrayList<Person> persons) {
        for (int i = 0; i <= persons.size()-1; i++) {
            String filename = "delete_offender";
            PreparedStatement statement = assignStringValueToSqlParam(filename + ".sql", 1, persons.get(i).getCrn());
            logger.info("running " + filename + " script to delete " + persons.get(i).getCrn() + "...");
            executeSql(statement);
            checkIfCrnDeleted(persons.get(i).getCrn());
        }
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

package utils.dbUtils;

import java.sql.PreparedStatement;

import static org.testng.Assert.assertEquals;
import static utils.dbUtils.ConnectionPool.assignStringValueToSqlParam;
import static utils.dbUtils.ConnectionPool.executeSqlAndReturnValue;

public class DatabaseAssertions {

    public static void assertOffenderDetail(String crn, String value) throws Throwable {
        PreparedStatement statement = assignStringValueToSqlParam("preferred_name_from_offender_table.sql", 1, crn);
        String dbValue = executeSqlAndReturnValue(statement);
        assertEquals(dbValue, value);
    }
}
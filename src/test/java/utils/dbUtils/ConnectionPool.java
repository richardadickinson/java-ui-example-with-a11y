package utils.dbUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

import static utils.PropertiesFileReader.getPropertyValueFromFile;
import static utils.dbUtils.SqlFileReader.readContentsOfSqlFile;

public class ConnectionPool {
    protected static Logger logger = LoggerFactory.getLogger(ConnectionPool.class);

    private static Connection establishDatabaseConnection() throws Throwable {
        String url = getPropertyValueFromFile("db_url");
        String dbUsername = getPropertyValueFromFile("db_username");
        String dbPassword = System.getenv("DB_PASSWORD");

        // Registering drivers
        DriverManager.registerDriver(
                new oracle.jdbc.OracleDriver());

        // Reference to connection interface
        return DriverManager.getConnection(url, dbUsername,
                dbPassword);
    }

    public static PreparedStatement assignStringValueToSqlParam(String fileName, int index, String value) throws Throwable {
        String sql = readContentsOfSqlFile(fileName);
        PreparedStatement preparedStatement = establishDatabaseConnection().prepareStatement(sql);
        preparedStatement.setString(index, value);
        return preparedStatement;
    }

    public static String executeSqlAndReturnValue(PreparedStatement statement) {
        try {
            ResultSet rs = statement.executeQuery();
            rs.next();
            int rowCount = rs.getRow();
            if (rowCount != 1) {
                throw new RuntimeException("Unexpected number of rows returned: " + rowCount);
            } else if (rs.getMetaData().getColumnCount() != 1) {
                throw new RuntimeException("Unexpected number of columns returned: " + rowCount);
            }
            int firstColumntIndex = 1;
            String value = rs.getString(firstColumntIndex);
            logger.info("Returned value: {}", value);
            return value;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}

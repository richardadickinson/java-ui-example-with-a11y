package utils.dbUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.TestConfigManager;

import java.sql.*;

import static utils.dbUtils.SqlFileReader.readContentsOfSqlFile;

public class ConnectionPool {
    protected static Logger logger = LoggerFactory.getLogger(ConnectionPool.class);

    /**
     * This method checks if a Database URL has been set in the Environment variable.
     * If this has not been set and is null,
     * then a database URL is assigned according to the delius environment established in the WebDriverConfig
     * These hardcoded database URLs are needed when running the tests locally on localhost.
     * When this framework is run remotely (AWS - Codebuild), the database URL is set as Environment Variable "DB_URL"
     * without any manual intervention
     */
    public static String dbUrl() {
        String dbUrl = System.getenv("DB_URL");
        String testEnv = TestConfigManager.get().getEnvironment();
        if (dbUrl == null) {
            switch (testEnv) {
                case "test":
                    dbUrl = "jdbc:oracle:thin:@//localhost:1801/TEST";
                    break;
                case "pre-prod":
                    dbUrl = "jdbc:oracle:thin:@//localhost:1801/PREPROD";
                    break;
                case "stage":
                    dbUrl = "jdbc:oracle:thin:@//localhost:1801/STAGE";
                    break;
                default:
                    throw new RuntimeException("Could not configure DataBase URLs for environment: " + testEnv);
            }
        }
        logger.info("Database URL is set to: " + dbUrl);
        return dbUrl;
    }

    private static Connection establishDatabaseConnection() throws SQLException {
        String dbUsername = "database_app_schema";
        String dbPassword = System.getenv("DB_PASSWORD");
        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
        return DriverManager.getConnection(dbUrl(), dbUsername, dbPassword);
    }

    public static PreparedStatement assignStringValueToSqlParam(String fileName, int index, String value) {
        try {
            String sql = readContentsOfSqlFile(fileName);
            PreparedStatement preparedStatement = establishDatabaseConnection().prepareStatement(sql);
            preparedStatement.setString(index, value);
            return preparedStatement;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
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
            int firstColumnIndex = 1;
            String value = rs.getString(firstColumnIndex);
            logger.debug("Returned value from SQL: {}", value);
            return value;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void executeSql(PreparedStatement statement) {
        try {
            statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

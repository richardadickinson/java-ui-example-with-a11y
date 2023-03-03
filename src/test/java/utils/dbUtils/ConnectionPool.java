package utils.dbUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

import static utils.dbUtils.SqlFileReader.readContentsOfSqlFile;
import static utils.webDriver.config.WebDriverConfig.getEnvironment;

public class ConnectionPool {
    protected static Logger logger = LoggerFactory.getLogger(ConnectionPool.class);
    public static String dbUrl() {
        String dbUrl = System.getenv("DB_URL");
        if (dbUrl == null) {
            System.out.println("getEnvironment(): "+getEnvironment());
            switch (getEnvironment()) {
                case "DEFAULT":
                case "delius-test":
                    dbUrl = "jdbc:oracle:thin:@//localhost:1801/TSTNDA";
                    break;
                case "delius-pre-prod":
                    dbUrl = "jdbc:oracle:thin:@//localhost:1801/PRENDA";
                    break;
                case "delius-stage":
                    dbUrl = "jdbc:oracle:thin:@//localhost:1801/STGNDA";
                    break;
                default:
                    throw new RuntimeException("Could not configure DataBase URLs for environment: " + getEnvironment());
            }

            logger.info("Database URL is set to: " + dbUrl);
            return dbUrl;

        } else
            logger.info("Database URL is set to: " + dbUrl);
        return dbUrl;
    }

    private static Connection establishDatabaseConnection() throws SQLException {
        String dbUsername = "delius_app_schema";
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

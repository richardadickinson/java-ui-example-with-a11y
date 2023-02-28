package utils.dbUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.TestConfigManager;

import java.sql.*;

import static utils.dbUtils.SqlFileReader.readContentsOfSqlFile;

public class ConnectionPool {
    protected static Logger logger = LoggerFactory.getLogger(ConnectionPool.class);

    private static Connection establishDatabaseConnection() throws SQLException {
        String url = TestConfigManager.get().getDatabaseUrl();
        String dbUsername = TestConfigManager.get().getDatabaseUsername();
        String dbPassword = System.getenv("DB_PASSWORD");

        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());

        return DriverManager.getConnection(url, dbUsername, dbPassword);
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

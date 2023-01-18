package utils.dbUtils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PreparedStatementFactory {

    private static final Logger logger = LoggerFactory.getLogger(PreparedStatementFactory.class);

    public static PreparedStatement getInstance(Connection connection, String sql) throws SQLException {
        logger.info("Query: {}", sql);
        return connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    }

    public static PreparedStatement getInstance(Connection connection, String sql, String param) throws SQLException {
        logger.info("Query: {}", sql);
        logger.info("Param value: {}", param);
        PreparedStatement stmt = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        stmt.setString(1, param);
        return stmt;
    }

    public static PreparedStatement getInstance(Connection connection, String sql, String[] params) throws SQLException {
        logger.info("Query: {}", sql);
        PreparedStatement stmt = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        for (int i = 0; i < params.length; i++) {
            int stmtParamId = i + 1;
            String param = params[i];
            logger.info("Param #{} value: {}", stmtParamId, param);
            stmt.setString(stmtParamId, param);
        }
        return stmt;
    }

    public static PreparedStatement getInstance(Connection connection, String sql, int[] params) throws SQLException {
        logger.info("Query: {}", sql);
        PreparedStatement stmt = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            int stmtParamId = i + 1;
            int param = params[i];
            logger.info("Param #{} value: {}", stmtParamId, param);
            stmt.setInt(stmtParamId, param);
        }
        return stmt;
    }
}

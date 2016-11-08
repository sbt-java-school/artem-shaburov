package ru.sbt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 */
public class JdbcTemplate {
    private String url;

    public JdbcTemplate(String url) {
        this.url = url;
    }

    public <T> T execute(JdbcAction<T> action) {
        try (Connection connection = openConnection()) {
            return action.execute(connection);
        } catch (Exception e) {
            throw new IllegalStateException("Execute error", e);
        }
    }

    private Connection openConnection() throws SQLException {
        return DriverManager.getConnection(url);
    }
}

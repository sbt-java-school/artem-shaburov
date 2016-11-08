package ru.sbt;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Collectors;

/**
 *
 */
public class DatabaseCreator {

    private static Logger logger = LoggerFactory.getLogger(DatabaseCreator.class);
    private static final String DATABASE_PATH =
            "jdbc:h2:~/";

    public static void main(String[] args) {
        new DatabaseCreator().createTable();
    }

    private void createTable() {
        executeScriptFromFile("persistent-cache/sql-scripts/create_cache_table.sql");
    }

    private void executeScriptFromFile(String filename) {
        try {
            String sql = FileUtils.readFileToString(new File(filename));
            executeSql(sql);
        } catch (IOException e) {
            throw new IllegalArgumentException("Can't read file: " + filename, e);
        } catch (SQLException e) {
            throw new IllegalArgumentException("Bad script: " + filename, e);
        }
    }

    private String getStringFromFile(String filename) throws IOException {
        Path path = Paths.get(filename);
        return Files.lines(path).collect(Collectors.joining());
    }

    private void executeSql(String sql) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DATABASE_PATH);
             Statement statement = connection.createStatement()) {
            boolean execute = statement.execute(sql);
            logger.info("State: {}", execute);
            logger.info("SQL:\n{}", sql);
        }
    }
}

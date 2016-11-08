package ru.sbt;

import java.sql.Connection;

/**
 *
 */
public interface JdbcAction<T> {
    T execute(Connection connection) throws Exception;
}

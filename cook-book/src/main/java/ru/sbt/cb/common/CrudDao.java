package ru.sbt.cb.common;

/**
 * Общий интерфейс для CRUD операций
 *
 * @param <T>  dao entity
 * @param <PK> primary key
 */
public interface CrudDao<T, PK> {
    T create(T t);

    T get(PK id);

    T update(T t);

    void delete(PK id);

    Class<T> getEntityClass();
}

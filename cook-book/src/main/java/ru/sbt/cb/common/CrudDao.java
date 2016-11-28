package ru.sbt.cb.common;

/**
 * Общий интерфейс для CRUD операций.
 *
 * @param <T>  entity type
 * @param <PK> primary key
 */
public interface CrudDao<T, PK> {

    /**
     * Создание сущности.
     *
     * @param t сущность
     * @return сущность с присвоенным id
     */
    T create(T t);

    /**
     * Получение сущности по primary key.
     *
     * @param id primary key
     * @return сущность из базы данных
     */
    T get(PK id);

    /**
     * Обновление сущности.
     *
     * @param t сущность
     * @return обновленную сущность
     */
    T update(T t);

    /**
     * Удаление сущности.
     *
     * @param id primary key.
     */
    void delete(PK id);

    /**
     * Класс сущности.
     *
     * @return класс сущности
     */
    Class<T> getEntityClass();
}

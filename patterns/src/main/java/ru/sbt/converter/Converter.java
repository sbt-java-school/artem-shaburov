package ru.sbt.converter;

/**
 * @author artem
 */
public interface Converter {

    <T> T convert(Object value, Class<T> resultClass);
    void add(Class<?> originClass, Class<?> resultClass, ValueConverter converter);

}

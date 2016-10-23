package ru.sbt.converter.implementations.integer;

import ru.sbt.converter.ValueConverter;

import javax.annotation.Nonnull;

/**
 * @author artem
 */
public class IntegerToStringConverter implements ValueConverter<Integer, String> {
    @Override
    public String convert(@Nonnull Integer value) {
        return String.valueOf(value);
    }
}

package ru.sbt.converter.implementations.integer;

import ru.sbt.converter.ValueConverter;

import javax.annotation.Nonnull;

/**
 * @author artem
 */
public class IntegerToLongConverter implements ValueConverter<Integer, Long> {
    @Override
    public Long convert(@Nonnull Integer value) {
        return value.longValue();
    }
}

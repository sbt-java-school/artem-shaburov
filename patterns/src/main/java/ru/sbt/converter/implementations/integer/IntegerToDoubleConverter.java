package ru.sbt.converter.implementations.integer;

import ru.sbt.converter.ValueConverter;

import javax.annotation.Nonnull;

/**
 * @author artem
 */
public class IntegerToDoubleConverter implements ValueConverter<Integer, Double> {
    @Override
    public Double convert(@Nonnull Integer value) {
        return value.doubleValue();
    }
}

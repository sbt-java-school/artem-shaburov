package ru.sbt.converter;

import javax.annotation.Nonnull;

/**
 * @author artem
 */
public interface ValueConverter<F, T> {

    T convert(@Nonnull F value);

}

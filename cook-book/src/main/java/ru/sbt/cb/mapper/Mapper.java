package ru.sbt.cb.mapper;

import java.util.List;

public interface Mapper {
    <S, D> D map(S sourceObject, Class<D> destinationClass);

    <S, D> List<D> mapAsList(Iterable<S> source, Class<D> destinationClass);
}

package ru.sbt.dao;

import ru.sbt.entities.Operation;
import ru.sbt.entities.CacheModel;

import java.util.Optional;

/**
 *
 */
public interface CacheDao {
    Optional<CacheModel> findByKey(Operation operation);
    boolean create(CacheModel cacheModel);
}

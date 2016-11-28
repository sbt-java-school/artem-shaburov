package ru.sbt.cb.dao;

import org.springframework.stereotype.Repository;
import ru.sbt.cb.common.CrudDao;
import ru.sbt.cb.entity.IngredientEntity;

import java.util.List;

@Repository
public interface IngredientDao extends CrudDao<IngredientEntity, Long> {
    List<IngredientEntity> list();
}

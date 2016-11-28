package ru.sbt.cb.dao;

import org.springframework.stereotype.Repository;
import ru.sbt.cb.common.CrudDao;
import ru.sbt.cb.entity.IngredientEntity;

import java.util.List;

/**
 * Dao ингредиентов. Расширяет возможности CrudDao специфичными методами.
 * Например, getBySomColumn(someColumnName).
 */
@Repository
public interface IngredientDao extends CrudDao<IngredientEntity, Long> {

    /**
     * Список всех ингредиентов.
     *
     * @return список всех ингредиентов
     */
    List<IngredientEntity> list();
}

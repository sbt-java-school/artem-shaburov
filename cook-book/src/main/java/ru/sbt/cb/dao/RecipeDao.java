package ru.sbt.cb.dao;

import org.springframework.stereotype.Repository;
import ru.sbt.cb.common.CrudDao;
import ru.sbt.cb.entity.RecipeEntity;

import java.util.List;

/**
 * Dao рецептов. Расширяет возможности CrudDao специфичными методами.
 * Например, getBySomColumn(someColumnName).
 */
@Repository
public interface RecipeDao extends CrudDao<RecipeEntity, Long> {

    /**
     * Список всех ингредиентов.
     *
     * @return список всех ингредиентов
     */
    List<RecipeEntity> list();
}

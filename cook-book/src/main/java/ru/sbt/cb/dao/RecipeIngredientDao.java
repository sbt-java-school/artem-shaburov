package ru.sbt.cb.dao;

import org.springframework.stereotype.Repository;
import ru.sbt.cb.common.CrudDao;
import ru.sbt.cb.entity.RecipeIngredientEntity;

import java.util.List;

/**
 * Dao связной таблицы. Расширяет возможности CrudDao специфичными методами.
 * Например, getBySomColumn(someColumnName).
 */
@Repository
public interface RecipeIngredientDao extends CrudDao<RecipeIngredientEntity, Long> {

    /**
     * Список всех ингредиентов.
     *
     * @return список всех ингредиентов
     */
    List<RecipeIngredientEntity> list();

    /**
     * Удаление по id рецепта
     *
     * @param recipeId id рецепта
     */
    void deleteByRecipeId(Long recipeId);
}

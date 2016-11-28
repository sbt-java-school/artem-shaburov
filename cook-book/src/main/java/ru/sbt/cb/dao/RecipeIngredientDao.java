package ru.sbt.cb.dao;

import org.springframework.stereotype.Repository;
import ru.sbt.cb.common.CrudDao;
import ru.sbt.cb.entity.RecipeIngredientEntity;

import java.util.List;

@Repository
public interface RecipeIngredientDao extends CrudDao<RecipeIngredientEntity, Long> {
    List<RecipeIngredientEntity> list();

    void deleteByRecipeId(Long recipeId);
}

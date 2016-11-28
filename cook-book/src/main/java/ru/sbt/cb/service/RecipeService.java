package ru.sbt.cb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sbt.cb.dao.IngredientDao;
import ru.sbt.cb.dao.RecipeDao;
import ru.sbt.cb.entity.IngredientEntity;
import ru.sbt.cb.entity.RecipeEntity;
import ru.sbt.cb.entity.RecipeIngredientEntity;
import ru.sbt.cb.mapper.Mapper;
import ru.sbt.cb.model.Ingredient;
import ru.sbt.cb.model.Recipe;
import ru.sbt.cb.model.RecipeIngredient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RecipeService {
    private RecipeDao recipeDao;
    private IngredientDao ingredientDao;
    private Mapper mapper;

    @Autowired
    public RecipeService(RecipeDao recipeDao, IngredientDao ingredientDao, Mapper mapper) {
        this.recipeDao = recipeDao;
        this.ingredientDao = ingredientDao;
        this.mapper = mapper;
    }

    public Long create(Recipe recipe) {
        RecipeEntity recipeEntity = mapper.map(recipe, RecipeEntity.class);
        if (recipeEntity.getRecipeIngredients() != null) {
            for (RecipeIngredientEntity recipeIngredientEntity : recipeEntity.getRecipeIngredients()) {
                recipeIngredientEntity.setRecipe(recipeEntity);
            }
        }
        recipeDao.create(recipeEntity);
        return recipeEntity.getId();
    }

    public Recipe getById(Long id) {
        RecipeEntity recipeEntity = recipeDao.get(id);
        if (recipeEntity != null) {
            return mapper.map(recipeEntity, Recipe.class);
        }
        return null;
    }

    public List<Recipe> list() {
        return mapper.mapAsList(recipeDao.list(), Recipe.class);
    }

    public Recipe update(Recipe recipe) {
        RecipeEntity recipeEntity = mapper.map(recipe, RecipeEntity.class);
        if (recipeEntity.getRecipeIngredients() != null) {
            for (RecipeIngredientEntity recipeIngredientEntity : recipeEntity.getRecipeIngredients()) {
                recipeIngredientEntity.setRecipe(recipeEntity);
            }
        }
        RecipeEntity updatedRecipe = recipeDao.update(recipeEntity);
        return mapper.map(updatedRecipe, Recipe.class);
    }

    public void delete(Long id) {
        recipeDao.delete(id);
    }

    public List<Ingredient> getIngredientsForRecipe(Long id) {
        Recipe recipe = getById(id);
        if (recipe != null) {
            List<RecipeIngredient> recipeIngredients = recipe.getRecipeIngredients();
            if (recipeIngredients != null) {
                return recipeIngredients.stream()
                        .map(recipeIngredient -> ingredientDao.get(recipeIngredient.getIngredientId()))
                        .map(ingredientEntity -> mapper.map(ingredientEntity, Ingredient.class))
                        .collect(Collectors.toList());
            }
        }
        return null;
    }
}

package ru.sbt.cb;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.sbt.cb.model.Recipe;
import ru.sbt.cb.model.RecipeIngredient;
import ru.sbt.cb.service.RecipeService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RecipeServiceTest extends BaseTest {
    @Autowired
    private RecipeService recipeService;

    @Test
    public void testRecipeCreate() {
        Recipe recipe = new Recipe();
        recipe.setName("Рецепт 1");
        recipe.setInstruction("Приготовь");

        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setIngredientId(1L);
        recipeIngredient.setQuantity(new BigDecimal(10));
        recipeIngredient.setUnits("кг");

        List<RecipeIngredient> recipeIngredients = new ArrayList<>();
        recipeIngredients.add(recipeIngredient);
        recipe.setRecipeIngredients(recipeIngredients);
        recipeService.create(recipe);
    }

    @Test
    public void testRecipeGetById() {
        Recipe recipe = recipeService.getById(1L);
        Assert.assertNotNull(recipe);
    }

    @Test
    public void testRecipeUpdate() {
        Recipe recipe = recipeService.getById(1L);
        Assert.assertNotNull(recipe);

        recipe.setName("New Рецепт 1");

        if (recipe.getRecipeIngredients() != null) {
            for (RecipeIngredient recipeIngredient : recipe.getRecipeIngredients()) {
                recipeIngredient.setQuantity(new BigDecimal(250));
            }
            RecipeIngredient recipeIngredient = new RecipeIngredient();
            recipeIngredient.setIngredientId(1L);
            recipeIngredient.setQuantity(new BigDecimal(10));
            recipeIngredient.setUnits("кг");
            recipe.getRecipeIngredients().add(recipeIngredient);
        }

        recipeService.update(recipe);

        Recipe recipe2 = recipeService.getById(1L);
        Assert.assertEquals(recipe.getRecipeIngredients().size(), recipe2.getRecipeIngredients().size());
    }

    @Test
    public void testRecipeDelete() {
        recipeService.delete(1L);
        Recipe recipe = recipeService.getById(1L);
        Assert.assertNull(recipe);
    }


    @Test
    public void testUpdateRecipe() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipe.setName("Ингредиент 2");
        recipeService.update(recipe);
    }
}

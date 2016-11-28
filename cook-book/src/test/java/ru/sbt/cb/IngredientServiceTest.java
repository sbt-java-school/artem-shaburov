package ru.sbt.cb;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.sbt.cb.model.Ingredient;
import ru.sbt.cb.service.IngredientService;

import java.util.List;

public class IngredientServiceTest extends BaseTest {
    @Autowired
    private IngredientService ingredientService;

    @Test
    public void testCreateIngredient() {
        Ingredient ingredient = new Ingredient();
        ingredient.setName("Ингредиент Тест 1");
        ingredientService.create(ingredient);
    }

//    @Test
//    public void testUpdateIngredient() {
//        Ingredient ingredient = new Ingredient();
//        ingredient.setId(1L);
//        ingredient.setName("Ингредиент Тест 2");
//        ingredientService.ingredientUpdate(ingredient);
//    }

    @Test
    public void testIngredientBrowseByParam() {
        List<Ingredient> ingredientEntities = ingredientService.list();
        Assert.assertNotNull(ingredientEntities);
    }
}

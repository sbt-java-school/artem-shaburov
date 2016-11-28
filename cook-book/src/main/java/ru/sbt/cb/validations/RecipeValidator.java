package ru.sbt.cb.validations;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.sbt.cb.model.Recipe;
import ru.sbt.cb.model.RecipeIngredient;

import java.math.BigDecimal;
import java.util.List;

public class RecipeValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Recipe.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", "name.empty");
        Recipe recipe = (Recipe) target;
        List<RecipeIngredient> recipeIngredients = recipe.getRecipeIngredients();
        if (recipeIngredients != null) {
            for (RecipeIngredient recipeIngredient : recipeIngredients) {
                if (recipeIngredient.getQuantity().compareTo(new BigDecimal(0)) < 0) {
                    errors.rejectValue("quantity", "negative");
                }
            }
        }
    }
}

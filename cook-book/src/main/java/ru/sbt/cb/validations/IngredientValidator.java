package ru.sbt.cb.validations;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.sbt.cb.model.Ingredient;

public class IngredientValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Ingredient.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", "name.empty");
        Ingredient ingredient = (Ingredient) target;
    }
}

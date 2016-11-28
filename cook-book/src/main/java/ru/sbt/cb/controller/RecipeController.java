package ru.sbt.cb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.sbt.cb.model.Ingredient;
import ru.sbt.cb.model.Recipe;
import ru.sbt.cb.model.RecipeIngredient;
import ru.sbt.cb.service.RecipeService;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Mvc контроллер рецептов.
 */
@Controller
public class RecipeController {

    private RecipeService recipeService;

    private static final Logger LOGGER = LoggerFactory.getLogger(RecipeController.class);

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    /**
     * Индекс страница рецептов. Показывает список всех рецептов.
     *
     * @return ModelAndView для jsp страницы recipes/list
     */
    @RequestMapping(value = {"/recipes"}, method = GET)
    public ModelAndView list() {
        List<Recipe> recipes = recipeService.list();
        ModelAndView modelAndView = new ModelAndView("recipes/list");
        modelAndView.addObject("recipes", recipes);
        return modelAndView;
    }

    /**
     * Форма для создания рецепта.
     *
     * @return jsp страницу recipes/form
     */
    @RequestMapping(value = {"/recipes/new"}, method = GET)
    public String form() {
        return "recipes/form";
    }

    /**
     * Создание рецепта.
     *
     * @param recipe рецепт для создания
     * @return редиректит на индекс страницу
     */
    @RequestMapping(value = {"/recipes"}, method = POST)
    public String create(@ModelAttribute Recipe recipe) {
        Long id = recipeService.create(recipe);
        LOGGER.info("Recipe created, id = {}", id);
        return "redirect:/cook-book/recipes";
    }

    /**
     * Страница конкретного рецепта.
     *
     * @param id id рецепта
     * @return ModelAndView для jsp страницы recipes/recipe
     */
    @RequestMapping(value = {"/recipes/{id}"}, method = GET)
    public ModelAndView get(@PathVariable("id") Long id) {
        Recipe recipe = recipeService.getById(id);
        // здесь нужно получать лист ингредиентов для этого рецепта, чтобы отображать их
        List<Ingredient> ingredients = recipeService.getIngredientsForRecipe(id);
        ModelAndView modelAndView = new ModelAndView("recipes/recipe");
        modelAndView.addObject("recipe", recipe);
        modelAndView.addObject("ingredients", ingredients);
        return modelAndView;
    }

    /**
     * Редактирование рецепта.
     *
     * @param id id рецепта
     * @return ModelAndView для jsp страницы recipes/edit
     */
    @RequestMapping(value = {"/recipes/{id}/edit"}, method = GET)
    public ModelAndView edit(@PathVariable("id") Long id) {
        Recipe recipe = recipeService.getById(id);
        // здесь нужно получать лист ингредиентов для этого рецепта,
        // чтобы обновлять связанную таблицу, зная ингредиент, из которого можно достать id,
        // зная рецепт, из которого можно достать id + кол-во и юниты
        List<Ingredient> ingredients = recipeService.getIngredientsForRecipe(id);
        ModelAndView modelAndView = new ModelAndView("recipes/edit");
        modelAndView.addObject("recipe", recipe);
        modelAndView.addObject("ingredients", ingredients);
        return modelAndView;
    }

    /**
     * Обновление рецепта.
     *
     * @param id     id рецепта
     * @param recipe рецепт для обновления
     * @return редиректит на индекс страницу
     */
    @RequestMapping(value = {"/recipes/{id}/edit"}, method = {POST, PATCH, PUT})
    public String update(@PathVariable("id") Long id, @ModelAttribute Recipe recipe) {
        recipe.setId(id);
        recipeService.update(recipe);
        return "redirect:/cook-book/recipes";
    }

    /**
     * Удаление рецепта.
     *
     * @param id id рецепта
     * @return редиректит на индекс страницу
     */
    @RequestMapping(value = {"/recipes/{id}/delete"}, method = {GET, DELETE})
    public String delete(@PathVariable("id") Long id) {
        recipeService.delete(id);
        return "redirect:/cook-book/recipes";
    }
}

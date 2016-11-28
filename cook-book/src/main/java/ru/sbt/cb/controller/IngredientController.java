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
import ru.sbt.cb.service.IngredientService;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Mvc контроллер ингредиентов.
 */
@Controller
public class IngredientController {

    private IngredientService ingredientService;

    private static final Logger LOGGER = LoggerFactory.getLogger(IngredientController.class);

    @Autowired
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    /**
     * Индекс страница ингредиентов. Показывает список всех ингредиентов.
     *
     * @return ModelAndView для jsp страницы ingredients/list
     */
    @RequestMapping(value = {"/ingredients"}, method = GET)
    public ModelAndView list() {
        List<Ingredient> ingredients = ingredientService.list();
        ModelAndView modelAndView = new ModelAndView("ingredients/list");
        modelAndView.addObject("ingredients", ingredients);
        return modelAndView;
    }

    /**
     * Форма для создания ингредиента.
     *
     * @return jsp страницу ingredients/form
     */
    @RequestMapping(value = {"/ingredients/new"}, method = GET)
    public String form() {
        return "ingredients/form";
    }

    /**
     * Создание ингредиента.
     *
     * @param ingredient ингредиент для создания
     * @return редиректит на индекс страницу
     */
    @RequestMapping(value = {"/ingredients"}, method = POST)
    public String create(@ModelAttribute Ingredient ingredient) {
        Long id = ingredientService.create(ingredient);
        LOGGER.info("Ingredient created, id = {}", id);
        return "redirect:/cook-book/ingredients";
    }

    /**
     * Страница конкретного ингредиента.
     *
     * @param id id ингредиента
     * @return ModelAndView для jsp страницы ingredients/ingredient
     */
    @RequestMapping(value = {"/ingredients/{id}"}, method = GET)
    public ModelAndView get(@PathVariable("id") Long id) {
        Ingredient ingredient = ingredientService.getById(id);
        ModelAndView modelAndView = new ModelAndView("ingredients/ingredient");
        modelAndView.addObject("ingredient", ingredient);
        return modelAndView;
    }

    /**
     * Редактирование ингредиента.
     *
     * @param id id ингредиента
     * @return ModelAndView для jsp страницы ingredients/edit
     */
    @RequestMapping(value = {"/ingredients/{id}/edit"}, method = GET)
    public ModelAndView edit(@PathVariable("id") Long id) {
        Ingredient ingredient = ingredientService.getById(id);
        ModelAndView modelAndView = new ModelAndView("ingredients/edit");
        modelAndView.addObject("ingredient", ingredient);
        return modelAndView;
    }

    /**
     * Обновление ингредиента.
     *
     * @param id         id ингредиента
     * @param ingredient ингредиент для обновления
     * @return редиректит на индекс страницу
     */
    @RequestMapping(value = {"/ingredients/{id}/edit"}, method = {POST, PATCH, PUT})
    public String update(@PathVariable("id") Long id, @ModelAttribute Ingredient ingredient) {
        ingredient.setId(id);
        Ingredient updatedIngredient = ingredientService.update(ingredient);
        LOGGER.info("Ingredient updated {}", updatedIngredient);
        return "redirect:/cook-book/ingredients";
    }

    /**
     * Удаление ингредиента.
     *
     * @param id id ингредиента
     * @return редиректит на индекс страницу
     */
    @RequestMapping(value = {"/ingredients/{id}/delete"}, method = {GET, DELETE})
    public String delete(@PathVariable("id") Long id) {
        ingredientService.delete(id);
        LOGGER.info("Ingredient with id = {} removed", id);
        return "redirect:/cook-book/ingredients";
    }
}

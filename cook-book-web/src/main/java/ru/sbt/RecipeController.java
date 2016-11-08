package ru.sbt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.sbt.dao.RecipeDao;
import ru.sbt.entities.Recipe;

import java.util.List;

/**
 *
 */
@Controller("recipes")
public class RecipeController {
    private final Logger logger = LoggerFactory.getLogger(RecipeController.class);

    private final RecipeDao recipeDao;

    public RecipeController(RecipeDao recipeDao) {
        this.recipeDao = recipeDao;
    }

    /**
     * показывает все рецепты
     *
     * @return null
     */
    @RequestMapping(value = "/recipes", method = RequestMethod.GET)
    public ModelAndView getRecipes() {
        List<Recipe> recipes = recipeDao.list();
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("recipes", recipes);
        return modelAndView;
    }

    /**
     * возвращает форму для создания рецепта
     *
     * @return null
     */
    @RequestMapping(value = "/recipes/new", method = RequestMethod.GET)
    public String getForm() {
        return "new_recipe";
    }

    /**
     * создает новый рецепт
     *
     * @return null
     */
    @RequestMapping(value = "/recipes", method = RequestMethod.POST, consumes = "application/json")
    public ModelAndView createRecipe(@RequestBody Recipe recipe) {
        boolean success = recipeDao.create(recipe);
        ModelAndView modelAndView = new ModelAndView("new_recipe");
        return modelAndView;
    }

    /**
     * показывает один рецепт
     *
     * @param id рецепта
     * @return null
     */
    @RequestMapping(value = "/recipes/{id}", method = RequestMethod.GET)
    public ModelAndView getRecipe(@PathVariable("id") long id) {
        Recipe recipe = recipeDao.findById(id);
        if (recipe == null) {

        }
        return null;
    }

    /**
     * показывает форму для изменения рецепта
     *
     * @param id рецепта
     * @return null
     */
    @RequestMapping(value = "/recipes/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editRecipe(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("edit_recipe");
        Recipe recipe = recipeDao.findById(id);
        if (recipe != null) {
            modelAndView.addObject(recipe);
        }
        // отправляем форму с рецептом
        return modelAndView;
    }

    /**
     * меняет рецепт
     *
     * @param id рецепта
     * @return null
     */
    @RequestMapping(value = "/recipes/{id}", method = {RequestMethod.PATCH, RequestMethod.PUT})
    public ModelAndView updateRecipe(@PathVariable("id") long id) {
        return null;
    }

    /**
     * удаляет рецепт
     *
     * @param id рецепта
     * @return null
     */
    @RequestMapping(value = "/recipes/{id}", method = RequestMethod.DELETE)
    public ModelAndView deleteRecipe(@PathVariable("id") long id) {
        recipeDao.delete(id);
        return null;
    }
}

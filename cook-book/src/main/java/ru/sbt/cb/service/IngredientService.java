package ru.sbt.cb.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sbt.cb.mapper.Mapper;
import ru.sbt.cb.dao.IngredientDao;
import ru.sbt.cb.entity.IngredientEntity;
import ru.sbt.cb.model.Ingredient;

import java.util.List;

@Service
@Transactional
public class IngredientService {

    private IngredientDao ingredientDao;
    private Mapper mapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(IngredientService.class);

    @Autowired
    public IngredientService(IngredientDao ingredientDao, Mapper mapper) {
        this.ingredientDao = ingredientDao;
        this.mapper = mapper;
    }

    public Long create(Ingredient ingredient) {
        validate(ingredient);
        IngredientEntity ingredientEntity = mapper.map(ingredient, IngredientEntity.class);
        ingredientDao.create(ingredientEntity);
        Long id = ingredientEntity.getId();
        LOGGER.info("Ingredient created, id = {}.", id);
        return id;
    }

    public Ingredient getById(Long ingredientId) {
        IngredientEntity ingredientEntity = ingredientDao.get(ingredientId);
        if (ingredientEntity != null) {
            LOGGER.info("Ingredient found: {}", ingredientEntity);
            return mapper.map(ingredientEntity, Ingredient.class);
        }
        LOGGER.info("No ingredient found.");
        return null;
    }

    public List<Ingredient> list() {
        return mapper.mapAsList(ingredientDao.list(), Ingredient.class);
    }

    public Ingredient update(Ingredient ingredient) {
        IngredientEntity ingredientEntity = mapper.map(ingredient, IngredientEntity.class);
        IngredientEntity updatedEntity = ingredientDao.update(ingredientEntity);
        LOGGER.info("Ingredient updated: {}", updatedEntity);
        return mapper.map(updatedEntity, Ingredient.class);
    }

    public void delete(Long id) {
        ingredientDao.delete(id);
    }

    private void validate(Ingredient ingredient) {
    }
}

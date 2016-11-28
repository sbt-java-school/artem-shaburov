package ru.sbt.cb.dao;

import org.springframework.stereotype.Repository;
import ru.sbt.cb.common.CrudJpaDao;
import ru.sbt.cb.entity.RecipeIngredientEntity;

import java.util.List;

/**
 * Реализация RecipeIngredientDao. Наследует все CRUD операции от {@link CrudJpaDao}.
 * Реализует другие методы RecipeIngredientDao.
 */
@Repository
public class RecipeIngredientJpaDao extends CrudJpaDao<RecipeIngredientEntity, Long>
        implements RecipeIngredientDao {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RecipeIngredientEntity> list() {
        return entityManager.createNamedQuery("RecipeIngredientEntity.list", RecipeIngredientEntity.class).getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteByRecipeId(Long recipeId) {
        if (recipeId == null) {
            throw new NullPointerException("Id can't be null");
        }
        entityManager.createNamedQuery("RecipeIngredientEntity.deleteByRecipeId").
                setParameter("recipeId", recipeId).
                executeUpdate();
    }
}

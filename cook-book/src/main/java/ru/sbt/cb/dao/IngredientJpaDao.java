package ru.sbt.cb.dao;

import org.springframework.stereotype.Repository;
import ru.sbt.cb.common.CrudJpaDao;
import ru.sbt.cb.entity.IngredientEntity;

import java.util.List;

/**
 * Реализация IngredientDao. Наследует все CRUD операции от {@link CrudJpaDao}.
 * Реализует другие методы IngredientDao.
 */
@Repository
public class IngredientJpaDao extends CrudJpaDao<IngredientEntity, Long> implements IngredientDao {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IngredientEntity> list() {
        return entityManager.createNamedQuery("IngredientEntity.list", IngredientEntity.class).getResultList();
    }
}

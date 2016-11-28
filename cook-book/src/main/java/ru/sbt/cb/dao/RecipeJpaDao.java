package ru.sbt.cb.dao;

import org.springframework.stereotype.Repository;
import ru.sbt.cb.common.CrudJpaDao;
import ru.sbt.cb.entity.RecipeEntity;

import java.util.List;

/**
 * Реализация RecipeDao. Наследует все CRUD операции от {@link CrudJpaDao}.
 * Реализует другие методы RecipeDao.
 */
@Repository
public class RecipeJpaDao extends CrudJpaDao<RecipeEntity, Long> implements RecipeDao {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RecipeEntity> list() {
        return entityManager.createNamedQuery("RecipeEntity.list", RecipeEntity.class).getResultList();
    }
}

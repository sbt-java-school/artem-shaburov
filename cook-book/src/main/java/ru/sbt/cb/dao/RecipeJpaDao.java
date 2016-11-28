package ru.sbt.cb.dao;

import org.springframework.stereotype.Repository;
import ru.sbt.cb.common.CrudJpaDao;
import ru.sbt.cb.entity.RecipeEntity;

import java.util.List;

@Repository
public class RecipeJpaDao extends CrudJpaDao<RecipeEntity, Long> implements RecipeDao {

    @Override
    public List<RecipeEntity> list() {
        return entityManager.createNamedQuery("RecipeEntity.list", RecipeEntity.class).getResultList();
    }
}

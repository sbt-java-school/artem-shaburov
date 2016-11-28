package ru.sbt.cb.dao;

import org.springframework.stereotype.Repository;
import ru.sbt.cb.common.CrudDao;
import ru.sbt.cb.entity.RecipeEntity;

import java.util.List;

@Repository
public interface RecipeDao extends CrudDao<RecipeEntity, Long> {
    List<RecipeEntity> list();
}

package ru.sbt.dao;

import ru.sbt.entities.Recipe;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public interface RecipeDao {
    @Nullable
    Recipe findById(Long id);

    List<Recipe> list();

    boolean create(@Nonnull Recipe recipe);

    boolean update(@Nonnull Recipe recipe);

    boolean delete(@Nonnull Long id);
}

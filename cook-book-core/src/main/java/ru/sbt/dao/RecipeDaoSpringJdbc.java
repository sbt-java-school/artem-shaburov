package ru.sbt.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.sbt.entities.Recipe;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class RecipeDaoSpringJdbc implements RecipeDao {
    private JdbcTemplate jdbcTemplate;

    public RecipeDaoSpringJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Nullable
    @Override
    public Recipe findById(Long id) {
        return null;
    }

    @Override
    public List<Recipe> list() {
        return null;
    }

    @Override
    public boolean create(@Nonnull Recipe recipe) {
        return false;
    }

    @Override
    public boolean update(@Nonnull Recipe recipe) {
        return false;
    }

    @Override
    public boolean delete(@Nonnull Long id) {
        return false;
    }
}

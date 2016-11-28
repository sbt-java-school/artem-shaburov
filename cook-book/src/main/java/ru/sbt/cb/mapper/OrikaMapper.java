package ru.sbt.cb.mapper;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;
import ru.sbt.cb.entity.IngredientEntity;
import ru.sbt.cb.entity.RecipeEntity;
import ru.sbt.cb.entity.RecipeIngredientEntity;
import ru.sbt.cb.model.Ingredient;
import ru.sbt.cb.model.Recipe;
import ru.sbt.cb.model.RecipeIngredient;

import java.util.List;

@Component
public class OrikaMapper implements Mapper {
    private MapperFacade mapperFacade;

    public OrikaMapper() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFacade = mapperFactory.getMapperFacade();
        register(mapperFactory);
    }

    private void register(MapperFactory mapperFactory) {
        mapperFactory.classMap(Ingredient.class, IngredientEntity.class)
                .byDefault()
                .register();

        mapperFactory.classMap(Recipe.class, RecipeEntity.class)
                .byDefault()
                .register();

        mapperFactory.classMap(RecipeIngredient.class, RecipeIngredientEntity.class)
                .field("recipeId", "recipe.id")
                .field("ingredientId", "ingredient.id")
                .byDefault()
                .register();
    }

    @Override
    public <S, D> D map(S sourceObject, Class<D> destinationClass) {
        return mapperFacade.map(sourceObject, destinationClass);
    }

    @Override
    public <S, D> List<D> mapAsList(Iterable<S> source, Class<D> destinationClass) {
        return mapperFacade.mapAsList(source, destinationClass);
    }
}

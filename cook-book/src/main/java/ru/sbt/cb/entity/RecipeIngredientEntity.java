package ru.sbt.cb.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "RECIPE_INGREDIENTS")
@NamedQueries({
        @NamedQuery(
                name = "RecipeIngredientEntity.list",
                query = "select recipeIngredientEntity from RecipeIngredientEntity recipeIngredientEntity"),
        @NamedQuery(
                name = "RecipeIngredientEntity.deleteByRecipeId",
                query = "delete from RecipeIngredientEntity recipeIngredientEntity " +
                        "where recipeIngredientEntity.recipe.id = :recipeId")
})
public class RecipeIngredientEntity {
    private Long id;
    private RecipeEntity recipe;
    private IngredientEntity ingredient;
    private BigDecimal quantity;
    private String units;

    public RecipeIngredientEntity() {
    }

    @Id
    @GeneratedValue
    @Column(name = "RECIPE_INGREDIENT_ID", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RECIPE_ID", nullable = false)
    public RecipeEntity getRecipe() {
        return recipe;
    }

    public void setRecipe(RecipeEntity recipe) {
        this.recipe = recipe;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INGREDIENT_ID", nullable = false)
    public IngredientEntity getIngredient() {
        return ingredient;
    }

    public void setIngredient(IngredientEntity ingredient) {
        this.ingredient = ingredient;
    }

    @Column(name = "QUANTITY", nullable = false)
    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    @Column(name = "UNITS", nullable = false)
    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }
}

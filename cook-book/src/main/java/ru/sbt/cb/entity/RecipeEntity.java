package ru.sbt.cb.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "RECIPES")
@NamedQueries({
        @NamedQuery(
                name = "RecipeEntity.list",
                query = "select recipeEntity from RecipeEntity recipeEntity")
})
public class RecipeEntity {
    private Long id;
    private String name;
    private String instruction;
    private List<RecipeIngredientEntity> recipeIngredients;

    public RecipeEntity() {
    }

    @Id
    @GeneratedValue
    @Column(name = "RECIPE_ID", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NAME", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "INSTRUCTION", nullable = false)
    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    @OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    public List<RecipeIngredientEntity> getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(List<RecipeIngredientEntity> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }
}

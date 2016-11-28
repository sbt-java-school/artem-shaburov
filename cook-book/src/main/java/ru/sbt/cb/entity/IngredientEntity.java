package ru.sbt.cb.entity;

import javax.persistence.*;

@Entity
@Table(name = "INGREDIENTS")
@NamedQueries({
        @NamedQuery(
                name = "IngredientEntity.list",
                query = "select ingredientEntity from IngredientEntity ingredientEntity")
})
public class IngredientEntity {
    private Long id;
    private String name;

    public IngredientEntity() {
    }

    @Id
    @GeneratedValue
    @Column(name = "INGREDIENT_ID", nullable = false)
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
}

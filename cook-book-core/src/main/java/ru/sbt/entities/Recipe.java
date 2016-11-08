package ru.sbt.entities;

import java.util.List;

public class Recipe {
    private long id;
    private String name;
    private List<Ingredient> ingredients;
    private String instruction;
    private String descroption;

    // Дефолтный конструктор для json jackson
    public Recipe() {
    }

    public Recipe(String name, List<Ingredient> ingredients, String instruction) {
        this.name = name;
        this.ingredients = ingredients;
        this.instruction = instruction;
    }

    public Recipe(String name, String instruction) {
        this.name = name;
        this.instruction = instruction;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String descroption) {
        this.descroption = descroption;
    }
}

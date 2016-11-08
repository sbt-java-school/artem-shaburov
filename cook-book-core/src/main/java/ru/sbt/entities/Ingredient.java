package ru.sbt.entities;

public class Ingredient {
    private long id;
    private String name;

    public Ingredient() {

    }

    public Ingredient(String name) {
        this.name = name;
    }

    public Ingredient(long id, String name) {
        this.id = id;
        this.name = name;
    }
}

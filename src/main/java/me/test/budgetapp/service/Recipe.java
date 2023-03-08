package me.test.budgetapp.service;

import java.util.List;

public class Recipe {
    private final String name;
    private final Integer cookingTime;
    private final List<Ingredient> ingredients;
    private final List<String> steps;

    public Recipe(String name, Integer cookingTime, List<Ingredient> ingredients, List<String> steps) {
        if (name.isEmpty() || name.isBlank()) {
            this.name = "Название рецепта не указано";
        } else {
            this.name = name;
        }
        if (cookingTime == null || cookingTime <= 0) {
            this.cookingTime = 0;
        } else {
            this.cookingTime = cookingTime;
        }
        this.ingredients = ingredients;
        this.steps = steps;

    }

    public String getName() {
        return name;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<String> getSteps() {
        return steps;
    }
}

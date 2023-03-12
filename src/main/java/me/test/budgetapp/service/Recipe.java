package me.test.budgetapp.service;

import lombok.Data;

import java.util.List;
@Data
public class Recipe {
    private final String name;
    private final Integer cookingTime;
    private final List<Ingredient> ingredients;
    private final List<String> steps;

}

package me.test.budgetapp.service;

import lombok.Data;

@Data
public class Ingredient {
    private final String ingredientName;
    private final Integer ingredientQuantity;
    private final String measureUnit;

}

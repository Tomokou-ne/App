package me.test.budgetapp.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {
    private String name;
    private Integer cookingTime;
    private List<Ingredient> ingredients;
    private List<String> steps;

}

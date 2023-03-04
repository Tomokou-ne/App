package me.test.budgetapp.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class IngredientService {
    private final Map<Integer, Ingredient> ingredients =new HashMap();

    public Ingredient
    addIngredient (Ingredient ingredient) {
        if (ingredients.containsKey(ingredient.getId())) {
            throw new RuntimeException("Тфкой элемент уже существует");
        } else {
            ingredients.put(ingredient.getId(), ingredient);
            }return ingredient;
        }
    public Ingredient
    getRecipeId(Integer id) {
        if (ingredients.containsKey(id)) {
            return ingredients.get(id);
        } else {
            throw new RuntimeException("Элемент не найден");
        }
    }
    public Collection<Ingredient> getAllIngredients() {
        return ingredients.values();
    }
}




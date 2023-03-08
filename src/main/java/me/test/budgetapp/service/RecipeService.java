package me.test.budgetapp.service;

import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
@Service
public class RecipeService {
    private final Map<Long, Recipe> recipes = new HashMap<>();
    public long counter = 0;

    public Recipe addRecipe(Recipe recipe) {
        recipes.put(this.counter++, recipe);
        return recipe;
    }
    public Recipe getRecipeId(long id) {
        if (recipes.containsKey(id)) {
            return recipes.get(id);
        } else {
            throw new RuntimeException("Элемент не найден");
        }
    }

    public Collection<Recipe> getAllRecipes() {
        return recipes.values();
    }

    public Recipe removeRecipe(long id) throws FileNotFoundException {
        if (this.recipes.containsKey(id)) {
            return this.recipes.remove(id);
        } else {
            throw new FileNotFoundException();
        }
    }

    public Recipe updateRecipe (long id, Recipe recipe) {
        if (recipes.containsKey(id)) {
            return recipes.put(id, recipe);
        }
        return null;
    }
}

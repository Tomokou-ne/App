package me.test.budgetapp.service;

import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
@Service
public class IngredientService {
    public final Map<Long, Ingredient> ingredients =new HashMap<>();
    public long counter = 0;

    public Ingredient addIngredient (Ingredient ingredient) {
        ingredients.put(this.counter++, ingredient);
        return ingredient;
    }
    public Ingredient getIngredientId(Integer id) {
        if (ingredients.containsKey(id)) {
            return ingredients.get(id);
        } else {
            throw new RuntimeException("Элемент не найден");
        }
    }

    public Ingredient removeIngredient(long id) throws FileNotFoundException {
        if (this.ingredients.containsKey(id)) {
            return this.ingredients.remove(id);
        } else {
            throw new FileNotFoundException();
        }
    }
    public Collection<Ingredient> getAllIngredients() {
        return ingredients.values();
    }

    public Ingredient updateIngredient (long id, Ingredient ingredient) {
        if (ingredients.containsKey(id)) {
            return ingredients.put(id, ingredient);
        }
        return null;
    }
}




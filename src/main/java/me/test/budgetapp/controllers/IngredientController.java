package me.test.budgetapp.controllers;

import me.test.budgetapp.service.Ingredient;
import me.test.budgetapp.service.IngredientService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("{id}")
    public Ingredient getIngredient(@PathVariable Integer id) {
        return this.ingredientService.getIngredientId(id);
    }

    @PostMapping()
    public Ingredient addIngredient(@RequestBody Ingredient ingredient) {
        return this.ingredientService.addIngredient(ingredient);
    }

    @GetMapping()
    public Collection<Ingredient> getAllIngredients() {
        return this.ingredientService.getAllIngredients();
    }
}

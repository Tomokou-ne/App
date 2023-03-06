package me.test.budgetapp.controllers;

import me.test.budgetapp.service.Recipe;
import me.test.budgetapp.service.RecipeService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("{id}")
    public Recipe getRecipe(@PathVariable Integer id) {
        return this.recipeService.getRecipeId(id);
    }

    @PostMapping()
    public Recipe addRecipe(@RequestBody Recipe recipe) {
        return this.recipeService.addRecipe(recipe);
    }

    @GetMapping()
    public Collection<Recipe> getAllRecipes() {
        return this.recipeService.getAllRecipes();
    }
}

package me.test.budgetapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.test.budgetapp.service.Recipe;
import me.test.budgetapp.service.RecipeService;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.Collection;

@RestController
@RequestMapping("/recipe")
@Tag(name = "Рецепты", description = "Полное описание рецептов, состоящее из: названия, времени приготовления блюда, списка ингредиентов и пошаговой иструкции процесса приготовления")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("{id}")
    @Operation(summary = "Поиск рецепта по ID", description = " ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Рецепт найден")
    }
    )
    public Recipe getRecipe(@PathVariable Integer id) {
        return this.recipeService.getRecipeId(id);
    }

    @PostMapping()
    @Operation(summary = "Добавление рецепта", description = "Добавление рецепта в базу приложения")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Рецепт добавлен")
    }
    )
    public Recipe addRecipe(@RequestBody Recipe recipe) {
        return this.recipeService.addRecipe(recipe);
    }

    @GetMapping()
    @Operation(summary = "Показать все рецепты", description = " ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Рецепты найдены")
    }
    )
    public Collection<Recipe> getAllRecipes() {
        return this.recipeService.getAllRecipes();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменение рецепта", description = "Изменение рецепта по ID с возможностью редактирования всех данных о нем")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Рецепт изменен")
    }
    )
    public Recipe updateRecipe(@PathVariable("id") long id, @RequestBody Recipe recipe) {
        return recipeService.updateRecipe(id, recipe);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление рецепта", description = "Удаление рецепта по ID из базы приложения")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Рецепт удален")
    }
    )
    public Recipe removeRecipe(@PathVariable("id") long id) throws FileNotFoundException {
        return recipeService.removeRecipe(id);
    }
}

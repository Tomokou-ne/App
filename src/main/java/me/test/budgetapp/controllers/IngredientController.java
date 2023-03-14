package me.test.budgetapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.test.budgetapp.service.Ingredient;
import me.test.budgetapp.service.IngredientService;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.Collection;

@RestController
@RequestMapping("/ingredient")
@Tag(name = "Ингредиенты", description = "Ингредиенты, их количество и единица меры")
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("{id}")
    @Operation(summary = "Поиск ингредиента по ID", description = " ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ингредиент найден"),
            @ApiResponse(responseCode = "400", description = "Неверный запрос"),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере")
        }
    )
    public Ingredient getIngredient(@PathVariable long id) {
        return this.ingredientService.getIngredientId(id);
    }


    @PostMapping()
    @Operation(summary = "Добавление ингредиента", description = "Добавление ингредиента в базу приложения")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ингредиент добавлен")
    }
    )
    public Ingredient addIngredient(@RequestBody Ingredient ingredient) {
        return this.ingredientService.addIngredient(ingredient);
    }

    @GetMapping()
    @Operation(summary = "Показать все игредиенты", description = " ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ингредиенты найдены")
    }
    )
    public Collection<Ingredient> getAllIngredients() {
        return this.ingredientService.getAllIngredients();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменение ингредиента", description = "Изменение ингредиента по ID с возможностью редактирования всех данных о нем")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ингредиент изменен")
    }
    )
    public Ingredient updateIngredient(@PathVariable("id") long id, @RequestBody Ingredient ingredient) {
        return ingredientService.updateIngredient(id, ingredient);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление ингредиента", description = "Удаление ингредиента по ID из базы приложения")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ингредиент удален")
    }
    )
    public Ingredient removeIngredient(@PathVariable("id") long id) throws FileNotFoundException {
        return ingredientService.removeIngredient(id);
    }
}

package me.test.budgetapp.controllers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Первый контроллер", description = "Краткое ИНФО о приложении")
public class FirstController {

    @GetMapping
    public String helloWorld() {
        return "Приложение запущено";
    }

    @Operation(summary = "ИНФО о приложении", description = "Краткая сводка информации об исполнителе проекта, названии, дате создания и краткого описания")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запрос выполнен")
    }
    )
    @GetMapping("/info")
    public String info() {
        return "Проект выполняет Бычкова Маргарита. " +
                "Название: RecipeApp. " +
                "Дата создания: 25.02.2023. " +
                "Проект-коллекция рецептов";
    }

}
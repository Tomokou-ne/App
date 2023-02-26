package me.test.budgetapp.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {

    @GetMapping
    public String helloWorld() {
        return "Приложение запущено";
    }



    @GetMapping("/info")
    public String info() {
        return "Проект выполняет Бычкова Маргарита. " +
                "Название: BudgetApp. " +
                "Дата создания: 25.02.2023. " +
                "Проект по формированию бюджета.";
    }

}
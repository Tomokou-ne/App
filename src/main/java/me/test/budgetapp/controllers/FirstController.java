package me.test.budgetapp.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {

    @GetMapping
    public String helloWorld() {
        return "Hello, World!";
    }

    @GetMapping("/path/to/page")
    public String page() {
        return "Page 1";
    }

}
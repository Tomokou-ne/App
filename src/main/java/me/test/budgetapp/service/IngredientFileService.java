package me.test.budgetapp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class IngredientFileService {
    @Value(value = "${path.to.ingredient.file}")
    private String ingredientFilePath;

    @Value(value = "${name.to.ingredient.file}")
    private String ingredientFileName;

    public void saveToJsonFile(String json) {
        Path path = Path.of(ingredientFilePath, ingredientFileName);
        try {
            Files.createDirectories(path.getParent());
            removeJsonFile();
            Files.writeString(path, json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readJsonFile() {
        try {
            Path path = Path.of(ingredientFilePath, ingredientFileName);
            return Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void removeJsonFile() {
        try {
            Path path = Path.of(ingredientFilePath, ingredientFileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

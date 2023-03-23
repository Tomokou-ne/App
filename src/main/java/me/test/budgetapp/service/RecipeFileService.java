package me.test.budgetapp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class RecipeFileService {
    @Value(value = "${path.to.recipe.file}")
    private String recipeFilePath;

    @Value(value = "${name.to.recipe.file}")
    private String recipeFileName;

    public void saveToJsonFile(String json) {
        Path path = Path.of(recipeFilePath, recipeFileName);
        try {
            removeJsonFile();
            Files.writeString(path, json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readJsonFile() {
        try {
            return Files.readString(Path.of(recipeFilePath, recipeFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void removeJsonFile() {
        try {
            Path path = Path.of(recipeFilePath, recipeFileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getRecipeFile() {
        return new File(recipeFilePath + "/" + recipeFileName);
    }

    public Path createTempFile(String suffix) {
        try {
            return Files.createTempFile(Path.of(recipeFilePath), "tempFile", suffix);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

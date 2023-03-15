package me.test.budgetapp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class RecipeFileService {
    @Value(value = "${path.to.recipe.file}")
    private String dataFilePath;

    @Value(value = "${name.to.recipe.file}")
    private String dataFileName;

    public void saveToJsonFile(String json) {
        Path path = Path.of(dataFilePath, dataFileName);
        try {
            removeJsonFile();
            Files.writeString(path, json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readJsonFile() {
        try {
            return Files.readString(Path.of(dataFilePath, dataFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void removeJsonFile() {
        try {
            Path path = Path.of(dataFilePath, dataFileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

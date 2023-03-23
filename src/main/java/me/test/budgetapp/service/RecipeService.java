package me.test.budgetapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
@Service
public class RecipeService {
    private final RecipeFileService fileService;
    private Map<Long, Recipe> recipes = new LinkedHashMap<>();
    private long counter = 0;

    public RecipeService(RecipeFileService fileService) {
        this.fileService = fileService;
    }

    @PostConstruct
    public void init() {
        readJsonFile();
    }
    public Recipe addRecipe(Recipe recipe) {
        recipes.put(this.counter++, recipe);
        saveToJsonFile();
        return recipe;
    }
    public Recipe getRecipeId(long id) {
        if (recipes.containsKey(id)) {
            return recipes.get(id);
        } else {
            throw new RuntimeException("Элемент не найден");
        }
    }

    public Collection<Recipe> getAllRecipes() {
        return recipes.values();
    }

    public Recipe removeRecipe(long id) throws FileNotFoundException {
        if (this.recipes.containsKey(id)) {
            this.recipes.remove(id);
            saveToJsonFile();
        } else {
            throw new FileNotFoundException();
        }
        return null;
    }

    public Recipe updateRecipe (long id, Recipe recipe) {
        if (recipes.containsKey(id)) {
            saveToJsonFile();
            return recipes.put(id, recipe);
        }
        return null;
    }

    private void saveToJsonFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipes);
            fileService.saveToJsonFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readJsonFile() {
        String json = fileService.readJsonFile();
        try {
            recipes = new ObjectMapper().readValue(json, new TypeReference<LinkedHashMap<Long, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void addRecipesFromInputStream(InputStream inputStream) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] array = StringUtils.split(line, '|');
                Recipe recipe = new Recipe(array[0], Integer.valueOf(array[1]), array[2]);
                addRecipe(recipe);
            }
        }
    }

    public File createRecipesTxtFile() throws FileNotFoundException {
        Path path = fileService.createTempFile("Рецепты");
        try (Writer writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)){
            for (Recipe recipe : recipes.values()) {
                writer.append(recipe.toString());
                writer.append("\n");
            }
        } catch (IOException e) {
            throw new FileNotFoundException();
        }
        return path.toFile();
    }
}

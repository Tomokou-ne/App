package me.test.budgetapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
@Service
public class RecipeService {
    private final RecipeFileService fileService;
    private Map<Long, Recipe> recipes = new LinkedHashMap<>();
    public long counter = 0;

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
            return this.recipes.remove(id);
        } else {
            throw new FileNotFoundException();
        }
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
}

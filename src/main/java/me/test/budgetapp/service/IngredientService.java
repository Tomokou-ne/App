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
public class IngredientService {

    private final IngredientFileService fileService;
    private Map<Long, Ingredient> ingredients =new LinkedHashMap<>();
    private long counter = 0;

    @PostConstruct
    public void init() {
        readJsonFile();
    }

    public IngredientService(IngredientFileService fileService) {
        this.fileService = fileService;
    }

    public Ingredient addIngredient (Ingredient ingredient) {
        ingredients.put(this.counter++, ingredient);
        saveToJsonFile();
        return ingredient;
    }
    public Ingredient getIngredientId(long id) {
        if (ingredients.containsKey(id)) {
            return ingredients.get(id);
        } else {
            throw new RuntimeException("Элемент не найден");
        }
    }

    public Ingredient removeIngredient(long id) throws FileNotFoundException {
        if (this.ingredients.containsKey(id)) {
            this.ingredients.remove(id);
            saveToJsonFile();
        } else {
            throw new FileNotFoundException();
        }
        return null;
    }
    public Collection<Ingredient> getAllIngredients() {
        return ingredients.values();
    }

    public Ingredient updateIngredient (long id, Ingredient ingredient) {
        if (ingredients.containsKey(id)) {
            saveToJsonFile();
            return ingredients.put(id, ingredient);
        }
        return null;
    }

    private void saveToJsonFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredients);
            fileService.saveToJsonFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readJsonFile() {
        String json = fileService.readJsonFile();
        try {
            ingredients = new ObjectMapper().readValue(json, new TypeReference<LinkedHashMap<Long, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}




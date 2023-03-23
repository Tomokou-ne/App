package me.test.budgetapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.test.budgetapp.service.IngredientFileService;
import me.test.budgetapp.service.RecipeFileService;
import me.test.budgetapp.service.RecipeService;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@Tag(name = "Файлы", description = "Операции по работе с файлами рецептов и ингредиентов")
@RequestMapping("/files")
public class FilesController {
    private final RecipeFileService recipeFileService;
    private final IngredientFileService ingredientFileService;
    private final RecipeService recipeService;

    public FilesController(RecipeFileService recipeFileService, IngredientFileService ingredientFileService, RecipeService recipeService) {
        this.recipeFileService = recipeFileService;
        this.ingredientFileService = ingredientFileService;
        this.recipeService = recipeService;
    }

    @GetMapping("/export")
    @Operation(summary = "Скачать рецепты в виде json-файла")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "файл успешно скачен"),
            @ApiResponse(responseCode = "400", description = "отправлен некорректный запрос"),
            @ApiResponse(responseCode = "500", description = "сервер столкнулся с неожиданной ошибкой")})
    public ResponseEntity<InputStreamResource> downloadRecipesFile() throws FileNotFoundException {
        File file = recipeFileService.getRecipeFile();
        if (file.exists()) {
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .contentLength(file.length())
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"RecipesCollection.json\" ")
                .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/upload/recipes")
    @Operation(summary = "Загрузить файл с рецептами", description = "принимает json-файл с рецептами и заменяет сохраненный на жестком (локальном) диске файл на новый")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "файл успешно загружен"),
            @ApiResponse(responseCode = "400", description = "отправлен некорректный запрос"),
            @ApiResponse(responseCode = "500", description = "сервер столкнулся с неожиданной ошибкой")})
    public ResponseEntity<Void> uploadRecipesFile(@RequestParam MultipartFile file) {
        File fileRecipe = recipeFileService.getRecipeFile();
        try (FileOutputStream fos = new FileOutputStream(fileRecipe)){
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping("/upload/ingredients")
    @Operation(summary = "Загрузить файл с ингредиентами", description = "принимает json-файл с ингредиентами и заменяет сохраненный на жестком (локальном) диске файл на новый")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "файл успешно загружен"),
            @ApiResponse(responseCode = "400", description = "отправлен некорректный запрос"),
            @ApiResponse(responseCode = "500", description = "сервер столкнулся с неожиданной ошибкой")})
    public ResponseEntity<Void> uploadIngredientFile(@RequestParam MultipartFile file) {
        File fileIngredient = ingredientFileService.getIngredientFile();
        try (FileOutputStream fos = new FileOutputStream(fileIngredient)){
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Добавление рецептов из файла",
            description = "можно добавить рецепты в том числе из файла txt")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "запрос выполнен"),
            @ApiResponse(responseCode = "400", description = "ошибка в параметрах запроса"),
            @ApiResponse(responseCode = "404", description = "URL неверный или такого действия нет в веб-приложении"),
            @ApiResponse(responseCode = "500", description = "во время выполнения запроса произошла ошибка на сервере")})
    public ResponseEntity <Object> addRecipesFromFile(@RequestParam MultipartFile file) throws IOException {
        try (InputStream stream = file.getInputStream()) {
            recipeService.addRecipesFromInputStream(stream);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    @GetMapping("/export/recipes/txt")
    @Operation(summary = "Скачивание рецептов в формате txt",
            description = "можно скачать рецепты файлом txt")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "все хорошо, запрос выполнился"),
            @ApiResponse(responseCode = "400", description = "есть ошибка в параметрах запроса"),
            @ApiResponse(responseCode = "404", description = "URL неверный или такого действия нет в веб-приложении"),
            @ApiResponse(responseCode = "500", description = "во время выполнения запроса произошла ошибка на сервере")})
    public ResponseEntity<InputStreamResource> downloadRecipeTxtFile() throws FileNotFoundException {
        File downloadedFile = recipeService.createRecipesTxtFile();
        try {
            InputStreamResource stream = new InputStreamResource(new FileInputStream(downloadedFile));
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .contentLength(downloadedFile.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Recipes.txt\"")
                    .body(stream);
        } catch (IOException e) {
            return ResponseEntity.noContent().build();
        }
    }

}


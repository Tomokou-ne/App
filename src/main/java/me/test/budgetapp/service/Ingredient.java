package me.test.budgetapp.service;

public class Ingredient {
    private final String ingredientName;

    private final Integer ingredientQuantity;

    private final String measureUnit;

    public Ingredient( String ingredientName, Integer ingredientQuantity, String measureUnit) {
        if (ingredientName.isBlank() || ingredientName.isEmpty()) {
            this.ingredientName = "Название ингредиента не указано";
        } else {
            this.ingredientName = ingredientName;
        }
        if (ingredientQuantity == null || ingredientQuantity <=0) {
            this.ingredientQuantity = 1;
        } else {
            this.ingredientQuantity = ingredientQuantity;
        }
        if (measureUnit.isEmpty() || measureUnit.isBlank()) {
            this.measureUnit = "Единица измерения не указана";
        } else {
            this.measureUnit = measureUnit;
        }
    }


    public String getIngredientName() {
        return ingredientName;
    }

    public Integer getIngredientQuantity() {
        return ingredientQuantity;
    }

    public String getMeasureUnit() {
        return measureUnit;
    }
}

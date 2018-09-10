package com.eres.waiter.waiter.model;

public class ProdIngredient {
    private int id;
    private int productId;
    public Ingrident ingredient;
    private int ingredientId;
    private int count;
    private int unit;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Ingrident getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingrident ingredient) {
        this.ingredient = ingredient;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public ProdIngredient(int id, int productId, Ingrident ingredient, int ingredientId, int count, int unit) {
        this.id = id;
        this.productId = productId;
        this.ingredient = ingredient;
        this.ingredientId = ingredientId;
        this.count = count;
        this.unit = unit;
    }
}

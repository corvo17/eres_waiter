package com.eres.waiter.waiter.model;


import java.util.ArrayList;

public class ProductsItem extends BaseMyITem {

    private int categoryId;

    private int isDish;

    private String description;

    private int price;
    private boolean сonfirmed;

    public boolean isСonfirmed() {
        return сonfirmed;
    }

    public void setСonfirmed(boolean сonfirmed) {
        this.сonfirmed = сonfirmed;
    }

    private String imageUrl;

    private int id;

    private int departmentId;

    private int code;

    private String name;

    private int excluded;
    private ArrayList<Desire> specialDesires;
    private ArrayList<ProdIngredient> prodIngredients;

    public ArrayList<Desire> getSpecialDesires() {
        return specialDesires;
    }

    public ArrayList<ProdIngredient> getProdIngredients() {
        return prodIngredients;
    }

    public void setProdIngredients(ArrayList<ProdIngredient> prodIngredients) {
        this.prodIngredients = prodIngredients;
    }

    public ProductsItem(int categoryId, int isDish, String description, int price, String imageUrl, int id, int departmentId, int code, String name, int excluded, ArrayList<Desire> specialDesires, ArrayList<ProdIngredient> prodIngredients) {
        this.categoryId = categoryId;
        this.isDish = isDish;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.id = id;
        this.departmentId = departmentId;
        this.code = code;
        this.name = name;
        this.excluded = excluded;
        this.specialDesires = specialDesires;
        this.prodIngredients = prodIngredients;
    }

    public void setSpecialDesires(ArrayList<Desire> specialDesires) {
        this.specialDesires = specialDesires;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setIsDish(int isDish) {
        this.isDish = isDish;
    }

    public int getIsDish() {
        return isDish;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setExcluded(int excluded) {
        this.excluded = excluded;
    }

    public int getExcluded() {
        return excluded;
    }

}
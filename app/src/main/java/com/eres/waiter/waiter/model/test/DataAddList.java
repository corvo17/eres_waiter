package com.eres.waiter.waiter.model.test;

import com.eres.waiter.waiter.model.ProductsItem;

public class DataAddList {

    private int id;
    private ProductsItem productsItem;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductsItem getProductsItem() {
        return productsItem;
    }

    public void setProductsItem(ProductsItem productsItem) {
        this.productsItem = productsItem;
    }


    public DataAddList(int id, ProductsItem productsItem) {
        this.id = id;
        this.productsItem = productsItem;
    }
}

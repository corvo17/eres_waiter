package com.eres.waiter.waiter.model;

public class Desire {

    int id;
    String name;
    int productId;

    public boolean isChack() {
        return chack;
    }

    public void setChack(boolean chack) {
        this.chack = chack;
    }

    boolean chack=false;

    public Desire(int id, String name, int productId) {
        this.id = id;
        this.name = name;
        this.productId = productId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}

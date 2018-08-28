package com.eres.waiter.waiter.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class OrderItemsItem {

    @SerializedName("id")
    private int id;

    @SerializedName("productId")
    private int productId;
    @SerializedName("orderId")
    private int orderId;

    @SerializedName("factCount")
    private int factCount;

    @SerializedName("departmentId")
    private int departmentId;

    @SerializedName("stateId")
    private int stateId;

    @SerializedName("count")
    private int count;

    @SerializedName("orderItemSpecialDesires")
    private List<OrderItemSpecialDesire> orderItemSpecialDesires;

    @SerializedName("orderPartId")
    private int orderPartId;

    @SerializedName("descriptor")
    private String descriptor;
    @SerializedName("descriptor2")
    private String descriptor2;
    private ProductsItem product;

    public ProductsItem getProduct() {
        return product;
    }

    public void setProduct(ProductsItem product) {
        this.product = product;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductId() {
        return productId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setFactCount(int factCount) {
        this.factCount = factCount;
    }

    public int getFactCount() {
        return factCount;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public int getStateId() {
        return stateId;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public OrderItemsItem(int id, int productId, int orderId, int factCount, int departmentId, int stateId, int count, List<OrderItemSpecialDesire> orderItemSpecialDesires, int orderPartId, String descriptor, String descriptor2) {
        this.id = id;
        this.productId = productId;
        this.orderId = orderId;
        this.factCount = factCount;
        this.departmentId = departmentId;
        this.stateId = stateId;
        this.count = count;
        this.orderItemSpecialDesires = orderItemSpecialDesires;
        this.orderPartId = orderPartId;
        this.descriptor = descriptor;
        this.descriptor2 = descriptor2;
    }

    public List<OrderItemSpecialDesire> getOrderItemSpecialDesires() {
        return orderItemSpecialDesires;
    }

    public String getDescriptor2() {
        return descriptor2;
    }

    public void setOrderItemSpecialDesires(List<OrderItemSpecialDesire> orderItemSpecialDesires) {
        this.orderItemSpecialDesires = orderItemSpecialDesires;
    }

    public void setOrderPartId(int orderPartId) {
        this.orderPartId = orderPartId;
    }

    public int getOrderPartId() {
        return orderPartId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }
}
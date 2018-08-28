package com.eres.waiter.waiter.model;

public class OrderItemSpecialDesire {
    public long id;
    public long orderItemId;
    public long dSpecialDesireId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public long getdSpecialDesireId() {
        return dSpecialDesireId;
    }

    public void setdSpecialDesireId(long dSpecialDesireId) {
        this.dSpecialDesireId = dSpecialDesireId;
    }

    public OrderItemSpecialDesire(long id, long orderItemId, long dSpecialDesireId) {
        this.id = id;
        this.orderItemId = orderItemId;
        this.dSpecialDesireId = dSpecialDesireId;
    }
}

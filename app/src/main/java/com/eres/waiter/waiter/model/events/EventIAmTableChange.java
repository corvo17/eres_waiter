package com.eres.waiter.waiter.model.events;

public class EventIAmTableChange {
    private boolean type;

    public boolean isType() {
        return type;
    }

    public EventIAmTableChange(boolean type) {
        this.type = type;
    }
}

package com.eres.waiter.waiter.model.events;

public class EventMessageRemoveTable {
   private String message;

    public EventMessageRemoveTable(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

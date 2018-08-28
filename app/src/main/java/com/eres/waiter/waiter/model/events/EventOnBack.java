package com.eres.waiter.waiter.model.events;

public class EventOnBack {
private boolean onBack;

    public EventOnBack(boolean onBack) {
        this.onBack = onBack;
    }

    public boolean isOnBack() {

        return onBack;
    }

    public void setOnBack(boolean onBack) {
        this.onBack = onBack;
    }
}

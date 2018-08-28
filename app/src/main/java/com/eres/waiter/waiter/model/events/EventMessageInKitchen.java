package com.eres.waiter.waiter.model.events;

public class EventMessageInKitchen {
    private boolean send;

    public boolean isSend() {
        return send;
    }

    public void setSend(boolean send) {
        this.send = send;
    }

    public EventMessageInKitchen(boolean send) {
        this.send = send;
    }
}

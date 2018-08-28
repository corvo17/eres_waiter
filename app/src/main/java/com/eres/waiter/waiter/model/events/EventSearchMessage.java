package com.eres.waiter.waiter.model.events;

public class EventSearchMessage {
    private CharSequence s;

    public EventSearchMessage(CharSequence s) {
        this.s = s;
    }

    public CharSequence getS() {
        return s;
    }

    public void setS(CharSequence s) {
        this.s = s;
    }
}

package com.eres.waiter.waiter.model.test;

public class NotificationEventAlarm {

    public void setId(int id) {
        this.id = id;
    }

    public void setNotifType(int notifType) {
        this.notifType = notifType;
    }

    private int id;
    private int notifType;

    public int getId() {
        return id;
    }

    public int getNotifType() {
        return notifType;
    }

    public NotificationEventAlarm(int id, int notifType) {
        this.id = id;
        this.notifType = notifType;
    }
}

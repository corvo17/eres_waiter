package com.eres.waiter.waiter.model;

import com.eres.waiter.waiter.server.NotificationData;

public class IAmTables extends BaseIAmTable {
    public IAmTables(int hallId, Object description, int id, boolean isExtOrder, int defaultWaiterId, Object currentWaiterId, String name, int tableState) {
        this.hallId = hallId;
        this.description = description;
        this.id = id;
        this.isExtOrder = isExtOrder;
        this.defaultWaiterId = defaultWaiterId;
        this.currentWaiterId = currentWaiterId;
        this.name = name;
        this.tableState = tableState;
    }

    private int hallId;

    private Object description;

    private int id;

    private boolean isExtOrder;

    private int defaultWaiterId;

    private Object currentWaiterId;

    private String name;

    private int tableState;

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    public int getHallId() {
        return hallId;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public Object getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean isExtOrder() {
        return isExtOrder;
    }

    public void setExtOrder(boolean extOrder) {
        isExtOrder = extOrder;
    }

    public void setDefaultWaiterId(int defaultWaiterId) {
        this.defaultWaiterId = defaultWaiterId;
    }

    public int getDefaultWaiterId() {
        return defaultWaiterId;
    }

    public void setCurrentWaiterId(Object currentWaiterId) {
        this.currentWaiterId = currentWaiterId;
    }

    public Object getCurrentWaiterId() {
        return currentWaiterId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTableState(int tableState) {
        this.tableState = tableState;
    }

    public int getTableState() {
        return tableState;
    }

    @Override
    public String toString() {
        return
                "IAmTables{" +
                        "hallId = '" + hallId + '\'' +
                        ",description = '" + description + '\'' +
                        ",id = '" + id + '\'' +
                        ",isExtOrder = '" + isExtOrder + '\'' +
                        ",defaultWaiterId = '" + defaultWaiterId + '\'' +
                        ",currentWaiterId = '" + currentWaiterId + '\'' +
                        ",name = '" + name + '\'' +
                        ",tableState = '" + tableState + '\'' +
                        "}";
    }
}

class BaseIAmTable {
    private NotificationData notificationData;

    public NotificationData getNotificationData() {
        return notificationData;
    }

    public void setNotificationData(NotificationData notificationData) {
        this.notificationData = notificationData;
    }

    private int type = -1;

    public int isType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
package com.eres.waiter.waiter.model.events;

public class EventTable {
    private int tableId;

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public EventTable(int tableId) {
        this.tableId = tableId;
    }
}

package com.eres.waiter.waiter.model;


public class ArmoredTables {

    public ArmoredTables(int tableId, Table table, int clientId, String armoredStartTime, int id, boolean isClosed, Client client) {
        this.tableId = tableId;
        this.table = table;
        this.clientId = clientId;
        this.armoredStartTime = armoredStartTime;
        this.id = id;
        this.isClosed = isClosed;
        this.client = client;
    }

    private int tableId;

    private Table table;

    private int clientId;

    private String armoredStartTime;

    private int id;

    private boolean isClosed;

    private Client client;

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Table getTable() {
        return table;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setArmoredStartTime(String armoredStartTime) {
        this.armoredStartTime = armoredStartTime;
    }

    public String getArmoredStartTime() {
        return armoredStartTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    @Override
    public String toString() {
        return
                "ArmoredTables{" +
                        "tableId = '" + tableId + '\'' +
                        ",table = '" + table + '\'' +
                        ",clientId = '" + clientId + '\'' +
                        ",armoredStartTime = '" + armoredStartTime + '\'' +
                        ",id = '" + id + '\'' +
                        ",isClosed = '" + isClosed + '\'' +
                        ",client = '" + client + '\'' +
                        "}";
    }
}
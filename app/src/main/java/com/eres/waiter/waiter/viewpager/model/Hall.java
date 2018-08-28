package com.eres.waiter.waiter.viewpager.model;


import com.eres.waiter.waiter.model.Table;
import com.eres.waiter.waiter.viewpager.helper.ObservableCollection;

enum HallState { Working, Closed , Armored }
public class Hall
{
    public Hall()
    {
        tables = new ObservableCollection<Table>();
    }
    private long id;
    private String name;
    private String description;
    private long managerId;
    private ObservableCollection<Table> tables;
    private HallState hallState;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getManagerId() {
        return managerId;
    }

    public void setManagerId(long managerId) {
        this.managerId = managerId;
    }

    public ObservableCollection<com.eres.waiter.waiter.model.Table> getTables() {
        return tables;
    }

    public void setTables(ObservableCollection<Table> tables) {
        this.tables = tables;
    }

    public HallState getHallState() {
        return hallState;
    }

    public void setHallState(HallState hallState) {
        this.hallState = hallState;
    }
}
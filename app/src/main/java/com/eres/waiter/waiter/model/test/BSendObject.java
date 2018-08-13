package com.eres.waiter.waiter.model.test;

import com.eres.waiter.waiter.model.EmptyTable;
import com.eres.waiter.waiter.model.TablesItem;

import java.io.Serializable;
import java.util.ArrayList;

public class BSendObject implements Serializable {

    public transient ArrayList<TablesItem> tables;

    public BSendObject(ArrayList<TablesItem> tables) {
        this.tables = tables;
    }

    public ArrayList<TablesItem> getTables() {
        return tables;
    }
}

package com.eres.waiter.waiter.model.test;

import com.eres.waiter.waiter.model.EmptyTable;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TestClass {

    @SerializedName("")
    private EmptyTable[] tables;

    public EmptyTable[] getTables() {
        return tables;
    }

    public TestClass(EmptyTable[] tables) {
        this.tables = tables;
    }
}

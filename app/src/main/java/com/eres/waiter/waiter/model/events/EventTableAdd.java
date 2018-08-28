package com.eres.waiter.waiter.model.events;

import com.eres.waiter.waiter.model.TablesItem;

public class EventTableAdd {
    private TablesItem item;

    public TablesItem getItem() {
        return item;
    }

    public EventTableAdd(TablesItem item) {
        this.item = item;
    }
}

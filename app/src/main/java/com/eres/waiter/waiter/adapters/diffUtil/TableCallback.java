package com.eres.waiter.waiter.adapters.diffUtil;

import android.support.v7.util.DiffUtil;

import com.eres.waiter.waiter.model.OrderItemsItem;
import com.eres.waiter.waiter.model.TablesItem;

import java.util.ArrayList;
import java.util.List;

public class TableCallback extends DiffUtil.Callback {
    List<TablesItem> oldList;
    List<TablesItem> newList;

    public TableCallback(List<TablesItem> oldList, List<TablesItem> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.size() == newList.size();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.size() == newList.size();
    }
}

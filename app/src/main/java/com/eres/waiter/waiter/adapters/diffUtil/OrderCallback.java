package com.eres.waiter.waiter.adapters.diffUtil;

import android.support.v7.util.DiffUtil;

import com.eres.waiter.waiter.model.OrderItemsItem;

import java.util.ArrayList;

public class OrderCallback extends DiffUtil.Callback {
    ArrayList<OrderItemsItem> oldList;
    ArrayList<OrderItemsItem> newList;

    public OrderCallback(ArrayList<OrderItemsItem> oldList, ArrayList<OrderItemsItem> newList) {
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

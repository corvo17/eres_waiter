package com.eres.waiter.waiter.adapters.diffUtil;

import android.support.v7.util.DiffUtil;

import com.eres.waiter.waiter.model.IAmTables;

import java.util.List;

public class IAmTableCallback extends DiffUtil.Callback {
    List<IAmTables> oldList;
    List<IAmTables> newList;

    public IAmTableCallback(List<IAmTables> oldList, List<IAmTables> newList) {
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

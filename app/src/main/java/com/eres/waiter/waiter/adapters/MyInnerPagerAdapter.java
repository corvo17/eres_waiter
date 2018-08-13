package com.eres.waiter.waiter.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.eres.waiter.waiter.fragment.viewpager_fragment.FragmentTables;
import com.eres.waiter.waiter.model.EmptyTable;
import com.eres.waiter.waiter.model.TablesItem;

import java.util.ArrayList;

public class MyInnerPagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<EmptyTable> tables;
    private Context mC;
    private String TAG = "MY_LOG";


    public MyInnerPagerAdapter(FragmentManager fm, ArrayList<EmptyTable> list, Context c) {
        super(fm);
        this.mC = c;
        tables = new ArrayList<>();
        tables.clear();
        tables.addAll(list);

    }

    @Override
    public Fragment getItem(int position) {
        return FragmentTables.getInstance((ArrayList<TablesItem>) tables.get(position).getTables());
    }


    @Override
    public int getCount() {
        return tables.size();
    }

}

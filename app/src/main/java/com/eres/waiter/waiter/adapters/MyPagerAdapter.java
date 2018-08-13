package com.eres.waiter.waiter.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.eres.waiter.waiter.fragment.FragmentMenuItems;
import com.eres.waiter.waiter.fragment.viewpager_fragment.FragmentEmpty;
import com.eres.waiter.waiter.fragment.viewpager_fragment.FragmentITable;
import com.eres.waiter.waiter.fragment.viewpager_fragment.FragmentReservation;
import com.eres.waiter.waiter.model.CategoriesItem;

import java.util.ArrayList;

public class MyPagerAdapter extends FragmentStatePagerAdapter {
    private int key, l;

    public MyPagerAdapter(FragmentManager fm, int key, int leigh) {

        super(fm);
        this.key = key;
        l = leigh;
    }

    @Override
    public Fragment getItem(int position) {

        return FragmentMenuItems.getInstance(key, position);
    }

    @Override
    public int getCount() {
        return l;
    }
}

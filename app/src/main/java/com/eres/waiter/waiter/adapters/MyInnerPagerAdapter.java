package com.eres.waiter.waiter.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.util.Log;

import com.eres.waiter.waiter.fragment.viewpager_fragment.FragmentTables;
import com.eres.waiter.waiter.model.EmptyTable;
import com.eres.waiter.waiter.model.TablesItem;
import com.eres.waiter.waiter.preferance.SettingPreferances;

import java.util.ArrayList;

public class MyInnerPagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<EmptyTable> tables;
    private Context mC;
    private String TAG = "MY_LOG";
    private MyPagerNotif myPagerNotif;

    @Override
    public void notifyDataSetChanged() {
        Log.d("TEST_N", "notifyDataSetChanged: ");

        super.notifyDataSetChanged();

    }

    public MyInnerPagerAdapter(FragmentManager fm, ArrayList<EmptyTable> list, Context c) {
        super(fm);
        this.mC = c;
        tables = list;

    }


    @Override
    public Fragment getItem(int position) {
        Log.d("TEST", "getItem: ==" + position);
        FragmentTables tables = FragmentTables.getInstance(position);
        tables.setMyNotifyInterface(new FragmentTables.MyNotifyInterface() {
            @Override
            public void myNotify(int i) {
                notifyDataSetChanged();
                Log.d("TEST_N", "myNotify: ");
//                myPagerNotif.myN(i);

            }
        });

        return tables;
    }

    public interface MyPagerNotif {
        void myN(int i);

    }

    @Override
    public int getCount() {
        return tables.size();
    }

}

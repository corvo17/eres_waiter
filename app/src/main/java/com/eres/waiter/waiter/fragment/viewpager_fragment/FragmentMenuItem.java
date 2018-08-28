package com.eres.waiter.waiter.fragment.viewpager_fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.eres.waiter.waiter.R;
import com.eres.waiter.waiter.adapters.MyInnerPagerAdapter;
import com.eres.waiter.waiter.adapters.MyPagerAdapter;
import com.eres.waiter.waiter.fragment.FragmentMenu;
import com.eres.waiter.waiter.model.CategoriesItem;
import com.eres.waiter.waiter.model.Data;
import com.eres.waiter.waiter.model.EmptyTable;
import com.eres.waiter.waiter.model.singelton.DataSingelton;
import com.eres.waiter.waiter.retrofit.ApiClient;
import com.eres.waiter.waiter.retrofit.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentMenuItem extends Fragment {
    private TabLayout tabLayout;
    private ArrayList<CategoriesItem> list;
    private ArrayList<String> tabList;
    private ViewPager viewPager;
    private MyPagerAdapter myInnerPagerAdapter;
    private static String TAG = "MY_LOG";
    private int key = -1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.empty_layout, container, false);
        tabLayout = view.findViewById(R.id.listTab);
        viewPager = view.findViewById(R.id.pagerInner);
        key = getArguments().getInt("POS");
        onLoadListData();

        return view;
    }

    public static FragmentMenuItem getInstance(int i) {
        FragmentMenuItem fragmentMenuItem = new FragmentMenuItem();
        Bundle bundle = new Bundle();
        bundle.putInt("POS", i);
        fragmentMenuItem.setArguments(bundle);
        return fragmentMenuItem;
    }

    public void loadViewPager() {
        onLoadTabLayout();
        myInnerPagerAdapter = new MyPagerAdapter(getFragmentManager(), key, list.size());
        viewPager.setAdapter(myInnerPagerAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void onLoadTabLayout() {
        tabList = new ArrayList<>();
        tabList.clear();
        tabLayout.removeAllTabs();
        for (int i = 0; i < list.size(); i++) {
            tabList.add(list.get(i).getName());
            tabLayout.addTab(tabLayout.newTab().setText(tabList.get(i)));
        }

    }


    private void onLoadListData() {
        new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.d(TAG, "onTick: ");
                if (DataSingelton.isLoad()) {
                    list = (ArrayList<CategoriesItem>) DataSingelton.getDataMenus().get(key).getCategories();
                    loadViewPager();
                    Log.d(TAG, "Load Data: list size =" + millisUntilFinished + "===========" + list.size());
                    cancel();
                }
            }

            @Override
            public void onFinish() {
                Log.d(TAG, "onFinish: ");
            }
        }.start();


    }
}


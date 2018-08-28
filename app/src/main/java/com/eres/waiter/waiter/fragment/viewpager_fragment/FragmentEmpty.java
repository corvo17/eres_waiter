package com.eres.waiter.waiter.fragment.viewpager_fragment;

import android.arch.lifecycle.ViewModelProviders;
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
import com.eres.waiter.waiter.model.EmptyTable;
import com.eres.waiter.waiter.model.events.EventMessageAdapter;
import com.eres.waiter.waiter.model.events.EventMessageSendFood;
import com.eres.waiter.waiter.model.events.EventTable;
import com.eres.waiter.waiter.model.singelton.DataSingelton;
import com.eres.waiter.waiter.mvvm.repository.viewmodel.TableViewModel;
import com.eres.waiter.waiter.retrofit.ApiClient;
import com.eres.waiter.waiter.retrofit.ApiInterface;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentEmpty extends Fragment {
    private TabLayout tabLayout;
    private ArrayList<EmptyTable> list;
    private ArrayList<String> tabList;
    private ViewPager viewPager;
    private MyInnerPagerAdapter myInnerPagerAdapter;
    private static String TAG = "MY_LOG";
    private int a = 0;

    @Override
    public void onDestroy() {
        Log.d("TEST_N", "onDestroy: ");
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.empty_layout, container, false);
        EventBus.getDefault().register(this);
        tabLayout = view.findViewById(R.id.listTab);
        viewPager = view.findViewById(R.id.pagerInner);

        onLoadListData();
        return view;
    }


    public void loadViewPager() {
        onLoadTabLayout();
        myInnerPagerAdapter = new MyInnerPagerAdapter(getFragmentManager(), list, getContext());
        viewPager.setAdapter(myInnerPagerAdapter);
//myInnerPagerAdapter.s
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                a = tab.getPosition();
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
        Log.d("MY_LIST", "onLoadTabLayout: " + list.size());
        for (int i = 0; i < list.size(); i++) {
            tabList.add(list.get(i).getName());
            tabLayout.addTab(tabLayout.newTab().setText(tabList.get(i)));
        }

    }

    @Subscribe
    public void EventTableMessage(EventMessageAdapter tables) {
        Log.d("TEST_N", "EventTableMessage: " + tables.getA());
//        viewPager.setCurrentItem(tables.getA());
        myInnerPagerAdapter.notifyDataSetChanged();


    }

    private void onLoadListData() {
        // TODO: 16.08.2018 Responce kemaslik holini korish kerak

        list = DataSingelton.getEmptyTables();
        loadViewPager();

    }


}



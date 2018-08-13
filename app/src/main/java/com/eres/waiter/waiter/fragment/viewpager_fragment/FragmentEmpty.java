package com.eres.waiter.waiter.fragment.viewpager_fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.eres.waiter.waiter.R;
import com.eres.waiter.waiter.adapters.AdapterEmpty;
import com.eres.waiter.waiter.adapters.MyInnerPagerAdapter;
import com.eres.waiter.waiter.model.EmptyTable;
import com.eres.waiter.waiter.model.test.EventMessage;
import com.eres.waiter.waiter.model.test.EventMessageSendFood;
import com.eres.waiter.waiter.model.test.TestClass;
import com.eres.waiter.waiter.retrofit.ApiClient;
import com.eres.waiter.waiter.retrofit.ApiInterface;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.constraint.Constraints.TAG;

public class FragmentEmpty extends Fragment {
    private TabLayout tabLayout;
    private ArrayList<EmptyTable> list;
    private ArrayList<String> tabList;
    private ViewPager viewPager;
    private MyInnerPagerAdapter myInnerPagerAdapter;
    private static String TAG = "MY_LOG";
    private ProgressBar progressBar;

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.empty_layout, container, false);
        EventBus.getDefault().register(this);
        progressBar = view.findViewById(R.id.pB);
        onLoadListData();
        progressBar.setVisibility(View.VISIBLE);
        tabLayout = view.findViewById(R.id.listTab);
        viewPager = view.findViewById(R.id.pagerInner);
        return view;
    }

    @Subscribe
    public void EventMessage(EventMessageSendFood sendFood) {
        if (!sendFood.isSendFood()) onLoadListData();
    }

    public void loadViewPager() {
        progressBar.setVisibility(View.GONE);
        onLoadTabLayout();
        myInnerPagerAdapter = new MyInnerPagerAdapter(getFragmentManager(), list, getContext());
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
        list = new ArrayList<>();
        ApiInterface apiInterface = ApiClient.getRetrofit(getContext()).create(ApiInterface.class);
        Call<ArrayList<EmptyTable>> call = apiInterface.getEmptyTable();
        call.enqueue(new Callback<ArrayList<EmptyTable>>() {
            @Override
            public void onResponse(Call<ArrayList<EmptyTable>> call, Response<ArrayList<EmptyTable>> response) {
                if (response.body() != null) {
                    Log.d(TAG, "onResponse: +++++" + response.body().size());
                    list.addAll(response.body());
                    loadViewPager();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<EmptyTable>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


}



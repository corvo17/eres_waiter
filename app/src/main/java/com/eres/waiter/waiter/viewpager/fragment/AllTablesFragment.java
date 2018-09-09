package com.eres.waiter.waiter.viewpager.fragment;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eres.waiter.waiter.R;
import com.eres.waiter.waiter.app.App;
import com.eres.waiter.waiter.model.Table;
import com.eres.waiter.waiter.preferance.SettingPreferances;
import com.eres.waiter.waiter.viewpager.helper.ObservableCollection;
import com.eres.waiter.waiter.viewpager.model.Hall;
import com.eres.waiter.waiter.viewpager.viewmodel.AllTablesViewModel;
import com.labo.kaji.fragmentanimations.CubeAnimation;

public class AllTablesFragment extends Fragment {

    Table table = null;
    private ViewPager vPager;
    private MyFragmentPagerAdapter pageAdapter;
    private int numberOfColumns = 1;
    private TabLayout tabLayout;
    ObservableCollection<com.eres.waiter.waiter.model.Table> tables = null;
    ObservableCollection<Hall> halls = new ObservableCollection<>();
    private boolean isFist = false;
    private int tabPos = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setRetainInstance(true);
        for (Fragment fragment : getFragmentManager().getFragments()) {
            if (fragment instanceof TablesFragment) {
                getFragmentManager().beginTransaction().remove(fragment).commit();
                Log.i("SSS", "onCreate: remove ");

            }
        }
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.empty_layout, container, false);
        tabLayout = (TabLayout) view.findViewById(R.id.listTab);
        vPager = (ViewPager) view.findViewById(R.id.pagerInner);
        AllTablesViewModel model = ViewModelProviders.of(this).get(AllTablesViewModel.class);
        data = model.getData();
//        halls = model.getData().getValue();
        data.observe(this, new Observer<ObservableCollection<Hall>>() {
            @Override
            public void onChanged(@Nullable ObservableCollection<Hall> halls1) {
                halls = halls1;
                App.getApp().setTables(data.getValue());
                pageAdapter = new MyFragmentPagerAdapter(model.getData(), getFragmentManager());
                vPager.setAdapter(pageAdapter);
                tabLayout.setupWithViewPager(vPager);

//                vPager.setPageTransformer(false, new CubeAnimation().);

                pageAdapter.notifyDataSetChanged();
                if (tabLayout != null) {
                    TabLayout.Tab tab = tabLayout.getTabAt(SettingPreferances.preferances.getHallPosition());
                    if (tab != null) {
                        Log.d("LOG_TEST", "run: ");
                        tab.select();
                    }
                }

            }
        });
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                View view1 = LayoutInflater.from(getContext()).inflate(R.layout.custum_view, container, false);
//
//                tab.setCustomView(view1);
//                Log.d("TAB_TEST", "onTabSelected: " + tab.getText());

                tabPos = tab.getPosition();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        return view;
    }


    @Override
    public void onPause() {
        SettingPreferances.preferances.setHallPosition(tabPos);
        Log.d("LOG_TEST", "onPause: " + tabPos);
        super.onPause();
    }


    LiveData<ObservableCollection<Hall>> data = null;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    public ObservableCollection<Table> getTables(long hallId) {
        ObservableCollection<Hall> halls = data.getValue();
        for (int ci = 0; ci < halls.size(); ci++) {
            Hall hall = halls.get(ci);
            if (hall.getId() == hallId)
                return hall.getTables();
        }
        return null;
    }

    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        LiveData<ObservableCollection<Hall>> halldata;

        public MyFragmentPagerAdapter(LiveData<ObservableCollection<Hall>> _data, FragmentManager fm) {
            super(fm);
            this.halldata = _data;
        }

        @Override
        public Fragment getItem(int position) {
            return TablesFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            if (halldata != null && halldata.getValue() != null)
                return halldata.getValue().size();
            return 0;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            if (halldata != null && halldata.getValue() != null)
                return halldata.getValue().get(position).getName();
            return null;
        }


    }
}

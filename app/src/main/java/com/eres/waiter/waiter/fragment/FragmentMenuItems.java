package com.eres.waiter.waiter.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import com.eres.waiter.waiter.R;
import com.eres.waiter.waiter.adapters.MenuAdapter;
import com.eres.waiter.waiter.model.ProductsItem;
import com.eres.waiter.waiter.model.singelton.DataSingelton;
import com.eres.waiter.waiter.model.events.EventMessage;
import com.labo.kaji.fragmentanimations.MoveAnimation;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

public class FragmentMenuItems extends Fragment {
    private ArrayList<ProductsItem> items;
    private String TAG = "MY_LOG";
    private RecyclerView recyclerView;
    private MenuAdapter adapter;

//    @Override
//    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
//
//        return MoveAnimation.create(MoveAnimation.UP, enter, 300);
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_tables, container, false);
        int key = getArguments().getInt("KEY");
        int id = getArguments().getInt("ID");
        items = (ArrayList<ProductsItem>) DataSingelton.getDataMenus().get(key).getCategories().get(id).getProducts();
        recyclerView = view.findViewById(R.id.rec_tables);
        adapter = new MenuAdapter(items);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Log.d(TAG, "onCreateView: item " + items.size());
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);

    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);

    }

    public static FragmentMenuItems getInstance(int key, int id) {
        FragmentMenuItems fragmentMenuItem = new FragmentMenuItems();
        Bundle bundle = new Bundle();
        bundle.putInt("KEY", key);
        bundle.putInt("ID", id);
        fragmentMenuItem.setArguments(bundle);
        return fragmentMenuItem;
    }

    @Subscribe
    public void onEvent(EventMessage message) {
        Log.d(TAG, "onEvent: ");
        if (message.isaBoolean()) {
            adapter.notifyDataSetChanged();

        }
    }
}

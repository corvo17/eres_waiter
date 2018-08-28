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

import com.eres.waiter.waiter.R;
import com.eres.waiter.waiter.adapters.MenuAdapter;
import com.eres.waiter.waiter.model.CategoriesItem;
import com.eres.waiter.waiter.model.DataMenu;
import com.eres.waiter.waiter.model.ProductsItem;
import com.eres.waiter.waiter.model.events.EventSearchMessage;
import com.eres.waiter.waiter.model.singelton.DataSingelton;
import com.eres.waiter.waiter.viewpager.helper.ObservableCollection;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    private MenuAdapter adapter;
    ObservableCollection<ProductsItem> list;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        list = DataSingelton.searchItems;
        list.clear();
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.layout_tables, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rec_tables);
        Log.d("TEST_NNM", "onCreateView: " + list.size());
        adapter = new MenuAdapter((ArrayList<ProductsItem>) list);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
        return view;
    }


    private void loadSearchData(CharSequence s) {
        Log.d("TEST_NNM", "loadSearchData: " + s);
        list.clear();
        ArrayList<DataMenu> menus = DataSingelton.getDataMenus();
        for (DataMenu menu : menus) {
            for (CategoriesItem categoriesItem : menu.getCategories()) {
                for (ProductsItem item : categoriesItem.getProducts()) {
                    if (item.getName().toUpperCase().contains((s + "").toUpperCase())) {
                        list.add(item);
                    }
                }
            }
        }

        Log.d("TEST_NNM", "loadSearchData: " + list.size());
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Subscribe
    public void EventSearchItem(EventSearchMessage message) {
        loadSearchData(message.getS());
        adapter.notifyDataSetChanged();

    }

}

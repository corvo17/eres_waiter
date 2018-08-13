package com.eres.waiter.waiter.fragment.viewpager_fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import com.eres.waiter.waiter.R;
import com.eres.waiter.waiter.adapters.AdapterEmpty;
import com.eres.waiter.waiter.model.TablesItem;
import com.eres.waiter.waiter.model.test.BSendObject;
import com.labo.kaji.fragmentanimations.CubeAnimation;
import com.labo.kaji.fragmentanimations.MoveAnimation;

import java.util.ArrayList;

public class FragmentTables extends Fragment {
    private RecyclerView recyclerView;
    private AdapterEmpty adapterEmpty;
    private ArrayList<TablesItem> items;
    private String TAG = "MY_LOG";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_tables, container, false);
        recyclerView = view.findViewById(R.id.rec_tables);
        items = new ArrayList<>();
        items.clear();
        BSendObject bSendObject = (BSendObject) getArguments().getSerializable("DATA");
        items.addAll(bSendObject.getTables());
        adapterEmpty = new AdapterEmpty(items);
        recyclerView.setAdapter(adapterEmpty);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));

        return view;
    }


    public static FragmentTables getInstance(ArrayList<TablesItem> tables) {
        FragmentTables fragmentTables = new FragmentTables();
        BSendObject bSendObject = new BSendObject(tables);
        Bundle bundle = new Bundle();
        bundle.putSerializable("DATA", bSendObject);
        fragmentTables.setArguments(bundle);
        return fragmentTables;
    }
}



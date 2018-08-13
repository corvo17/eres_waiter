package com.eres.waiter.waiter.fragment.viewpager_fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import com.eres.waiter.waiter.R;
import com.eres.waiter.waiter.adapters.AdapterArmored;
import com.eres.waiter.waiter.adapters.AdapterITable;
import com.eres.waiter.waiter.model.ArmoredTables;
import com.eres.waiter.waiter.retrofit.ApiClient;
import com.eres.waiter.waiter.retrofit.ApiInterface;
import com.labo.kaji.fragmentanimations.CubeAnimation;
import com.labo.kaji.fragmentanimations.MoveAnimation;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentReservation extends Fragment {
    private RecyclerView recyclerView;
    private AdapterArmored adapterArmored;
    private ArrayList<ArmoredTables> list;
    private String TAG = "MY_LOG";
    private boolean q = false;

    @Override
    public void onStart() {
        super.onStart();
        if (q)
            loadData();
    }

    @Override
    public void onStop() {
        super.onStop();
        q = false;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reservation_layout, container, false);
        recyclerView = view.findViewById(R.id.rec_layout_reser);
        list = new ArrayList<>();
        loadData();
        q = true;

        return view;

    }

    public void loadRecyler() {
        adapterArmored = new AdapterArmored(list);
        recyclerView.setAdapter(adapterArmored);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));

    }

    public void loadData() {
        ApiInterface apiInterface = ApiClient.getRetrofit(getContext()).create(ApiInterface.class);
        Call<ArrayList<ArmoredTables>> call = apiInterface.getArmoredTables();
        call.enqueue(new Callback<ArrayList<ArmoredTables>>() {
            @Override
            public void onResponse(Call<ArrayList<ArmoredTables>> call, Response<ArrayList<ArmoredTables>> response) {
                if (response.body() != null) {
                    list.clear();
                    list.addAll(response.body());
                    Log.d(TAG, "onResponse: " + response.body().size());
                    loadRecyler();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ArmoredTables>> call, Throwable t) {
                t.printStackTrace();
            }
        });


    }
}

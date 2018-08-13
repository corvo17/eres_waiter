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
import com.eres.waiter.waiter.adapters.AdapterITable;
import com.eres.waiter.waiter.model.IAmTables;
import com.eres.waiter.waiter.retrofit.ApiClient;
import com.eres.waiter.waiter.retrofit.ApiInterface;
import com.labo.kaji.fragmentanimations.CubeAnimation;
import com.labo.kaji.fragmentanimations.MoveAnimation;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentITable extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<IAmTables> strings;
    private String TAG = "MY_LOG";
    private AdapterITable adapterITable;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.i_table_layout, container, false);
        strings = new ArrayList<>();

        loadData();
        recyclerView = view.findViewById(R.id.rec_layout_itable);

        return view;
    }

    public void loadRecycler() {
        adapterITable = new AdapterITable(strings);
        recyclerView.setAdapter(adapterITable);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));

    }

    private void loadData() {
        strings.clear();
        ApiInterface apiInterface = ApiClient.getRetrofit(getContext()).create(ApiInterface.class);
        Call<ArrayList<IAmTables>> call = apiInterface.getIamTables();
        call.enqueue(new Callback<ArrayList<IAmTables>>() {
            @Override
            public void onResponse(Call<ArrayList<IAmTables>> call, Response<ArrayList<IAmTables>> response) {
                if (response.body() != null) {
                    strings.addAll(response.body());
                    Log.d(TAG, "MY TABLE size : " + response.body().size());
                    loadRecycler();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<IAmTables>> call, Throwable t) {
                t.printStackTrace();
            }
        });


    }
}

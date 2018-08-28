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
import android.widget.ImageView;

import com.eres.waiter.waiter.R;
import com.eres.waiter.waiter.adapters.AdapterITable;
import com.eres.waiter.waiter.app.App;
import com.eres.waiter.waiter.model.Data;
import com.eres.waiter.waiter.model.IAmTables;
import com.eres.waiter.waiter.model.singelton.DataSingelton;
import com.eres.waiter.waiter.retrofit.ApiClient;
import com.eres.waiter.waiter.retrofit.ApiInterface;
import com.eres.waiter.waiter.server.NotificationData;
import com.labo.kaji.fragmentanimations.CubeAnimation;
import com.labo.kaji.fragmentanimations.MoveAnimation;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentITable extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<IAmTables> iAmTabless;
    private String TAG = "MY_LOG";
    private AdapterITable adapterITable;
    Disposable disposable;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.i_table_layout, container, false);
        recyclerView = view.findViewById(R.id.rec_layout_itable);
        loadData();
        return view;
    }

    public void loadRecycler() {


        adapterITable = new AdapterITable(iAmTabless);
        recyclerView.setAdapter(adapterITable);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        disposable.dispose();
    }

    private void loadData() {
        disposable = App.getApp().getAllIAmTables().subscribe(new Consumer<ArrayList<IAmTables>>() {
            @Override
            public void accept(ArrayList<IAmTables> iAmTables) throws Exception {
                iAmTabless = iAmTables;
                DataSingelton.iAmTables.clear();
                for (IAmTables table : iAmTables) {
                    for (Integer integer : DataSingelton.eventTables) {
                        Log.d(TAG, "Event Table Id " + integer);
                        if (table.getId() == integer.intValue()) {
                            Log.d(TAG, "table Id: " + table.getId());
                            table.setType(true);
                        }
                    }
                }
                DataSingelton.iAmTables.addAll(iAmTables);

                Log.d(TAG, "MY TABLE size : " + iAmTables.size());
                loadRecycler();
            }
        });

    }


}

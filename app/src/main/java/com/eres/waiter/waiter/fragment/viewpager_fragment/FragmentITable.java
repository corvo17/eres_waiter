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

import com.eres.waiter.waiter.R;
import com.eres.waiter.waiter.adapters.AdapterITable;
import com.eres.waiter.waiter.app.App;
import com.eres.waiter.waiter.model.IAmTables;
import com.eres.waiter.waiter.model.singelton.DataSingelton;
import com.eres.waiter.waiter.model.test.NotificationEventAlarm;

import java.util.ArrayList;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

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
        Log.d(TAG, "notifyData: size " + DataSingelton.eventNotifAlarm.size());
        disposable = App.getApp().getAllIAmTables().subscribe(new Consumer<ArrayList<IAmTables>>() {
            @Override
            public void accept(ArrayList<IAmTables> iAmTables1) throws Exception {
                iAmTabless = iAmTables1;
                for (IAmTables table : iAmTabless) {
                    table.setType(-1);
                    for (NotificationEventAlarm eventAlarm : DataSingelton.eventNotifAlarm) {
                        if (table.getId() == eventAlarm.getId()) {
                            Log.d("TEST_EVENT1", "table Id: " + table.getId() + " type ==" + eventAlarm.getNotifType());
                            table.setType(eventAlarm.getNotifType());
                        }
                    }
                }
                DataSingelton.iAmTables.clear();
                DataSingelton.iAmTables.addAll(iAmTabless);
                Log.d(TAG, "MY TABLE size : " + iAmTabless.size());
                loadRecycler();
            }
        });


    }

    public void notifyData() {

        disposable = App.getApp().getAllIAmTables().subscribe(new Consumer<ArrayList<IAmTables>>() {
            @Override
            public void accept(ArrayList<IAmTables> iAmTables1) throws Exception {
                if (iAmTabless != null) {
                    iAmTabless.clear();
                }
                iAmTabless = iAmTables1;
                for (IAmTables table : iAmTabless) {

                    table.setType(-1);
                    for (NotificationEventAlarm eventAlarm : DataSingelton.eventNotifAlarm) {
                        int k = 0;
                        if (table.getId() == eventAlarm.getId()) {
                            Log.d(TAG, k++ + "table Id: " + table.getId() + "== eventID = " + eventAlarm.getId() + " type ==" + eventAlarm.getNotifType());
                            table.setType(eventAlarm.getNotifType());
                        }
                    }
                }
                DataSingelton.iAmTables.clear();
                DataSingelton.iAmTables.addAll(iAmTabless);
                adapterITable.notifyDataSetChanged();
                Log.d(TAG, "MY TABLE size : " + iAmTabless.size());
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adapterITable != null)
            notifyData();

        Log.d(TAG, "notiife size on resume : " + DataSingelton.eventNotifAlarm.size() + " === data size ==== " + DataSingelton.iAmTables.size());
    }
}

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
import com.eres.waiter.waiter.model.events.EventIAmTableChange;
import com.eres.waiter.waiter.model.singelton.DataSingelton;
import com.eres.waiter.waiter.model.test.NotificationEventAlarm;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class FragmentITable extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<IAmTables> iAmTabless;
    private String TAG = "MY_LOG";
    private AdapterITable adapterITable;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.i_table_layout, container, false);

        iAmTabless = DataSingelton.getiAmTables();
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
        EventBus.getDefault().unregister(this);

    }

    private void loadData() {
        Log.d(TAG, "notifyData: size " + DataSingelton.eventNotifAlarm.size());

        Log.d(TAG, "MY TABLE size : " + iAmTabless.size());
        loadRecycler();
        notifyData();
    }

    public void loadNotifyTable() {
        for (IAmTables table : DataSingelton.getiAmTables()) {
            table.setType(-1);
            Integer a = DataSingelton.eventNotifAlarm.get("" + table.getId());
            if (a != null) {
                Log.d("TEST_EVENT1", "table Id: " + table.getId() + " type ==" + a);
                table.setType(a);
            }
        }

        adapterITable.loadNewData(DataSingelton.getiAmTables());
    }


    public void notifyData() {
        loadNotifyTable();
        adapterITable.notifyDataSetChanged();
        Log.d(TAG, "MY TABLE size : " + iAmTabless.size());

    }

    @Override
    public void onResume() {
        super.onResume();
        if (adapterITable != null)
            notifyData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    public void EventITable(EventIAmTableChange eventIAmTableChange) {
        notifyData();
        Log.d(TAG, "notiife size on resume : " + DataSingelton.eventNotifAlarm.size() + " === data size ==== " + DataSingelton.iAmTables.size());
    }
}

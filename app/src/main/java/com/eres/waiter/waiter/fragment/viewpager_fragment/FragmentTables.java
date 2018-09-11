package com.eres.waiter.waiter.fragment.viewpager_fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eres.waiter.waiter.R;
import com.eres.waiter.waiter.adapters.AdapterEmpty;
import com.eres.waiter.waiter.model.TablesItem;
import com.eres.waiter.waiter.model.events.EventMessageAdapter;
import com.eres.waiter.waiter.model.events.EventMessageRemoveTable;
import com.eres.waiter.waiter.model.events.EventTable;
import com.eres.waiter.waiter.model.singelton.DataSingelton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class FragmentTables extends Fragment {
    private RecyclerView recyclerView;
    private AdapterEmpty adapterEmpty;
    private List<TablesItem> items;
    private String TAG = "MY_LOG";
    private int posit = 0;
    private int page = 0;
    private MyNotifyInterface myNotifyInterface;

    public void setMyNotifyInterface(MyNotifyInterface myNotifyInterface) {
        this.myNotifyInterface = myNotifyInterface;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_tables, container, false);
        EventBus.getDefault().register(this);
        recyclerView = view.findViewById(R.id.rec_tables);
        int i = getArguments().getInt("DATA");
        page = i;
        items = DataSingelton.singelton.getHalls().get(i).getTables();
        Log.d("TEST_N", "onCreateView: begin table size == " + items.size());
        adapterEmpty = new AdapterEmpty(items);
        adapterEmpty.setMyInterfaceItem(new AdapterEmpty.MyInterfaceItem() {
            @Override
            public void itemPosition(int position) {
                posit = position;
            }
        });

        recyclerView.setAdapter(adapterEmpty);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        return view;
    }

    @Subscribe
    public void EventTable(EventTable table) {
//        if (table.getTableId() - 1 == page) {
//            Log.d("TEST_N", "Table size : " + items.size());
//
//            adapterEmpty.updateList(items);
//
//            recyclerView.scrollToPosition(items.size());
//            adapterEmpty.notifyDataSetChanged();
////            EventBus.getDefault().post(new EventMessageAdapter(table.getTableId() - 1));
//            myNotifyInterface.myNotify(table.getTableId() - 1);
//        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    public void eventRemove(EventMessageRemoveTable removeTable) {
        adapterEmpty.updateList(items);
        adapterEmpty.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("TEST_N", "onDestroyView: ");
        EventBus.getDefault().unregister(this);
    }

    public static FragmentTables getInstance(int pos) {
        FragmentTables fragmentTables = new FragmentTables();
        Bundle bundle = new Bundle();
        bundle.putInt("DATA", pos);
        fragmentTables.setArguments(bundle);
        return fragmentTables;
    }

    public interface MyNotifyInterface {
        void myNotify(int i);
    }
}



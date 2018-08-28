package com.eres.waiter.waiter.viewpager.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import com.eres.waiter.waiter.R;
import com.eres.waiter.waiter.activity.MyMenuActivity;
import com.eres.waiter.waiter.model.ProductsItem;
import com.eres.waiter.waiter.model.Table;
import com.eres.waiter.waiter.model.singelton.DataSingelton;
import com.eres.waiter.waiter.preferance.SettingPreferances;
import com.eres.waiter.waiter.viewpager.helper.ObservableCollection;

import java.util.ArrayList;

public class TablesAdapter extends RecyclerView.Adapter<TablesAdapter.MyViewHolder>
        implements ObservableCollection.CollectionChangeListener {


    private Context mContext;
    private ObservableCollection<Table> tables;
    private int row_index;

    public TablesAdapter(ObservableCollection<Table> _halls) {
        this.tables = _halls;
        this.tables.setCollectionChangeListener(this);
        notifyDataSetChanged();
        Log.d("TEST_NN", "setData: " + this.tables.size());

    }

    public void setData(ObservableCollection<Table> _tables) {
        this.tables = _tables;
        this.tables.setCollectionChangeListener(this);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_first_rec, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.textView.setText(tables.get(position).getId() + "");
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index = position;
                notifyDataSetChanged();
                DataSingelton.getMyOrders().clear();
                SettingPreferances.preferances.setOrderId(0);
                SettingPreferances preferances = SettingPreferances.getSharedPreferance(null);
                preferances.setTableId((int) tables.get(position).getId());
                for (ProductsItem item : DataSingelton.testSet) {
                    item.setCount(0);
                }
                DataSingelton.testSet.clear();
                holder.view.getContext().startActivity(new Intent(holder.textView.getContext(), MyMenuActivity.class));
// TODO: 20.08.2018 open table
            }
        });

    }

    @Override
    public int getItemCount() {
        if (tables == null) return 0;
        return tables.size();
    }

    @Override
    public void onCollectionChange(ObservableCollection.NotifyCollectionChangedAction action, Object obj, long position) {
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView view;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.table);
            view = itemView.findViewById(R.id.mm);
        }
    }

}


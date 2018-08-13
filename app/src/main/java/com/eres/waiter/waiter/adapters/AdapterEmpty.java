package com.eres.waiter.waiter.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eres.waiter.waiter.R;
import com.eres.waiter.waiter.activity.MyMenuActivity;
import com.eres.waiter.waiter.model.TablesItem;
import com.eres.waiter.waiter.model.singelton.DataSingelton;
import com.eres.waiter.waiter.preferance.SettingPreferances;

import java.util.ArrayList;

public class AdapterEmpty extends RecyclerView.Adapter<AdapterEmpty.MyViewHolder> {
    ArrayList<TablesItem> strings;


    public AdapterEmpty(ArrayList<TablesItem> strings) {
        this.strings = strings;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_first_rec, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(strings.get(position).getName());
        holder.view.setOnClickListener(v -> {
            DataSingelton.getMyOrders().clear();
            SettingPreferances.preferances.setOrderId(0);
            SettingPreferances preferances = SettingPreferances.getSharedPreferance(null);
            preferances.setTableId(strings.get(position).getId());
            holder.view.getContext().startActivity(new Intent(holder.textView.getContext(), MyMenuActivity.class));
        });
    }

    @Override
    public int getItemCount() {
        return strings.size();
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

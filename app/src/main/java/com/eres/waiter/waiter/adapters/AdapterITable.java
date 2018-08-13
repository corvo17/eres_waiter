package com.eres.waiter.waiter.adapters;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eres.waiter.waiter.R;
import com.eres.waiter.waiter.activity.FoodListActivity;
import com.eres.waiter.waiter.model.IAmTables;
import com.eres.waiter.waiter.model.singelton.DataSingelton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdapterITable extends RecyclerView.Adapter<AdapterITable.MyViewHolder> {
    private ArrayList<IAmTables> list;

    public AdapterITable(ArrayList<IAmTables> list1) {
        list = new ArrayList<>();
        list.clear();
        list.addAll(list1);

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_first_rec, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.number.setText(list.get(position).getName());
        holder.imageView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.number.getContext(), FoodListActivity.class);

            Bundle bundle = new Bundle();
            bundle.putString("ID", list.get(position).getId() + "");
            intent.putExtras(bundle);
            holder.number.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView number;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.mm);
            number = itemView.findViewById(R.id.table);
        }
    }
}

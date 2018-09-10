package com.eres.waiter.waiter.adapters;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eres.waiter.waiter.R;
import com.eres.waiter.waiter.activity.AboutActivity;
import com.eres.waiter.waiter.model.ArmoredTables;

import java.util.ArrayList;

public class AdapterArmored extends RecyclerView.Adapter<AdapterArmored.MyViewHolder> {
    private ArrayList<ArmoredTables> list;

    public AdapterArmored(ArrayList<ArmoredTables> list1) {

        list = list1;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_first_rec, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.number.setText(list.get(position).getTable().getName());
        holder.imageView.setOnClickListener((View v) -> {
            ArmoredTables tables = list.get(position);
            Bundle bundle = new Bundle();
            bundle.putInt("ID", tables.getTableId());
            bundle.putString("TABLE_NAME", tables.getTable().getName());
            bundle.putString("CLIENT_NAME", tables.getClient().getName());
            bundle.putString("CLIENT_NUMBER", tables.getClient().getPhones());
            bundle.putString("DATE", tables.getArmoredStartTime());

            Intent intent = new Intent(holder.number.getContext(), AboutActivity.class);
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
        LinearLayout imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_liner);
            number = itemView.findViewById(R.id.table);
        }
    }
}

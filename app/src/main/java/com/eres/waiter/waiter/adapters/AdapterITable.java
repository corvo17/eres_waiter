package com.eres.waiter.waiter.adapters;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eres.waiter.waiter.R;
import com.eres.waiter.waiter.activity.FoodListActivity;
import com.eres.waiter.waiter.app.App;
import com.eres.waiter.waiter.model.IAmTables;
import com.eres.waiter.waiter.model.events.EventIAmTableChange;
import com.eres.waiter.waiter.model.singelton.DataSingelton;
import com.eres.waiter.waiter.server.NotificationData;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdapterITable extends RecyclerView.Adapter<AdapterITable.MyViewHolder> {
    private ArrayList<IAmTables> list;

    public AdapterITable(ArrayList<IAmTables> list1) {
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
        holder.number.setText(list.get(position).getName());
        if (list.get(position).isType()) {
            holder.event.setVisibility(View.VISIBLE);
        } else
            holder.event.setVisibility(View.GONE);
        holder.imageView.setOnClickListener(v -> {

            Intent intent = new Intent(holder.number.getContext(), FoodListActivity.class);
            if (list.get(position).isType()) {
                removeEventTableId(list.get(position).getId());
                App.getApp().removeMessage(list.get(position).getNotificationData());
                list.get(position).setType(false);
                notifyDataSetChanged();
            }
            Bundle bundle = new Bundle();
            bundle.putString("ID", list.get(position).getId() + "");
            intent.putExtras(bundle);
            holder.number.getContext().startActivity(intent);
        });
    }

    private void removeEventTableId(int id) {
        for (int i = 0; i < DataSingelton.eventTables.size(); i++) {
            Log.d("TEST_EVENT", DataSingelton.eventTables.get(i).toString() + "removeEventTableId: " + id);
            if (id == DataSingelton.eventTables.get(i)) {
                Log.d("TEST_EVENT", "removeEventTableId: removed");
                DataSingelton.eventTables.remove(i);

                if (DataSingelton.eventTables.size() == 0) {
                    Log.d("TEST_EVENT", "Clear bar notif ");
                    EventBus.getDefault().post(new EventIAmTableChange(false));
                }
            }
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView number;
        ImageView imageView;
        View event;

        public MyViewHolder(View itemView) {
            super(itemView);
            event = itemView.findViewById(R.id.event);
            imageView = itemView.findViewById(R.id.mm);
            number = itemView.findViewById(R.id.table);
        }
    }
}

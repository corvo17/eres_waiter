package com.eres.waiter.waiter.adapters;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eres.waiter.waiter.R;
import com.eres.waiter.waiter.activity.FoodListActivity;
import com.eres.waiter.waiter.adapters.diffUtil.IAmTableCallback;
import com.eres.waiter.waiter.app.App;
import com.eres.waiter.waiter.model.IAmTables;
import com.eres.waiter.waiter.model.enums.NotificationTypees;
import com.eres.waiter.waiter.model.events.EventIAmTableChange;
import com.eres.waiter.waiter.model.singelton.DataSingelton;
import com.eres.waiter.waiter.model.test.NotificationEventAlarm;
import com.eres.waiter.waiter.server.NotificationData;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdapterITable extends RecyclerView.Adapter<AdapterITable.MyViewHolder> {
    private ArrayList<IAmTables> list;

    public AdapterITable(ArrayList<IAmTables> list1) {
        list = list1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_itables_rec, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.number.setText(list.get(position).getName());
        loadEventITableState(holder, position);
        holder.imageView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.number.getContext(), FoodListActivity.class);

            Bundle bundle = new Bundle();
            bundle.putString("ID", list.get(position).getId() + "");
            intent.putExtras(bundle);

            if (list.get(position).isType() != -1) {
                removeEventTableId(list.get(position).getId());
            }
            holder.number.getContext().startActivity(intent);
        });
    }

    public void loadNewData(List<IAmTables> iAmTables) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new IAmTableCallback(list, iAmTables));
        diffResult.dispatchUpdatesTo(this);
        notifyDataSetChanged();
    }

    private void loadEventITableState(MyViewHolder holder, int position) {
        int a = list.get(position).isType();
        Log.d("MY_LOGG", "loadEventITableState: " + a);
        int res = R.drawable.itables_buttom;
        if (NotificationTypees.CompleteKitchen.ordinal() == a) {
            res = R.drawable.back_button_green;
        } else if (NotificationTypees.OrderProblemsInKithen.ordinal() == a)
            res = R.drawable.back_button_red;
        else if (NotificationTypees.OrderAcceptedInKitchen.ordinal() == a)
            res = R.drawable.back_button_yellow;
        holder.event.setBackgroundResource(res);
    }

    private void removeEventTableId(int id) {
        DataSingelton.eventNotifAlarm.remove(id + "");
        if (DataSingelton.eventNotifAlarm.size() == 0) {
            Log.d("TEST_EVENT1", "Clear bar notif ");
            EventBus.getDefault().post(new EventIAmTableChange(false));
        }
    }


    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView number;
        ConstraintLayout imageView;
        View event;

        public MyViewHolder(View itemView) {
            super(itemView);
            event = itemView.findViewById(R.id.event);
            imageView = itemView.findViewById(R.id.mm);
            number = itemView.findViewById(R.id.table);
        }
    }
}

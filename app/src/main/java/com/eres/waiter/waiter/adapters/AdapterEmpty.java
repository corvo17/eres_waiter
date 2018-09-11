package com.eres.waiter.waiter.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.util.ListUpdateCallback;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eres.waiter.waiter.R;
import com.eres.waiter.waiter.activity.MyMenuActivity;
import com.eres.waiter.waiter.adapters.diffUtil.TableCallback;
import com.eres.waiter.waiter.model.TablesItem;
import com.eres.waiter.waiter.model.singelton.DataSingelton;
import com.eres.waiter.waiter.preferance.SettingPreferances;

import java.util.ArrayList;
import java.util.List;

public class AdapterEmpty extends RecyclerView.Adapter<AdapterEmpty.MyViewHolder> {
    List<TablesItem> strings;
    private MyInterfaceItem myInterfaceItem;

    public void setMyInterfaceItem(MyInterfaceItem myInterfaceItem) {
        this.myInterfaceItem = myInterfaceItem;
    }

    public AdapterEmpty(List<TablesItem> string) {
        strings = new ArrayList<>();
        strings.clear();
        strings.addAll(string);

    }

    public void updateList(List<TablesItem> newList) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new TableCallback(strings, newList));
        Log.d("TEST_N", "updateList: " + strings.size() + "===" + newList.size());
        strings.clear();
        strings.addAll(newList);
        diffResult.dispatchUpdatesTo(this);
        notifyDataSetChanged();
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
            myInterfaceItem.itemPosition(position);
            DataSingelton.getMyOrders().clear();
            SettingPreferances preferances = SettingPreferances.getSharedPreferance(null);
            preferances.setOrderId(0);
            preferances.setTableId(strings.get(position).getId());
            holder.view.getContext().startActivity(new Intent(holder.textView.getContext(), MyMenuActivity.class));
        });
    }

    public interface MyInterfaceItem {
        void itemPosition(int position);
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private LinearLayout view;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.table);
            view = itemView.findViewById(R.id.item_liner);
        }
    }
}

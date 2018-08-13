package com.eres.waiter.waiter.adapters.dialogadapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.eres.waiter.waiter.R;
import com.eres.waiter.waiter.model.ProductsItem;
import com.eres.waiter.waiter.model.singelton.DataSingelton;
import com.eres.waiter.waiter.model.test.DataAddList;

import java.util.ArrayList;
import java.util.HashSet;

public class AdapterList extends RecyclerView.Adapter<AdapterList.MyListViewHolder> {
    private ArrayList<ProductsItem> lists;
    private String TAG = "MM_LOG";

    public AdapterList() {
        this.lists = new ArrayList<ProductsItem>(DataSingelton.testSet);
        for (int i = 0; i < lists.size(); i++) {
            removedItem(i);
        }
    }

    @NonNull
    @Override
    public MyListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dialog_list, parent, false);
        return new MyListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyListViewHolder holder, int position) {
        holder.name.setText(lists.get(position).getName() + "");
        holder.count.setText(lists.get(position).getCount() + "");
        holder.add.setOnClickListener(v -> {
            lists.get(position).addCount();
            holder.count.setText(lists.get(position).getCount() + "");
        });
        holder.minus.setOnClickListener(v -> {
            if (lists.get(position).getCount() > 0) {
                lists.get(position).minusCount();
                holder.count.setText(lists.get(position).getCount() + "");

            }

        });
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public void removedItem(int i) {
        if (lists.get(i).getCount() == 0) {
            DataSingelton.testSet.remove(lists.get(i));
            lists.remove(i);
            notifyDataSetChanged();
        }
    }

    public class MyListViewHolder extends RecyclerView.ViewHolder {

        private ImageButton add;
        private ImageButton minus;
        private TextView name;
        private TextView count;

        public MyListViewHolder(View itemView) {
            super(itemView);
            add = itemView.findViewById(R.id.add);
            minus = itemView.findViewById(R.id.minus);
            name = itemView.findViewById(R.id.name);
            count = itemView.findViewById(R.id.count);

        }
    }
}

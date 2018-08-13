package com.eres.waiter.waiter.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.eres.waiter.waiter.R;
import com.eres.waiter.waiter.model.OrderItemsItem;
import com.eres.waiter.waiter.model.ProductsItem;
import com.eres.waiter.waiter.model.enums.OrderState;
import com.eres.waiter.waiter.preferance.SettingPreferances;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;

public class AdapterMyTableList extends RecyclerView.Adapter<AdapterMyTableList.MyViewHolder> {
    ArrayList<OrderItemsItem> items;
    ArrayList<ProductsItem> productsItems;

    public AdapterMyTableList(ArrayList<OrderItemsItem> items, ArrayList<ProductsItem> productsItems) {
        this.items = items;
        this.productsItems = productsItems;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_table_prodact, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(productsItems.get(position).getName());
        String s = SettingPreferances.preferances.getUrl() + productsItems.get(position).getImageUrl();
//        Picasso.get().load(s).resize(100, 100).into(holder.circularImageView);
        Glide.with(holder.textView.getContext()).load(s).into(holder.circularImageView);
        loadStateId(holder, items.get(position).getStateId());

    }

    private void loadStateId(MyViewHolder holder, int stateId) {
        holder.layout.setVisibility(View.GONE);
        if (stateId == OrderState.SendToKitchen.ordinal())
            holder.imageView.setImageResource(R.drawable.help);
        else if (stateId == OrderState.CompleteKitchen.ordinal()) {
            holder.layout.setVisibility(View.VISIBLE);
            holder.imageView.setImageResource(R.drawable.compile);
        } else if (stateId == OrderState.AcceptedInKitchen.ordinal()) {
            holder.layout.setVisibility(View.VISIBLE);
            holder.imageView.setImageResource(R.drawable.accept);

        } else if (stateId == OrderState.ProblemInKitchen.ordinal()) {

            holder.imageView.setImageResource(R.drawable.ic_error_outline_black_24dp);
        }


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layout;
        TextView textView;
        ImageView imageView;
        CircularImageView circularImageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.block);
            circularImageView = itemView.findViewById(R.id.imgs);
            textView = itemView.findViewById(R.id.name);
            imageView = itemView.findViewById(R.id.status);
        }
    }
}

package com.eres.waiter.waiter.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.eres.waiter.waiter.R;
import com.eres.waiter.waiter.model.ProductsItem;
import com.eres.waiter.waiter.model.singelton.DataSingelton;
import com.eres.waiter.waiter.model.test.DataAddList;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyMenuViewHolder> {
    private ArrayList<ProductsItem> items;

    public MenuAdapter(ArrayList<ProductsItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public MyMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new MyMenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyMenuViewHolder holder, int position) {
        holder.name.setText(items.get(position).getName());
        setMyTextView(holder, position);
//        Glide.with(holder.name.getContext()).load("http://192.168.0.118:8000/" + items.get(position).getImageUrl()).into(holder.img);
//        Glide.with(holder.name.getContext()).load("http://loremflickr.com/320/240/dog").into(holder.img);

        Picasso.get().load("http://192.168.0.118:8000/" + items.get(position).getImageUrl()).resize(100, 100).into(holder.img);
        holder.add.setOnClickListener(v -> {
            items.get(position).addCount();
            setMyTextView(holder, position);
            DataSingelton.testSet.add(items.get(position));
        });

        holder.minus.setOnClickListener(v -> {
            if (items.get(position).getCount() > 0) {
                items.get(position).minusCount();
                setMyTextView(holder, position);

                DataSingelton.testSet.add(items.get(position));
            }

        });

    }

    public void setMyTextView(MyMenuViewHolder holder, int pos) {
        holder.count.setText(items.get(pos).getCount() + "");

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyMenuViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private CircularImageView img;
        private ImageButton add, minus;
        private TextView count;

        public MyMenuViewHolder(View itemView) {
            super(itemView);
            count = itemView.findViewById(R.id.count);
            name = itemView.findViewById(R.id.name);
            img = itemView.findViewById(R.id.imga);
            add = itemView.findViewById(R.id.add);
            minus = itemView.findViewById(R.id.minus);
        }
    }
}

package com.eres.waiter.waiter.adapters;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.eres.waiter.waiter.R;
import com.eres.waiter.waiter.adapters.diffUtil.OrderCallback;
import com.eres.waiter.waiter.logic.BaseLogic;
import com.eres.waiter.waiter.model.OrderItemsItem;
import com.eres.waiter.waiter.model.enums.OrderState;
import com.eres.waiter.waiter.preferance.SettingPreferances;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterMyTableList extends RecyclerView.Adapter<AdapterMyTableList.MyViewHolder> {
    ArrayList<OrderItemsItem> items;
    private MyRemoveListiner myRemoveListiner;

    public void setMyRemoveListiner(MyRemoveListiner myRemoveListiner) {
        this.myRemoveListiner = myRemoveListiner;
    }

    public void updateList(ArrayList<OrderItemsItem> newList) {
        if (newList != null) {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new OrderCallback(items, newList));
            items.clear();
            items.addAll(newList);
            diffResult.dispatchUpdatesTo(this);
            notifyDataSetChanged();
        }
    }

    public AdapterMyTableList(ArrayList<OrderItemsItem> items) {
        this.items = items;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_table_prodact, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        BaseLogic baseLogic = new BaseLogic();
        holder.textView.setText(baseLogic.textSubString(items.get(position).getProduct().getName(), 25,true));
        holder.count.setText(items.get(position).getCount() + " шт");
        holder.remove.setOnClickListener(v -> {
            holder.swipeRevealLayout.close(true);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            items.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, items.size());
//            holder.swipeRevealLayout.close(true);
            myRemoveListiner.onRemoveItem();
        });
        String s = SettingPreferances.preferances.getUrl() + items.get(position).getProduct().getImageUrl();
//        Picasso.get().load(s).into(holder.circularImageView);
//        Glide.with(holder.textView.getContext()).load(s).into(holder.circularImageView);
        loadStateId(holder, items.get(position).getStateId());

    }

    private void loadStateId(MyViewHolder holder, int stateId) {
        holder.layout.setVisibility(View.GONE);
        ImageView img = holder.imageView;
        img.setVisibility(View.VISIBLE);

        holder.swipeRevealLayout.setLockDrag(false);
        boolean swap = false;
        if (stateId == OrderState.SendToKitchen.ordinal()) {
            swap = true;
            img.setVisibility(View.GONE);
            holder.container.setBackgroundResource(R.drawable.background_i_table_item);

        } else if (stateId == OrderState.CompleteKitchen.ordinal()) {
            holder.layout.setVisibility(View.VISIBLE);
            holder.container.setBackgroundResource(R.drawable.background_i_table_item_green);
            holder.imageView.setImageResource(R.drawable.ic_check);
        } else if (stateId == OrderState.AcceptedInKitchen.ordinal()) {
            holder.layout.setVisibility(View.VISIBLE);
            holder.imageView.setImageResource(R.drawable.ic_waiting);
            holder.container.setBackgroundResource(R.drawable.background_i_table_item_green);
        } else if (stateId == OrderState.ProblemInKitchen.ordinal()) {
            swap = true;
            holder.container.setBackgroundResource(R.drawable.background_i_table_item_red);
            holder.imageView.setImageResource(R.drawable.ic_error);
        }
        if (!swap) {
            holder.swipeRevealLayout.setLockDrag(true);
        }

    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layout;
        TextView textView;
        ImageView imageView;
        ConstraintLayout remove;
        ConstraintLayout container;
        SwipeRevealLayout swipeRevealLayout;
        TextView count;

        public MyViewHolder(View itemView) {
            super(itemView);
            count = itemView.findViewById(R.id.count);
            container = itemView.findViewById(R.id.contain);
            layout = itemView.findViewById(R.id.block);
            textView = itemView.findViewById(R.id.name);
            imageView = itemView.findViewById(R.id.status);
            remove = itemView.findViewById(R.id.remove);
            swipeRevealLayout = itemView.findViewById(R.id.swipe);
        }
    }

    public interface MyRemoveListiner {
        void onRemoveItem();
    }
}

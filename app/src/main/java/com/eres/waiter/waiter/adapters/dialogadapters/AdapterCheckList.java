package com.eres.waiter.waiter.adapters.dialogadapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;

import com.eres.waiter.waiter.R;
import com.eres.waiter.waiter.model.Desire;

import java.util.ArrayList;

public class AdapterCheckList extends RecyclerView.Adapter<MyCheckHolder> {
    private ArrayList<Desire> desires;

    public AdapterCheckList(ArrayList<Desire> desires) {
        this.desires = desires;
    }

    @NonNull

    @Override
    public MyCheckHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog_chack_list_item, parent, false);
        return new MyCheckHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCheckHolder holder, int position) {
        holder.checkedTextView.setText(desires.get(position).getName());
        boolean a = desires.get(position).isChack();

        if (a) {
            holder.checkedTextView.setCheckMarkDrawable(R.drawable.ic_check_true);
        } else holder.checkedTextView.setCheckMarkDrawable(R.drawable.ic_check_false);
        holder.checkedTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean a = desires.get(position).isChack();
                desires.get(position).setChack(!a);
                holder.checkedTextView.setChecked(!a);
                if (holder.checkedTextView.isChecked()) {
                    holder.checkedTextView.setCheckMarkDrawable(R.drawable.ic_check_true);
                } else holder.checkedTextView.setCheckMarkDrawable(R.drawable.ic_check_false);
            }
        });
    }

    @Override
    public int getItemCount() {
        return desires == null ? 0 : desires.size();
    }
}

class MyCheckHolder extends RecyclerView.ViewHolder {
    CheckedTextView checkedTextView;

    public MyCheckHolder(View itemView) {
        super(itemView);
        checkedTextView = itemView.findViewById(R.id.check);

    }
}

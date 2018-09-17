package com.eres.waiter.waiter.adapters;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.eres.waiter.waiter.R;
import com.eres.waiter.waiter.adapters.dialogadapters.AdapterCheckList;
import com.eres.waiter.waiter.model.ProdIngredient;
import com.eres.waiter.waiter.model.ProductsItem;
import com.eres.waiter.waiter.model.singelton.DataSingelton;
import com.eres.waiter.waiter.model.test.DataAddList;
import com.eres.waiter.waiter.preferance.SettingPreferances;
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
        // TODO: 15.09.2018 true ni ornigaa isDeleteni yozishh kerak
        if (items.get(position).getExcluded() == 1 && true) {
            holder.cont.setBackgroundResource(R.drawable.background_new_item_red);
            holder.back.setVisibility(View.VISIBLE);
        } else {
            holder.back.setVisibility(View.GONE);
            holder.cont.setBackgroundResource(R.drawable.background_new_item);

        }
//        Glide.with(holder.name.getContext()).load("http://192.168.0.118:8000/" + items.get(position).getImageUrl()).into(holder.img);
//        Glide.with(holder.name.getContext()).load("http://loremflickr.com/320/240/dog").into(holder.img);
        holder.info.setOnClickListener(v -> {
            info(items.get(position), v);


        });
        String url = SettingPreferances.preferances.getUrl() + items.get(position).getImageUrl();
        Log.d("PING_TEST", "onBindViewHolder: " + url);
        Picasso.get().load(url).resize(100, 100).into(holder.img);
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
        holder.sendData.setOnClickListener(v -> {

            Dialog dialog = new Dialog(holder.sendData.getContext());
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_send_data_layout);
            Button button = dialog.findViewById(R.id.ok);
            RecyclerView recyclerView = dialog.findViewById(R.id.rec_chack);
            AdapterCheckList adapterCheckList = new AdapterCheckList(items.get(position).getSpecialDesires());
            recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
            recyclerView.setAdapter(adapterCheckList);
            button.setOnClickListener(v1 -> {
                EditText editText = dialog.findViewById(R.id.comment);
                String s = editText.getText().toString();
                items.get(position).setTextSpecialDesires(s);
                dialog.dismiss();
            });
            dialog.show();
        });


    }

    private void info(ProductsItem item, View view) {
        Dialog dialog = new Dialog(view.getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_menu_info);

        TextView textView = dialog.findViewById(R.id.description);
        Button btnDes, btnRet;
        btnDes = dialog.findViewById(R.id.desc);
        btnRet = dialog.findViewById(R.id.ret);
        String s = item.getDescription();
        TextView textV = dialog.findViewById(R.id.title);
        textV.setText(item.getName());
        String ingredients = "";
        if (item.getProdIngredients() != null) {
            for (ProdIngredient prodIngredient : item.getProdIngredients()) {
                ingredients += prodIngredient.getIngredient().getName() + "\n";
            }

        }

        s = s.replaceAll(" ", "");
        String res = s.replaceAll(",", "\n");
        String finalIngredients = ingredients;
        btnDes.setOnClickListener(v -> {
            btnRet.setBackgroundResource(R.drawable.back_button);
            btnDes.setBackgroundResource(R.drawable.back_button_press);
            textView.setText(finalIngredients);
        });
        btnRet.setOnClickListener(v -> {
            btnRet.setBackgroundResource(R.drawable.back_button_press);
            btnDes.setBackgroundResource(R.drawable.back_button);
            textView.setText(res);
        });

        btnRet.callOnClick();
        Button button = dialog.findViewById(R.id.ok);
        button.setOnClickListener(v -> {
            dialog.dismiss();
        });
        dialog.show();
    }

    public void setMyTextView(MyMenuViewHolder holder, int pos) {
        holder.count.setText(items.get(pos).getCount() + "");

    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public class MyMenuViewHolder extends RecyclerView.ViewHolder {

        private ConstraintLayout cont;
        private TextView name;
        private ImageView img;
        private ImageButton add, minus;
        private TextView count;
        private ImageView sendData;
        private ImageView info;
        private LinearLayout back;

        public MyMenuViewHolder(View itemView) {
            super(itemView);
            back = itemView.findViewById(R.id.block);
            cont = itemView.findViewById(R.id.cont);
            info = itemView.findViewById(R.id.info);
            sendData = itemView.findViewById(R.id.sendComment);
            count = itemView.findViewById(R.id.count);
            name = itemView.findViewById(R.id.name);
            img = itemView.findViewById(R.id.imga);
            add = itemView.findViewById(R.id.add);
            minus = itemView.findViewById(R.id.minus);
        }
    }
}

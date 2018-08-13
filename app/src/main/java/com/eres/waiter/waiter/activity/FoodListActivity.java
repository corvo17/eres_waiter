package com.eres.waiter.waiter.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.eres.waiter.waiter.R;
import com.eres.waiter.waiter.adapters.AdapterMyTableList;
import com.eres.waiter.waiter.model.OrderData;
import com.eres.waiter.waiter.model.OrderItemsItem;
import com.eres.waiter.waiter.model.ProductsItem;
import com.eres.waiter.waiter.model.singelton.DataSingelton;
import com.eres.waiter.waiter.model.test.EventMessageSendFood;
import com.eres.waiter.waiter.preferance.SettingPreferances;
import com.eres.waiter.waiter.retrofit.ApiClient;
import com.eres.waiter.waiter.retrofit.ApiInterface;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import retrofit2.Callback;
import retrofit2.Response;

public class FoodListActivity extends AppCompatActivity {
    ArrayList<OrderItemsItem> items;
    private RecyclerView recyclerView;
    String id;
    private String TAG = "MY_LOGG";
    ArrayList<Integer> integers;
    ArrayList<ProductsItem> productsItems;
    private ImageView imageView;
    private Button button;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: 10.08.2018 shu yerga versiga ishlashim kerak  
        setContentView(R.layout.activity_food_lists);
        EventBus.getDefault().register(this);
        id = getIntent().getExtras().getString("ID");
        items = new ArrayList<>();
        button = findViewById(R.id.add);
        button.setOnClickListener(v -> {
            DataSingelton.getMyOrders().clear();
            DataSingelton.setMyOrders(items);
            Log.d(TAG, "TESTSETT: " + DataSingelton.getMyOrders().size());
            SettingPreferances.getSharedPreferance(null).setTableId(Integer.parseInt(id));
            startActivity(new Intent(this, MyMenuActivity.class));
        });
        imageView = findViewById(R.id.back);
        imageView.setOnClickListener(v -> {
            onBackPressed();
        });
        recyclerView = findViewById(R.id.rec_list);
        loadData();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void loadData() {
        integers = new ArrayList<>();
        ApiInterface apiInterface = ApiClient.getRetrofit(this).create(ApiInterface.class);
        retrofit2.Call<ArrayList<OrderData>> call = apiInterface.getMyTableList(id);
        call.enqueue(new Callback<ArrayList<OrderData>>() {
            @Override
            public void onResponse(retrofit2.Call<ArrayList<OrderData>> call, Response<ArrayList<OrderData>> response) {
                if (response.body() != null) {
                    items.clear();
                    integers.clear();
                    SettingPreferances.preferances.setOrderId(response.body().get(0).getId());
                    items.addAll(response.body().get(0).getOrderItems());
                    for (int i = 0; i < items.size(); i++) {
                        integers.add(items.get(i).getProductId());
                    }
                    loadArray();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<ArrayList<OrderData>> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    private void loadArray() {
        productsItems = new ArrayList<>();
        ApiInterface apiInterface = ApiClient.getRetrofit(this).create(ApiInterface.class);
        retrofit2.Call<ArrayList<ProductsItem>> call = apiInterface.getProduct(integers);
        call.enqueue(new Callback<ArrayList<ProductsItem>>() {
            @Override
            public void onResponse(retrofit2.Call<ArrayList<ProductsItem>> call, Response<ArrayList<ProductsItem>> response) {
                if (response.body() != null) {
                    productsItems.clear();
                    productsItems.addAll(response.body());
                    loadAdapter();
                    Log.d(TAG, "onResponse: " + productsItems.size());
                }
            }

            @Override
            public void onFailure(retrofit2.Call<ArrayList<ProductsItem>> call, Throwable t) {
                Log.d(TAG, "onFailure: eerrrrrr");
            }
        });

    }

    @Subscribe
    public void EventMessageFood(EventMessageSendFood sendFood) {
        if (sendFood.isSendFood()) loadData();
    }

    public void loadAdapter() {

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        AdapterMyTableList adapterMyTableList = new AdapterMyTableList(items, productsItems);
        recyclerView.setAdapter(adapterMyTableList);
    }

}

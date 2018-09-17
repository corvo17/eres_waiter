package com.eres.waiter.waiter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.eres.waiter.waiter.R;
import com.eres.waiter.waiter.adapters.AdapterMyTableList;
import com.eres.waiter.waiter.app.App;
import com.eres.waiter.waiter.model.OrderData;
import com.eres.waiter.waiter.model.OrderItemsItem;
import com.eres.waiter.waiter.model.ProductsItem;
import com.eres.waiter.waiter.model.events.EventIAmTableChange;
import com.eres.waiter.waiter.model.events.EventMessageSendFood;
import com.eres.waiter.waiter.model.singelton.DataSingelton;
import com.eres.waiter.waiter.preferance.SettingPreferances;
import com.eres.waiter.waiter.retrofit.ApiClient;
import com.eres.waiter.waiter.retrofit.ApiInterface;
import com.eres.waiter.waiter.server.NotificationData;
import com.eres.waiter.waiter.viewpager.helper.ObservableCollection;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodListActivity extends AppCompatActivity {
    ArrayList<OrderItemsItem> items;
    private RecyclerView recyclerView;
    public static String id;
    private Disposable disposable;
    int orderId = 0;
    private String TAG = "MY_LOGG";
    ArrayList<Integer> integers;
    ArrayList<ProductsItem> productsItems;
    private ImageView imageView;
    private Button button;
    private Button sendCasheir;
    AdapterMyTableList adapterMyTableList;
    private boolean load = false;
    private TextView tableName;


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        disposable.dispose();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: 10.08.2018 shu yerga versiga ishlashim kerak  
        setContentView(R.layout.activity_food_lists);
        tableName = findViewById(R.id.table_name);
        tableName.setText("   "+SettingPreferances.preferances.getTableName()+" Стол - "+SettingPreferances.preferances.getHallName()+ " зал");

        listenerKitchen();

        EventBus.getDefault().register(this);
        id = getIntent().getExtras().getString("ID");
        items = new ArrayList<>();
        button = findViewById(R.id.add);
        button.setOnClickListener(v -> {
            DataSingelton.getMyOrders().clear();
            DataSingelton.getMyOrders().addAll(items);
            Toast.makeText(this, "" + DataSingelton.getMyOrders().size()
                    , Toast.LENGTH_SHORT).show();
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
        sendCasheir = findViewById(R.id.check);
        sendCasheir.setOnClickListener(v -> {
            ApiInterface apiInterface = ApiClient.getRetrofit(this).create(ApiInterface.class);
            Call<OrderData> call = apiInterface.sendCashier(orderId + "");
            call.enqueue(new Callback<OrderData>() {
                @Override
                public void onResponse(Call<OrderData> call, Response<OrderData> response) {
                    if (response.body() != null) Log.d(TAG, "onResponse: send Cashier");
                    Toast.makeText(FoodListActivity.this, "Send Cashier !", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onFailure(Call<OrderData> call, Throwable t) {
                    Log.d(TAG, "onFailure: Cashier");
                }
            });
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (adapterMyTableList != null) {
            loadDataNotif();

            Log.d(TAG, "onLoad 1");
        }
        if (!load) load = true;
        else loadData();

    }

    public void loadData() {
        disposable = App.getApp().getAllOrderData(id).subscribe(new Consumer<ArrayList<OrderData>>() {
            @Override
            public void accept(ArrayList<OrderData> orderData) throws Exception {
                items.clear();
                items.addAll(orderData.get(0).getOrderItems());
                SettingPreferances.preferances.setOrderId(orderData.get(0).getId());
                orderId = orderData.get(0).getId();
                loadArray();

            }
        });

    }

    public void loadDataNotif() {
//        ApiInterface apiInterface = ApiClient.getRetrofit(this).create(ApiInterface.class);
//        Call<ArrayList<OrderData>> dataCall=apiInterface.getMyTableListRetroofit(id);
//        dataCall.enqueue(new Callback<ArrayList<OrderData>>() {
//            @Override
//            public void onResponse(Call<ArrayList<OrderData>> call, Response<ArrayList<OrderData>> response) {
//                if (response.code()==200){
//                    if (response.body().get(0).getOrderItems()!=null){
//                        adapterMyTableList.updateList((ArrayList<OrderItemsItem>) response.body().get(0).getOrderItems());
//                    }
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<OrderData>> call, Throwable t) {
//                Log.d(TAG, "onFailure: Error  notif data ");
//            }
//        });

        disposable = App.getApp().getAllOrderData(id).subscribe(new Consumer<ArrayList<OrderData>>() {
            @Override
            public void accept(ArrayList<OrderData> orderData) throws Exception {
                adapterMyTableList.updateList((ArrayList<OrderItemsItem>) orderData.get(0).getOrderItems());

            }
        });
    }

    public void listenerKitchen() {
        ObservableCollection<NotificationData> notificationData = App.getMessage();
        notificationData.setCollectionChangeListener(new ObservableCollection.CollectionChangeListener() {
            @Override
            public void onCollectionChange(ObservableCollection.NotifyCollectionChangedAction action, Object obj, long position) {
                Log.d(TAG, "onLoadd 3");
                loadDataNotif();
            }
        });

    }

    private void loadArray() {
        productsItems = new ArrayList<>();
        loadAdapter();

    }

    @Subscribe
    public void EventMessageFood(EventMessageSendFood sendFood) {
        Log.d(TAG, "onLoad 2");
        if (sendFood.isSendFood()) loadDataNotif();
    }


    public void loadAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapterMyTableList = new AdapterMyTableList(items);
        recyclerView.setAdapter(adapterMyTableList);
        adapterMyTableList.setMyRemoveListiner(new AdapterMyTableList.MyRemoveListiner() {
            @Override
            public void onRemoveItem() {
                sendData();
            }
        });
    }

    public void sendData() {

        Log.d(TAG, "sendData: item size" + items.size());

        OrderData orderData = new OrderData(
                Integer.parseInt(id),
                "2018-07-17",
                items,
                "2018-07-17",
                "10",
                1,
                1,
                null,
                orderId,
                2);
        ApiInterface apiInterface = ApiClient.getRetrofit(this).create(ApiInterface.class);
        Call<OrderData> call = apiInterface.sendData(orderData);
        call.enqueue(new Callback<OrderData>() {
            @Override
            public void onResponse(Call<OrderData> call, Response<OrderData> response) {
                if (response.body() != null) {
                    Log.d(TAG, "onResponse:  yesss ");
//                    adapterMyTableList.updateList(items);

                }
            }

            @Override
            public void onFailure(Call<OrderData> call, Throwable t) {
                t.printStackTrace();

            }
        });

    }


}

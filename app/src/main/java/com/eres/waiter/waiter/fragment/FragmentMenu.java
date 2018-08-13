package com.eres.waiter.waiter.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.eres.waiter.waiter.R;
import com.eres.waiter.waiter.adapters.dialogadapters.AdapterList;
import com.eres.waiter.waiter.fragment.viewpager_fragment.FragmentMenuItem;
import com.eres.waiter.waiter.model.OrderData;
import com.eres.waiter.waiter.model.OrderItemsItem;
import com.eres.waiter.waiter.model.ProductsItem;
import com.eres.waiter.waiter.model.singelton.DataSingelton;
import com.eres.waiter.waiter.model.test.EventMessage;
import com.eres.waiter.waiter.model.test.EventMessageSendFood;
import com.eres.waiter.waiter.preferance.SettingPreferances;
import com.eres.waiter.waiter.retrofit.ApiClient;
import com.eres.waiter.waiter.retrofit.ApiInterface;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentMenu extends Fragment {
    private Dialog dialog;
    private ImageView list_btn;
    private String TAG = "M_LOG";
    private ImageView btn_back;
    private boolean state = false;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    onCreateFragment(0);
                    return true;
                case R.id.navigation_dashboard:
                    onCreateFragment(1);
                    return true;
                case R.id.navigation_notifications:
                    onCreateFragment(2);
                    return true;
            }
            return false;
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_my_menu, container, false);
        BottomNavigationView navigation = view.findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        onCreateFragment(0);
        state = false;
        btn_back = view.findViewById(R.id.back);
        btn_back.setOnClickListener(v -> {
            getActivity().onBackPressed();

        });
        list_btn = view.findViewById(R.id.list);
        list_btn.setOnClickListener(v -> {
            loadDialog();
        });
        return view;

    }

    public void onCreateFragment(int i) {
        FragmentMenuItem fragmentMenuItem = FragmentMenuItem.getInstance(i);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.cc, fragmentMenuItem);
        transaction.commit();
    }

    public void loadDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
        dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_list);
        Button button = dialog.findViewById(R.id.clr);
        RecyclerView recyclerView = dialog.findViewById(R.id.list_menu);
        AdapterList adapterList = new AdapterList();

        recyclerView.setAdapter(adapterList);
        TextView textView = dialog.findViewById(R.id.table);
        textView.setText(SettingPreferances.preferances.getTableId() + " - Стол");
        button.setOnClickListener(v -> {
            for (ProductsItem productsItem : DataSingelton.testSet) {
                productsItem.setCount(0);
            }

            adapterList.notifyDataSetChanged();
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                Log.d(TAG, "onDismiss: ");
                EventBus.getDefault().post(new EventMessage(true));

            }
        });
        final boolean[] a = {false};
        Button sendData = dialog.findViewById(R.id.yes_res);
        sendData.setOnClickListener(v -> {
                    if (a[0]) sendData.setEnabled(false);

                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("Оформление заказа");
                    builder.setMessage("Вы действительно хотите оформить заказ ?");
                    builder.setPositiveButton("ДА", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Log.d(TAG, "onClick: daaa ");
                            a[0] = true;
                            sendDataApi();

                        }
                    });
                    builder.setNegativeButton("НЕТ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.show();
                }
        );


        dialog.show();

    }

    public void sendDataApi() {
        int tableId = SettingPreferances.preferances.getTableId();
        ArrayList<OrderItemsItem> orderItemsItems = new ArrayList<>();
        for (ProductsItem item : DataSingelton.testSet) {

            orderItemsItems.add(new OrderItemsItem(
                    0,
                    item.getId(),
                    0,
                    2,
                    0,
                    1,
                    item.getCount(),
                    null,
                    0,
                    null));
        }
        int orderId = 0;
        if (DataSingelton.getDataMenus() != null) {
            state = true;
            orderId = SettingPreferances.preferances.getOrderId();
            orderItemsItems.addAll(DataSingelton.getMyOrders());
        }
        Log.d(TAG, "sendDataApi: " + orderItemsItems.size() + " set size = " + DataSingelton.testSet.size());
        OrderData orderData = new OrderData(
                tableId,
                "2018-07-17",
                orderItemsItems,
                "2018-07-17",
                "10",
                1,
                1,
                null,
                orderId,
                2);
        ApiInterface apiInterface = ApiClient.getRetrofit(getContext()).create(ApiInterface.class);
        Call<OrderData> call = apiInterface.sendData(orderData);
        call.enqueue(new Callback<OrderData>() {
            @Override
            public void onResponse(Call<OrderData> call, Response<OrderData> response) {
                if (response.body() != null) {
                    EventBus.getDefault().post(new EventMessageSendFood(state));
                    Log.d(TAG, "onResponse:  yesss ");
                    //   Toast.makeText(getContext(), "Send Data Kitchen", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OrderData> call, Throwable t) {
                t.printStackTrace();
            }
        });
        clearData();

    }

    private void clearData() {
        for (ProductsItem item : DataSingelton.testSet) {
            item.setCount(0);
        }
        DataSingelton.testSet.clear();

        DataSingelton.testSet.clear();
        dialog.dismiss();
        getActivity().finish();
    }
}

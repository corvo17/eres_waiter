package com.eres.waiter.waiter.model.singelton;

import android.content.Context;
import android.util.Log;

import com.eres.waiter.waiter.model.DataMenu;
import com.eres.waiter.waiter.model.OrderItemsItem;
import com.eres.waiter.waiter.model.ProductsItem;
import com.eres.waiter.waiter.model.test.DataAddList;
import com.eres.waiter.waiter.retrofit.ApiClient;
import com.eres.waiter.waiter.retrofit.ApiInterface;

import java.util.ArrayList;
import java.util.HashSet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataSingelton {
    public static DataSingelton singelton;
    private static Context mC;
    private static ArrayList<DataMenu> dataMenus;
    private static ArrayList<DataAddList> productsItems;
    private static boolean load = false;
    public static HashSet<ProductsItem> testSet;
    private static final String TAG = "DataSingelton";

    public static ArrayList<OrderItemsItem> getMyOrders() {
        return myOrders;
    }

    public static void setMyOrders(ArrayList<OrderItemsItem> myOrders) {
        DataSingelton.myOrders = myOrders;
    }

    private static ArrayList<OrderItemsItem> myOrders;

    public static ArrayList<DataAddList> getProducts() {
        return productsItems;
    }

    public static void setProducts(ArrayList<DataAddList> productsItems) {
        DataSingelton.productsItems = productsItems;
    }

    public static ArrayList<DataMenu> getDataMenus() {
        return isLoad() ? dataMenus : null;
    }

    public static boolean isLoad() {
        return load;
    }


    private DataSingelton() {
        dataMenus = new ArrayList<>();
        productsItems = new ArrayList<>();
        testSet = new HashSet<>();
        myOrders = new ArrayList<>();
    }

    public static DataSingelton getInstance(Context context) {
        if (singelton == null) {
            mC = context;
            singelton = new DataSingelton();
        }
        return singelton;
    }

    public void loadData() {
        ApiInterface apiInterface = ApiClient.getRetrofit(mC).create(ApiInterface.class);
        Call<ArrayList<DataMenu>> call = apiInterface.getMenuAll();
        call.enqueue(new Callback<ArrayList<DataMenu>>() {
            @Override
            public void onResponse(Call<ArrayList<DataMenu>> call, Response<ArrayList<DataMenu>> response) {
                if (response.body() != null) {
                    dataMenus.clear();
                    dataMenus.addAll(response.body());
                    load = true;
                    Log.d(TAG, TAG + dataMenus.size());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<DataMenu>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}

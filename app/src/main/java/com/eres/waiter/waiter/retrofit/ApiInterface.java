package com.eres.waiter.waiter.retrofit;

import com.eres.waiter.waiter.model.AddedDb;
import com.eres.waiter.waiter.model.ArmoredTables;
import com.eres.waiter.waiter.model.DataMenu;
import com.eres.waiter.waiter.model.EmptyTable;
import com.eres.waiter.waiter.model.IAmTables;
import com.eres.waiter.waiter.model.ObjectGetMe;
import com.eres.waiter.waiter.model.OrderData;
import com.eres.waiter.waiter.model.ProductsItem;
import com.eres.waiter.waiter.model.Response;
import com.eres.waiter.waiter.model.TablesItem;
import com.eres.waiter.waiter.model.test.TestClass;
import com.eres.waiter.waiter.viewpager.helper.ObservableCollection;
import com.eres.waiter.waiter.viewpager.model.Hall;

import java.util.ArrayList;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;


public interface ApiInterface {

    @GET("api/tables")
    Single<ObservableCollection<Hall>> getAllTables();

//    @GET("api/tables")
//    Call<ObservableCollection<Hall>> getAllTables();

    @GET("api/tables/mytables")
    Single<ArrayList<IAmTables>> getAllIAmTables();

    @GET("itisyou")
    Call<Response> getResponse();

    @GET("api/admin/getme")
    Call<ObjectGetMe> getMe();

    @GET("api/addmyphone")
    Call<AddedDb> setToken();

    @GET("api/tables")
    Call<ArrayList<EmptyTable>> getEmptyTable();

    @GET("api/tables/mytables")
    Call<ArrayList<IAmTables>> getIamTables();

    @GET("api/tables/armored")
    Call<ArrayList<ArmoredTables>> getArmoredTables();

    @GET("api/menu/all")
    Call<ArrayList<DataMenu>> getMenuAll();

    @GET("api/orders/ordersfortable")
    Single<ArrayList<OrderData>> getMyTableList(@Query("tableId") String tableId);

    @POST("api/orders/saveForWaiter")
    Call<OrderData> sendData(@Body OrderData data);

    @POST("api/menu/productlist")
    Call<ArrayList<ProductsItem>> getProduct(@Body ArrayList<Integer> ints);

    @POST("api/orders/sendtocashier")
    Call<OrderData> sendCashier(@Query("orderId") String orderId);

    @GET("api/tables/tablesByHallId")
    Call<ArrayList<TablesItem>> getTables(@Query("hallId") String hallId);
}
package com.eres.waiter.waiter.viewpager.service;

import com.eres.waiter.waiter.viewpager.helper.ObservableCollection;
import com.eres.waiter.waiter.viewpager.model.Hall;

import java.util.ArrayList;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface    IService {

    @GET("api/tables")
    Single<ObservableCollection<Hall>>  getAllTables();

}

package com.eres.waiter.waiter.retrofit;

import android.content.Context;

import com.eres.waiter.waiter.preferance.SettingPreferances;
import com.readystatesoftware.chuck.ChuckInterceptor;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClientNew {

    public static Retrofit getRetrofit(Context context, String baseUrl) {
        Retrofit retrofit;

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

}

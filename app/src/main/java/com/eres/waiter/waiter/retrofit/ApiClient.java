package com.eres.waiter.waiter.retrofit;

import android.content.Context;
import android.util.Log;

import com.eres.waiter.waiter.preferance.SettingPreferances;
import com.readystatesoftware.chuck.ChuckInterceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {
    private static final String BASE_URL = SettingPreferances.preferances.getUrl();
    private static Retrofit retrofit = null;


    public static Retrofit getRetrofit(Context context) {

        if (retrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
            OkHttpClient client = builder
                    .addInterceptor(new ChuckInterceptor(context)).addInterceptor(chain -> {
                        Request request = chain.request().newBuilder().addHeader("token", "354649084272372").build();
                        return chain.proceed(request);
                    }).build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

}

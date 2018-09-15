package com.eres.waiter.waiter.retrofit;

import android.content.Context;

import com.eres.waiter.waiter.preferance.SettingPreferances;
import com.readystatesoftware.chuck.ChuckInterceptor;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {
    private static final String BASE_URL = SettingPreferances.preferances.getUrl();
    private static Retrofit retrofit = null;

    public static Retrofit getRetrofit(Context context) {

        if (retrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
            OkHttpClient client = builder
                    .addInterceptor(new ChuckInterceptor(context)).addInterceptor(chain -> {
                        Request request = chain.request().newBuilder().addHeader("token", SettingPreferances.preferances.getIme()).addHeader("Authorization","bearer "+SettingPreferances.preferances.getAuthorization()).build();
                        return chain.proceed(request);
                    }).build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

}

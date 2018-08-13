package com.eres.waiter.waiter.activity;

import android.os.CountDownTimer;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.eres.waiter.waiter.R;
import com.eres.waiter.waiter.fragment.FirstFragment;
import com.eres.waiter.waiter.interfaces.MyTestInterface;
import com.eres.waiter.waiter.model.ObjectGetMe;
import com.eres.waiter.waiter.model.singelton.DataSingelton;
import com.eres.waiter.waiter.preferance.SettingPreferances;
import com.eres.waiter.waiter.retrofit.ApiClient;
import com.eres.waiter.waiter.retrofit.ApiInterface;
import com.eres.waiter.waiter.server.WebServer;

import java.io.IOException;

import fi.iki.elonen.NanoHTTPD;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MyTestInterface {
    private static final String TAG = "MainActivity";
    private int k = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SettingPreferances preferances = SettingPreferances.getSharedPreferance(this);
        preferances.setUrl("http://192.168.0.102:9000/");
        getMe();
        loadServer();

        FirstFragment firstFragment = new FirstFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, firstFragment);

        transaction.addToBackStack("first");
        transaction.commit();
        DataSingelton.getInstance(this);
    }

    private void getMe() {
        ApiInterface apiInterface = ApiClient.getRetrofit(this).create(ApiInterface.class);
        Call<ObjectGetMe> call = apiInterface.getMe();
        call.enqueue(new Callback<ObjectGetMe>() {
            @Override
            public void onResponse(Call<ObjectGetMe> call, Response<ObjectGetMe> response) {
                if (response.body() != null) {
                    Toast.makeText(MainActivity.this, "MyIP = " + response.body().getData().getUrl(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ObjectGetMe> call, Throwable t) {

            }
        });

    }

    public void loadServer() {

        WebServer webServer = new WebServer("192.168.0.118", 10000);
        webServer.setOnMessageListener(new WebServer.OnMessageListener() {
            @Override
            public void onMessage(NanoHTTPD.IHTTPSession session, String txt) {
                Log.d("MY_SERVER", "IP Adress = " + session.getRemoteIpAddress() + "URI =" + session.getUri());
            }
        });
        try {
            webServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {

        int a = getSupportFragmentManager().getBackStackEntryCount();

        if (a == 1) {
            k++;
            if (k == 2)
                finish();
            else {
                Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
                new CountDownTimer(30000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        k = 0;
                    }
                }.start();
            }
        } else super.onBackPressed();
        Log.d(TAG, "onBackPressed: " + a);
    }

    @Override
    public void send(String s) {
        Log.d("MY_LOG", "send: " + s);
    }
}

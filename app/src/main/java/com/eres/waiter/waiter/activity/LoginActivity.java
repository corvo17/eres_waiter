package com.eres.waiter.waiter.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.eres.waiter.waiter.R;
import com.eres.waiter.waiter.app.App;
import com.eres.waiter.waiter.model.AddedDb;
import com.eres.waiter.waiter.model.IAmTables;
import com.eres.waiter.waiter.model.ObjectGetMe;
import com.eres.waiter.waiter.model.Table;
import com.eres.waiter.waiter.model.TablesItem;
import com.eres.waiter.waiter.model.singelton.DataSingelton;
import com.eres.waiter.waiter.preferance.SettingPreferances;
import com.eres.waiter.waiter.retrofit.ApiClient;
import com.eres.waiter.waiter.retrofit.ApiClientNew;
import com.eres.waiter.waiter.retrofit.ApiInterface;
import com.eres.waiter.waiter.viewpager.helper.ObservableCollection;
import com.eres.waiter.waiter.viewpager.model.Hall;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.eres.waiter.waiter.model.singelton.DataSingelton.singelton;

public class LoginActivity extends AppCompatActivity {
    private ImageView button;
    private Disposable disposable;
    private boolean loadServer;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(LoginActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                if (grantResults.length > 0 && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(LoginActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if ((keyCode == KeyEvent.KEYCODE_HOME)) {
//            Toast.makeText(this, "You pressed the home button!",
//                    Toast.LENGTH_LONG).show();
//            return false;
//        }
//        return super.onKeyDown(keyCode, event);
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SettingPreferances preferances = SettingPreferances.getSharedPreferance(this);
        preferances.setHallPosition(0);
        itIsYouServer();
        ActivityCompat.requestPermissions(LoginActivity.this,
                new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_WIFI_STATE},
                1);
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(this.TELEPHONY_SERVICE);
        if (!(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED)) {
            Toast.makeText(this, "" + telephonyManager.getDeviceId(), Toast.LENGTH_SHORT).show();
            preferances.setIme(telephonyManager.getDeviceId() + "");
// TODO: 07.09.2018 preferenseni uchir  
        }
        button = findViewById(R.id.open);
        button.setOnClickListener(v -> {
            if (DataSingelton.getDataMenus().get(0).getCategories() != null)
                startActivity(new Intent(this, MainActivity.class));
            else {
                Toast.makeText(this, "Menejerga uchrashing !!!", Toast.LENGTH_SHORT).show();
                new CountDownTimer(30000, 2000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        if (DataSingelton.getDataMenus().get(0).getCategories() == null) {
                            loadAllData();
                        } else onFinish();
                    }

                    @Override
                    public void onFinish() {

                    }
                }.start();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null) {


            disposable.dispose();
        }
    }

    private void itIsYouServer() {
        ApiInterface apiInterface = ApiClientNew.getRetrofit(this, SettingPreferances.getSharedPreferance(this).getUrl()).create(ApiInterface.class);
        apiInterface.getResponse().enqueue(new Callback<com.eres.waiter.waiter.model.Response>() {
            @Override
            public void onResponse(Call<com.eres.waiter.waiter.model.Response> call, Response<com.eres.waiter.waiter.model.Response> response) {
                if (response.body() != null && response.body().getStatus().equals("itisme")) {
                    getMe();
                }
            }

            @Override
            public void onFailure(Call<com.eres.waiter.waiter.model.Response> call, Throwable t) {
                searchServer();
            }
        });
    }

    private void loadAllData() {
        DataSingelton dataSingelton = DataSingelton.getInstance(this);
        loadIAmTable();
        dataSingelton.loadArmoredTable();
        dataSingelton.loadData();
        loadHalls();
    }

    private void isServer(String ipS) {
        final String ip = "http://" + ipS + ":9000/";
        ApiInterface apiInterface = ApiClientNew.getRetrofit(this, ip).create(ApiInterface.class);
        Call<com.eres.waiter.waiter.model.Response> call = apiInterface.getResponse();
        call.enqueue(new Callback<com.eres.waiter.waiter.model.Response>() {
            @Override
            public void onResponse(Call<com.eres.waiter.waiter.model.Response> call, Response<com.eres.waiter.waiter.model.Response> response) {
                if (response.body() != null) {
                    if (response.body().getStatus().equals("itisme")) {

                        SettingPreferances.preferances.setUrl(ip);
                        Toast.makeText(LoginActivity.this, "SERVER : " + ip, Toast.LENGTH_SHORT).show();
                        getMe();
                        loadServer = true;
                    }
                }
            }

            @Override
            public void onFailure(Call<com.eres.waiter.waiter.model.Response> call, Throwable t) {

            }
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void getMe() {

        App.getApp().updateData();
        ApiInterface apiInterface = ApiClient.getRetrofit(this).create(ApiInterface.class);
        Call<ObjectGetMe> call = apiInterface.getMe();
        call.enqueue(new Callback<ObjectGetMe>() {
            @Override
            public void onResponse(Call<ObjectGetMe> call, Response<ObjectGetMe> response) {
                if (response.body() != null) {
                    if (!response.body().getStatus().equals("ok")) {
                        Call<AddedDb> dbCall = apiInterface.setToken();
                        dbCall.enqueue(new Callback<AddedDb>() {
                            @Override
                            public void onResponse(Call<AddedDb> call, Response<AddedDb> response) {
                                if (response.body() != null)
                                    Toast.makeText(LoginActivity.this, "Registration Successfull", Toast.LENGTH_SHORT).show();

                                loadAllData();
                            }

                            @Override
                            public void onFailure(Call<AddedDb> call, Throwable t) {

                            }
                        });

                    } else
                        loadAllData();
//                    Toast.makeText(LoginActivity.this, "MyIP = " + response.body().getData().getUrl(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ObjectGetMe> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "You are phone NO !!!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void loadHalls() {
        disposable = App.getApp().getAllTables().subscribe(new Consumer<ObservableCollection<Hall>>() {
            @Override
            public void accept(ObservableCollection<Hall> halls) throws Exception {
                singelton.getHalls().clear();
                singelton.setHalls(halls);

            }
        });
    }

    public void searchServer() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                WifiManager wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
                String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());

                NetworkInterface iFace = null;

                try {
                    iFace = NetworkInterface
                            .getByInetAddress(InetAddress.getByName(ip));
                } catch (SocketException e) {
                    e.printStackTrace();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }

                for (int i = 99; i <= 200; i++) {
                    if (loadServer) {
                        break;
                    }
                    // build the next IP address
                    String addr = ip;
                    addr = addr.substring(0, addr.lastIndexOf('.') + 1) + i;
                    InetAddress pingAddr = null;
                    try {
                        pingAddr = InetAddress.getByName(addr);
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }

                    isServer(addr);

                }

            }
        }).start();
    }

    private void loadIAmTable() {
        disposable = App.getApp().getAllIAmTables().subscribe(new Consumer<ArrayList<IAmTables>>() {
            @Override
            public void accept(ArrayList<IAmTables> iAmTables) throws Exception {
                DataSingelton.getiAmTables().clear();
                DataSingelton.getiAmTables().addAll(iAmTables);
            }
        });
    }
}

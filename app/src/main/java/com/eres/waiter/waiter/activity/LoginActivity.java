package com.eres.waiter.waiter.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.eres.waiter.waiter.R;
import com.eres.waiter.waiter.app.App;
import com.eres.waiter.waiter.model.AccountToken;
import com.eres.waiter.waiter.model.AddedDb;
import com.eres.waiter.waiter.model.IAmTables;
import com.eres.waiter.waiter.model.ObjectGetMe;
import com.eres.waiter.waiter.model.singelton.DataSingelton;
import com.eres.waiter.waiter.preferance.SettingPreferances;
import com.eres.waiter.waiter.retrofit.ApiClientNew;
import com.eres.waiter.waiter.retrofit.ApiInterface;
import com.eres.waiter.waiter.viewpager.helper.ObservableCollection;
import com.eres.waiter.waiter.viewpager.model.Hall;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import dmax.dialog.SpotsDialog;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.eres.waiter.waiter.model.singelton.DataSingelton.singelton;

public class LoginActivity extends AppCompatActivity {
    private Button button;
    private Disposable disposable;
    private boolean loadServer;
    private static final String TAG = "LoginActivity";
    private Spinner spinner;
    private EditText pass;
    private ArrayList<String> spinnerTexts;
    private boolean isSpinner = false;
    private boolean reg = false;
    private SpotsDialog.Builder dialog;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dialog = new SpotsDialog.Builder();
        pass = findViewById(R.id.pass);
        spinner = findViewById(R.id.spinner);
        spinnerTexts = new ArrayList<>();
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
        button.setOnClickListener(this::onClickBUtton);
        pass.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                Log.d(TAG, "onEditorAction: ");
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    pressButton();
                    return true;
                }
                return false;
            }
        });
    }

    public void onClickBUtton(View view) {

        Log.d(TAG, "onPressedButton: ");
        pressButton();
    }

    private void pressButton() {
        if (pass.getText().toString().equals("")) {
            pass.setError("No Login");

        } else {
            ApiInterface apiInterface = ApiClientNew.getRetrofit(this, SettingPreferances.getSharedPreferance(this).getUrl()).create(ApiInterface.class);
            loadAutherization(apiInterface);

        }

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
                    Log.d(TAG, "onResponse:11 " + SettingPreferances.preferances.getUrl());
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
        loadHalls();
        loadIAmTable();
        dataSingelton.loadArmoredTable();
        dataSingelton.loadData();
//        dataSingelton.loadITable();

//        dataSingelton.loadAllHall();

    }

    private void isServer(String ipS) {
        final String ip = "http://" + ipS + ":9000/";
        Log.d(TAG, "isServer: " + ip);
        ApiInterface apiInterface = ApiClientNew.getRetrofit(this, ip).create(ApiInterface.class);
        Call<com.eres.waiter.waiter.model.Response> call = apiInterface.getResponse();
        call.enqueue(new Callback<com.eres.waiter.waiter.model.Response>() {
            @Override
            public void onResponse(Call<com.eres.waiter.waiter.model.Response> call, Response<com.eres.waiter.waiter.model.Response> response) {
                if (response.body() != null) {
                    Log.d(TAG, "onResponse: ");
                    if (response.body().getStatus().equals("itisme")) {
                        dialog.build().dismiss();
                        Log.d(TAG, "onResponse: itishme");
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
//
//        App.getApp().updateData();
        ApiInterface apiInterface = ApiClientNew.getRetrofit(this, SettingPreferances.preferances.getUrl()).create(ApiInterface.class);
        Call<ObjectGetMe> call = apiInterface.getMe(SettingPreferances.preferances.getIme());
        call.enqueue(new Callback<ObjectGetMe>() {
            @Override
            public void onResponse(Call<ObjectGetMe> call, Response<ObjectGetMe> response) {
                if (response.code() == 500) {
                    Log.d(TAG, "add  phone: ");
                    Call<AddedDb> dbCall = apiInterface.setToken(SettingPreferances.preferances.getIme());
                    dbCall.enqueue(new Callback<AddedDb>() {
                        @Override
                        public void onResponse(Call<AddedDb> call, Response<AddedDb> response) {
                            if (response.body() != null && reg) {
                                Toast.makeText(LoginActivity.this, "Registration Successfull", Toast.LENGTH_SHORT).show();
                                reg = true;
                                button.setClickable(true);
                            }
                        }

                        @Override
                        public void onFailure(Call<AddedDb> call, Throwable t) {
                            Log.d(TAG, "onFailure: ");
                            t.printStackTrace();
                        }
                    });
                    isGetMe();
                }
                if (response.body() != null) {
                    loadSpinner(response.body().getUsers());


//                    loadAllData();
//                    Toast.makeText(LoginActivity.this, "MyIP = " + response.body().getData().getUrl(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ObjectGetMe> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "You are phone NO !!!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void isGetMe() {

        new CountDownTimer(400000, 10000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.d(TAG, "onTick: " + millisUntilFinished);

                if (isSpinner) {
                    onFinish();
                }
                getMe();
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    private void loadSpinner(ArrayList<String> strings) {
        spinnerTexts.clear();
        spinnerTexts.add("Пользователь");
        spinnerTexts.addAll(strings);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(LoginActivity.this, R.layout.item_spinner, spinnerTexts);
        spinner.setAdapter(adapter);
        isSpinner = true;

    }

    private void loadAutherization(ApiInterface apiInterface) {
        String log = spinner.getSelectedItem().toString();

        Log.d(TAG, "loadAutherization: " + log + "pass = " + pass.getText().toString());

        Call<AccountToken> call = apiInterface.getAutzToken(SettingPreferances.preferances.getIme(), log, pass.getText().toString().trim());
        call.enqueue(new Callback<AccountToken>() {
            @Override
            public void onResponse(Call<AccountToken> call, Response<AccountToken> response) {
                if (response.body() != null && response.body().getAccess_token() != null) {
                    Log.d(TAG, "onResponse: asdasd");
                    SettingPreferances.preferances.setAuthorization(response.body().getAccess_token());
                    App.getApp().updateData();
                    loadAllData();
                }
                if (response.code() == 400) {
                    Toast.makeText(LoginActivity.this, "Login yoki Parol xato  !!!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AccountToken> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });

    }

    public void loadHalls() {
        disposable = App.getApp().getAllTables().subscribe(new Consumer<ObservableCollection<Hall>>() {
            @Override
            public void accept(ObservableCollection<Hall> halls) throws Exception {
                singelton.getHalls().clear();
                singelton.setHalls(halls);
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });


    }

    public void searchServer() {


        dialog.setContext(this);
        dialog.setMessage("Search Server ...");
        dialog.build().show();
        new Thread(new Runnable() {
            @Override
            public void run() {

                WifiManager wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);

                String ip = "192.168.0.100";
                // TODO: 12.09.2018 String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());

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

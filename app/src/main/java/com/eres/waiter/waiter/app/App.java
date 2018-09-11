package com.eres.waiter.waiter.app;

import android.app.Application;
import android.util.Log;

import com.eres.waiter.waiter.model.IAmTables;
import com.eres.waiter.waiter.model.OrderData;
import com.eres.waiter.waiter.model.TablesItem;
import com.eres.waiter.waiter.model.enums.NotificationTypees;
import com.eres.waiter.waiter.model.events.EventIAmTableChange;
import com.eres.waiter.waiter.model.events.EventMessageRemoveTable;
import com.eres.waiter.waiter.model.singelton.DataSingelton;
import com.eres.waiter.waiter.model.test.NotificationEventAlarm;
import com.eres.waiter.waiter.retrofit.ApiClient;
import com.eres.waiter.waiter.retrofit.ApiInterface;
import com.eres.waiter.waiter.server.NotificationData;
import com.eres.waiter.waiter.viewpager.helper.ObservableCollection;
import com.eres.waiter.waiter.viewpager.model.Hall;
import com.eres.waiter.waiter.viewpager.service.WebServer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class App extends Application {
    public static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public static App getApp;
    ApiInterface apiInterface;
    public static WebServer webServer = null;
    private static App app = null;
    private ObservableCollection<Hall> tables;
    public static ObservableCollection<NotificationData> message;
    private DataSingelton singelton;

    public ObservableCollection<Hall> getTables() {
        return tables;
    }

    public void setTables(ObservableCollection<Hall> tables) {
        this.tables = tables;
    }

    public static App getApp() {
        if (app != null)
            return app;
        app = new App();
        app.onCreate();
        return App.app;
    }


    public static ObservableCollection<NotificationData> getMessage() {
        return message;
    }

    public static void setMessage() {
        App.message = message;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        webServer = new WebServer(10000);
        message = new ObservableCollection<>();
        tables = new ObservableCollection<>();
        app = this;
        try {
            webServer.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//
//    public void removeMessage(int id, int type) {
//        int q = type;
//        for (NotificationData data : message) {
//            if (data.getTableId() == id && q == data.getNotificationTypeId()) {
//                message.remove(data);
//            }
//        }
//        Log.d("TEST_EVENT1", "removeMessage: " + message.size());
//    }

    public void updateData() {
        apiInterface = ApiClient.getRetrofit(getApplicationContext()).create(ApiInterface.class);
        singelton = DataSingelton.getInstance(this);

    }

    public Single<ObservableCollection<Hall>> getAllTables() {
        return apiInterface.getAllTables()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    public Single<ArrayList<IAmTables>> getAllIAmTables() {
        return apiInterface.getAllIAmTables().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<ArrayList<OrderData>> getAllOrderData(String id) {
        return apiInterface.getMyTableList(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public void loadLocalServer(Collection<? extends NotificationData> noteAll) {
        message.addAll(noteAll);
        Log.d("TAG_R", "loadLocalServer: " + App.getMessage().size());
        Log.d("TAG_R", "onCollectionChange: ");
        int size = noteAll.size();
        for (NotificationData note : noteAll) {


            int a = note.getNotificationTypeId();
            if (a == NotificationTypees.MenuChanged.ordinal()) {
                DataSingelton.singelton.loadData();
                noteAll.remove(note);
            }
            if (a == NotificationTypees.OrderAcceptedInKitchen.ordinal() || NotificationTypees.OrderProblemsInKithen.ordinal() == a || NotificationTypees.CompleteKitchen.ordinal() == a) {

                Log.d("TEST_EVENT1", "Strart note event : " + note.getTableId() + " type =  " + a);
                checkEventIAmTable(note.getTableId(), a);
                App.message.remove(note);

                // Qaysi afitsiant notifay qsa ham event ketaveradi !!!!
            }

            if (a == 15) {
                first:
                for (Hall hall : DataSingelton.singelton.getHalls()) {
                    for (TablesItem table : hall.getTables()) {
                        if (table.getId() == note.getTableId()) {
                            hall.getTables().remove(table);
                            message.remove(note);
                            EventBus.getDefault().post(new EventMessageRemoveTable(""));
                            break first;
                        }
                    }
                }

            }
        }


    }

    public void checkEventIAmTable(int id, int event) {
        DataSingelton.eventNotifAlarm.put("" + id, event);
        EventBus.getDefault().post(new EventIAmTableChange(true));
    }
}


package com.eres.waiter.waiter.viewpager.model;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.eres.waiter.waiter.app.App;

import java.util.ArrayList;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class HallLiveData extends LiveData<ArrayList<Hall>> {
    public Disposable disposable;
    private Context context;
    private static HallLiveData instance;

    public static HallLiveData getInstance(Context context){
        if (instance==null){
            instance = new HallLiveData(context.getApplicationContext());
        }
        return instance;
    }

    private HallLiveData(Context context) {
        this.context = context;
    }
    private void getData()
    {
        disposable =
                App.getApp().getAllTables()
                        .subscribe(new Consumer<ArrayList<Hall>>() {
                            @Override
                            public void accept(ArrayList<Hall> _halls) throws Exception {

                                setValue(_halls);
                            }
                        });

    }
    @Override
    protected void onActive() {
        getData();
    }

    @Override
    protected void onInactive() {

        if(disposable!=null)
            disposable.dispose();
    }
}

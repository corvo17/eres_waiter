package com.eres.waiter.waiter.viewpager.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.eres.waiter.waiter.app.App;
import com.eres.waiter.waiter.model.singelton.DataSingelton;
import com.eres.waiter.waiter.viewpager.helper.ObservableCollection;
import com.eres.waiter.waiter.viewpager.model.Hall;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class AllTablesViewModel extends ViewModel {
    private MutableLiveData<ObservableCollection<Hall>> data;

    public LiveData<ObservableCollection<Hall>> getData() {
        if (data == null) {
            data = new MutableLiveData<>();
            loadData();
        }
        return data;
    }

    private void loadData() {
        data.setValue(DataSingelton.singelton.getHalls());
    }

}

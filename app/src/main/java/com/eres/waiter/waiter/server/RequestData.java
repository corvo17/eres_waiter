package com.eres.waiter.waiter.server;

import com.eres.waiter.waiter.viewpager.helper.ObservableCollection;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RequestData {
    @SerializedName("Notes")
    public ObservableCollection<NotificationData> data;

    public ObservableCollection<NotificationData> getData() {
        return data;
    }

    public void setData(ObservableCollection<NotificationData> data) {
        this.data = data;
    }
}

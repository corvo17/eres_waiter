package com.eres.waiter.waiter.viewpager.service;

import com.eres.waiter.waiter.server.NotificationData;
import com.eres.waiter.waiter.viewpager.helper.ObservableCollection;

public class Notes {
    private ObservableCollection<NotificationData> Notes=new ObservableCollection<>();

    public ObservableCollection<NotificationData> getNotes() {
        return Notes;
    }

    public void setNotes(ObservableCollection<NotificationData> notes) {
        Notes = notes;
    }
}


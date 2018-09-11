package com.eres.waiter.waiter.viewpager.service;


import android.util.Log;

import com.eres.waiter.waiter.app.App;
import com.eres.waiter.waiter.server.NotificationData;
import com.eres.waiter.waiter.viewpager.helper.ObservableCollection;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import fi.iki.elonen.NanoHTTPD;
public class WebServer extends NanoHTTPD {
    //    public static ObservableCollection<ERESNotification> Messages=
//            new ObservableCollection<>();

    public static ObservableCollection<NotificationData> localM;

    public WebServer(int port) {
        super(port);
        init();
    }

    public void init() {
        localM = new ObservableCollection<>();

    }

    public WebServer(String hostname, int port) {
        super(hostname, port);
        init();

    }

    @Override
    public Response serve(IHTTPSession session) {
        Map<String, String> files = new HashMap<String, String>();
        try {
            session.parseBody(files);
        } catch (IOException ioe) {
            return newFixedLengthResponse("bad request");


        } catch (ResponseException re) {
            return newFixedLengthResponse("bad request");

        }
        if (files.isEmpty())
            return newFixedLengthResponse("bad request");

        String bodyJson = "";// session.getQueryParameterString();
        for (Map.Entry<String, String> entry : files.entrySet()) {
            bodyJson = entry.getValue();
            break;
        }
        if (bodyJson == "")
            return newFixedLengthResponse("bad request");
        try {

            //localM = App.gson.fromJson(bodyJson, new TypeToken<ObservableCollection<ERESNotification>>(){}.getType());

            localM.addAll(App.gson.fromJson(bodyJson, Notes.class).getNotes());
            Log.d("TAG_R", "serve: ");
            App app =App.getApp();
            app.loadLocalServer(App.gson.fromJson(bodyJson, Notes.class).getNotes());

        } catch (JsonSyntaxException ee) {
            return newFixedLengthResponse("bad request");
        }

        return newFixedLengthResponse(new Response.IStatus() {
            @Override
            public String getDescription() {
                return "200";
            }

            @Override
            public int getRequestStatus() {
                return 200;
            }
        }, "application/json", "{ret:200}");
    }

//    public static void removeNote(ERESNotification note) {
//        Messages.remove(note);
//    }
}

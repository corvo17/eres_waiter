package com.eres.waiter.waiter.server;


import android.util.Log;

import com.eres.waiter.waiter.model.TablesItem;
import com.eres.waiter.waiter.model.enums.NotificationTypees;
import com.eres.waiter.waiter.model.events.EventMessage;
import com.eres.waiter.waiter.model.events.EventMessageInKitchen;
import com.eres.waiter.waiter.model.events.EventTable;
import com.eres.waiter.waiter.model.events.EventTableAdd;
import com.eres.waiter.waiter.model.singelton.DataSingelton;
import com.eres.waiter.waiter.preferance.SettingPreferances;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import fi.iki.elonen.NanoHTTPD;

public class WebServer extends NanoHTTPD {
    private String TAG = "MY_LOG";
    private OnMessageListener onMessageListener;
    private Gson gson;
    private Map<String, String> files;

    public WebServer() {
        super(10000);

        gson = new Gson();
        files = new HashMap<String, String>();

    }

    public WebServer(String hostname, int port) {
        super(hostname, port);
    }

    @Override
    public Response serve(IHTTPSession session) {
        String s = SettingPreferances.preferances.getUrl();
        s = s.substring(0, s.indexOf(":"));
        Log.d(TAG, "serve: " + s);
//        if (session.getRemoteIpAddress().equals(s)) {

            try {
                loadNotifacation(session);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ResponseException e) {
                e.printStackTrace();
            }
        return newFixedLengthResponse(
                Response.Status.OK
                , "html/text", "Ok");
    }



    public interface OnMessageListener {
        void onMessage(NanoHTTPD.IHTTPSession session, String txt);
    }

    public void loadNotifacation(NanoHTTPD.IHTTPSession session) throws IOException, ResponseException {
        Log.d(TAG, "loadNotifacation: ");
        session.parseBody(files);

        for (Map.Entry<String, String> entry : files.entrySet()) {
            String bodyJson = entry.getValue();
            Log.d(TAG, "loadNotifacation: "+bodyJson);
            RequestData data = gson.fromJson(bodyJson, RequestData.class);
            int i = 0;
            if (data.getData() != null) {
                i = data.getData().size();
                for (int i1 = 0; i1 < data.getData().size(); i1++) {
                }
            }
            Log.d("M_SS", "loadNotifacation: " + i + " idd " + entry.getValue());
        }
    }


}

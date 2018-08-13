package com.eres.waiter.waiter.server;


import fi.iki.elonen.NanoHTTPD;

public class WebServer extends NanoHTTPD {
    private OnMessageListener onMessageListener;

    public WebServer() {
        super(10000);

    }

    public WebServer(String hostname,int port) {
        super(hostname, port);
    }

    @Override
    public Response serve(IHTTPSession session) {
        if(onMessageListener!=null)
            onMessageListener.onMessage(session,"1");
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(onMessageListener!=null)
            onMessageListener.onMessage(session,"2");

        return newFixedLengthResponse(
                Response.Status.OK
                ,"html/text","Ok");
    }


    public void setOnMessageListener(OnMessageListener onMessageListener) {
        this.onMessageListener = onMessageListener;
    }

    public interface OnMessageListener {
        void onMessage(NanoHTTPD.IHTTPSession session, String txt);
    }


}
package com.eres.waiter.waiter.preferance;

import android.content.Context;
import android.content.SharedPreferences;

public class SettingPreferances {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    public static SettingPreferances preferances;

    private SettingPreferances(Context context) {
        pref = context.getSharedPreferences("First", Context.MODE_PRIVATE);
    }

    public static SettingPreferances getSharedPreferance(Context context) {
        if (preferances == null) {
            preferances = new SettingPreferances(context);
        }
        return preferances;
    }

    public void setTableId(int id) {
        editor = pref.edit();
        editor.putInt("ID", id);
        editor.apply();

    }

    public int getTableId() {
        return pref.getInt("ID", 0);
    }

    public void setOrderId(int id) {
        editor = pref.edit();
        editor.putInt("OID", id);
        editor.apply();

    }

    public int getOrderId() {
        return pref.getInt("OID", 0);
    }

    public void setUrl(String url) {
        editor = pref.edit();
        editor.putString("URL", url);
        editor.apply();
    }

    public String getUrl() {
        return pref.getString("URL", "127.0.0.1");
    }

}

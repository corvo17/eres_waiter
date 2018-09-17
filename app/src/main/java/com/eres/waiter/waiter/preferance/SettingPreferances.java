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
        return pref.getString("URL", "http://192.168.0.102:9000/");
    }

    public void setIme(String ime) {
        editor = pref.edit();
        editor.putString("IME", ime);
        editor.apply();
    }

    public String getIme() {
        return pref.getString("IME", "356316064051832");
    }

    public void setHallId(int id) {
        editor = pref.edit();
        editor.putInt("HALLID", id);
        editor.apply();
    }

    public int getHallId() {
        return pref.getInt("HALLID", 1);
    }

    public void setHallPosition(int id) {
        editor = pref.edit();
        editor.putInt("HALLP", id);
        editor.apply();
    }

    public int getHallPosition() {
        return pref.getInt("HALLP", 0);
    }

    public void setAuthorization(String authorization) {
        editor = pref.edit();
        editor.putString("AUTH", authorization);
        editor.apply();
    }

    public String getAuthorization() {
        return pref.getString("AUTH", "0");
    }

    public void setTableName(String name) {
        editor = pref.edit();
        editor.putString("T_NAME", name);
        editor.apply();
    }

    public void setHallName(String hallName) {
        editor = pref.edit();
        editor.putString("HALL_NAME", hallName);
        editor.apply();
    }

    public String getHallName() {
        return pref.getString("HALL_NAME", "Hall");
    }

    public String getTableName() {
        return pref.getString("T_NAME", "0");
    }
}

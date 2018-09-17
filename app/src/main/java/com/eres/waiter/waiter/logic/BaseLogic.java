package com.eres.waiter.waiter.logic;

import com.eres.waiter.waiter.model.singelton.DataSingelton;
import com.eres.waiter.waiter.preferance.SettingPreferances;
import com.eres.waiter.waiter.viewpager.model.Hall;

public class BaseLogic {


    public String textSubString(String s, int n, boolean more) {
        return more ? s.substring(0, n) + "..."
                : s.substring(0, n);
    }

    public void seveHallName(int id) {
        for (Hall hall : DataSingelton.singelton.getHalls()) {
            if (hall.getId() == id) {
                SettingPreferances.preferances.setHallName(hall.getName());
                break;
            }
        }


    }
}

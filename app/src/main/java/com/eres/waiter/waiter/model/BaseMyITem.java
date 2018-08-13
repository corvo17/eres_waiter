package com.eres.waiter.waiter.model;

import com.eres.waiter.waiter.model.singelton.DataSingelton;
import com.eres.waiter.waiter.model.test.DataAddList;

import java.util.ArrayList;

class BaseMyITem {
    public int count = 0;

    public int getCount() {
        return count;
    }


    public void setCount(int count) {
        this.count = count;
    }

    public void addCount() {
        this.count++;
    }

    public void minusCount() {
        this.count--;
    }
}

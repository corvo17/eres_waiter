package com.eres.waiter.waiter.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.eres.waiter.waiter.R;
import com.eres.waiter.waiter.fragment.FragmentMenu;
import com.eres.waiter.waiter.model.events.EventOnBack;

import org.greenrobot.eventbus.EventBus;

public class MyMenuActivity extends AppCompatActivity {
    private int k = 0;
    private static final String TAG = "MyMenuActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_contaner);
        FragmentMenu fragmentMenu = new FragmentMenu();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container1, fragmentMenu);
        transaction.addToBackStack("ss");
        transaction.commit();

    }

    @Override
    public void onBackPressed() {
        int a = getSupportFragmentManager().getBackStackEntryCount();
        Log.d("MY_BACK", "onBackPressed: " + a);
        if (a == 2) {
            EventBus.getDefault().post(new EventOnBack(true));
        }
        if (a == 1) {
            finish();
        } else super.onBackPressed();

    }
}

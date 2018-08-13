package com.eres.waiter.waiter.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.eres.waiter.waiter.R;
import com.eres.waiter.waiter.fragment.viewpager_fragment.FragmentEmpty;
import com.eres.waiter.waiter.fragment.viewpager_fragment.FragmentITable;
import com.eres.waiter.waiter.fragment.viewpager_fragment.FragmentReservation;
import com.eres.waiter.waiter.interfaces.MyTestInterface;
import com.eres.waiter.waiter.model.Response;
import com.eres.waiter.waiter.model.test.EventMessage;
import com.eres.waiter.waiter.retrofit.ApiClient;
import com.eres.waiter.waiter.retrofit.ApiInterface;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import retrofit2.Call;
import retrofit2.Callback;

public class FirstFragment extends Fragment {
    private String TAG = "APP_LOGG";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    onLoadView(0);

                    return true;
                case R.id.navigation_dashboard:
                    onLoadView(1);
                    return true;
                case R.id.navigation_notifications:
                    onLoadView(2);
                    return true;
            }
            return false;
        }
    };

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_layout, container, false);

        EventBus.getDefault().register(this);
        loadDataApi();
        Fragment fragment = getMyItem(0);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.cont, fragment);
        transaction.commit();
        BottomNavigationView navigation = view.findViewById(R.id.navigation1);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        return view;
    }


    @Subscribe
    public void MessageEvent(EventMessage message) {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public Fragment getMyItem(int position) {
        switch (position) {
            case 0:
                return new FragmentEmpty();
            case 1:
                return new FragmentITable();
            case 2:
                return new FragmentReservation();

        }

        return null;
    }

    private void onLoadView(int i) {

        Fragment fragment = getMyItem(i);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.cont, fragment);
        transaction.commit();
    }


    public void loadDataApi() {
        ApiInterface apiInterface = ApiClient.getRetrofit(getContext()).create(ApiInterface.class);
        Call<Response> call = apiInterface.getResponse();
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                // TODO: 04.08.2018 responsni tekshirish
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });
    }

}

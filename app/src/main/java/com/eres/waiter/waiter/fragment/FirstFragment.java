package com.eres.waiter.waiter.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.eres.waiter.waiter.R;
import com.eres.waiter.waiter.fragment.viewpager_fragment.FragmentEmpty;
import com.eres.waiter.waiter.fragment.viewpager_fragment.FragmentITable;
import com.eres.waiter.waiter.fragment.viewpager_fragment.FragmentReservation;
import com.eres.waiter.waiter.model.Response;
import com.eres.waiter.waiter.model.events.EventIAmTableChange;
import com.eres.waiter.waiter.model.events.EventMessage;
import com.eres.waiter.waiter.model.events.EventOnBack;
import com.eres.waiter.waiter.retrofit.ApiClient;
import com.eres.waiter.waiter.retrofit.ApiInterface;
import com.eres.waiter.waiter.viewpager.fragment.AllTablesFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import retrofit2.Call;
import retrofit2.Callback;

public class FirstFragment extends Fragment {
    private String TAG = "APP_LOGG";
    private ImageView notifIcon;
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_layout, container, false);
        EventBus.getDefault().register(this);
        notifIcon = view.findViewById(R.id.notifIAmTable);
        ImageView userInfo = view.findViewById(R.id.user);

        userInfo.setOnClickListener(v -> {
            // TODO: 14.08.2018  

        });
        loadDataApi();
        BottomNavigationView navigation = view.findViewById(R.id.navigation1);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Fragment fragment = getMyItem(0);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.cont, fragment);
        transaction.commit();
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public Fragment getMyItem(int position) {
        switch (position) {
            case 0: {
                return new AllTablesFragment();
            }
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

    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    public void EventIAmTableChange(EventIAmTableChange eventIAmTableChange) {

        Log.d("TEST_EVENT", "EventIAmTableChange: ");
        if (eventIAmTableChange.isType()) {
            notifIcon.setVisibility(View.VISIBLE);
        } else notifIcon.setVisibility(View.GONE);
    }

}

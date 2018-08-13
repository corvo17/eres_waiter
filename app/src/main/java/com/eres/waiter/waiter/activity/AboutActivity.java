package com.eres.waiter.waiter.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.eres.waiter.waiter.R;
import com.eres.waiter.waiter.preferance.SettingPreferances;

public class AboutActivity extends AppCompatActivity {
    private TextView name;
    private TextView number;
    private TextView date;
    private TextView tableName;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.armored_table_about);
        Bundle bundle = getIntent().getExtras();
        button = findViewById(R.id.openList);
        name = findViewById(R.id.name);
        number = findViewById(R.id.phone);
        date = findViewById(R.id.date);
        tableName = findViewById(R.id.table_number);

        name.setText(bundle.getString("CLIENT_NAME"));
        number.setText(bundle.getString("CLIENT_NUMBER"));
        tableName.setText(bundle.getString("TABLE_NAME"));
        button.setOnClickListener(v -> {
            SettingPreferances.getSharedPreferance(null).setTableId(bundle.getInt("ID"));
// TODO: 09.08.2018 share praferance ga tableId ni berish kerak
            this.startActivity(new Intent(this, MyMenuActivity.class));
            this.finish();
        });


    }
}

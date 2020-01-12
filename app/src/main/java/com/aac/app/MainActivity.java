package com.aac.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.aac.app.livedata.LiveDataActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv_livedata;
    TextView tv_lifecycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        tv_lifecycle = findViewById(R.id.tv_lifecycle);
        tv_livedata = findViewById(R.id.tv_livedata);
        tv_livedata.setOnClickListener(this);
        tv_lifecycle.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_livedata) {
            startActivity(new Intent(MainActivity.this, LiveDataActivity.class));
        } else if (v.getId() == R.id.tv_lifecycle) {
            startActivity(new Intent(MainActivity.this, LifeCycleActivity.class));
        }
    }



}

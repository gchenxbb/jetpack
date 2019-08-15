package com.livedata.app;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_top_android);

        Fragment fragment = new TopDataFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_top, fragment);
        fragmentTransaction.commit();

    }

    public <T extends ViewModel> T get(Class<T> clazz) {
        return getViewModelProvider().get(clazz);
    }


    private ViewModelProvider getViewModelProvider() {
        return ViewModelProviders.of(this);
    }
}

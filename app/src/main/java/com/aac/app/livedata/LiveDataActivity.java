package com.aac.app.livedata;

import android.os.Bundle;

import com.aac.app.R;
import com.aac.app.business.BusinessFragmentV;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class LiveDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_top_android);

        Fragment fragment = new BusinessFragmentV();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_top, fragment);
        fragmentTransaction.commit();

    }
}

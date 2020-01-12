package com.aac.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

public class LifeCycleActivity extends AppCompatActivity {
    private final String TAG = "LifeCycleActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lifecycle);

        getLifecycle().addObserver(new LifecycleEventObserver() {
            @Override
            public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
                Log.i(TAG, "onStateChanged--->" + event.name());

            }
        });

        getLifecycle().addObserver(new LifecycleObserver() {

            @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
            public void onResume() {
                Log.i(TAG, "LifecycleObserver--->onResume");
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
            public void onPause() {
                Log.i(TAG, "LifecycleObserver--->onPause");
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
            public void onStop() {
                Log.i(TAG, "LifecycleObserver--->onStop");
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
            void onLifecycleChanged(LifecycleOwner owner,
                                    Lifecycle.Event event) {
                Log.i(TAG, "LifecycleObserver--->onChange" + event.name());
            }


        });
    }


}

package com.livedata.app;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

//视图层，关联ViewModel
public class TopDataFragment extends BaselifecycleFragment<TopViewModel> {
    public static final String TAG = "TopDataFragment";

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_movie_top;
    }

    @Override
    protected void dataObserver() {
        mViewModel.getDemo1ResponseMutableLiveData().observe(this, new Observer<Subjects>() {
            @Override
            public void onChanged(@Nullable Subjects subjects) {
                showData(subjects);
            }
        });

        mViewModel.getDemo1ResponseMutableLiveData().observe(this, new Observer<Subjects>() {
            @Override
            public void onChanged(@Nullable Subjects subjects) {
                Log.d(TAG, "observe get!!");
            }
        });
    }

    private void showData(Subjects subjects) {
        Toast.makeText(getContext(), subjects.getTitle()+"", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void initData() {
        mViewModel.getRequestMovieTop(false);
    }

    @Override
    protected void initView() {
        super.initView();
    }

}

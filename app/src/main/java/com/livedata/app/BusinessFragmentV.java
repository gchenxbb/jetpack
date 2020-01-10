package com.livedata.app;

import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

//V业务层
//关联业务VM
public class BusinessFragmentV extends BaselifecycleFragment<BusinessVM> {
    public static final String TAG = "TopDataFragment";

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_movie_top;
    }

    @Override
    protected void dataObserver() {
        MutableLiveData<Subjects> responseMutableLiveData = mViewModel.getResponseMutableLiveData();
        responseMutableLiveData.observe(this, new Observer<Subjects>() {
            @Override
            public void onChanged(@Nullable Subjects subjects) {
                Toast.makeText(getContext(), subjects.getTitle() + "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void initData() {
        mViewModel.getRequestMovieTop();
    }
}
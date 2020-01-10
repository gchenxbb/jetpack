package com.livedata.app;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

//V业务层
//关联业务VM
public class BusinessFragmentV extends BaselifecycleFragment<BusinessVM> {
    public static final String TAG = "TopDataFragment";

    private TextView mTv;

    @Override
    protected void initView(View view) {
        mTv = view.findViewById(R.id.tv_title);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_businessx;
    }

    @Override
    protected void dataObserver() {
        MutableLiveData<Subjects> responseMutableLiveData = mViewModel.getResponseMutableLiveData();
        responseMutableLiveData.observe(this, new Observer<Subjects>() {
            @Override
            public void onChanged(@Nullable Subjects subjects) {
                mTv.setText(subjects.getTitle());
            }
        });
    }

    @Override
    protected void initData() {
        mViewModel.getRequestMovieTop();
    }
}
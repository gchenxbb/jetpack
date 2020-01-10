package com.livedata.app;

import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

//baseV层
public abstract class BaselifecycleFragment<T extends BaseViewModel> extends Fragment {

    protected T mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), null);
        initView();
        return view;
    }

    protected abstract int getLayoutId();

    protected void initView() {
        ViewModelProvider.AndroidViewModelFactory factory =
                ViewModelProvider.AndroidViewModelFactory.getInstance(this.getActivity().getApplication());
        Class<T> tClass = getParameterizedTypeClass(this, 0);
        mViewModel = factory.create(tClass);


        MutableLiveData liveData = mViewModel.loadState;
        liveData.observe(this, observer);

        dataObserver();
    }

    protected Observer<String> observer = new Observer<String>() {
        @Override
        public void onChanged(@Nullable String s) {
            Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
        }
    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    protected abstract void initData();

    protected abstract void dataObserver();

    //泛型类型，即和它绑定的ViewModel类型
    public <T> T getParameterizedTypeClass(Object object, int i) {
        if (object != null) {
            ParameterizedType type = (ParameterizedType) object.getClass().getGenericSuperclass();
            Type[] array = type.getActualTypeArguments();
            return (T) array[i];
        }
        return null;
    }
}

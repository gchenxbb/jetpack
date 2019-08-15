package com.livedata.app;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BaselifecycleFragment<T extends BaseViewModel> extends Fragment {

    protected T mViewModel;
    private boolean hasLoadOnce;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), null);
        initView();
        return view;
    }

    protected abstract int getLayoutId();

    protected void initView() {
        //第二参数是自定义ViewModel的Class
        mViewModel = createViewModel(this, (Class<T>) getInstance(this, 0));
        if (mViewModel != null) {
            MutableLiveData liveData = mViewModel.loadState;
            //基类自动加上网络状态观察者
            liveData.observe(this, new Observer() {
                @Override
                public void onChanged(@Nullable Object o) {

                }
            });
            dataObserver();
        }
    }

    protected <T extends ViewModel> T createViewModel(Fragment fragment, Class<T> modelClazz) {
        ViewModelProvider viewModelProvider = ViewModelProviders.of(fragment);
        return viewModelProvider.get(modelClazz);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (!hasLoadOnce && isVisible() && isVisibleToUser) {
            hasLoadOnce = true;
            doLazyRequest();//如果有ViewPager，第2,3..个Fragment会走这里
        }
        super.setUserVisibleHint(isVisibleToUser);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if (getUserVisibleHint()) {//fragment可见
            hasLoadOnce = true;
            doLazyRequest();//ViewPager的第1个Fragment会走这里
        } else {
        }
        super.onActivityCreated(savedInstanceState);
    }

    protected void doLazyRequest() {
        //真正加载数据
        initData();
    }
    protected abstract void initData();


    //观察者回调，更新UI，让子类实现
    protected abstract void dataObserver();

    //公共回调，基类实现
    protected Observer<String> observer = new Observer<String>() {
        @Override
        public void onChanged(@Nullable String s) {
            //根据状态弹出网络信息UI
        }
    };

    //获取泛型类型，即和它绑定的ViewModel类型
    public <T> T getInstance(Object object, int i) {
        if (object != null) {
            ParameterizedType type = (ParameterizedType) object.getClass().getGenericSuperclass();
            Type[] array = type.getActualTypeArguments();
            return (T) array[i];
        }
        return null;
    }
}

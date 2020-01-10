package com.livedata.app;

import android.app.Application;
import android.text.TextUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

//VM
//绑定M
public class BaseViewModel<T extends BaseDataModel> extends AndroidViewModel {
    private Map<String, MutableLiveData> liveDataMap;

    //网络状态LiveData
    public MutableLiveData<String> loadState;

    //数据源
    public T mDataModel;

    public BaseViewModel(Application application) {
        super(application);
        loadState = new MutableLiveData<>();
        liveDataMap = new ConcurrentHashMap<>();
        mDataModel = getMInstance(this, 0);
    }

    protected <T> MutableLiveData<T> get(Class<T> clazz) {
        return get(null, clazz);
    }

    //根据key和class查找MutableLiveData
    protected <T> MutableLiveData<T> get(String key, Class<T> clazz) {
        String keyName;
        if (TextUtils.isEmpty(key)) {
            keyName = clazz.getCanonicalName();
        } else {
            keyName = key;
        }
        MutableLiveData<T> mutableLiveData = liveDataMap.get(keyName);
        if (mutableLiveData != null) {
            return mutableLiveData;
        }
        mutableLiveData = new MutableLiveData<>();
        liveDataMap.put(keyName, mutableLiveData);
        return mutableLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (liveDataMap != null) {
            liveDataMap.clear();
        }
    }

    //根据当前VM，创建M
    public <T> T getMInstance(Object object, int i) {
        if (object != null) {
            try {
                ParameterizedType type = (ParameterizedType) object.getClass().getGenericSuperclass();
                Type[] array = type.getActualTypeArguments();
                Class<T> clazz = (Class<T>) array[i];
                return clazz.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}

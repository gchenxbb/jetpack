package com.livedata.app;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.text.TextUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//基础ViewModel,绑定DataModel
public class BaseViewModel<T extends BaseDataModel> extends AndroidViewModel {
    //存储MutableLiveData集合类
    private Map<String, MutableLiveData> liveDataMap;

    public MutableLiveData<String> loadState;//网络状态LiveData

    public T mDataModel;//数据源

    public BaseViewModel(Application application) {
        super(application);
        loadState = new MutableLiveData<>();
        liveDataMap = new ConcurrentHashMap<>();
        //创建数据源
        mDataModel = getNewInstance(this, 0);
    }

    //根据class查找MutableLiveData
    protected <T> MutableLiveData<T> get(Class<T> clazz) {
        return get(null, clazz);
    }

    //根据key和class查找MutableLiveData
    protected <T> MutableLiveData<T> get(String key, Class<T> clazz) {
        String keyName;
        if (TextUtils.isEmpty(key))
            keyName = clazz.getCanonicalName();
        else
            keyName = key;

        MutableLiveData<T> mutableLiveData = liveDataMap.get(keyName);
        if (mutableLiveData != null)
            return mutableLiveData;
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

    //根据当前ViewModel，创建DataModel
    public <T> T getNewInstance(Object object, int i) {
        if (object != null) {
            try {
                ParameterizedType type = (ParameterizedType) object.getClass().getGenericSuperclass();
                Type[] array = type.getActualTypeArguments();
                Class<T> clazz = (Class<T>) array[i];
                return clazz.newInstance();
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            } catch (ClassCastException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}

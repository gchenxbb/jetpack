package com.livedata.app;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

/**
 * 逻辑层
 */
public class TopViewModel extends BaseViewModel<TopDataModel> {
    public final static String TAG = "TopViewModel";

    private MutableLiveData<Subjects> demo1ResponseMutableLiveData;

    public TopViewModel(Application application) {
        super(application);
    }

    //获取LiveData
    public MutableLiveData<Subjects> getDemo1ResponseMutableLiveData() {
        if (demo1ResponseMutableLiveData == null) {
            demo1ResponseMutableLiveData = new MutableLiveData<>();
        }
        return demo1ResponseMutableLiveData;
    }

    public void getRequestMovieTop(boolean isLoad) {
        //通过datamodel实现
        mDataModel.requestNetWorkTopData(new Callback() {
            @Override
            public void onNoNetwork() {
                Log.d(TAG, "noNetwork!");
                loadState.postValue(" error,noNetwork!");
            }

            @Override
            public void onNext(Object o) {
                Log.d(TAG, o.toString());
                if (o instanceof Subjects) {
                    Subjects TopResponse = (Subjects) o;
                    demo1ResponseMutableLiveData.postValue(TopResponse);
                    loadState.postValue(" success!");
                }
            }

            @Override
            public void onError(String e) {
                Log.d(TAG, e);
                loadState.postValue(" 错误error,!" + e);
            }
        },isLoad);

    }
}

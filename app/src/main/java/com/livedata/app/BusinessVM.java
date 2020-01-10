package com.livedata.app;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

//业务VM
public class BusinessVM extends BaseViewModel<BusinessM> {
    public final static String TAG = "BusinessVM";

    private MutableLiveData<Subjects> responseMutableLiveData;

    public BusinessVM(Application application) {
        super(application);
    }

    //获取LiveData
    public MutableLiveData<Subjects> getResponseMutableLiveData() {
        if (responseMutableLiveData == null) {
            responseMutableLiveData = new MutableLiveData<>();
        }
        return responseMutableLiveData;
    }

    public void getRequestMovieTop( ) {
        mDataModel.requestNetWorkTopData(new Callback() {
            @Override
            public void onNoNetwork() {
                Log.d(TAG, "noNetwork!");
                loadState.postValue(" Error,noNetwork!");
            }

            @Override
            public void onNext(Object o) {
                Log.d(TAG, o.toString());
                if (o instanceof Subjects) {
                    Subjects TopResponse = (Subjects) o;
                    responseMutableLiveData.postValue(TopResponse);
                    loadState.postValue(" success!");
                }
            }

            @Override
            public void onError(String e) {
                Log.d(TAG, e);
                loadState.postValue(" 错误Error,!" + e);
            }
        });

    }
}

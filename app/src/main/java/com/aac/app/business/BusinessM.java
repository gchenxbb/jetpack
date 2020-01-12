package com.aac.app.business;


import com.aac.app.livedata.BaseDataModel;
import com.aac.app.livedata.Callback;
import com.aac.app.livedata.Subjects;

public class BusinessM extends BaseDataModel {

    public void requestNetWorkTopData(Callback listener) {
        listener.onNext(new Subjects("BusinessTitle", "BusinessM24", "BusinessM402"));
    }
}

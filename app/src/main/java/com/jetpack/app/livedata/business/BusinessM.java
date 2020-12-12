package com.jetpack.app.livedata.business;

import com.jetpack.app.livedata.BaseDataModel;
import com.jetpack.app.livedata.Callback;
import com.jetpack.app.livedata.Subjects;

public class BusinessM extends BaseDataModel {

    public void requestNetWorkTopData(Callback listener) {
        listener.onNext(new Subjects("BusinessTitle", "BusinessM24", "BusinessM402"));
    }
}

package com.livedata.app;


public class BusinessM extends BaseDataModel {

    public void requestNetWorkTopData(Callback listener) {
        listener.onNext(new Subjects("BusinessTitle", "BusinessM24", "BusinessM402"));
    }
}

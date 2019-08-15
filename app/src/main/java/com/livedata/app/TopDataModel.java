package com.livedata.app;


public class TopDataModel extends BaseDataModel {

    public TopDataModel() {
    }

    public void requestNetWorkTopData(Callback listener, boolean isLoad) {
        listener.onNext(new Subjects("title1", "24", "434532"));
    }
}

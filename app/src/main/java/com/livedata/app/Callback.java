package com.livedata.app;

public interface Callback<T> {
    void onNoNetwork();

    void onNext(T t);

    void onError(String e);
}

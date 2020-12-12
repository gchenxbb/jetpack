package com.jetpack.app.livedata;

public interface Callback<T> {
    void onNoNetwork();

    void onNext(T t);

    void onError(String e);
}

package com.jetpack.app.room;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;


public class UserViewModel extends ViewModel {
    public static final String TAG = "UserViewModel";

    private UserRepository userRepository;
    private LiveData<List<User>> listLiveData;

    private MutableLiveData<User> mLiveData;

    public UserViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void init() {
        mLiveData = new MutableLiveData<>();
        mLiveData.setValue(new User("123"));
    }

    public MutableLiveData<User> getmLiveData() {
        return mLiveData;
    }

    public void refresh(String userId) {
        User newUser = new User(userId);
        mLiveData.setValue(newUser);
    }
}

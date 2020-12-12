package com.jetpack.app.room;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UserRepository {
    UserDao mUserDao;
    private static UserRepository INSTANCE;

    private UserRepository(UserDao userDao) {
        mUserDao = userDao;
    }

    public static UserRepository getInstance(UserDao userDao) {
        if (INSTANCE == null) {
            synchronized (UserRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UserRepository(userDao);
                }
            }
        }
        return INSTANCE;
    }

    public LiveData<List<User>> getUsers() {
        return mUserDao.getUsers();
    }
}

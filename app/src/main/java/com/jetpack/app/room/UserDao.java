package com.jetpack.app.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT*FROM users WHERE id LIKE:id ")
    List<User> queryById(String id);

    @Insert
    void insert(User user);

    @Query("DELETE FROM users WHERE id=:id")
    void deleteByCid(String id);

    @Update
    void update(User user);

    @Query("SELECT * FROM users ORDER BY user_name")
    LiveData<List<User>> getUsers();

}

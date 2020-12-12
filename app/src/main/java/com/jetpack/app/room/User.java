package com.jetpack.app.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "users")
public class User implements Serializable {
    @NonNull
    @PrimaryKey(autoGenerate = false)
    private String id;
    @ColumnInfo(name = "user_name")
    private String name;
    @ColumnInfo(name = "user_sex")
    private String sex;
    @ColumnInfo(name = "user_age")
    private String age;

//    public User(@NonNull String id, String name, String sex, String age) {
//        this.id = id;
//        this.name = name;
//        this.sex = sex;
//        this.age = age;
//    }

    public User(String id) {
        this.id = id;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}

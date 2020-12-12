package com.jetpack.app.room;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CouponDao {
    @Query("SELECT*from coupons")
    List<Coupon> quiryAll();

    @Query("SELECT*FROM coupons WHERE cid LIKE:cid ")
    List<Coupon> quiryByCid(String cid);

    @Query("SELECT*FROM coupons limit 1")
    Coupon quiryFirst();

    @Insert
    void insert(Coupon coupons);

    @Query("DELETE FROM coupons")
    void deleteAll();

    @Query("DELETE FROM coupons WHERE cid=:cid")
    void deleteByCid(String cid);

    @Update
    void update(Coupon coupon);

    @Query("SELECT * FROM coupons WHERE cid=:cid")
    LiveData<Coupon> getCouponObservable(String cid);
}

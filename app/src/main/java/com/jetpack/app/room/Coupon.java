package com.jetpack.app.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity(tableName = "coupons")
public class Coupon implements Serializable {
    @NonNull
    @PrimaryKey(autoGenerate = false)
    private String cid;//优惠券id，主键
    @ColumnInfo(name = "coupon_title")
    private String title;// 活动文案标题;
    @ColumnInfo(name = "coupon_sub_title")
    private String subtsitle;// 活动文案标题;
    @ColumnInfo(name = "coupon_text")
    private String text;// 活动文案;
    @ColumnInfo(name = "coupon_type")
    private int type;//新人，通用，活动
    @ColumnInfo(name = "coupon_url")
    private String url;// 跳转url;
    @ColumnInfo(name = "coupon_price")
    private float price;// 价格;
    @ColumnInfo(name = "coupon_endtime")
    private String endtime;// 截至时间;

    @ColumnInfo(name = "coupon_original_price")
    private int originalPrice;//新增字段，原价
    public Coupon() {

    }

    @Ignore
    public Coupon(String cid) {
        this.cid = cid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtsitle() {
        return subtsitle;
    }

    public void setSubtsitle(String subtsitle) {
        this.subtsitle = subtsitle;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(int originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }



}

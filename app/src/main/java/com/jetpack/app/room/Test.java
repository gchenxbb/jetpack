package com.jetpack.app.room;

import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class Test {
    public static String deleteCid = "";
    public static String searchCid = "";

    public static Coupon getCoupon() {
        return getCoupon(null);
    }

    public static Coupon getCoupon(String cid) {
        if (TextUtils.isEmpty(cid)) {
            cid = getUUID();
        }
        deleteCid = cid;
        searchCid = cid;
        Coupon coupon = new Coupon(cid);
        coupon.setEndtime(randomDate("2015-01-01", "2019-12-31"));
        Random random = new Random();
        coupon.setPrice(random.nextInt(10000));
        coupon.setSubtsitle("最后的优惠");
        coupon.setType(random.nextInt(10));
        coupon.setText("今天商城全部打折促销啦");
        coupon.setTitle("优惠");
        coupon.setOriginalPrice(random.nextInt(100));
        coupon.setUrl("www.baidu.com");
        return coupon;
    }

    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }

    private static String randomDate(String beginDate, String endDate) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date start = format.parse(beginDate);
            Date end = format.parse(endDate);
            if (start.getTime() >= end.getTime()) {
                return null;
            }
            long date = random(start.getTime(), end.getTime());
            Date time = new Date(date);
            String dateStr = format.format(time);
            return dateStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static long random(long begin, long end) {
        long rtn = begin + (long) (Math.random() * (end - begin));
        if (rtn == begin || rtn == end) {
            return random(begin, end);
        }
        return rtn;
    }
}

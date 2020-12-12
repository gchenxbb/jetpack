package com.jetpack.app.room;


import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

public class CouponViewModel extends ViewModel {
    public static final String TAG = "CouponDatabase";
    private MediatorLiveData<Coupon> mLiveData;

    public MutableLiveData<Coupon> getmLiveData(String cid) {
        if (mLiveData == null) {
            mLiveData = new MediatorLiveData<>();
            //该表内容改变时，回调observer。
            LiveData<Coupon> liveData = CouponDatabase.getInstance().couponDao().getCouponObservable(cid);
            mLiveData.addSource(liveData, new Observer<Coupon>() {
                @Override
                public void onChanged(@Nullable Coupon coupon) {
                    if (coupon != null) {
//                        Log.d(TAG, "CouponViewModel cid:" + coupon.getCid());
                        //通知外部observer
                        mLiveData.setValue(coupon);
                    } else {
//                        Log.d(TAG, "CouponViewModel coupon is null!");
                    }
                }
            });
        }
        return mLiveData;
    }

}

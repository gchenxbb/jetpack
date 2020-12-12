package com.jetpack.app.room;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.jetpack.app.R;

import java.util.ArrayList;
import java.util.List;

//RoomDatabase demo
public class UserActivity extends AppCompatActivity implements View.OnClickListener {
    private final static String TAG = "CouponDatabase";
    List<Coupon> allCoupons = new ArrayList<>();
    CouponDao couponDao;
    private int currentIndex;
    String msg = "";
    private Handler mH = new H();

    private CouponViewModel mViewModel;
    private UserViewModel mUserViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        UserDatabase.setContext(getApplicationContext());
        CouponDatabase.setContext(getApplicationContext());

        mViewModel = ViewModelProviders.of(this).get(CouponViewModel.class);
//        mUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        mUserViewModel = ViewModelProviders.of(this, new UserModelFactory(UserRepository.getInstance(UserDatabase.getInstance().userDao())))
                .get(UserViewModel.class);


        mUserViewModel.init();
        mUserViewModel.getmLiveData().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                //第一次，ReportFragment的onStart方法，dispatch(Lifecycle.Event.ON_START);
                if (user != null) {
//                    Toast.makeText(UserActivity.this, "userId:" + user.getId(), Toast.LENGTH_LONG).show();
//                    Log.d(TAG, "userId:" + user.getId());
                }
            }
        });



        //插入一项
        findViewById(R.id.tv_insert).setOnClickListener(this);
        //删除一项
        findViewById(R.id.tv_delete).setOnClickListener(this);
        //删除所有
        findViewById(R.id.tv_delete_all).setOnClickListener(this);
        //查找一项
        findViewById(R.id.tv_quiry).setOnClickListener(this);
        //查找所有
        findViewById(R.id.tv_find_all).setOnClickListener(this);
        //更新
        findViewById(R.id.tv_update).setOnClickListener(this);
        //刷新用户
        findViewById(R.id.tv_refresh_user).setOnClickListener(this);

        findViewById(R.id.close).setOnClickListener(this);

        //查找第一项
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Coupon coupon = CouponDatabase.getInstance().couponDao().quiryFirst();
//                if (coupon != null) {
//                    Message message = new Message();
//                    message.what = 0xff;
//                    message.obj = coupon;
//                    mH.sendMessage(message);
//                }
            }
        });
        thread.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                couponDao = CouponDatabase.getInstance().couponDao();
            }
        });
        thread2.start();


    }

    @Override
    public void onClick(final View v) {
        if (v.getId() == R.id.tv_refresh_user) {
            mUserViewModel.refresh("234");
            return;
        } else if (v.getId() == R.id.close) {
            CouponDatabase.getInstance().close();
            return;
        }

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int what = 0;
                switch (v.getId()) {
                    case R.id.tv_insert:
                        insertData();
                        what = 0x1;
                        break;
                    case R.id.tv_update:
                        updateData(Test.searchCid);
                        what = 0x2;
                        break;
                    case R.id.tv_quiry:
                        quiryData(Test.searchCid);
                        what = 0x3;
                        break;
                    case R.id.tv_delete:
                        deleteData(Test.deleteCid);
                        what = 0x4;
                        break;
                    case R.id.tv_delete_all:
                        what = 0x5;
                        deleteAllData();
                        break;
                    case R.id.tv_find_all:
                        what = 0x6;
                        quiryAllData();
                        break;
                    default:
                        break;
                }
                Message message = new Message();
                message.obj = msg;
                message.what = what;
                mH.sendMessage(message);
            }
        });
        thread.start();
    }

    public class H extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //第一个
            if (msg.what == 0xff) {
                Coupon coupon = (Coupon) msg.obj;
                register(coupon);
            } else {
                String m = (String) msg.obj;
                showToast(m);
            }

        }
    }

    public void register(Coupon cou) {
        if (cou == null) {
            return;
        }
//        Log.d(TAG, "register cid: " + cou.getCid());
        mViewModel.getmLiveData(cou.getCid()).observe(this, new Observer<Coupon>() {
            @Override
            public void onChanged(@Nullable Coupon coupon) {
                if (coupon != null) {
//                    Log.d(TAG, "MainActivity cid:" + coupon.getCid());
                } else {
//                    Log.d(TAG, "coupon is null !!");
                }
            }
        });
    }

    /**
     * 新增一项
     */
    private void insertData() {
        Coupon coupon = Test.getCoupon();
        couponDao.insert(coupon);
        msg = "新增成功!";
    }

    /**
     * 删除一项
     */
    private void deleteData(String cid) {
        couponDao.deleteByCid(cid);
        msg = "删除成功!";
    }

    /**
     * 删除所有
     */
    private void deleteAllData() {
        couponDao.deleteAll();
        msg = "删除all成功!";
    }

    /**
     * 查找一项
     */
    private void quiryData(String cid) {
        List<Coupon> coupons = couponDao.quiryByCid(cid);
        if (coupons != null && coupons.size() > 0) {
            for (Coupon c : coupons) {
                Log.d(TAG, c.getCid() + c.getText());
            }
        }
        msg = "查找成功!";
    }

    /**
     * 查找所有数据
     */
    private void quiryAllData() {
        List<Coupon> couponAll = couponDao.quiryAll();
        if (couponAll != null && couponAll.size() > 0) {
            for (Coupon cal : couponAll) {
                Log.d(TAG, cal.getCid() + cal.getText());
            }
            allCoupons.addAll(couponAll);
        }
        msg = "查找all成功!";
    }

    /**
     * 更新一项
     */
    private void updateData(String cid) {
        List<Coupon> updates = couponDao.quiryByCid(Test.searchCid);
        if (updates != null && updates.size() > 0) {
            for (Coupon c2 : updates) {
                c2.setTitle("新标题!" + SystemClock.currentThreadTimeMillis());
                c2.setText("新内容!" + SystemClock.currentThreadTimeMillis());
                couponDao.update(c2);
            }
        }
        msg = "更新一项成功!";
    }

    private void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }


    private void test() {
        for (int i = 0; i < 5; i++) {
            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    Log.d(TAG, "insert start:" + Thread.currentThread().getName());
                    insertData();
                    Log.d(TAG, "insert end:" + Thread.currentThread().getName());
                }
            });
            thread1.start();

            Thread thread2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    Log.d(TAG, "query start:" + Thread.currentThread().getName());
                    quiryAllData();
                    Log.d(TAG, "query end:" + Thread.currentThread().getName());
                }
            });
            thread2.start();
        }
    }
}

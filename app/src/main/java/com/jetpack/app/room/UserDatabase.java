package com.jetpack.app.room;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.io.File;

/**
 * tbName:Coupon
 */
@Database(entities = {User.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {
    public static final String TAG = "CouponDatabase";
    public static Context mContext;
    public static UserDatabase mCouponDatabase;
    public static String DB_NAME = "coupons_database.db";

    public static void setContext(Context context) {
        mContext = context;
    }

    public static UserDatabase getInstance() {
        Log.d(TAG, "进入0 " + Thread.currentThread().getName());
        if (mCouponDatabase == null) {
            Log.d(TAG, "进入1 " + Thread.currentThread().getName());
//            synchronized (CouponDatabase.class) {
                Log.d(TAG, "进入2 " + Thread.currentThread().getName());
//                if (mCouponDatabase == null) {
                    Log.d(TAG, "进入3 " + Thread.currentThread().getName());
                    mCouponDatabase = Room.databaseBuilder(mContext, UserDatabase.class, DB_NAME)
                            .addCallback(new Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    //数据库创建回调，访问表时，库不存在首次创建。
//                                    try{
//                                        Thread.sleep(5000);
//                                    }catch (Exception e){
//
//                                    }

                                    Log.d(TAG, "database onCreate!!!!" + Thread.currentThread().getName());
                                }

                                @Override
                                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                                    super.onOpen(db);
                                    //数据库打开回调，访问表时。只打开一次，杀掉进程后关闭。
                                    Log.d(TAG, "database onOpen!!!!" + Thread.currentThread().getName());
                                }
                            })
                            .build();

//                    copDatabase();

//                    try{
//                        Thread.sleep(5000);
//                    }catch (Exception e){
//
//                    }

//                }

//            }
        }
        return mCouponDatabase;
    }

    private static boolean copDatabase() {
        File file = mContext.getDatabasePath(DB_NAME);
        Log.d(TAG, "查找数据库文件！" + file.getAbsolutePath());
        if (file == null || !file.exists()) {
            Log.d(TAG, "目标数据库不存在！");
            return false;
        }

        SQLiteDatabase sqLiteDatabase = mContext.openOrCreateDatabase(file.getAbsolutePath(), Context.MODE_PRIVATE, null);

        //查询库中的表，判断某个表是否存在

        if (sqLiteDatabase != null) {
            Log.d(TAG, "创建表。");

            boolean isTbExist = isTableExist(sqLiteDatabase, "coupons");
            Log.d(TAG, "表是否存在：" + isTbExist);

            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `coupons` (`cid` TEXT NOT NULL, `coupon_title` TEXT, `coupon_sub_title` TEXT, `coupon_text` TEXT, `coupon_type` INTEGER NOT NULL, `coupon_url` TEXT, `coupon_price` REAL NOT NULL, `coupon_endtime` TEXT, `coupon_original_price` INTEGER NOT NULL, PRIMARY KEY(`cid`))");

            Log.d(TAG, "当前线程：" + Thread.currentThread().getName());
//            sqLiteDatabase.close();

            sqLiteDatabase.rawQuery("select * from coupons", null);

        }
        return false;


    }

    //表是否存在
    private static boolean isTableExist(SQLiteDatabase db, String tbName) {
        if (TextUtils.isEmpty(tbName)) {
            return false;
        }
        try {
            String sql = "select count(*) from sqlite_master where type='table' and tbl_name='" + tbName + "'";
            Cursor cursor = db.rawQuery(sql, null);
            if (cursor.moveToNext()) {
                int count = cursor.getInt(0);
                if (count > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            Log.d(TAG, "error，查询表表是否存在？");
        }
        return false;
    }

    //查询数据库中的所有表
    private void queryAllTable(SQLiteDatabase sqLiteDatabase) {
        String sql = "select * from sqlite_master";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor != null) {
            int tableCount = cursor.getCount();
            Log.d(TAG, "一共有" + tableCount + " 张表");
            if (tableCount > 0) {
                if (cursor.moveToFirst()) {
                    do {
                        int columnCount = cursor.getColumnCount();
                        for (int i = 0; i < columnCount; i++) {
                            String name = cursor.getColumnName(i);
                            String value = cursor.getString(i);

                            Log.d(TAG, name + "--" + value);
                        }
                    } while (cursor.moveToNext());
                }
            }
        }

    }

    public abstract UserDao userDao();

    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            Log.d(TAG, "Migration:1->2");
            database.execSQL("ALTER TABLE coupons "
                    + " ADD COLUMN coupon_original_price INTEGER NOT NULL DEFAULT 0");
        }
    };

    //1019
    public static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            Log.d(TAG, "Migration:2->3");
//            database.execSQL("ALTER TABLE coupons "
//                    + " ADD COLUMN coupon_original_price INTEGER");
        }
    };

}

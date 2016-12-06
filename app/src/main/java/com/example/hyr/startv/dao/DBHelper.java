package com.example.hyr.startv.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Description:
 * 作者：hyr on 2016/11/25 10:36
 * 邮箱：2045446584@qq.com
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASENAME = "StarTv.db";
    private static final int DATABASEVERSION = 1;


    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBHelper(Context context) {
        super(context, DATABASENAME, null, DATABASEVERSION);
    }
    @Override public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(VideoDao.createTable());
        sqLiteDatabase.execSQL(VideoDao.createIndex());
    }


    @Override public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

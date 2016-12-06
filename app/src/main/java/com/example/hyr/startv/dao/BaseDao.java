package com.example.hyr.startv.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.hyr.startv.App;

/**
 * Description:
 * 作者：hyr on 2016/11/25 10:35
 * 邮箱：2045446584@qq.com
 */
public class BaseDao {

    private final DBHelper dbHelper;
    private final SQLiteDatabase writableDatabase;


    public BaseDao() {

        dbHelper = new DBHelper(App.getInstance().getApplicationContext());
        writableDatabase = dbHelper.getWritableDatabase();

    }

    public void close() {
        dbHelper.close();
        writableDatabase.close();
    }

    public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy){
        Cursor c = writableDatabase.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
        return c;
    }

    public long insert (String table, String nullColumnHack, ContentValues values){
        return writableDatabase.insert(table,nullColumnHack,values);
    }


    public int delete(String table, String whereClause, String[] whereArgs){
        return writableDatabase.delete(table,whereClause,whereArgs);
    }

    public int update(String table, ContentValues values, String whereClause, String[] whereArgs){
        return writableDatabase.update(table,values,whereClause,whereArgs);
    }


    /**
     * 数据替换，原理是先删除存在的整行数据后在重新插入
     * 需要先指定索引才能使用
     * @param table
     * @param nullColumnHack
     * @param initialValues
     * @return
     */
    public long replace(String table, String nullColumnHack, ContentValues initialValues){
        return writableDatabase.replace(table,nullColumnHack,initialValues);
    }


}

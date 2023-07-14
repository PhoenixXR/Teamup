package com.example.view.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
/*
自带数据库sqlite，已经更换为mysql
 */
//工具类  自动连接数据   创建表
public class DBOpenHelper extends SQLiteOpenHelper {
    public DBOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建表

        String sql01="create table if not exists info(kook varchar(20),lv varchar(2), num varchar(20))";
        db.execSQL(sql01);
        String sql02 = "create table if not exists user(username varchar(20),password varchar(20))";
        db.execSQL(sql02);
        //System.out.println("数据表创建成功");

        //db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //修改表
    }

    //判断表是否存在
    public void exitDataBase(SQLiteDatabase db,String dbTitle) {
        String sql = "select count(*) as c from test where type ='table' and name ='" + dbTitle + "';";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToNext()) {
            int count = cursor.getInt(0);
            if (count > 0) {
                System.out.println("数据表已经存在");
            } else {
                onCreate(db);
            }
        }
    }
}
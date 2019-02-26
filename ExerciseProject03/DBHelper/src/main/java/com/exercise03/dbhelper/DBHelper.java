package com.exercise03.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "persons.db";
    public static final int VERSION = 1;

    //指定数据库名称、版本信息
    public DBHelper(Context context){
        super(context,DB_NAME,null,VERSION);
    }

    //创建数据库中的表
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table person_tb(id integer primary key,name char(10))";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if(i < i1){
            String sql = "drop table if exists person_tb";
            sqLiteDatabase.execSQL(sql);
            onCreate(sqLiteDatabase);
        }
    }
}

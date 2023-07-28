package com.example.myapp.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDBHelper_All(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        // 表的建表语句，id是整型主键自增长
        // text表示文本类型
        val createNews1 = "create table News1 (" +
                " id integer primary key autoincrement, " +
                " title text, type text, author text)"
        db.execSQL(createNews1);


        val createNews2 = "create table News2 (" +
                " id integer primary key autoincrement, " +
                " title text, type text, author text, image integer)"
        db.execSQL(createNews2);

        val createAccounts = "create table Accounts (" +
                "id integer primary key autoincrement, " +
                "username text, password text, age text, phone text, sex text)"
        db.execSQL(createAccounts);
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }

    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "MyDataBase_All.db"
    }
}
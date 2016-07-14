package com.example.dllo.chanyouji.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dllo on 16/7/11.
 */
public class MySQLiteHelpter extends SQLiteOpenHelper {

    private static final String CREATE_CALL_LOG_TABLE="create table "+ DBValues.TABLE_NAME+"("+
            DBValues.COLUMN_ID+" integer primary key autoincrement ,"+ DBValues.COLUMN_PLACE+" text," +
             " text," +DBValues.COLUMN_MYID+" text)";
    public MySQLiteHelpter(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CALL_LOG_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

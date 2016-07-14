package com.example.dllo.chanyouji.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dllo.chanyouji.strategy.head.RecentLookBean;

import java.util.ArrayList;

/**
 * Created by dllo on 16/7/11.
 */
public class DBTools {
    private MySQLiteHelpter helper;
    private SQLiteDatabase database;
    private Context context;

    public DBTools(Context context) {
        this.context = context;
        helper = new MySQLiteHelpter(context,DBValues.DB_NAME,null,1);
        database = helper.getWritableDatabase();
    }

    public void addContent(RecentLookBean recentLookBean){
        ContentValues values = new ContentValues();
        values.put(DBValues.COLUMN_PLACE,recentLookBean.getPlace());
        values.put(DBValues.COLUMN_MYID,recentLookBean.getId());
        database.insert(DBValues.TABLE_NAME,null,values);
    }
    public ArrayList<RecentLookBean> queryContent(){
        ArrayList<RecentLookBean> recentLookBeen = new ArrayList<>();
        Cursor cursor = database.query(true, DBValues.TABLE_NAME, null, null, null, null, null, null, null);
        while (cursor.moveToNext()) {

            String place = cursor.getString(cursor.getColumnIndex(DBValues.COLUMN_PLACE));
            String id = cursor.getString(cursor.getColumnIndex(DBValues.COLUMN_MYID));
            RecentLookBean bean = new RecentLookBean();
            bean.setPlace(place);
            bean.setId(id);
            recentLookBeen.add(bean);


        }
        cursor.close();


    return recentLookBeen;

    }
}

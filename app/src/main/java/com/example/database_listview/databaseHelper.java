package com.example.database_listview;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class databaseHelper extends SQLiteOpenHelper {
    public databaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("헬퍼","헬퍼클래스에서 on create 작동");
        String sql = "create table if not exists healthmember ( " +
                "id_ integer primary key autoincrement , "+
                "name text , "+
                "phonenumber text , "+
                "age integer , " +
                "gender text , " +
                "month integer , " +
                "register_date text , " +
                "available_date text , " +
                "active integer)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("헬퍼","헬퍼클래스에서 on upgrade 작동");
        String sql  = "drop table if exists healthmember";
        db.execSQL(sql);
        onCreate(db); // 지우고 다시 만듬 --> 나중에는 안지우고 다시 만들 수 있도록 수정토록 해야함
    }

}

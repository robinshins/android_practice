package com.example.database_listview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class MySQliteHandler {
    databaseHelper helper;
    SQLiteDatabase db;

    public MySQliteHandler(Context context){
        helper = new databaseHelper(context,"healthmember",null,5);;
    }

    public static MySQliteHandler open(Context context){
        return new MySQliteHandler(context);
    }

    public void close(){
        db.close();
    }

    public void insert(String name, String phonenumber, int age, String gender){ // 새로운 정보 table에 insert
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("phonenumber",phonenumber);
        values.put("age",age);
        values.put("gender",gender);
        db.insert("healthmember",null,values);
        Log.d("헬퍼","자료 insert 됨");
    }
    public void insert_month(String name,int month, int active){ // 새로운 정보 table에 insert
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("month",month);
        values.put("active",active);
        db.update("healthmember",values,"name=?",new String[]{name});
        Log.d("헬퍼","자료 insert 됨");
    }
    public void change_active(String name,int active){ // 새로운 정보 table에 insert
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("month",active);
        db.update("healthmember",values,"name=?",new String[]{name});
        Log.d("헬퍼","자료 insert 됨");
    }


    public void delete(String name){ // 아마도 이름으로 테이블의 그 이름에 해당하는 열 자체를 지우는 거인듯 (확인 필요)
        db = helper.getWritableDatabase();
        db.delete("healthmember","name=?",new String[]{name});
        }
     public Cursor select(){
        db = helper.getReadableDatabase();
        Cursor c = db.query("healthmember",null,null,null,null,null,null);
        return c;
     }

}



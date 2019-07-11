package com.example.database_listview;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class member_showing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_showing);
        final MySQliteHandler handler = MySQliteHandler.open(getApplicationContext());
        ListView listView;
        showmemberAdapter adapter;
        adapter = new showmemberAdapter();
        listView = (ListView) findViewById(R.id.listview2);
        listView.setAdapter(adapter);
        Cursor c = handler.select();
        while(c.moveToNext()){
            int _id = c.getInt(c.getColumnIndex("id_"));
            String name = c.getString(c.getColumnIndex("name"));
            String phonenumber = c.getString(c.getColumnIndex("phonenumber"));
            int age = c.getInt(c.getColumnIndex("age"));
            String gender = c.getString(c.getColumnIndex("gender"));
            int month = c.getInt(c.getColumnIndex("month"));
            int active = c.getInt(c.getColumnIndex("active"));
            String registerdate = c.getString(c.getColumnIndex("register_date"));
            String availabledate = c.getString(c.getColumnIndex("available_date"));
            String monthstr = Integer.toString(month);
            adapter.addItem(name,gender,monthstr+"개월권",availabledate,phonenumber);
        }



    }
}

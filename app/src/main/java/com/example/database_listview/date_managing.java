package com.example.database_listview;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.os.IBinder;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class date_managing extends Service {
    public date_managing() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
      return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("서비스","서비스 시작됨");
        final MySQliteHandler handler = MySQliteHandler.open(getApplicationContext());
        String currentdate;
        String test = "20191229";
        SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
        Date time = new Date();
        currentdate= format1.format(time);
        Cursor c = handler.select();
        while(c.moveToNext()){
            String name = c.getString(c.getColumnIndex("name"));
            int active = c.getInt(c.getColumnIndex("active"));
            String registerdate = c.getString(c.getColumnIndex("register_date"));
            String availabledate = c.getString(c.getColumnIndex("available_date"));
            if(currentdate.equals(availabledate) && active==1){
                handler.change_active(name,0);
            }
        }




    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

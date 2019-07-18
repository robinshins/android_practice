package com.example.database_listview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class choose_membership extends AppCompatActivity {
    String name;
    String currentdate;
    int price1 = 70000;
    int price2 = 130000;
    int price3= 240000;
    int price4 = 430000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_membership);

        SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
        Date time = new Date();
        currentdate= format1.format(time); // 현재 날짜
        final MySQliteHandler handler = MySQliteHandler.open(getApplicationContext());
        ListView listView;
        ListViewAdapter adapter;
        adapter = new ListViewAdapter();
        listView = (ListView) findViewById(R.id.listview1);
        listView.setAdapter(adapter);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        adapter.addItem("1개월권","70,000원","수건,운동복 포함");
        adapter.addItem("3개월권","130,000원","수건,운동복 포함");
        adapter.addItem("6개월권","240,000원","수건,운동복 포함");
        adapter.addItem("12개월권","430,000원","수건,운동복 포함");
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position);
                String month = item.getMonth();
                if(month.equals("1개월권")){
                    int mon = 1;
                    handler.insert_month(name,mon,1);
                    String availabledate =null;
                    try {
                        availabledate = addDate(currentdate,mon);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    handler.insert_date(name,currentdate,availabledate);
                    Toast.makeText(getApplicationContext(), mon+"월권이 선택되었습니다",Toast.LENGTH_LONG).show();
                    Intent intent1 = new Intent(getApplicationContext(),choose_payment.class);
                    intent1.putExtra("month",Integer.toString(mon));
                    intent1.putExtra("price",price1);
                    startActivity(intent1);
                }else if(month.equals("3개월권")){
                    int mon=3;
                    handler.insert_month(name,mon,1);
                    String availabledate =null;
                    try {
                        availabledate = addDate(currentdate,mon);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    handler.insert_date(name,currentdate,availabledate);
                    Toast.makeText(getApplicationContext(), mon+"월권이 선택되었습니다",Toast.LENGTH_LONG).show();
                    Intent intent1 = new Intent(getApplicationContext(),choose_payment.class);
                    intent1.putExtra("month",Integer.toString(mon));
                    intent1.putExtra("price",price2);
                    startActivity(intent1);
                }else if(month.equals("6개월권")){
                    int mon = 6;
                    handler.insert_month(name,mon,1);
                    String availabledate =null;
                    try {
                        availabledate = addDate(currentdate,mon);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    handler.insert_date(name,currentdate,availabledate);
                    Toast.makeText(getApplicationContext(), mon+"월권이 선택되었습니다",Toast.LENGTH_LONG).show();
                    Intent intent1 = new Intent(getApplicationContext(),choose_payment.class);
                    intent1.putExtra("month",Integer.toString(mon));
                    intent1.putExtra("price",price3);
                    startActivity(intent1);
                }else if(month.equals("12개월권")){
                    int mon=12;
                    handler.insert_month(name,mon,1);
                    String availabledate =null;
                    try {
                        availabledate = addDate(currentdate,mon);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    handler.insert_date(name,currentdate,availabledate);
                    Toast.makeText(getApplicationContext(), mon+"월권이 선택되었습니다",Toast.LENGTH_LONG).show();
                    Intent intent1 = new Intent(getApplicationContext(),choose_payment.class);
                    intent1.putExtra("month",Integer.toString(mon));
                    intent1.putExtra("price",price4);
                    startActivity(intent1);

                }
            }
        });
    }


    private static String addDate(String dt, int m) throws Exception  {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        Date date = format.parse(dt);
        cal.setTime(date);
        cal.add(Calendar.MONTH, m);     //월 더하기

        return format.format(cal.getTime());

    }


}

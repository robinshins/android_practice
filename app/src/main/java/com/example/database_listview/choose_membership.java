package com.example.database_listview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class choose_membership extends AppCompatActivity {
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_membership);
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
                    handler.insert_month(name,1,1);
                }else if(month.equals("3개월권")){
                    handler.insert_month(name,3,1);
                }else if(month.equals("6개월권")){
                    handler.insert_month(name,6,1);
                }else if(month.equals("12개월권")){
                    handler.insert_month(name,12,1);
                }
            }
        });
    }
}

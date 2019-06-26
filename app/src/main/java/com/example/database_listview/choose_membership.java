package com.example.database_listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class choose_membership extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_membership);
        ListView listView;
        ListViewAdapter adapter;
        adapter = new ListViewAdapter();
        listView = (ListView) findViewById(R.id.listview1);
        listView.setAdapter(adapter);
        adapter.addItem("1개월권","70,000원","수건,운동복 포함");
        adapter.addItem("3개월권","130,000원","수건,운동복 포함");
        adapter.addItem("6개월권","240,000원","수건,운동복 포함");
        adapter.addItem("12개월권","430,000원","수건,운동복 포함");
    }
}

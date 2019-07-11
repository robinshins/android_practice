package com.example.database_listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class serviceclause_popup_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serviceclause_popup_activity);
        TextView textView = (TextView) findViewById(R.id.serviceagreement);
        textView.setMovementMethod(new ScrollingMovementMethod());
    }
}

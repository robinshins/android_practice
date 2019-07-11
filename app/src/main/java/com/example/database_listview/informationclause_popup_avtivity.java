package com.example.database_listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class informationclause_popup_avtivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informationclause_popup_avtivity);
        TextView textView = (TextView) findViewById(R.id.informationagreement);
        textView.setMovementMethod(new ScrollingMovementMethod());
    }
}

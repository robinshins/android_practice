package com.example.database_listview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class clausecheck_activity extends AppCompatActivity {

    CheckBox agree1;
    CheckBox agree2;
    CheckBox agree3;
    CheckBox agree4;
    CheckBox agree5;
    CheckBox allagree;
    TextView textView;
    TextView textView2;
    Button nextbutton;
    Boolean ischeck = false;
    Boolean ischeck2 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clausecheck_activity);
        agree1 = (CheckBox) findViewById(R.id.agree1);
        agree2 = (CheckBox)findViewById(R.id.agree2);
        agree3 = (CheckBox)findViewById(R.id.agree3);
        agree4 = (CheckBox)findViewById(R.id.agree4);
        agree5 = (CheckBox)findViewById(R.id.agree5);
        allagree = (CheckBox)findViewById(R.id.allagree);
        textView = (TextView) findViewById(R.id.serviceusing_clause);
        textView2 = (TextView)findViewById(R.id.informationusing_clause);


        allagree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(allagree.isChecked()){
                    agree2.setChecked(true);
                    agree3.setChecked(true);
                    agree4.setChecked(true);
                    agree5.setChecked(true);}
                else{
                    agree2.setChecked(false);
                    agree3.setChecked(false);
                    agree4.setChecked(false);
                    agree5.setChecked(false);
                }
            }
        });
        nextbutton = (Button) findViewById(R.id.nextbutton);
        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(agree1.isChecked()){
                    ischeck=true;
                }else {
                    ischeck = false;
                }
                if(agree2.isChecked()&&agree3.isChecked()&&agree4.isChecked()&&agree5.isChecked()){
                    ischeck2=true;
                }else {
                    ischeck2 = false;
                }
                if(ischeck && ischeck2){
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(getApplicationContext(),"전부 동의해주셔야 진행이 가능합니다",Toast.LENGTH_LONG).show();
                }
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),serviceclause_popup_activity.class);
                startActivity(intent);
            }
        });

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),informationclause_popup_avtivity.class);
                startActivity(intent);

            }
        });


    }
}

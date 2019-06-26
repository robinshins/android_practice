package com.example.database_listview;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    EditText editText ;// 이름
    EditText editText2 ;// 전화번호
    EditText editText3 ;// 나이
    RadioButton radioButton ;//남성
    RadioButton radioButton2 ;//여성
    RadioGroup radioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final MySQliteHandler handler = MySQliteHandler.open(getApplicationContext());


        textView = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.editText); // 이름
        editText2 = (EditText) findViewById(R.id.editText2); // 전화번호
        editText3 = (EditText) findViewById(R.id.editText3); // 나이
        radioButton = (RadioButton) findViewById(R.id.radioButton); //남성
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2);//여성
        Button button = (Button) findViewById(R.id.button);
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        radioGroup.setOnCheckedChangeListener(radiocheck);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString();
                String phonenumber = editText2.getText().toString();
                String agestr = editText3.getText().toString();
                int age = 0;
                String gender = null;
                Boolean pass = false;

                if(radioButton.isChecked()){
                    gender = "남";
                    println(gender);
                } else if(radioButton2.isChecked()){
                    gender = "여";
                    println(gender);
                }
                if(name.equals("") || phonenumber.equals("") || agestr.equals("") || gender.equals("")){
                    pass = false;
                }else{
                    age = Integer.parseInt(agestr);
                    pass =true;
                }
                println(pass.toString());

                if(pass){
                    handler.insert(name, phonenumber , age , gender);
                    println(pass.toString());
                    Intent intent = new Intent(getApplicationContext(),choose_membership.class);
                    startActivity(intent);
                } else{
                    Toast.makeText(getApplicationContext(),"양식을 다 채워주세요",Toast.LENGTH_LONG).show();
                }

            }

        });
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c = handler.select();
                while(c.moveToNext()){
                    int _id = c.getInt(c.getColumnIndex("id_"));
                    String name = c.getString(c.getColumnIndex("name"));
                    String phonenumber = c.getString(c.getColumnIndex("phonenumber"));
                    int age = c.getInt(c.getColumnIndex("age"));
                    String gender = c.getString(c.getColumnIndex("gender"));
                    println("뽑아온 데이터 : "+_id + " " + name + " " + phonenumber  + " " + age  + " " + gender);
                }
            }
        });

    }

    final RadioGroup.OnCheckedChangeListener radiocheck = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            Boolean manchecked = false;
            Boolean womanchecked = false;
            if(checkedId == R.id.radioButton){
                manchecked = true;
            } else if(checkedId == R.id.radioButton2){
                womanchecked = true;
            }
        }
    };

    public void println(String data){
        textView.append(data + "\n");
    }


}

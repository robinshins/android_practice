package com.example.database_listview;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class choose_payment extends AppCompatActivity {
    private Spinner month_spinner;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    TextView course_name;
    TextView course_price;
    Button button;
    int month;
    Button button2;
    CheckBox checkBox;
    MySQliteHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        handler = MySQliteHandler.open(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_payment);
        course_name =(TextView) findViewById(R.id.course_name) ;
        course_price =(TextView) findViewById(R.id.course_price) ;
        Intent intent = getIntent();
        final int price = intent.getIntExtra("price",0);
        DecimalFormat dc = new DecimalFormat("###,###,###,###");
        String pricestr = dc.format(price);
        course_name.setText("짐박스 휘트니스 "+ intent.getStringExtra("month")+"개월권");
        course_price.setText(pricestr+"원");
        arrayList = new ArrayList<>();
        arrayList.add("일시불");
        arrayList.add("2개월");
        arrayList.add("3개월");
        arrayList.add("4개월");
        arrayList.add("5개월");
        arrayList.add("6개월");
        arrayAdapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,
                arrayList);
        month_spinner = (Spinner)findViewById(R.id.month_spinner);
        month_spinner.setAdapter(arrayAdapter);
        month_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                month = i+1;

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        checkBox = (CheckBox) findViewById(R.id.payment_checkBox);

        button = (Button) findViewById(R.id.start_payment);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()){
                String uri = String.format( "appposw://card-approval?&authKey=1234&inputAmount=%s&inputMonth=%s&inputAmountEditable=false&runStrategy=without-input-view&inputApprovalNo=77&runMode=null&printStrategy=%s", price,month,"forced");
                Intent intent = new Intent(Intent.ACTION_MAIN, Uri.parse(uri));
                startActivityForResult(intent,333);}else{
                    Toast.makeText(getApplicationContext(),"동의에 체크를 해주셔야 결제 진행이 가능합니다",Toast.LENGTH_LONG).show();
                }

            }
        });

        button2 = (Button) findViewById(R.id.test_payment);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()){
                String uri = String.format( "appposw://card-approval?&authKey=1234&inputAmount=%s&inputMonth=%s&inputAmountEditable=false&runStrategy=without-input-view&inputApprovalNo=77&runMode=null&printStrategy=%s", 10,month,"forced");
                Intent intent = new Intent(Intent.ACTION_MAIN, Uri.parse(uri));
                startActivityForResult(intent,333);
                Log.d("할부개월",""+month);;}else{
                    Toast.makeText(getApplicationContext(),"동의에 체크를 해주셔야 결제 진행이 가능합니다",Toast.LENGTH_LONG).show();
                }

            }
        });



    }

    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("가입절차를 중단하고 메인화면으로 이동하시겠습니까?");
        builder.setPositiveButton("예",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(),start_Activity.class);
                        startActivity(intent);
                        Intent intent1 = getIntent();
                        String name = intent1.getStringExtra("name");
                        handler.delete(name);  // 이과정에서 백버튼을 누르면 데이터 베이스 삭제해야 함, 중복 데이터 베이터 베이스 생성 막기

                    }
                });
        builder.setNegativeButton("아니오",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

        builder.show();


    }
}

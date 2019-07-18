package com.example.database_listview;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        button = (Button) findViewById(R.id.start_payment);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = String.format( "appposw://card-approval?&authKey=1234&inputAmount=%s&inputMonth=%s&inputAmountEditable=false&runStrategy=without-input-view&inputApprovalNo=77&runMode=null&printStrategy=%s", price,month,"forced");
                Intent intent = new Intent(Intent.ACTION_MAIN, Uri.parse(uri));
                startActivityForResult(intent,333);

            }
        });

        button2 = (Button) findViewById(R.id.test_payment);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = String.format( "appposw://card-approval?&authKey=1234&inputAmount=%s&inputMonth=%s&inputAmountEditable=false&runStrategy=without-input-view&inputApprovalNo=77&runMode=null&printStrategy=%s", 10,month,"forced");
                Intent intent = new Intent(Intent.ACTION_MAIN, Uri.parse(uri));
                startActivityForResult(intent,333);
                Log.d("할부개월",""+month);

            }
        });



    }
}

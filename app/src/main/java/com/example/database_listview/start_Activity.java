package com.example.database_listview;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class start_Activity extends AppCompatActivity {
    Button signbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_);
        ImageView imageView = (ImageView) findViewById(R.id.startimageview);
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
        animationDrawable.start();
        signbutton = (Button) findViewById(R.id.signin_button);
        signbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),clausecheck_activity.class);
                startActivity(intent);
            }
        });
    }
}

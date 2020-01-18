package com.example.vizbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class navigation_header extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_header);

        Intent intent = getIntent();
        String text = intent.getStringExtra(Signup.EXTRA_TEXT);
        String text1 = intent.getStringExtra(Signup.EXTRA_TEXT1);


        TextView textView1 = (TextView) findViewById(R.id.nameView);
        TextView textView2 = (TextView) findViewById(R.id.proText);

        textView1.setText(text);
        textView2.setText(text1);
    }
}

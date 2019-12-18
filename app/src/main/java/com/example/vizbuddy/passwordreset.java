package com.example.vizbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class passwordreset extends AppCompatActivity implements View.OnClickListener{

    public ImageView backbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passwordreset);

        backbtn = (ImageView) findViewById(R.id.backbtn);
    }

    @Override
    public void onClick(View v) {
        if(v == backbtn){
            finish();
            startActivity(new Intent(this, login.class));
        }
    }
}

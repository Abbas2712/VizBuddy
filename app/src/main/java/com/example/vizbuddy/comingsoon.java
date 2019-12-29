package com.example.vizbuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class comingsoon extends AppCompatActivity implements View.OnClickListener{

    public ImageView back;
    ConstraintLayout soon;
    AnimationDrawable animationDrawable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comingsoon);

        back = (ImageView) findViewById(R.id.backbtn1);

        back.setOnClickListener(this);

        soon = (ConstraintLayout) findViewById(R.id.soonLayout);

        animationDrawable = (AnimationDrawable) soon.getBackground();
        animationDrawable.setEnterFadeDuration(1500);
        animationDrawable.setExitFadeDuration(1500);
        animationDrawable.start();

    }

    @Override
    public void onClick(View v) {

        if(v == back){
            finish();
            startActivity(new Intent(this,Homepage.class));
        }


    }
}

package com.example.vizbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

public class Homepage extends AppCompatActivity implements View.OnClickListener{

    public CardView creative;

    private DrawerLayout mDrawerLayout;
    SpaceNavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        navigationView = findViewById(R.id.space);

        navigationView.initWithSaveInstanceState(savedInstanceState);
        navigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_home_black));
        navigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_photo_camera));
        navigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_person));

        navigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                Toast.makeText(Homepage.this,"onCentreButtonClick", Toast.LENGTH_SHORT).show();
                navigationView.setCentreButtonSelectable(true);
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                Toast.makeText(Homepage.this, itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
                Toast.makeText(Homepage.this, itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
            }
        });


        creative = (CardView) findViewById(R.id.creativeMode);

        creative.setOnClickListener(this);

//        Toolbar toolbar = findViewById(R.id.tBar);
//        setSupportActionBar(toolbar);

//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close);
//        mDrawerLayout.addDrawerListener(toggle);
//        toggle.syncState();


//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View v) {
        if(v == creative){
            finish();
            startActivity(new Intent(this, comingsoon.class));
        }
    }


    @Override
    public void onBackPressed(){
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }

    }

//    public void setSupportActionBar(Toolbar supportActionBar) {
//        this.supportActionBar = supportActionBar;
//    }
}

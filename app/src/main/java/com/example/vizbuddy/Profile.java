package com.example.vizbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

public class Profile extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth firebaseAuth;
    public Button signOut;
    public TextView proText;
    SpaceNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();

        navigationView = findViewById(R.id.space);
        navigationView.initWithSaveInstanceState(savedInstanceState);
        navigationView.addSpaceItem(new SpaceItem("Home", R.drawable.ic_home_black));
        navigationView.addSpaceItem(new SpaceItem("Profile", R.drawable.ic_person));

        navigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                Toast.makeText(Profile.this,"Camera", Toast.LENGTH_SHORT).show();
                navigationView.setCentreButtonSelectable(true);
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                Toast.makeText(Profile.this, itemIndex + " home " + itemName, Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(Profile.this,Homepage.class);
//                startActivity(intent);
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
                Toast.makeText(Profile.this, itemIndex + " profile " + itemName, Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(Profile.this,Profile.class);
//                startActivity(intent);
            }
        });

        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, login.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();


        proText = (TextView) findViewById(R.id.proText);
        signOut = (Button) findViewById(R.id.signOut);

        proText.setText("Welcome "+ user.getEmail());

        signOut.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if(v == signOut){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, login.class));
        }

    }
}

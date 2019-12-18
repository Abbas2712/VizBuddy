package com.example.vizbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Profile extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth firebaseAuth;
    public Button signOut;
    public TextView proText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();

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

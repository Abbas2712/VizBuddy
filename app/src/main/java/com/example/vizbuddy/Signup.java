package com.example.vizbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity implements View.OnClickListener {

    public Button sgSignUp;
    public EditText name;
    public EditText sgEmail;
    public EditText sgPassword;
    public TextView alreadyAccount;
    public TextView sgFp;
    public FirebaseAuth firebaseAuth;

    RelativeLayout myLayout;
    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(), Homepage.class));
        }

        sgEmail = (EditText) findViewById(R.id.sgEmail);
        name = (EditText) findViewById(R.id.name);
        sgPassword = (EditText) findViewById(R.id.sgPassword);
        sgSignUp = (Button) findViewById(R.id.sgSignUp);
        alreadyAccount = (TextView) findViewById(R.id.alreadyAccount);
        sgFp = (TextView) findViewById(R.id.sgFp);

        sgSignUp.setOnClickListener(this);
        alreadyAccount.setOnClickListener(this);


        myLayout = (RelativeLayout) findViewById(R.id.myLayout);

        animationDrawable = (AnimationDrawable) myLayout.getBackground();
        animationDrawable.setEnterFadeDuration(4500);
        animationDrawable.setExitFadeDuration(4500);
        animationDrawable.start();
    }


    private void registerUser(){
        String uName = name.getText().toString().trim();
        String email = sgEmail.getText().toString().trim();
        String password = sgPassword.getText().toString().trim();

        if(TextUtils.isEmpty(uName)){
            Toast.makeText(this, "Please Enter Name", Toast.LENGTH_SHORT).show();
        }

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Signup.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(Signup.this, "Could not Register... Please Try Again ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    @Override
    public void onClick(View v) {
        if(v == sgSignUp){
            registerUser();
        }
        if(v == alreadyAccount){
            finish();
            startActivity(new Intent(this, login.class));
        }
    }
}


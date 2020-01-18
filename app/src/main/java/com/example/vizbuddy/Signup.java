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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_TEXT = "com.example.application.vizbddy.EXTRA_TEXT";
    public static final String EXTRA_TEXT1 = "com.example.application.vizbddy.EXTRA_TEXT";
    public Button sgSignUp;
    public EditText sgName;
    public EditText sgEmail;
    public EditText sgPassword;
    public TextView alreadyAccount;
    public FirebaseAuth firebaseAuth;

    DatabaseReference databaseUserInfo;

    RelativeLayout myLayout;
    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseUserInfo = FirebaseDatabase.getInstance().getReference("userInfo");

        if(firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(), Profile.class));
        }

        sgEmail = (EditText) findViewById(R.id.sgEmail);
        sgName = (EditText) findViewById(R.id.name);
        sgPassword = (EditText) findViewById(R.id.sgPassword);
        sgSignUp = (Button) findViewById(R.id.sgSignUp);
        alreadyAccount = (TextView) findViewById(R.id.alreadyAccount);

        sgSignUp.setOnClickListener(this);
        alreadyAccount.setOnClickListener(this);


        myLayout = (RelativeLayout) findViewById(R.id.myLayout);

        animationDrawable = (AnimationDrawable) myLayout.getBackground();
        animationDrawable.setEnterFadeDuration(1500);
        animationDrawable.setExitFadeDuration(1500);
        animationDrawable.start();
    }

    public void UserInfo(){
        String uName = sgName.getText().toString().trim();
        String email = sgEmail.getText().toString().trim();

        if(TextUtils.isEmpty(uName)){
            Toast.makeText(this, "Please Enter Name", Toast.LENGTH_SHORT).show();
            return;
        }else {
            String id = databaseUserInfo.push().getKey();
            userInfo userInfo = new userInfo(id,uName,email);
            databaseUserInfo.child(id).setValue(userInfo);
        }

    }


    private void registerUser(){
        String email = sgEmail.getText().toString().trim();
        String password = sgPassword.getText().toString().trim();


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
                            finish();
                            startActivity(new Intent(getApplicationContext(), Homepage.class));
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
            openActivity();
            UserInfo();
        }
        if(v == alreadyAccount){
            finish();
            startActivity(new Intent(this, login.class));
        }
    }

    public void openActivity() {
        EditText editText1 = (EditText) findViewById(R.id.name);
        String text1 = editText1.getText().toString();

        EditText editText2 = (EditText) findViewById(R.id.sgEmail);
        String text2 = editText2.getText().toString();

        Intent intent = new Intent(this, navigation_header.class);
        intent.putExtra(EXTRA_TEXT, text1);
        intent.putExtra(EXTRA_TEXT1, text2);
        startActivity(intent);
    }

}


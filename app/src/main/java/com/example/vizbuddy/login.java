package com.example.vizbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.agrawalsuneet.dotsloader.loaders.BounceLoader;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_TEXT = "com.example.application.vizbddy.EXTRA_TEXT";
    public static final String EXTRA_TEXT1 = "com.example.application.vizbddy.EXTRA_TEXT";
    public Button signIn;
    public EditText lgEmail;
    public EditText lgPassword;
    public TextView noAccount;
    public TextView igFp;
    public BounceLoader bounceLoader;
    public FirebaseAuth firebaseAuth;

    RelativeLayout myLayout;
    AnimationDrawable animationDrawable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), Homepage.class));
            finish();
        }

        bounceLoader = (BounceLoader) findViewById(R.id.proLoad);
        signIn = (Button) findViewById(R.id.signIn);
        lgEmail = (EditText) findViewById(R.id.lgEmail);
        lgPassword = (EditText) findViewById(R.id.lgPassword);
        noAccount = (TextView) findViewById(R.id.noAccount);
        igFp = (TextView) findViewById(R.id.igFp);

        signIn.setOnClickListener(this);
        noAccount.setOnClickListener(this);
        igFp.setOnClickListener(this);

        myLayout = (RelativeLayout) findViewById(R.id.myLayout);

        animationDrawable = (AnimationDrawable) myLayout.getBackground();
        animationDrawable.setEnterFadeDuration(1500);
        animationDrawable.setExitFadeDuration(1500);
        animationDrawable.start();

    }//
//        BounceLoader bounceLoader = new BounceLoader(this,
//                60,
//                ContextCompat.getColor(this, R.color.loader_selected),
//                true,
//                ContextCompat.getColor(this, R.color.loader_selected));
//
//        bounceLoader.setAnimDuration(1000);
//        bounceLoader.addView(bounceLoader);


    private void userLogin(){
        String email = lgEmail.getText().toString().trim();
        String password = lgPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                      if(task.isSuccessful()){
                          finish();
                        startActivity(new Intent(getApplicationContext(), Homepage.class));
                          Toast.makeText(login.this, "Welcome Back!", Toast.LENGTH_SHORT).show();
                      }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if(v == signIn){
            userLogin();
//            openActivity();
        }
        if(v == noAccount){
            finish();
            startActivity(new Intent(this, Signup.class));
        }

        if(v == igFp){
            finish();
            startActivity(new Intent(this,passwordreset.class));
        }
    }

//    public void openActivity() {
//        EditText editText1 = (EditText) findViewById(R.id.name);
//        String text1 = editText1.getText().toString();
//
//        EditText editText2 = (EditText) findViewById(R.id.lgEmail);
//        String text2 = editText2.getText().toString();
//
//        Intent intent = new Intent(this, navigation_header.class);
//        intent.putExtra(EXTRA_TEXT, text1);
//        intent.putExtra(EXTRA_TEXT1, text2);
//        startActivity(intent);
//    }
}

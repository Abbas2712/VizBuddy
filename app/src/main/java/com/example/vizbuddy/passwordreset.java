package com.example.vizbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class passwordreset extends AppCompatActivity implements View.OnClickListener{

    public TextView noAccountFp;
    public Button fpbtn;
    public EditText fpemailid;
    public ImageView backBtn;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passwordreset);

        firebaseAuth = FirebaseAuth.getInstance();

        noAccountFp = (TextView) findViewById(R.id.noAccountFp);
        backBtn = (ImageView) findViewById(R.id.backbtn);
        fpemailid = (EditText) findViewById(R.id.fpemailid);
        fpbtn = (Button) findViewById(R.id.fpbtn);

        fpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = fpemailid.getText().toString().trim();

                if (username.equals("")){
                    Toast.makeText(passwordreset.this, "Enter your email", Toast.LENGTH_SHORT).show();
                }else{
                    firebaseAuth.sendPasswordResetEmail(username).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(passwordreset.this, "Password Reset Email Sent!!", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(passwordreset.this, login.class));
                            }else{
                                Toast.makeText(passwordreset.this, "Error In Sending Password Reset Email", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        if(v == backBtn){
            finish();
            startActivity(new Intent(this, login.class));
        }

        if(v == noAccountFp){
            finish();
            startActivity(new Intent(this, Signup.class));
        }
    }
}

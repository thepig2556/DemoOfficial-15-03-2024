package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SecondActivity extends AppCompatActivity {
    FirebaseAuth auth;
    Button btnSignout, btnBack;
    TextView tvUserEmail;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        auth=FirebaseAuth.getInstance();
        btnSignout=findViewById(R.id.btnSignout);
        btnBack=findViewById(R.id.btnBack);
        tvUserEmail=findViewById(R.id.tvUserEmail);
        user=auth.getCurrentUser();
        if (user==null)
        {
            Intent intent = new Intent(getApplicationContext(), activity_login.class);
            startActivity(intent);
        }
        else {
            tvUserEmail.setText(user.getEmail());
        }
        btnSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), activity_login.class);
                startActivity(intent);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
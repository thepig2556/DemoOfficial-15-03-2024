package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class activity_register extends AppCompatActivity {
    private EditText useredit, passedit;
    private Button  btncreate, btnthoat;
    private FirebaseAuth mAuth;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        useredit = findViewById(R.id.dangnhap);
        passedit = findViewById(R.id.matkhau);

        btncreate = findViewById(R.id.btncreate);
        btnthoat = findViewById(R.id.btnthoat);
        mAuth = FirebaseAuth.getInstance();

        btncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }


        });
        btnthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_register.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }


    private void register() {
        String user, pass;
        user = useredit.getText().toString();
        pass = passedit.getText().toString();
        if(TextUtils.isEmpty(user)){
            Toast.makeText(this,"Email không được trống",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Mật khẩu không được trống",Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(activity_register.this, "Đăng ký thành công, vui lòng xác nhận Email", Toast.LENGTH_SHORT).show();
                                useredit.setText("");
                                passedit.setText("");
                            }else {
                                Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                            }
                        }
                    });



                }
                else{
                    Toast.makeText(getApplicationContext(),"ĐĂNG KÝ THẤT BẠI", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InsertAcitivity extends AppCompatActivity {
    EditText nameAdd,authorAdd,imgAdd;
    Button btnAdd, btnBack;
    DatabaseReference mangaDbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_acitivity);

        nameAdd = findViewById(R.id.nameAdd);
        imgAdd = findViewById(R.id.imgAdd);
        authorAdd = findViewById(R.id.authorAdd);
        btnAdd = findViewById(R.id.btnAdd);
        btnBack = findViewById(R.id.btnBack);
        mangaDbRef = FirebaseDatabase.getInstance().getReference().child("Data");
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertManga();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InsertAcitivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void insertManga() {
        String name = nameAdd.getText().toString();
        String img = imgAdd.getText().toString();
        String author = authorAdd.getText().toString();
        String id = mangaDbRef.push().getKey();
        Model manga = new Model(id,name,img,author);
        assert id != null;
        mangaDbRef.child(id).setValue(manga);
        Toast.makeText(this, "Insert Succesful", Toast.LENGTH_SHORT).show();
        nameAdd.setText("");
        imgAdd.setText("");
        authorAdd.setText("");
    }

}
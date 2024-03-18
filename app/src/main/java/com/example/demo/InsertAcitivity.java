package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.demo.object.Model;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InsertAcitivity extends AppCompatActivity {
    EditText nameAdd,authorAdd,imgAdd, viewAdd;
    Button btnAdd, btnBack;
    DatabaseReference mangaDbRef;
    long maxid=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_acitivity);

        nameAdd = findViewById(R.id.nameAdd);
        imgAdd = findViewById(R.id.imgAdd);
        authorAdd = findViewById(R.id.authorAdd);
        viewAdd = findViewById(R.id.viewAdd);
        btnAdd = findViewById(R.id.btnAdd);
        btnBack = findViewById(R.id.btnBack);
        mangaDbRef = FirebaseDatabase.getInstance().getReference().child("Data");
        mangaDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {
                    maxid=snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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
        String id = String.valueOf(maxid);
        String view = viewAdd.getText().toString();
        Model manga = new Model(id,name,img,author,view);
        assert id != null;
        mangaDbRef.child(id).setValue(manga);
        Toast.makeText(this, "Insert Succesful", Toast.LENGTH_SHORT).show();
        nameAdd.setText("");
        imgAdd.setText("");
        authorAdd.setText("");
        viewAdd.setText("");
    }
}
package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.demo.listchap.ListChapterActivity;
import com.example.demo.object.Model;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class MainActivity extends AppCompatActivity {
    LinearLayoutManager mLinearLayoutManager;
    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;
    FirebaseRecyclerAdapter<Model,ViewHolder> firebaseRecyclerAdapter;
    FirebaseRecyclerOptions<Model>options;
    EditText inputSearch;
    LinearLayout linearItem;
    //
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLinearLayoutManager=new LinearLayoutManager(this);
        mLinearLayoutManager.setReverseLayout(true);
        inputSearch=findViewById(R.id.inputSearch);
        mLinearLayoutManager.setStackFromEnd(true);
        mRecyclerView=findViewById(R.id.recyclerView);
        mFirebaseDatabase=FirebaseDatabase.getInstance();
        mDatabaseReference= mFirebaseDatabase.getReference("Data");
        showData("");
        setClick();
//        linearItem=findViewById(R.id.linear_item);
        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString()!=null)
                {
                    showData(s.toString());
                }
                else{
                    showData("");
                }
            }
        });
    }

    private void showData(String data) {
        Query query = mDatabaseReference.orderByChild("title").startAt(data).endAt(data+"\uf8ff");
//        Query quary = mDatabaseReference.orderByChild("author").startAt(data).endAt(data+"\uf8ff");
        options = new FirebaseRecyclerOptions.Builder<Model>().setQuery(query,Model.class).build();
//        options = new FirebaseRecyclerOptions.Builder<Model>().setQuery(quary,Model.class).build();
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Model, ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder holder, final int position, @NonNull Model model) {
                holder.setDetails(getApplicationContext(),model.getTitle(), model.getImage(), model.getAuthor(), model.getLuotxem());

//                Click chapter
                //Set on Click Item List Chapter
holder.mview.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent( MainActivity.this,ListChapterActivity.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("key",model);
        intent.putExtras(bundle);
        startActivity(intent);
//        dữ iệu ảo list chapter

    }
});
            }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
                ViewHolder viewHolder=new ViewHolder(itemView);
                viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
         //               Toast.makeText(MainActivity.this,"Hello",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
//                        Toast.makeText(MainActivity.this,"Hello",Toast.LENGTH_SHORT).show();
                    }
                });
                return viewHolder;
            }
        };
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        firebaseRecyclerAdapter.startListening();
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(firebaseRecyclerAdapter!=null){

            firebaseRecyclerAdapter.startListening();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mnu = getMenuInflater();
        mnu.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.mnAdd){
            Intent intent = new Intent(this, InsertAcitivity.class);
            startActivity(intent);
        }
        if(item.getItemId()==R.id.mnRetrieve){
            Intent intent = new Intent(this, RetrieveDataActivity.class);
            startActivity(intent);
        }
        if(item.getItemId()==R.id.logout){
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    private void setClick()
    {


}

}
package com.example.videoplayer;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ImageActivity extends AppCompatActivity {
    ListView imageList;
    String location,name;
    CustomImageAdapter customImageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        Intent in = getIntent();
        location=in.getStringExtra("Location");
        name=in.getStringExtra("Name");
        String locationArray[] = location.split(",");
        String nameArray[] = name.split(",");
        customImageAdapter=new CustomImageAdapter(this,locationArray,nameArray);
        imageList=findViewById(R.id.imageList);
        imageList.setAdapter(customImageAdapter);

    }
}
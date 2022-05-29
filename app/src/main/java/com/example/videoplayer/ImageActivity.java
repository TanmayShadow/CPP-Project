package com.example.videoplayer;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class ImageActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    ListView imageList;
    String location,name,iName;
    CustomImageAdapter customImageAdapter;
    BottomNavigationView bmv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        Objects.requireNonNull(getSupportActionBar()).hide();

        bmv= findViewById(R.id.bmv);
        bmv.setSelectedItemId(R.id.images);
        bmv.setOnNavigationItemSelectedListener(this);

        Intent in = getIntent();
        location=in.getStringExtra("Location");
        name=in.getStringExtra("Name");
        iName=in.getStringExtra("iName");
        String locationArray[] = location.split(",");
        String nameArray[] = name.split(",");
        String iNameArray[] = iName.split(",");
        customImageAdapter=new CustomImageAdapter(this,locationArray,nameArray,iNameArray);
        imageList=findViewById(R.id.imageList);
        imageList.setAdapter(customImageAdapter);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.profile:
                Intent intent =new Intent(this,PreUserProfileActivity.class);
                startActivity(intent);
                return true;
            case R.id.videos:
                Intent i = new Intent(this,PreVideoActivity.class);
                startActivity(i);
                return true;

            case R.id.images:
                Intent i1 = new Intent(this,PreImageActivity.class);
                startActivity(i1);
                return true;

            case R.id.audios:
                Intent i2 = new Intent(this,PreAudioActivity.class);
                startActivity(i2);
                return true;
        }
        return false;
    }

    public void goBackButton(View view) {
        Intent i = new Intent(this,PreVideoActivity.class);
        startActivity(i);
    }
}
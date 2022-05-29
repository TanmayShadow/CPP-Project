package com.example.videoplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class MyContentActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    TextView title;
    ListView myContentList;
    MyContentAdapter mc;
    BottomNavigationView bmv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_my_content);
        title=findViewById(R.id.MyContentText);
        myContentList=findViewById(R.id.MyContentList);
        bmv= findViewById(R.id.bmv);
        bmv.setSelectedItemId(R.id.profile);
        bmv.setOnNavigationItemSelectedListener(this);
        Intent i = getIntent();
        String titleName = i.getStringExtra("title");
        String imageName[] = i.getStringArrayExtra("imageName");
        String imageLocation[] = i.getStringArrayExtra("imageLocation");
        mc = new MyContentAdapter(this,imageLocation,imageName);
        title.setText(titleName);

        myContentList.setAdapter(mc);
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

    public void goBack(View view) {
        Intent i =new Intent(this,PreUserProfileActivity.class);
        startActivity(i);
    }
}
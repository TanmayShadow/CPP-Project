package com.example.videoplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class ShowImageActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView bmv;
    ImageView im;
    TextView tv;
    Button b;
    String name,location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);
        Objects.requireNonNull(getSupportActionBar()).hide();
        bmv= findViewById(R.id.bmv);
        bmv.setSelectedItemId(R.id.images);
        bmv.setOnNavigationItemSelectedListener(this);
        Intent i = getIntent();


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
}
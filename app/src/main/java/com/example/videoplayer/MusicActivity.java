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

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class MusicActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView bmv;
    String location,aname;
    String locationA[],anameA[];
    MusicAdapter ma;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        Objects.requireNonNull(getSupportActionBar()).hide();

        Intent i = getIntent();
        location=i.getStringExtra("Location");
        aname=i.getStringExtra("AName");

        locationA = location.split(",");
        anameA = aname.split(",");


        ListView listViewMusic = findViewById(R.id.listViewMusic);
        ma = new MusicAdapter(this,locationA,anameA);
        listViewMusic.setAdapter(ma);

        bmv= findViewById(R.id.bmv);
        bmv.setSelectedItemId(R.id.audios);
        bmv.setOnNavigationItemSelectedListener(this);

    }
    public void goToParent(View view)
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);

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

    @Override
    protected void onPause() {
        super.onPause();
        if(ma.player.isPlaying())
            ma.player.pause();
    }
}
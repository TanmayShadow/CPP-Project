package com.example.videoplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MusicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        RecyclerView music = (RecyclerView) findViewById(R.id.musicRecycler);
        music.setLayoutManager(new LinearLayoutManager(this));
        String[] musicName = {"greatful","guitar","relax"};
        music.setAdapter(new MusicAdapter(musicName));
    }
    public void goToParent(View view)
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);

    }

}
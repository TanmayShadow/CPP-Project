package com.example.videoplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class PreVideoActivity extends AppCompatActivity {

    BackgroundMYSQL backgroundMYSQL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_video);
        backgroundMYSQL = new BackgroundMYSQL(this);
        backgroundMYSQL.execute("getVideo");
    }
}
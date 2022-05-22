package com.example.videoplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String MSG = "com.example.videoplayer.User";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void showVideoActivity(View view)
    {
            Intent intent = new Intent(this,PreVideoActivity.class);
            startActivity(intent);
    }
    public void showMusic(View view)
    {
        Intent intent = new Intent(this,MusicActivity.class);
        startActivity(intent);
    }
    public void showNewMusic(View view)
    {
        Intent intent = new Intent(this,MusicLayout.class);
        startActivity(intent);
    }

    public void signup(View view) {
        Intent i =new Intent(this,SignupActivity.class);
        startActivity(i);
    }

    public void login(View view) {
        Intent i =new Intent(this,LoginActivity.class);
        startActivity(i);
    }


    public void upload(View view) {
        Intent i = new Intent(this,UploadActivity.class);
        startActivity(i);
    }

    public void showImage(View view) {
        Intent i = new Intent(this,PreImageActivity.class);
        startActivity(i);
    }
}
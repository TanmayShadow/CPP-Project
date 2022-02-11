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
            String username = "Login Successful";
            Intent intent = new Intent(this,VideoActivity.class);
            intent.putExtra(MSG,username);
            startActivity(intent);

    }
}
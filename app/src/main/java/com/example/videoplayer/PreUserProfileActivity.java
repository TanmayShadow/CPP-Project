package com.example.videoplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.Objects;

public class PreUserProfileActivity extends AppCompatActivity {
    SQLiteDatabaseClass db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_user_profile);
        Objects.requireNonNull(getSupportActionBar()).hide();
        db=new SQLiteDatabaseClass(this);
        int id = db.getLogin();
        Intent i;
        if(id!=-1)
        {
            BackgroundMYSQL backgroundMYSQL = new BackgroundMYSQL(this);
            backgroundMYSQL.execute("getProfile",""+id);
        }
        else
        {
            i = new Intent(this, LoginActivity.class);
            startActivity(i);
        }

    }
}
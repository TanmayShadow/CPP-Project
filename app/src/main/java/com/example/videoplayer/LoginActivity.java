package com.example.videoplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    EditText email,pass;
    SQLiteDatabaseClass db;
    BottomNavigationView bmv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Objects.requireNonNull(getSupportActionBar()).hide();
        email = findViewById(R.id.login_email);
        pass = findViewById(R.id.login_pass);
        db = new SQLiteDatabaseClass(this);
        int id = db.getLogin();
//        Toast.makeText(this, "ID:"+id, Toast.LENGTH_SHORT).show();
        if(id!=-1)
        {
            Intent i =new Intent(this,UserProfileActivity.class);
            startActivity(i);
        }
        bmv= findViewById(R.id.bmv);
        bmv.setSelectedItemId(R.id.profile);
        bmv.setOnNavigationItemSelectedListener(this);
    }
    public void login(View v)
    {
        String e = email.getText().toString();
        String p = pass.getText().toString();
        String type="login";
        BackgroundMYSQL backgroundMYSQL = new BackgroundMYSQL(this);
        backgroundMYSQL.execute(type,e,p);

    }
    public void signup(View v)
    {
        Intent i = new Intent(this,SignupActivity.class);
        startActivity(i);
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

    public void forgot(View view) {
        Intent i3 = new Intent(this,ForgotPasswordActivity.class);
        startActivity(i3);
    }
}
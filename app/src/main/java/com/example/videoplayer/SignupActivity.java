package com.example.videoplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Objects;

public class SignupActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    EditText sign_mail,sign_pass,sign_fname,sign_lname;
    BottomNavigationView bmv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Objects.requireNonNull(getSupportActionBar()).hide();
        sign_mail=findViewById(R.id.signup_email);
        sign_pass=findViewById(R.id.signup_pass);
        sign_fname=findViewById(R.id.signup_fname);
        sign_lname=findViewById(R.id.signup_lname);
        bmv= findViewById(R.id.bmv);
        bmv.setSelectedItemId(R.id.profile);
        bmv.setOnNavigationItemSelectedListener(this);
    }


    public void sign(View view) {
        String mail=sign_mail.getText().toString();
        String pass=sign_pass.getText().toString();
        String fname=sign_fname.getText().toString();
        String lname=sign_lname.getText().toString();
        String type="signup";
        BackgroundMYSQL backgroundMYSQL = new BackgroundMYSQL(this);
        backgroundMYSQL.execute(type,mail,pass,fname,lname);
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
package com.example.videoplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class SignupActivity extends AppCompatActivity {

    EditText sign_mail,sign_pass,sign_fname,sign_lname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        sign_mail=findViewById(R.id.signup_email);
        sign_pass=findViewById(R.id.signup_pass);
        sign_fname=findViewById(R.id.signup_fname);
        sign_lname=findViewById(R.id.signup_lname);
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
}
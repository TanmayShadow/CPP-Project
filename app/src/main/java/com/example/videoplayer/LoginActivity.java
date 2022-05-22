package com.example.videoplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText email,pass;
    SQLiteDatabaseClass db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.login_email);
        pass = findViewById(R.id.login_pass);
        db = new SQLiteDatabaseClass(this);
        int id = db.getLogin();
        Toast.makeText(this, "ID:"+id, Toast.LENGTH_SHORT).show();
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
}
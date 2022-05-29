package com.example.videoplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Objects;

public class ForgotPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        Objects.requireNonNull(getSupportActionBar()).hide();
    }

    public void forgotPassword(View view) {
        EditText user = findViewById(R.id.editTextTextPersonName);
        BackgroundMYSQL backgroundMYSQL = new BackgroundMYSQL(this);
        backgroundMYSQL.execute("forgotPassword",""+user.getText().toString());
    }
}
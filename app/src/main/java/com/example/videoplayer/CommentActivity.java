package com.example.videoplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;

public class CommentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width),(int)(height*.8));
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.BOTTOM;
        params.x = 0;
        params.y = 0;
        getWindow().setAttributes(params);

        RecyclerView comments = (RecyclerView) findViewById(R.id.recycler);
        comments.setLayoutManager(new LinearLayoutManager(this));
        String[] name={"Tanmay","Rihan","Aftab","Ammar","Yash","Amit","Sohel","Pranav","Pranav Shinde"};
        String[] allComment={"good","very good","nice","not so bad","I dont like it","i like it","good voice","good acting","not good sound"};
        String[] images={"sss","sss","sss","sss","sss","sss","sss","sss","sss"};
        comments.setAdapter(new RecyclerAdapter(images,name,allComment));

    }
}
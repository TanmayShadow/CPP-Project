package com.example.videoplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class more_options extends AppCompatActivity {

    Button about,report,submit;
    TextView date,description,report_title,report_info;
    EditText report_description;
    String des,vid;
    BackgroundMYSQL backgroundMYSQL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_options);

        Intent i = getIntent();
        des = i.getStringExtra("des");
        vid = i.getStringExtra("vid");

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width),(int)(height*.5));
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.BOTTOM;
        params.x = 0;
        params.y = 0;
        getWindow().setAttributes(params);

        about = (Button) findViewById(R.id.button8);
        report = (Button) findViewById(R.id.button9);
        date = (TextView) findViewById(R.id.textView8);
        description = (TextView) findViewById(R.id.textView9);
        report_title = (TextView) findViewById(R.id.textView10);
        report_info = (TextView) findViewById(R.id.textView12);
        submit = (Button) findViewById(R.id.button10);
        report_description = (EditText) findViewById(R.id.editTextTextMultiLine);

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAbout(view);
            }
        });

        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showReport(view);
            }
        });
    }
    //showAbout function
    public void showAbout(View view)
    {
        about.setVisibility(View.INVISIBLE);
        report.setVisibility(View.INVISIBLE);
        date.setVisibility(View.INVISIBLE);
        description.setVisibility(View.VISIBLE);
        description.setText(des);
    }

    //showReport function
    public  void showReport(View view)
    {
        about.setVisibility(View.INVISIBLE);
        report.setVisibility(View.INVISIBLE);
        report_title.setVisibility(View.VISIBLE);
        report_info.setVisibility(View.VISIBLE);
        report_description.setVisibility(View.VISIBLE);
        submit.setVisibility(View.VISIBLE);
    }

    public void submitReport(View view) {
        String reason=report_description.getText().toString();
        backgroundMYSQL = new BackgroundMYSQL(this);
        SQLiteDatabaseClass sqLiteDatabaseClass = new SQLiteDatabaseClass(this);
        String uid = String.valueOf(sqLiteDatabaseClass.getLogin());
        if(!uid.equals("-1"))
        {
            backgroundMYSQL.execute("report",vid,uid,reason);
        }
        else
            Toast.makeText(this, "Login Required", Toast.LENGTH_SHORT).show();
    }
}
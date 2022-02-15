package com.example.videoplayer;

import androidx.appcompat.app.AppCompatActivity;

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
    TextView username,date,description,report_title,report_info;
    EditText report_description;
    ImageView about_icon,report_icon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_options);

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
        username = (TextView) findViewById(R.id.textView7);
        date = (TextView) findViewById(R.id.textView8);
        description = (TextView) findViewById(R.id.textView9);
        about_icon = (ImageView) findViewById(R.id.imageView7);
        report_icon = (ImageView) findViewById(R.id.imageView10);
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
        about_icon.setVisibility(View.INVISIBLE);
        report_icon.setVisibility(View.INVISIBLE);
        username.setVisibility(View.VISIBLE);
        date.setVisibility(View.VISIBLE);
        description.setVisibility(View.VISIBLE);
    }

    //showReport function
    public  void showReport(View view)
    {
        about.setVisibility(View.INVISIBLE);
        report.setVisibility(View.INVISIBLE);
        about_icon.setVisibility(View.INVISIBLE);
        report_icon.setVisibility(View.INVISIBLE);
        report_title.setVisibility(View.VISIBLE);
        report_info.setVisibility(View.VISIBLE);
        report_description.setVisibility(View.VISIBLE);
        submit.setVisibility(View.VISIBLE);
    }
}
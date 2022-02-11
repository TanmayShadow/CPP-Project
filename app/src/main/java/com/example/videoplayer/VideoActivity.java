package com.example.videoplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.IOException;

public class VideoActivity extends AppCompatActivity {

    int videoStatus;
    TextView likeNo;
    ImageView like;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        //hiding actionbar

        
        //Getting the intent
        Intent intent = getIntent();
        String user = intent.getStringExtra(MainActivity.MSG);

        //Getting video height and width
        MediaPlayer mp ;
        mp = MediaPlayer.create(this,R.raw.newtest);
        int width = mp.getVideoWidth();
        int height = mp.getVideoHeight();
        Toast.makeText(this, "Width:"+width, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Height:"+height, Toast.LENGTH_SHORT).show();

        //Adding video to the VideoView
        VideoView videoView = findViewById(R.id.videoView);
        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.newtest);
        videoView.start();
        videoStatus= 1;

        //Adding onclick listener to VideoView
        videoView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if(videoStatus==1)
                {
                    videoView.pause();
                    videoStatus=0;
                }
                else {
                    videoView.start();
                    videoStatus = 1;
                }
            }
        });

        //Adding onclick listener to the like image
        like = findViewById(R.id.imageView);
        like.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                checkLike(view);
            }
        });

    }
    public void checkLike(View view)
    {
        ImageView like = (ImageView) findViewById(R.id.imageView);
        String check = like.getTag().toString();
        TextView likeNo = findViewById(R.id.textView4);
        int no = Integer.parseInt(likeNo.getText().toString());
        if(check.equals("0"))
        {
            like.setTag("1");
            like.setImageResource(R.drawable.smallliked1);
            no=no+1;
            likeNo.setText(""+no);
        }
        else
        {
            like.setTag("0");
            like.setImageResource(R.drawable.smallunliked1);
            no=no-1;
            likeNo.setText(""+no);
        }

    }
    public void goToParent(View view)
    {
        Intent parentIntent = new Intent(this,MainActivity.class);
        startActivity(parentIntent);
    }

}
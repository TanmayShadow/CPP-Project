package com.example.videoplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.IOException;

public class VideoActivity extends AppCompatActivity {

    int videoStatus;
    ImageView like, dislike, likeHeartImg;
    boolean likeStatus=false, dislikeStatus=false;
    Animation scale,rotate;
    VideoView videoView;
    DisplayMetrics displayMetrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        
        //Getting the intent
        Intent intent = getIntent();
        String user = intent.getStringExtra(MainActivity.MSG);

        //Creating objects
        dislike = findViewById(R.id.imageView3);
        likeHeartImg = findViewById(R.id.imageView9);
        scale = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.scale);
        rotate = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.small_rotate);
        displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenHeight = displayMetrics.heightPixels;
        int screenWidth = displayMetrics.widthPixels;


        //Getting video height and width
        MediaPlayer mp ;
        mp = MediaPlayer.create(this,R.raw.newtest);
        int width = mp.getVideoWidth();
        int height = mp.getVideoHeight();
        Toast.makeText(this, "Width:"+width, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Height:"+height, Toast.LENGTH_SHORT).show();


        //Adding video to the VideoView
        videoView = findViewById(R.id.videoView);
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

        //Adding onTouch and double tap listener
        videoView.setOnTouchListener(new View.OnTouchListener()
        {
            GestureDetector gestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener()
            {


                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                    float diff = e2.getY() - e1.getY();
                    if(diff  > 0)
                    {
                        Toast.makeText(VideoActivity.this, "Swipe down...", Toast.LENGTH_SHORT).show();
                        //take the link of next video from the database and set it
                        //this is temporary
                        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.newtest);
                        videoView.start();
                        videoStatus= 0;
                        //get the like status for the video from the database
                        //this is temporary
                        like.setTag("0");
                        like.setImageResource(R.drawable.new_like_thumb_white);
                        likeStatus=false;
                        //dislike status
                        dislike.setImageResource(R.drawable.ic_baseline_thumb_down_white);
                        dislike.setTag("0");
                        dislikeStatus=false;
                    }
                    else
                    {
                        Toast.makeText(VideoActivity.this, "Swipe up...", Toast.LENGTH_SHORT).show();
                        //take the link of next video from the database and set it
                        //this is temporary
                        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.smallshortvideo);
                        videoView.start();
                        videoStatus= 0;
                        //get the like status for the video from the database
                        //this is temporary
                        like.setTag("0");
                        like.setImageResource(R.drawable.new_like_thumb_white);
                        likeStatus=false;
                        //dislike status
                        dislike.setImageResource(R.drawable.ic_baseline_thumb_down_white);
                        dislike.setTag("0");
                        dislikeStatus=false;

                    }
                    return super.onFling(e1, e2, velocityX, velocityY);
                }

                @Override
                public boolean onDoubleTap(MotionEvent e) {
                    //Adding animation to like image

                    likeHeartImg.setVisibility(View.VISIBLE);
                    likeHeartImg.startAnimation(scale);
                    likeHeartImg.setVisibility(View.INVISIBLE);
                    //Changing the upThumb (like button)
                    if(dislikeStatus)
                    {
                        dislike.setImageResource(R.drawable.ic_baseline_thumb_down_white);
                        dislike.setTag("0");
                        dislikeStatus=false;
                    }
                    like.setTag("1");
                    like.setImageResource(R.drawable.new_like_thumb);
                    like.startAnimation(rotate);
                    likeStatus=true;
                    return super.onDoubleTap(e);
                }
            });
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) 
            {
                gestureDetector.onTouchEvent(motionEvent);

                return false;
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


        if(check.equals("0"))
        {
            if(dislikeStatus)
            {
                dislike.setImageResource(R.drawable.ic_baseline_thumb_down_white);
                dislike.setTag("0");
                dislikeStatus=false;
            }
            like.setTag("1");
            like.setImageResource(R.drawable.new_like_thumb);
            like.startAnimation(rotate);
            likeStatus=true;
        }
        else
        {
            like.setTag("0");
            like.setImageResource(R.drawable.new_like_thumb_white);
            likeStatus=false;
        }

    }

    public void checkDislike(View view)
    {
        String tag = dislike.getTag().toString();
        if(tag.equals("0"))
        {
            if(likeStatus)
            {
                like.setTag("0");
                like.setImageResource(R.drawable.new_like_thumb_white);
                likeStatus=false;
            }
            dislike.setImageResource(R.drawable.ic_baseline_thumb_down_24);
            dislike.setTag("1");
            dislikeStatus=true;
        }
        else
        {
            dislike.setImageResource(R.drawable.ic_baseline_thumb_down_white);
            dislike.setTag("0");
            dislikeStatus=false;
        }

    }

    //showMore function
    public void showMore(View view)
    {
        Intent popUpWindowIntent = new Intent(this,more_options.class);
        startActivity(popUpWindowIntent);
    }

    //showComment fundtion
    public void showComment(View view)
    {
        Intent commentIntent = new Intent(this,CommentActivity.class);
        startActivity(commentIntent);
    }
    public void goToParent(View view)
    {
        Intent parentIntent = new Intent(this,MainActivity.class);
        startActivity(parentIntent);
    }

}
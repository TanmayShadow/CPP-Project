package com.example.videoplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class UserProfileActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    SQLiteDatabaseClass sql;
    int id;
    TextView username,imageCount,followersCount,followingCount;
    BottomNavigationView bmv;
    String uName,imageLocation,videoLocation,audioLocation,following,followers,imageName,videoName,audioName;
    String uNameA[],imageLocationA[],videoLocationA[],audioLocationA[],followingA[],followersA[],imageNameA[],
            videoNameA[],audioNameA[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        Objects.requireNonNull(getSupportActionBar()).hide();

        username = findViewById(R.id.profileUserName);
        imageCount = findViewById(R.id.imageCount);
        followersCount = findViewById(R.id.followersCount);
        followingCount = findViewById(R.id.followingCount);

        bmv= findViewById(R.id.bmv);
        bmv.setSelectedItemId(R.id.profile);
        bmv.setOnNavigationItemSelectedListener(this);



        Intent intent = getIntent();
        uName = intent.getStringExtra("UName");
        imageLocation = intent.getStringExtra("imageLocation");
        videoLocation = intent.getStringExtra("videoLocation");
        audioLocation = intent.getStringExtra("audioLocation");
        following = intent.getStringExtra("following");
        followers = intent.getStringExtra("followers");
        imageName = intent.getStringExtra("imageName");
        videoName = intent.getStringExtra("videoName");
        audioName = intent.getStringExtra("audioName");

        uNameA = uName.split(",");
        imageLocationA = imageLocation.split(",");
        videoLocationA = videoLocation.split(",");
        audioLocationA = audioLocation.split(",");
        followingA = following.split(",");
        followersA = followers.split(",");
        imageNameA = imageName.split(",");
        videoNameA = videoName.split(",");
        audioNameA = audioName.split(",");

        username.setText(uNameA[0]);
//        Toast.makeText(this, ""+followersA[0], Toast.LENGTH_SHORT).show();
        if(imageLocationA[0].equals(""))
            imageCount.setText("00");
        else
            imageCount.setText(""+(imageLocationA.length));
        if(followingA[0].equals(""))
            followingCount.setText("00");
        else
            followingCount.setText(""+(followingA.length));
        if(followersA[0].equals(""))
            followersCount.setText("00");
        else
            followersCount.setText(""+(followersA.length));

        sql=new SQLiteDatabaseClass(this);

    }

    public void logout(View view) {
        sql.deleteLogin();
        Intent i = new Intent(this,LoginActivity.class);
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

    public void Upload(View view) {
        Intent i = new Intent(this,UploadActivity.class);
        startActivity(i);
    }

    public void showMyImage(View view) {
        Intent i = new Intent(this,MyContentActivity.class);
        i.putExtra("title","Images");
        i.putExtra("imageName",imageNameA);
        i.putExtra("imageLocation",imageLocationA);
        startActivity(i);
    }

    public void showMyVideo(View view) {
        Intent i = new Intent(this,MyContentActivity.class);
        i.putExtra("title","Videos");
        i.putExtra("imageName",videoNameA);
        i.putExtra("imageLocation",videoLocationA);
        startActivity(i);
    }

    public void showMyAudio(View view) {
        Intent i = new Intent(this,MyContentActivity.class);
        i.putExtra("title","Audios");
        i.putExtra("imageName",audioNameA);
        i.putExtra("imageLocation",audioLocationA);
        startActivity(i);
    }
}
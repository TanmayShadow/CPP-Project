package com.example.videoplayer;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.HandlerThread;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

public class MusicAdapter extends BaseAdapter{

    Context context;
    String[] location;
    String[] names;
    LayoutInflater inflater;
    Uri audioUri;
   public MediaPlayer player;
    public MusicAdapter(Context c,String[] l,String[] n)
    {
        this.context=c;
        this.location=l;
        this.names=n;
        inflater=LayoutInflater.from(c);
    }
    @Override
    public int getCount() {
        return location.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=inflater.inflate(R.layout.activity_music_layout,null);
        TextView name = view.findViewById(R.id.textView15);
        ImageView playButton = view.findViewById(R.id.imageView16);
        ImageView musicImage = view.findViewById(R.id.imageView15);
        SeekBar seekBar = view.findViewById(R.id.seekBar2);
        ImageView send = view.findViewById(R.id.imageView17);

        name.setText(names[i]);


        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String path1 = "https://firebasestorage.googleapis.com/v0/b/cpe-project-ddf59.appspot.com/o/audios%2F"+location[i]+"?alt=media";
                audioUri = Uri.parse(path1);
                String tag = playButton.getTag().toString();
                if(tag.equals("play")) {
                    playButton.setImageResource(R.drawable.ic_baseline_pause_circle_outline_24);
                    playButton.setTag("pause");
                    player = MediaPlayer.create(context, audioUri);
                    musicImage.setImageResource(R.drawable.vertical);
                    player.start();
                    seekBar.setMax(player.getDuration());
                    new Timer().scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            seekBar.setProgress(player.getCurrentPosition());
                        }
                    },0,3000);
                    seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                            player.seekTo(i);
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {

                        }
                    });
                }
                else
                {
                    playButton.setImageResource(R.drawable.play_music);
                    playButton.setTag("play");
                    player.pause();
                    musicImage.setImageResource(R.drawable.music);
                }
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String path1 = "https://firebasestorage.googleapis.com/v0/b/cpe-project-ddf59.appspot.com/o/audios%2F"+location[i]+"?alt=media";
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT,"Video");
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,path1);
                context.startActivity(Intent.createChooser(shareIntent, "Share via"));
            }
        });


        return view;
    }
}

//public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.RecyclerViewHolder> {
//
//    private String location[],AName[];
//    Uri audioUri;
//    public MusicAdapter(String[] location,String[] AName)
//    {
//        this.location=location;
//        this.AName=AName;
//    }
//    @NonNull
//    @Override
//    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//        View view = layoutInflater.inflate(R.layout.activity_music_layout,parent,false);
//        return new RecyclerViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull  RecyclerViewHolder holder, int position) {
//        String nameString = AName[position];
//        holder.musicName.setText(nameString);
//    }
//
//    @Override
//    public int getItemCount() {
//        return AName.length;
//    }
//
//    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        TextView musicName;
//        ImageView playPause,musicImage;
//        MediaPlayer player;
//        SeekBar seekBar;
//
//        public RecyclerViewHolder(@NonNull View itemView)
//        {
//            super(itemView);
//            musicName = (TextView) itemView.findViewById(R.id.textView15);
//            playPause = (ImageView) itemView.findViewById(R.id.imageView16);
//            musicImage = (ImageView) itemView.findViewById(R.id.imageView15);
//            seekBar = (SeekBar) itemView.findViewById(R.id.seekBar2);
//            playPause.setOnClickListener(this);
//        }
//
//        @Override
//        public void onClick(View view) {
//            String tag = playPause.getTag().toString();
//
//            int scroll=0;
//            for(int i=0;i<AName.length;i++)
//            {
//                if(musicName.getText().toString().equals(AName[i]))
//                {
//                    scroll=1;
//                    break;
//                }
//            }
//            String path1 = "https://firebasestorage.googleapis.com/v0/b/cpe-project-ddf59.appspot.com/o/audios%2F"+location[scroll]+"?alt=media";
//            audioUri = Uri.parse(path1);
//            if(tag.equals("play"))
//            {
//                playPause.setImageResource(R.drawable.ic_baseline_pause_circle_outline_24);
//                playPause.setTag("pause");
//                player = MediaPlayer.create(itemView.getContext(),audioUri);
//                musicImage.setImageResource(R.drawable.vertical);
//                player.start();
////                if(musicName.getText().toString().equals("greatful"))
////                {
////                    if(player==null)
////                    {
////                        player = MediaPlayer.create(itemView.getContext(),R.raw.greatful);
////                    }
////                    musicImage.setImageResource(R.drawable.vertical);
////                    player.start();
////                }
////                if(musicName.getText().toString().equals("guitar"))
////                {
////                    if(player==null)
////                    {
////                        player = MediaPlayer.create(itemView.getContext(),R.raw.guitar);
////                    }
////                    musicImage.setImageResource(R.drawable.vertical);
////                    player.start();
////                }
////                if(musicName.getText().toString().equals("relax"))
////                {
////                    if(player==null)
////                    {
////                        player = MediaPlayer.create(itemView.getContext(),R.raw.relax);
////                    }
////                    musicImage.setImageResource(R.drawable.vertical);
////                    player.start();
////                }
//                seekBar.setMax(player.getDuration());
//                new Timer().scheduleAtFixedRate(new TimerTask() {
//                    @Override
//                    public void run() {
//                        seekBar.setProgress(player.getCurrentPosition());
//                    }
//                },0,2000);
//                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//                    @Override
//                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
//                        player.seekTo(i);
//                    }
//
//                    @Override
//                    public void onStartTrackingTouch(SeekBar seekBar) {
//
//                    }
//
//                    @Override
//                    public void onStopTrackingTouch(SeekBar seekBar) {
//
//                    }
//                });
//            }
//            else
//            {
//                playPause.setImageResource(R.drawable.play_music);
//                playPause.setTag("play");
//                player.pause();
//                musicImage.setImageResource(R.drawable.music);
//            }
//            Toast.makeText(itemView.getContext(), "Tag:"+playPause.getTag().toString(), Toast.LENGTH_SHORT).show();
//
//        }
//    }
//}

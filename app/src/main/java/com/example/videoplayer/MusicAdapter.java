package com.example.videoplayer;

import android.media.MediaPlayer;
import android.os.HandlerThread;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.RecyclerViewHolder> {

    private String[] name;
    public MusicAdapter(String[] name)
    {
        this.name=name;
    }
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_music_layout,parent,false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  RecyclerViewHolder holder, int position) {
        String nameString = name[position];
        holder.musicName.setText(nameString);
    }

    @Override
    public int getItemCount() {
        return name.length;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView musicName;
        ImageView playPause,musicImage;
        MediaPlayer player;
        SeekBar seekBar;

        public RecyclerViewHolder(@NonNull View itemView)
        {
            super(itemView);
            musicName = (TextView) itemView.findViewById(R.id.textView15);
            playPause = (ImageView) itemView.findViewById(R.id.imageView16);
            musicImage = (ImageView) itemView.findViewById(R.id.imageView15);
            seekBar = (SeekBar) itemView.findViewById(R.id.seekBar2);
            playPause.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            String tag = playPause.getTag().toString();

            if(tag.equals("play"))
            {
                playPause.setImageResource(R.drawable.ic_baseline_pause_circle_outline_24);
                playPause.setTag("pause");
                if(musicName.getText().toString().equals("greatful"))
                {
                    if(player==null)
                    {
                        player = MediaPlayer.create(itemView.getContext(),R.raw.greatful);
                    }
                    musicImage.setImageResource(R.drawable.vertical);
                    player.start();
                }
                if(musicName.getText().toString().equals("guitar"))
                {
                    if(player==null)
                    {
                        player = MediaPlayer.create(itemView.getContext(),R.raw.guitar);
                    }
                    musicImage.setImageResource(R.drawable.vertical);
                    player.start();
                }
                if(musicName.getText().toString().equals("relax"))
                {
                    if(player==null)
                    {
                        player = MediaPlayer.create(itemView.getContext(),R.raw.relax);
                    }
                    musicImage.setImageResource(R.drawable.vertical);
                    player.start();
                }
                seekBar.setMax(player.getDuration());
                new Timer().scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        seekBar.setProgress(player.getCurrentPosition());
                    }
                },0,2000);
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
                playPause.setImageResource(R.drawable.play_music);
                playPause.setTag("play");
                player.pause();
                musicImage.setImageResource(R.drawable.music);
            }
            Toast.makeText(itemView.getContext(), "Tag:"+playPause.getTag().toString(), Toast.LENGTH_SHORT).show();

        }
    }
}

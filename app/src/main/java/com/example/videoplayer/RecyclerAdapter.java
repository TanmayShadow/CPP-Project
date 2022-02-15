package com.example.videoplayer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {

    private String[] image;
    private String[] name;
    private String[] comment;
    public RecyclerAdapter(String[] image, String[] name, String[] comment)
    {
        this.image = image;
        this.name = name;
        this.comment = comment;
    }
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.comment_list_layout,parent,false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        String imageString = image[position];
        String nameString = name[position];
        String commentString = comment[position];
        holder.userImage.setImageResource(R.drawable.about_icon);
        holder.userName.setText(nameString);
        holder.userComment.setText(commentString);
    }

    @Override
    public int getItemCount() {
        return name.length;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
        ImageView userImage;
        TextView userName, userComment;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            userImage = (ImageView) itemView.findViewById(R.id.imageView11);
            userName = (TextView) itemView.findViewById(R.id.textView11);
            userComment = (TextView) itemView.findViewById(R.id.textView13);

        }
    }
}

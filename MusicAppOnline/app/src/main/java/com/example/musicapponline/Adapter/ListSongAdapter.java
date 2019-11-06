package com.example.musicapponline.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.musicapponline.Activity.MusicPlayerActivity;
import com.example.musicapponline.Model.Song;
import com.example.musicapponline.R;
import com.example.musicapponline.Service.IncSongLikeNumberAsyncTask;

import java.util.ArrayList;

public class ListSongAdapter extends RecyclerView.Adapter<ListSongAdapter.ViewHolder> {
    ArrayList<Song> songs;
    Context context;
    View view;

    public ListSongAdapter(ArrayList<Song> songs, Context context) {
        this.songs = songs;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.list_song_base,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Song song = songs.get(i);
        viewHolder.tv_index.setText(i + 1 + "");
        viewHolder.tv_song_name.setText(song.getName());
        viewHolder.tv_singer_name.setText(song.getSinger());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MusicPlayerActivity.class);
                intent.putExtra("song",song);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv_song_name,tv_singer_name,tv_index;
        ImageView iv_love;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_index = itemView.findViewById(R.id.tv_list_index);
            tv_singer_name = itemView.findViewById(R.id.tv_singer_name_in_list);
            tv_song_name = itemView.findViewById(R.id.tv_song_name_in_list);
            iv_love = itemView.findViewById(R.id.iv_love_in_list);

            iv_love.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IncSongLikeNumberAsyncTask incSongLikeNumberAsyncTask = new IncSongLikeNumberAsyncTask(context,iv_love);
                    incSongLikeNumberAsyncTask.execute(String.valueOf(songs.get(getPosition()).getId()));
                }
            });
        }
    }
}

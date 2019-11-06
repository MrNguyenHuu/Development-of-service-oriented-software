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

import com.example.musicapponline.Activity.MusicPlayerActivity;
import com.example.musicapponline.Model.Song;
import com.example.musicapponline.R;
import com.example.musicapponline.Service.IncSongLikeNumberAsyncTask;
import com.example.musicapponline.Service.WebService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PopularSongAdapter extends RecyclerView.Adapter<PopularSongAdapter.ViewHolder> {

    ArrayList<Song> songs;
    Context context;
    View view;

    public PopularSongAdapter(ArrayList<Song> songs, Context context) {
        this.songs = songs;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.popular_song_base,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Song song = songs.get(i);
        viewHolder.tv_popular_song_name.setText(song.getName());
        viewHolder.tv_popular_song_singer.setText(song.getSinger());
        Picasso.with(context).load(WebService.getImageUrl(song.getImage())).into(viewHolder.iv_popular_song);

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

        ImageView iv_popular_song,iv_like;
        TextView tv_popular_song_name,tv_popular_song_singer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_popular_song = itemView.findViewById(R.id.iv_popular_song);
            iv_like = itemView.findViewById(R.id.iv_like);
            tv_popular_song_name = itemView.findViewById(R.id.tv_popular_song_name);
            tv_popular_song_singer = itemView.findViewById(R.id.tv_popular_song_singer);

            iv_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IncSongLikeNumberAsyncTask incSongLikeNumberAsyncTask = new IncSongLikeNumberAsyncTask(context,iv_like);
                    incSongLikeNumberAsyncTask.execute(String.valueOf(songs.get(getPosition()).getId()));
                }
            });
        }
    }

}

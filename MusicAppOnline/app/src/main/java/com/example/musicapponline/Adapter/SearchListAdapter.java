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

public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.ViewHolder>{

    View view;
    Context context;
    ArrayList<Song> songs;

    public SearchListAdapter(Context context, ArrayList<Song> songs) {
        this.context = context;
        this.songs = songs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.search_song_base,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Song song = songs.get(i);
        viewHolder.tv_singer.setText(song.getSinger());
        viewHolder.tv_name.setText(song.getName());
        Picasso.with(context).load(WebService.getImageUrl(song.getImage())).into(viewHolder.iv_search_song);

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

        ImageView iv_search_song,iv_love;
        TextView tv_name,tv_singer;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_search_song_name);
            tv_singer = itemView.findViewById(R.id.tv_search_singer);
            iv_love = itemView.findViewById(R.id.iv_search_love);
            iv_search_song = itemView.findViewById(R.id.iv_search_song);

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

package com.example.musicapponline.Adapter;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.musicapponline.Activity.MusicPlayerActivity;
import com.example.musicapponline.Model.Song;
import com.example.musicapponline.R;
import com.example.musicapponline.Service.WebService;

import java.util.ArrayList;

public class MusicPlayerAdapter  extends RecyclerView.Adapter<MusicPlayerAdapter.ViewHolder> {
    Context context;
    ArrayList<Song> songs;
    View view;
    boolean canClick = true;

    public MusicPlayerAdapter(Context context, ArrayList<Song> songs) {
        this.context = context;
        this.songs = songs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.music_player_list_base,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final Song song = songs.get(i);
        viewHolder.tv_list_song.setText(song.getName());
        viewHolder.tv_list_singer.setText(song.getSinger());
        viewHolder.tv_index.setText(i + 1 + "");

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MusicPlayerActivity.position != i && canClick == true){
                    if (MusicPlayerActivity.mediaPlayer.isPlaying() || MusicPlayerActivity.mediaPlayer != null){
                        MusicPlayerActivity.mediaPlayer.stop();
                        MusicPlayerActivity.mediaPlayer.release();
                        MusicPlayerActivity.mediaPlayer = null;
                        MusicPlayerActivity.musicListFragment.removeColor(MusicPlayerActivity.position);
                    }
                    MusicPlayerActivity.position = i;
                    MusicPlayerActivity.PlayMp3 mp3  = new MusicPlayerActivity.PlayMp3();
                    mp3.execute(WebService.getMusicUrl(song.getUrl()));
                    MusicPlayerActivity.musicDiskFragment.playMusic(song.getImage());
                    MusicPlayerActivity.musicListFragment.addColor(i);
                    ((AppCompatActivity)context).getSupportActionBar().setTitle(song.getName());

                    canClick = false;
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            canClick = true;
                        }
                    },2000);
                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv_list_song,tv_list_singer,tv_index;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_index = itemView.findViewById(R.id.tv_music_player_list_index);
            tv_list_singer = itemView.findViewById(R.id.tv_music_player_list_singer);
            tv_list_song = itemView.findViewById(R.id.tv_music_player_list_song);
        }
    }

}

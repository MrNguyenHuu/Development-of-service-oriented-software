package com.example.musicapponline.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.musicapponline.Activity.MusicPlayerActivity;
import com.example.musicapponline.Adapter.MusicPlayerAdapter;
import com.example.musicapponline.R;

public class FragmentMusicPlayerList extends Fragment {
    View view;
    RecyclerView rv_list_music_player;
    MusicPlayerAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_music_player_list,container,false);
        rv_list_music_player = view.findViewById(R.id.rv_list_music_player);

        if (MusicPlayerActivity.songs.size() > 0){
            adapter = new MusicPlayerAdapter(getActivity(), MusicPlayerActivity.songs);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            rv_list_music_player.setLayoutManager(layoutManager);
            rv_list_music_player.setAdapter(adapter);
        }
        return view;
    }

    public void addColor(int position){
        View itemView = rv_list_music_player.getChildAt(position);
        TextView tv_list_song,tv_list_singer,tv_index;

        tv_index = itemView.findViewById(R.id.tv_music_player_list_index);
        tv_list_singer = itemView.findViewById(R.id.tv_music_player_list_singer);
        tv_list_song = itemView.findViewById(R.id.tv_music_player_list_song);

        tv_index.setTextColor(getResources().getColor(R.color.xanh));
        tv_list_singer.setTextColor(getResources().getColor(R.color.xanh));
        tv_list_song.setTextColor(getResources().getColor(R.color.xanh));
    }

    public void removeColor(int position){
        View itemView = rv_list_music_player.getChildAt(position);
        TextView tv_list_song,tv_list_singer,tv_index;

        tv_index = itemView.findViewById(R.id.tv_music_player_list_index);
        tv_list_singer = itemView.findViewById(R.id.tv_music_player_list_singer);
        tv_list_song = itemView.findViewById(R.id.tv_music_player_list_song);

        tv_index.setTextColor(Color.WHITE);
        tv_list_singer.setTextColor(Color.WHITE);
        tv_list_song.setTextColor(Color.WHITE);
    }
}

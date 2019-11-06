package com.example.musicapponline.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.musicapponline.Adapter.PopularSongAdapter;
import com.example.musicapponline.Model.Advertisement;
import com.example.musicapponline.Model.Album;
import com.example.musicapponline.Model.Category;
import com.example.musicapponline.Model.Playlist;
import com.example.musicapponline.Model.Song;
import com.example.musicapponline.Model.Topic;
import com.example.musicapponline.R;
import com.example.musicapponline.Service.AsyncArrayListResponse;
import com.example.musicapponline.Service.GetPopularSongAsyncTask;

import java.util.ArrayList;

public class FragmentPopularSong extends Fragment implements AsyncArrayListResponse {
    View view;
    RecyclerView rv_popular_song;
    PopularSongAdapter popularSongAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_popular_song,container,false);
        rv_popular_song = view.findViewById(R.id.rv_popular_song);
        getData();
        return view;
    }

    private void getData() {
        GetPopularSongAsyncTask getPopularSongAsyncTask = new GetPopularSongAsyncTask();
        getPopularSongAsyncTask.response = this;
        getPopularSongAsyncTask.execute();
    }

    @Override
    public void finishGettingAdvertisement(ArrayList<Advertisement> output) {

    }

    @Override
    public void finishGettingPlaylist(ArrayList<Playlist> output) {

    }

    @Override
    public void finishGettingTopic(ArrayList<Topic> output) {

    }

    @Override
    public void finishGettingCategory(ArrayList<Category> output) {

    }

    @Override
    public void finishGettingAlbum(ArrayList<Album> output) {

    }

    @Override
    public void finishGettingSong(ArrayList<Song> output) {
        popularSongAdapter = new PopularSongAdapter(output,getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_popular_song.setLayoutManager(layoutManager);
        rv_popular_song.setAdapter(popularSongAdapter);
    }
}

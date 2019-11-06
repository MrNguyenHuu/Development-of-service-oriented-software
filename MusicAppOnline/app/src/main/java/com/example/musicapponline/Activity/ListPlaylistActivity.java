package com.example.musicapponline.Activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.musicapponline.Adapter.PlaylistListAdapter;
import com.example.musicapponline.Model.Advertisement;
import com.example.musicapponline.Model.Album;
import com.example.musicapponline.Model.Category;
import com.example.musicapponline.Model.Playlist;
import com.example.musicapponline.Model.Song;
import com.example.musicapponline.Model.Topic;
import com.example.musicapponline.R;
import com.example.musicapponline.Service.AsyncArrayListResponse;
import com.example.musicapponline.Service.GetPlaylistAsyncTask;

import java.util.ArrayList;

public class ListPlaylistActivity extends AppCompatActivity implements AsyncArrayListResponse {

    Toolbar toolbar;
    RecyclerView rv_playlist_list;
    PlaylistListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_playlist);
        getViewComponent();

        init();
        getData();
    }

    private void getData() {
        GetPlaylistAsyncTask getPlaylistAsyncTask = new GetPlaylistAsyncTask();
        getPlaylistAsyncTask.response = this;
        getPlaylistAsyncTask.execute("");
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất cả Playlist");
        toolbar.setTitleTextColor(getResources().getColor(R.color.mautim));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void getViewComponent() {
        toolbar = findViewById(R.id.toolbar_list_playlist);
        rv_playlist_list = findViewById(R.id.rv_list_playlist);
    }

    @Override
    public void finishGettingAdvertisement(ArrayList<Advertisement> output) {

    }

    @Override
    public void finishGettingPlaylist(ArrayList<Playlist> output) {
        adapter = new PlaylistListAdapter(this,output);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        rv_playlist_list.setLayoutManager(gridLayoutManager);
        rv_playlist_list.setAdapter(adapter);

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

    }
}

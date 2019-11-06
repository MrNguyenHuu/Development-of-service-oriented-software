package com.example.musicapponline.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.musicapponline.Adapter.ListAlbumAdapter;
import com.example.musicapponline.Model.Advertisement;
import com.example.musicapponline.Model.Album;
import com.example.musicapponline.Model.Category;
import com.example.musicapponline.Model.Playlist;
import com.example.musicapponline.Model.Song;
import com.example.musicapponline.Model.Topic;
import com.example.musicapponline.R;
import com.example.musicapponline.Service.AsyncArrayListResponse;
import com.example.musicapponline.Service.GetAlbumAsyncTask;

import java.util.ArrayList;

public class ListAlbumActivity extends AppCompatActivity implements AsyncArrayListResponse {

    ArrayList<Album> albums;
    Toolbar toolbar;
    RecyclerView rv_list_album;
    ListAlbumAdapter albumAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_album);
        getViewComponent();
        getData();
        init();
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất cả Album");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getData() {
        GetAlbumAsyncTask getAlbumAsyncTask = new GetAlbumAsyncTask();
        getAlbumAsyncTask.response = this;
        getAlbumAsyncTask.execute("");
    }

    private void getViewComponent() {
        toolbar = findViewById(R.id.toolbar_list_album);
        rv_list_album = findViewById(R.id.rv_list_album);
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
        albumAdapter = new ListAlbumAdapter(this,output);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        rv_list_album.setLayoutManager(layoutManager);
        rv_list_album.setAdapter(albumAdapter);
    }

    @Override
    public void finishGettingSong(ArrayList<Song> output) {

    }
}

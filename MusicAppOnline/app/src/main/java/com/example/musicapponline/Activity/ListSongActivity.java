package com.example.musicapponline.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;


import com.example.musicapponline.Adapter.ListSongAdapter;
import com.example.musicapponline.Model.Advertisement;
import com.example.musicapponline.Model.Album;
import com.example.musicapponline.Model.Category;
import com.example.musicapponline.Model.Playlist;
import com.example.musicapponline.Model.Song;
import com.example.musicapponline.Model.Topic;
import com.example.musicapponline.R;
import com.example.musicapponline.Service.AsyncArrayListResponse;
import com.example.musicapponline.Service.GetSongInAlbumAsyncTask;
import com.example.musicapponline.Service.GetSongInCategoryAsyncTask;
import com.example.musicapponline.Service.GetSongInPlaylistAsyncTask;
import com.example.musicapponline.Service.WebService;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ListSongActivity extends AppCompatActivity implements AsyncArrayListResponse {

    Advertisement advertisement;
    Playlist playlist;
    Category category;
    Album album;
    ArrayList<Song> songs;

    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView rv_list_song;
    FloatingActionButton floatingActionButton;
    ImageView iv_list_song;
    ListSongAdapter listSongAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_song);
        getDataIntent();
        getViewComponent();
        init();

        if (advertisement != null && !advertisement.getSong().getName().equals("")){
            setValueInView(advertisement.getSong().getName(),advertisement.getSong().getImage());

            songs = new ArrayList<>();
            songs.add(advertisement.getSong());
            listSongAdapter = new ListSongAdapter(songs,ListSongActivity.this);
            rv_list_song.setLayoutManager(new LinearLayoutManager(ListSongActivity.this));
            rv_list_song.setAdapter(listSongAdapter);
            clickEvent();
        }

        if (playlist != null && !playlist.getName().equals("")){
            setValueInView(playlist.getName(),playlist.getIcon());
            getSongInPlaylist(String.valueOf(playlist.getId()));
        }

        if (category != null && !category.getName().equals("")){
            setValueInView(category.getName(),category.getImage());
            getSongInCategory(String.valueOf(category.getId()));
        }

        if (album != null && !album.getName().equals("")){
            setValueInView(album.getName(),album.getImage());
            getSongInAlbum(String.valueOf(album.getId()));
        }
    }

    private void getSongInAlbum( String album_id) {
        GetSongInAlbumAsyncTask getSongInAlbumAsyncTask = new GetSongInAlbumAsyncTask();
        getSongInAlbumAsyncTask.response = this;
        getSongInAlbumAsyncTask.execute(album_id);
    }

    private void getSongInCategory(String category_id) {
        GetSongInCategoryAsyncTask getSongInCategoryAsyncTask = new GetSongInCategoryAsyncTask();
        getSongInCategoryAsyncTask.response = this;
        getSongInCategoryAsyncTask.execute(category_id);
    }

    private void getSongInPlaylist(String playlist_id) {
        GetSongInPlaylistAsyncTask getSongInPlaylistAsyncTask = new GetSongInPlaylistAsyncTask();
        getSongInPlaylistAsyncTask.response = this;
        getSongInPlaylistAsyncTask.execute(playlist_id);

    }

    private void setValueInView(String title, String image) {
        collapsingToolbarLayout.setTitle(title);
        try {
            URL url = new URL(WebService.getImageUrl(image));
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(),bitmap);
            collapsingToolbarLayout.setBackground(bitmapDrawable);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Picasso.with(this).load(WebService.getImageUrl(image)).into(iv_list_song);

    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        floatingActionButton.setEnabled(false);
    }

    private void getViewComponent() {
        coordinatorLayout = findViewById(R.id.coordinator_list_song);
        collapsingToolbarLayout = findViewById(R.id.collapsToolBar);
        toolbar = findViewById(R.id.toolbar_list);
        rv_list_song = findViewById(R.id.rv_list_song);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        iv_list_song = findViewById(R.id.iv_list_song);
    }

    private void getDataIntent() {
        Intent intent = getIntent();
        if (intent != null){
            if (intent.hasExtra("banner")){
                advertisement = (Advertisement)intent.getParcelableExtra("banner");
            }

            if (intent.hasExtra("playlist")){
                playlist = (Playlist)intent.getSerializableExtra("playlist");
            }

            if (intent.hasExtra("category")){
                category = (Category)intent.getSerializableExtra("category");
            }

            if (intent.hasExtra("album")){
                album = (Album)intent.getSerializableExtra("album");
            }
        }

    }

    private void clickEvent(){
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListSongActivity.this,MusicPlayerActivity.class);
                intent.putExtra("songs",songs);
                startActivity(intent);
            }
        });

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
        songs = output;
        listSongAdapter = new ListSongAdapter(output,ListSongActivity.this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(ListSongActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_list_song.setLayoutManager(layoutManager);
        rv_list_song.setAdapter(listSongAdapter);
        clickEvent();
    }


}

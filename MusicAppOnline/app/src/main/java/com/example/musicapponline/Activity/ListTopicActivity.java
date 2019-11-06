package com.example.musicapponline.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.example.musicapponline.Adapter.ListTopicAdapter;
import com.example.musicapponline.Model.Advertisement;
import com.example.musicapponline.Model.Album;
import com.example.musicapponline.Model.Category;
import com.example.musicapponline.Model.Playlist;
import com.example.musicapponline.Model.Song;
import com.example.musicapponline.Model.Topic;
import com.example.musicapponline.R;
import com.example.musicapponline.Service.AsyncArrayListResponse;
import com.example.musicapponline.Service.GetTopicAsyncTask;

import java.util.ArrayList;

public class ListTopicActivity extends AppCompatActivity implements AsyncArrayListResponse {

    Toolbar toolbar;
    RecyclerView rv_list_topic;
    ListTopicAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_topic);
        getViewComponent();
        init();
        getData();

    }

    private void getData() {
        GetTopicAsyncTask getTopicAsyncTask = new GetTopicAsyncTask();
        getTopicAsyncTask.response = this;
        getTopicAsyncTask.execute("");
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất cả chủ đề");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getViewComponent() {
        toolbar = findViewById(R.id.toolbar_list_topic);
        rv_list_topic = findViewById(R.id.rv_list_topic);
    }

    @Override
    public void finishGettingAdvertisement(ArrayList<Advertisement> output) {

    }

    @Override
    public void finishGettingPlaylist(ArrayList<Playlist> output) {

    }

    @Override
    public void finishGettingTopic(ArrayList<Topic> output) {
        adapter = new ListTopicAdapter(this,output);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_list_topic.setLayoutManager(layoutManager);
        rv_list_topic.setAdapter(adapter);
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

package com.example.musicapponline.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.musicapponline.Adapter.ListCategoryAdapter;
import com.example.musicapponline.Model.Advertisement;
import com.example.musicapponline.Model.Album;
import com.example.musicapponline.Model.Category;
import com.example.musicapponline.Model.Playlist;
import com.example.musicapponline.Model.Song;
import com.example.musicapponline.Model.Topic;
import com.example.musicapponline.R;
import com.example.musicapponline.Service.AsyncArrayListResponse;
import com.example.musicapponline.Service.GetCategoryFromTopicAsyncTask;

import java.util.ArrayList;

public class ListCategoryActivity extends AppCompatActivity implements AsyncArrayListResponse {

    Topic topic;
    Toolbar toolbar;
    RecyclerView rv_list_category;
    ListCategoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_category);
        getViewComponent();
        Intent intent = getIntent();
        getIntentData(intent);
        init();
        getData();

    }

    private void getData() {
        GetCategoryFromTopicAsyncTask getCategoryFromTopicAsyncTask = new GetCategoryFromTopicAsyncTask();
        getCategoryFromTopicAsyncTask.response = this;
        getCategoryFromTopicAsyncTask.execute(String.valueOf(topic.getId()));
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(topic.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getViewComponent() {
        toolbar = findViewById(R.id.toolbar_list_category);
        rv_list_category = findViewById(R.id.rv_list_category);
    }

    private void getIntentData(Intent intent) {
        if (intent != null){
            if (intent.hasExtra("topic")){
                topic = (Topic) intent.getSerializableExtra("topic");
            }
        }
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
        adapter = new ListCategoryAdapter(this,output);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        rv_list_category.setLayoutManager(layoutManager);
        rv_list_category.setAdapter(adapter);
    }

    @Override
    public void finishGettingAlbum(ArrayList<Album> output) {

    }

    @Override
    public void finishGettingSong(ArrayList<Song> output) {

    }
}

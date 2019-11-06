package com.example.musicapponline.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.musicapponline.Activity.ListAlbumActivity;
import com.example.musicapponline.Adapter.AlbumAdapter;
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

public class FragmentAlbum extends Fragment implements AsyncArrayListResponse {
    View view;
    RecyclerView rv_album;
    TextView tv_viewMore;
    AlbumAdapter albumAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_album,container,false);

        rv_album = view.findViewById(R.id.rv_album);
        tv_viewMore = view.findViewById(R.id.tv_view_more_album);

        tv_viewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListAlbumActivity.class);
                startActivity(intent);
            }
        });

        getData();

        return view;
    }

    private void getData() {
        GetAlbumAsyncTask getAlbumAsyncTask = new GetAlbumAsyncTask();
        getAlbumAsyncTask.response = this;
        getAlbumAsyncTask.execute("true");
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
        albumAdapter = new AlbumAdapter(getActivity(),output);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_album.setLayoutManager(linearLayoutManager);
        rv_album.setAdapter(albumAdapter);
        
    }

    @Override
    public void finishGettingSong(ArrayList<Song> output) {

    }
}

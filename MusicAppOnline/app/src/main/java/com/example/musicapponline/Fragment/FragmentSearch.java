package com.example.musicapponline.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.SearchView;
import android.widget.TextView;

import com.example.musicapponline.Adapter.SearchListAdapter;
import com.example.musicapponline.Model.Advertisement;
import com.example.musicapponline.Model.Album;
import com.example.musicapponline.Model.Category;
import com.example.musicapponline.Model.Playlist;
import com.example.musicapponline.Model.Song;
import com.example.musicapponline.Model.Topic;
import com.example.musicapponline.R;
import com.example.musicapponline.Service.AsyncArrayListResponse;
import com.example.musicapponline.Service.GetSearchSongAsyncTask;

import java.util.ArrayList;

public class FragmentSearch extends Fragment implements AsyncArrayListResponse {
    View view;
    Toolbar toolbar;
    RecyclerView rv_search;
    TextView tv_no_song;
    SearchListAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search,container,false);

        toolbar = view.findViewById(R.id.toolbar_search);
        rv_search = view.findViewById(R.id.rv_search_song);
        tv_no_song = view.findViewById(R.id.tv_no_song_found);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle("");
        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu,menu);
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //searchSong(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchSong(newText);
                return true;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    private void searchSong(String key){
        GetSearchSongAsyncTask getSearchSongAsyncTask = new GetSearchSongAsyncTask();
        getSearchSongAsyncTask.response = this;
        getSearchSongAsyncTask.execute(key);
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
        if (output != null){
            adapter = new SearchListAdapter(getActivity(),output);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            rv_search.setLayoutManager(layoutManager);
            rv_search.setAdapter(adapter);

            tv_no_song.setVisibility(View.GONE);
            rv_search.setVisibility(View.VISIBLE);

        } else {
            rv_search.setVisibility(View.GONE);
            tv_no_song.setVisibility(View.VISIBLE);
        }
    }
}

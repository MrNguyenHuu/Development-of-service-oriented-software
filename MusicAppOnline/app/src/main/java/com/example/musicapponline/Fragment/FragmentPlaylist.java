package com.example.musicapponline.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.musicapponline.Activity.ListPlaylistActivity;
import com.example.musicapponline.Activity.ListSongActivity;
import com.example.musicapponline.Adapter.PlaylistAdapter;
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

public class FragmentPlaylist extends Fragment implements AsyncArrayListResponse {
    ListView lv_playlist;
    View view;
    TextView tv_titlePlaylist;
    TextView tv_viewMorePlaylist;

    PlaylistAdapter playlistAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_playlist,container,false);
        getViewComponent();
        getData();
        tv_viewMorePlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListPlaylistActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void getData() {
        GetPlaylistAsyncTask playlistAsync = new GetPlaylistAsyncTask();
        playlistAsync.response = this;
        playlistAsync.execute("true");
    }

    private void getViewComponent() {
        lv_playlist = (ListView)view.findViewById(R.id.lv_playlist);
        tv_titlePlaylist = (TextView)view.findViewById(R.id.tv_playlist_title);
        tv_viewMorePlaylist = (TextView)view.findViewById(R.id.tv_viewmoreplaylist);
    }

    @Override
    public void finishGettingAdvertisement(ArrayList<Advertisement> output) {

    }

    @Override
    public void finishGettingPlaylist(final ArrayList<Playlist> output) {
        playlistAdapter = new PlaylistAdapter(getActivity(),android.R.layout.simple_list_item_1,output);
        lv_playlist.setAdapter(playlistAdapter);
        setListViewHeightBasedOnChildren(lv_playlist);
        lv_playlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ListSongActivity.class);
                intent.putExtra("playlist", output.get(position));
                startActivity(intent);
            }
        });

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

    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);

            if(listItem != null){
                // This next line is needed before you call measure or else you won't get measured height at all. The listitem needs to be drawn first to know the height.
                listItem.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                totalHeight += listItem.getMeasuredHeight();

            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}

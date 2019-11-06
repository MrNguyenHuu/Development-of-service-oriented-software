package com.example.musicapponline.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.musicapponline.Adapter.BannerAdapter;
import com.example.musicapponline.Model.Advertisement;
import com.example.musicapponline.Model.Album;
import com.example.musicapponline.Model.Category;
import com.example.musicapponline.Model.Playlist;
import com.example.musicapponline.Model.Song;
import com.example.musicapponline.Model.Topic;
import com.example.musicapponline.R;
import com.example.musicapponline.Service.AsyncArrayListResponse;
import com.example.musicapponline.Service.GetAdvertisementAsyncTask;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

public class FragmentBanner extends Fragment implements AsyncArrayListResponse {
    ViewPager vp_banner;
    CircleIndicator circleIndicator;
    View view;
    BannerAdapter bannerAdapter;
    Runnable runnable;
    Handler handler;
    int currentItem;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_banner,container,false);

        getViewComponent();
        getData();
        return view;
    }

    private void getData() {
        GetAdvertisementAsyncTask advAsync = new GetAdvertisementAsyncTask();
        advAsync.response = this;
        advAsync.execute();
    }

    private void getViewComponent() {
        vp_banner = (ViewPager)view.findViewById(R.id.vp_banner);
        circleIndicator = (CircleIndicator)view.findViewById(R.id.indicatorCircle);
    }

    @Override
    public void finishGettingAdvertisement(ArrayList<Advertisement> output) {
        bannerAdapter = new BannerAdapter(getActivity(),output);
        vp_banner.setAdapter(bannerAdapter);
        circleIndicator.setViewPager(vp_banner);

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                currentItem = vp_banner.getCurrentItem();
                currentItem++;
                if (currentItem >= vp_banner.getAdapter().getCount()){
                    currentItem = 0;
                }
                vp_banner.setCurrentItem(currentItem,true);
                handler.postDelayed(runnable,5500);
            }
        };
        handler.postDelayed(runnable,5500);
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

    }
}

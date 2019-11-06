package com.example.musicapponline.Service;

import com.example.musicapponline.Model.Advertisement;
import com.example.musicapponline.Model.Album;
import com.example.musicapponline.Model.Category;
import com.example.musicapponline.Model.Playlist;
import com.example.musicapponline.Model.Song;
import com.example.musicapponline.Model.Topic;

import java.util.ArrayList;

public interface AsyncArrayListResponse {
    void finishGettingAdvertisement(ArrayList<Advertisement> output);
    void finishGettingPlaylist(ArrayList<Playlist> output);
    void finishGettingTopic(ArrayList<Topic> output);
    void finishGettingCategory(ArrayList<Category> output);
    void finishGettingAlbum(ArrayList<Album> output);
    void finishGettingSong(ArrayList<Song> output);
}

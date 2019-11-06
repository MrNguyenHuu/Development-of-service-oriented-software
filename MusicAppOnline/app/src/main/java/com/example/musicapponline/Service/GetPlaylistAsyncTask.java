package com.example.musicapponline.Service;

import android.os.AsyncTask;

import com.example.musicapponline.Model.Playlist;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;

public class GetPlaylistAsyncTask extends AsyncTask<String, Void, ArrayList<Playlist>> {
    public AsyncArrayListResponse response;
    private static final String NAMESPACE = "http://service";
    public static final String METHOD_NAME = "getRandomPlaylists";

    @Override
    protected ArrayList<Playlist> doInBackground(String... strings) {

        Vector<SoapObject> results = WebService.getArrayListResultFromRandom(METHOD_NAME,NAMESPACE,strings[0]);
        if (results != null){
            Enumeration resultEnum = results.elements();
            ArrayList<Playlist> playlists = new ArrayList<>();
            while (resultEnum.hasMoreElements()) {
                SoapObject result = (SoapObject) resultEnum.nextElement();
                Playlist playlist = playlistFromSoap(result);
                playlists.add(playlist);
            }
            return playlists;
        } else {
            return null;
        }
    }

    private Playlist playlistFromSoap(SoapObject result) {
        Playlist playlist = new Playlist();

        playlist.setId(Long.parseLong(result.getProperty("id").toString()));
        playlist.setName(result.getProperty("name").toString());
        playlist.setBackground(result.getProperty("background").toString());
        playlist.setIcon(result.getProperty("icon").toString());

        return playlist;
    }

    @Override
    protected void onPostExecute(ArrayList<Playlist> playlists) {
        if (playlists != null) {
            response.finishGettingPlaylist(playlists);
        }

    }
}

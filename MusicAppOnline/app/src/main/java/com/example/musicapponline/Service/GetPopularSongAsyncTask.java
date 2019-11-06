package com.example.musicapponline.Service;

import android.os.AsyncTask;

import com.example.musicapponline.Model.Song;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;

public class GetPopularSongAsyncTask extends AsyncTask<String,Void, ArrayList<Song>> {

    public AsyncArrayListResponse response;
    private static final String NAMESPACE = "http://service";
    public static final String METHOD_NAME = "getPopularSong";
    @Override
    protected ArrayList<Song> doInBackground(String... strings) {
        Vector<SoapObject> results = WebService.getArrayListResultDefault(METHOD_NAME,NAMESPACE);

        if (results != null){

            ArrayList<Song> songs = new ArrayList<>();
            Enumeration vEnum = results.elements();
            while (vEnum.hasMoreElements()){
                SoapObject result = (SoapObject) vEnum.nextElement();
                Song song = WebService.songFromSoap(result);
                songs.add(song);
            }

            return songs;
        } else {
            return null;
        }
    }


    @Override
    protected void onPostExecute(ArrayList<Song> songs) {
        if (songs != null){
            response.finishGettingSong(songs);
        }
    }
}

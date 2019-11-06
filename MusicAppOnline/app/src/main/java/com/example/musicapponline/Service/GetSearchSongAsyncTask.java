package com.example.musicapponline.Service;

import android.os.AsyncTask;

import com.example.musicapponline.Model.Song;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;

public class GetSearchSongAsyncTask extends AsyncTask<String,Void, ArrayList<Song>> {

    public AsyncArrayListResponse response;
    private static final String NAMESPACE = "http://service";
    private static final String METHOD_NAME = "getFindSong";

    @Override
    protected ArrayList<Song> doInBackground(String... strings) {
        Vector<SoapObject> results = WebService.getArrayListResultFromId(METHOD_NAME,NAMESPACE,strings[0]);
        if (results != null){
            Enumeration vEnum = results.elements();
            ArrayList<Song> songs = new ArrayList<>();
            while (vEnum.hasMoreElements()){
                SoapObject result = (SoapObject) vEnum.nextElement();
                Song song = WebService.songFromSoap(result);
                songs.add(song);
            }
            return  songs;
        } else {
            return null;
        }
    }

    @Override
    protected void onPostExecute(ArrayList<Song> songs) {
        response.finishGettingSong(songs);
    }
}

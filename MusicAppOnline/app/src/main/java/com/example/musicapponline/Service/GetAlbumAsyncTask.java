package com.example.musicapponline.Service;

import android.os.AsyncTask;

import com.example.musicapponline.Model.Album;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;

public class GetAlbumAsyncTask extends AsyncTask<String,Void, ArrayList<Album>> {

    public AsyncArrayListResponse response;
    private static final String METHOD_NAME = "getRandomAlbum";
    private static final String NAMESPACE = "http://service";

    @Override
    protected ArrayList<Album> doInBackground(String... strings) {

        Vector<SoapObject> results = WebService.getArrayListResultFromRandom(METHOD_NAME,NAMESPACE,strings[0]);
        if (results != null){

            Enumeration vEnum = results.elements();
            ArrayList<Album> albums = new ArrayList<>();
            while (vEnum.hasMoreElements()){
                SoapObject result = (SoapObject) vEnum.nextElement();
                Album album = albumFromSoap(result);
                albums.add(album);
            }

            return albums;
        } else {
            return null;
        }
    }

    private Album albumFromSoap(SoapObject result) {
        Album album = new Album();
        album.setId(Long.parseLong(result.getProperty("id").toString()));
        album.setImage(result.getProperty("image").toString());
        album.setName(result.getProperty("name").toString());
        album.setSinger(result.getProperty("singer").toString());

        return album;
    }

    @Override
    protected void onPostExecute(ArrayList<Album> albums) {
        if (albums != null){
            response.finishGettingAlbum(albums);
        }
    }
}

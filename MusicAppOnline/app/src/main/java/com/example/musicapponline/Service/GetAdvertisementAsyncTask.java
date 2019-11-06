package com.example.musicapponline.Service;

import android.os.AsyncTask;

import com.example.musicapponline.Model.Advertisement;
import com.example.musicapponline.Model.Song;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;

public class GetAdvertisementAsyncTask extends AsyncTask<String, Void, ArrayList<Advertisement>> {
    public AsyncArrayListResponse response;
    private static final String NAMESPACE = "http://service";
    public static final String METHOD_NAME = "getAllAdvertisement";

    @Override
    protected ArrayList<Advertisement> doInBackground(String... strings) {

        Vector<SoapObject> results = WebService.getArrayListResultDefault(METHOD_NAME, NAMESPACE);
        if (results != null) {
            Enumeration resultEnum = results.elements();
            ArrayList<Advertisement> advertisements = new ArrayList<>();

            while (resultEnum.hasMoreElements()) {
                SoapObject result = (SoapObject) resultEnum.nextElement();
                Advertisement advertisement = advertisementFromSoap(result);
                advertisements.add(advertisement);
            }

            return advertisements;
        } else {
            return null;
        }
    }


    @Override
    protected void onPostExecute(ArrayList<Advertisement> advertisements) {
        if (advertisements != null) {
            response.finishGettingAdvertisement(advertisements);
        }
    }

    private Advertisement advertisementFromSoap(SoapObject result) {
        Advertisement advertisement = new Advertisement();

        SoapObject object = (SoapObject) result.getProperty("song");

        Song song = new Song();
        song.setId(Long.parseLong(object.getProperty("id").toString()));
        song.setName(object.getProperty("name").toString());
        song.setAlbum_id(object.getProperty("album_id").toString());
        song.setCategory_id(object.getProperty("category_id").toString());
        song.setImage(object.getProperty("image").toString());
        song.setLike_number(Integer.parseInt(object.getProperty("like_number").toString()));
        song.setPlaylist_id(object.getProperty("playlist_id").toString());
        song.setSinger(object.getProperty("singer").toString());
        song.setUrl(object.getProperty("url").toString());

        advertisement.setId(Long.parseLong(result.getProperty("id").toString()));
        advertisement.setContent(result.getProperty("content").toString());
        advertisement.setImage(result.getProperty("image").toString());
        advertisement.setSong(song);

        return advertisement;
    }
}

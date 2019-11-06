package com.example.musicapponline.Service;

import com.example.musicapponline.Model.Song;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.Vector;

public class WebService {
    private static final String base_url = "http://192.168.1.3:8080/MusicApp/";

    public static String getWSDLUrl(){
        return base_url + "services/AllMusicService?wsdl";
    }

    public static String getImageUrl(String url){
        return base_url + "app/image/" + url;
    }

    public static String getMusicUrl(String url){
        return base_url + "app/" + url;
    }

    public static Vector<SoapObject> getArrayListResultFromRandom(String METHOD_NAME,String NAMESPACE,String status){

        String SOAP_ACTION = NAMESPACE + "/" + METHOD_NAME;

        SoapObject request = new SoapObject(NAMESPACE,METHOD_NAME);
        request.addProperty("status",status);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransportSE = new HttpTransportSE(getWSDLUrl());
        httpTransportSE.debug = true;

        try {
            httpTransportSE.call(SOAP_ACTION,envelope);
            Vector<SoapObject> results = new Vector<SoapObject>();
            if (envelope.getResponse() instanceof SoapObject){
                SoapObject object = (SoapObject)envelope.getResponse();
                results.add(object);
            } else {
                results = (Vector<SoapObject>) envelope.getResponse();
            }
            return results;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static Vector<SoapObject> getArrayListResultDefault(String METHOD_NAME,String NAMESPACE){

        String SOAP_ACTION = NAMESPACE + "/" + METHOD_NAME;

        SoapObject request = new SoapObject(NAMESPACE,METHOD_NAME);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransportSE = new HttpTransportSE(getWSDLUrl());
        httpTransportSE.debug = true;

        try {
            httpTransportSE.call(SOAP_ACTION,envelope);
            Vector<SoapObject> results = new Vector<SoapObject>();
            if (envelope.getResponse() instanceof SoapObject){
                SoapObject object = (SoapObject)envelope.getResponse();
                results.add(object);
            } else {
                results = (Vector<SoapObject>) envelope.getResponse();
            }
            return results;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static Vector<SoapObject> getArrayListResultFromId(String METHOD_NAME,String NAMESPACE,String id){

        String SOAP_ACTION = NAMESPACE + "/" + METHOD_NAME;

        SoapObject request = new SoapObject(NAMESPACE,METHOD_NAME);
        request.addProperty("id",id);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransportSE = new HttpTransportSE(getWSDLUrl());
        httpTransportSE.debug = true;

        try {
            httpTransportSE.call(SOAP_ACTION,envelope);
            Vector<SoapObject> results = new Vector<SoapObject>();
            if (envelope.getResponse() instanceof SoapObject){
                SoapObject object = (SoapObject)envelope.getResponse();
                results.add(object);
            } else {
                results = (Vector<SoapObject>) envelope.getResponse();
            }
            return results;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            return null;
        }

    }



    public static Song songFromSoap(SoapObject result){
        Song song = new Song();
        song.setId(Long.parseLong(result.getProperty("id").toString()));
        song.setName(result.getProperty("name").toString());
        song.setImage(result.getProperty("image").toString());
        song.setAlbum_id(result.getProperty("album_id").toString());
        song.setCategory_id(result.getProperty("category_id").toString());
        song.setLike_number(Integer.parseInt(result.getProperty("like_number").toString()));
        song.setPlaylist_id(result.getProperty("playlist_id").toString());
        song.setSinger(result.getProperty("singer").toString());
        song.setUrl(result.getProperty("url").toString());

        return song;
    }
}

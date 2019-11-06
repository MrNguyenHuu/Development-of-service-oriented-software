package com.example.musicapponline.Service;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.musicapponline.R;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class IncSongLikeNumberAsyncTask extends AsyncTask<String,Void,String> {

    private static final String NAMESPACE = "http://service";
    private static final String METHOD_NAME = "incLikeNumber";

    Context context;
    ImageView iv_love;

    public IncSongLikeNumberAsyncTask(Context context, ImageView iv_love) {
        this.context = context;
        this.iv_love = iv_love;
    }

    @Override
    protected String doInBackground(String... strings) {
        String SOAP_ACTION = NAMESPACE + "/" + METHOD_NAME;

        SoapObject request = new SoapObject(NAMESPACE,METHOD_NAME);
        request.addProperty("id",strings[0]);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransportSE = new HttpTransportSE(WebService.getWSDLUrl());
        httpTransportSE.debug = true;

        try {
            httpTransportSE.call(SOAP_ACTION,envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            return result.toString();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String s) {
        if (s.equals("success")){
            iv_love.setImageResource(R.drawable.iconloved);
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
            iv_love.setEnabled(false);
        } else {
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
        }
    }
}

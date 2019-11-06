package com.example.musicapponline.Service;

import android.os.AsyncTask;

import com.example.musicapponline.Model.Topic;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;

public class GetTopicAsyncTask extends AsyncTask<String,Void, ArrayList<Topic>> {

    public AsyncArrayListResponse response;
    private static final String NAMESPACE = "http://service";
    public static final String METHOD_NAME = "getRandomTopic";

    @Override
    protected ArrayList<Topic> doInBackground(String... strings) {

        Vector<SoapObject> results = WebService.getArrayListResultFromRandom(METHOD_NAME,NAMESPACE,strings[0]);
        if (results != null){
            Enumeration resultEnum = results.elements();
            ArrayList<Topic> topics = new ArrayList<>();
            while (resultEnum.hasMoreElements()){
                SoapObject result = (SoapObject) resultEnum.nextElement();
                Topic topic = topicFromSoap(result);
                topics.add(topic);
            }
            return topics;
        } else {
            return null;
        }

    }

    private Topic topicFromSoap(SoapObject result) {
        Topic topic = new Topic();

        topic.setId(Long.parseLong(result.getProperty("id").toString()));
        topic.setName(result.getProperty("name").toString());
        topic.setImage(result.getProperty("image").toString());

        return topic;
    }

    @Override
    protected void onPostExecute(ArrayList<Topic> topics) {
        if (topics != null){
            response.finishGettingTopic(topics);
        }

    }
}

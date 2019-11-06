package com.example.musicapponline.Service;

import android.os.AsyncTask;

import com.example.musicapponline.Model.Category;


import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;

public class GetCategoryAsyncTask extends AsyncTask<String, Void, ArrayList<Category>> {
    public AsyncArrayListResponse response;
    private static final String NAMESPACE = "http://service";
    public static final String METHOD_NAME = "getRandomCategory";

    @Override
    protected ArrayList<Category> doInBackground(String... strings) {
        Vector<SoapObject> results = WebService.getArrayListResultFromRandom(METHOD_NAME, NAMESPACE, strings[0]);
        if (results != null) {
            Enumeration resultEnum = results.elements();
            ArrayList<Category> categories = new ArrayList<>();
            while (resultEnum.hasMoreElements()) {
                SoapObject result = (SoapObject) resultEnum.nextElement();
                Category category = categoryFromSoap(result);
                categories.add(category);
            }
            return categories;
        } else {
            return null;
        }
    }

    private Category categoryFromSoap(SoapObject result) {
        Category category = new Category();

        category.setId(Long.parseLong(result.getProperty("id").toString()));
        category.setName(result.getProperty("name").toString());
        category.setImage(result.getProperty("image").toString());
        category.setTopic_id(Long.parseLong(result.getProperty("topic_id").toString()));

        return category;
    }

    @Override
    protected void onPostExecute(ArrayList<Category> categories) {
        if (categories != null) {
            response.finishGettingCategory(categories);
        }

    }
}

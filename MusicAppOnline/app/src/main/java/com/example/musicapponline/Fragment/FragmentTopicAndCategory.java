package com.example.musicapponline.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.musicapponline.Activity.ListCategoryActivity;
import com.example.musicapponline.Activity.ListSongActivity;
import com.example.musicapponline.Activity.ListTopicActivity;
import com.example.musicapponline.Model.Advertisement;
import com.example.musicapponline.Model.Album;
import com.example.musicapponline.Model.Category;
import com.example.musicapponline.Model.Playlist;
import com.example.musicapponline.Model.Song;
import com.example.musicapponline.Model.Topic;
import com.example.musicapponline.R;
import com.example.musicapponline.Service.AsyncArrayListResponse;
import com.example.musicapponline.Service.GetCategoryAsyncTask;
import com.example.musicapponline.Service.GetTopicAsyncTask;
import com.example.musicapponline.Service.WebService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FragmentTopicAndCategory extends Fragment implements AsyncArrayListResponse {
    View view;
    TextView tv_viewMore;
    HorizontalScrollView horizontalScrollView;

    LinearLayout linearLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_topic_and_category,container,false);

        horizontalScrollView = view.findViewById(R.id.horizontalScrollView);
        tv_viewMore = view.findViewById(R.id.tv_view_more_topic);

        linearLayout = new LinearLayout(getActivity());
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);



        getData();

        horizontalScrollView.addView(linearLayout);

        tv_viewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListTopicActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void getData() {
        GetCategoryAsyncTask categoryAsyncTask = new GetCategoryAsyncTask();
        GetTopicAsyncTask topicAsyncTask = new GetTopicAsyncTask();

        categoryAsyncTask.response = this;
        topicAsyncTask.response = this;

        topicAsyncTask.execute("true");
        categoryAsyncTask.execute("true");


    }

    @Override
    public void finishGettingAdvertisement(ArrayList<Advertisement> output) {

    }

    @Override
    public void finishGettingPlaylist(ArrayList<Playlist> output) {

    }

    @Override
    public void finishGettingTopic(final ArrayList<Topic> output) {


        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(400,200);
        layoutParams.setMargins(10,20,10,30);
        for (int i = 0; i < output.size();i++){
            final Topic topic = output.get(i);
            CardView cardView = new CardView(getActivity());
            cardView.setRadius(10);
            ImageView imageView = new ImageView(getActivity());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            if (output.get(i).getImage() != null){
                Picasso.with(getActivity()).load(WebService.getImageUrl(topic.getImage())).into(imageView);
            }
            cardView.setLayoutParams(layoutParams);
            cardView.addView(imageView);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), ListCategoryActivity.class);
                    intent.putExtra("topic",topic);
                    startActivity(intent);
                }
            });

            linearLayout.addView(cardView);
        }
    }

    @Override
    public void finishGettingCategory(final ArrayList<Category> output) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(200,200);
        layoutParams.setMargins(10,20,10,30);
        for (int i = 0; i < output.size();i++){
            final Category category = output.get(i);
            CardView cardView = new CardView(getActivity());
            cardView.setRadius(10);
            ImageView imageView = new ImageView(getActivity());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            if (output.get(i).getImage() != null){
                Picasso.with(getActivity()).load(WebService.getImageUrl(category.getImage())).into(imageView);
            }
            cardView.setLayoutParams(layoutParams);
            cardView.addView(imageView);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), ListSongActivity.class);
                    intent.putExtra("category",category);
                    startActivity(intent);
                }
            });

            linearLayout.addView(cardView);
        }
    }

    @Override
    public void finishGettingAlbum(ArrayList<Album> output) {

    }

    @Override
    public void finishGettingSong(ArrayList<Song> output) {

    }
}

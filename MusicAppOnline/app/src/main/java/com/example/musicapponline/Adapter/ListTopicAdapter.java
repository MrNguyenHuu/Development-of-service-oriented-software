package com.example.musicapponline.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.musicapponline.Activity.ListCategoryActivity;
import com.example.musicapponline.Model.Topic;
import com.example.musicapponline.R;
import com.example.musicapponline.Service.WebService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListTopicAdapter extends RecyclerView.Adapter<ListTopicAdapter.ViewHolder>{

    Context context;
    ArrayList<Topic> topics;
    View view;
    public ListTopicAdapter(Context context, ArrayList<Topic> topics) {
        this.context = context;
        this.topics = topics;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.list_topic_base,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Topic topic = topics.get(i);
        Picasso.with(context).load(WebService.getImageUrl(topic.getImage())).into(viewHolder.iv_topic_list_item);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ListCategoryActivity.class);
                intent.putExtra("topic",topic);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return topics.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView iv_topic_list_item;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_topic_list_item = itemView.findViewById(R.id.iv_list_topic_item);
        }
    }
}

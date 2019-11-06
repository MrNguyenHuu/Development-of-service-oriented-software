package com.example.musicapponline.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.musicapponline.Activity.ListSongActivity;
import com.example.musicapponline.Model.Album;
import com.example.musicapponline.R;
import com.example.musicapponline.Service.WebService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListAlbumAdapter  extends RecyclerView.Adapter<ListAlbumAdapter.ViewHolder>{

    Context context;
    ArrayList<Album> albums;
    View view;

    public ListAlbumAdapter(Context context, ArrayList<Album> albums) {
        this.context = context;
        this.albums = albums;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.list_album_base,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Album album = albums.get(i);
        viewHolder.tv_album_singer.setText(album.getSinger());
        viewHolder.tv_album_name.setText(album.getName());
        Picasso.with(context).load(WebService.getImageUrl(album.getImage())).into(viewHolder.iv_album_item);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ListSongActivity.class);
                intent.putExtra("album",album);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView iv_album_item;
        TextView tv_album_name, tv_album_singer;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_album_item = itemView.findViewById(R.id.iv_list_album_item);
            tv_album_name = itemView.findViewById(R.id.tv_album_item_name);
            tv_album_singer = itemView.findViewById(R.id.tv_album_item_singer);
        }
    }
}

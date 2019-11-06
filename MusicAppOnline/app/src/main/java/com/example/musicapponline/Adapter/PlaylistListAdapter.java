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
import com.example.musicapponline.Model.Playlist;
import com.example.musicapponline.R;
import com.example.musicapponline.Service.WebService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PlaylistListAdapter extends RecyclerView.Adapter<PlaylistListAdapter.ViewHolder>{
    Context context;
    ArrayList<Playlist> playlists;
    View view;

    public PlaylistListAdapter(Context context, ArrayList<Playlist> playlists) {
        this.context = context;
        this.playlists = playlists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.playlist_list_base,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Playlist playlist = playlists.get(i);
        viewHolder.tv_playlist_list_item.setText(playlist.getName());
        Picasso.with(context).load(WebService.getImageUrl(playlist.getIcon())).into(viewHolder.iv_playlist_list_item);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ListSongActivity.class);
                intent.putExtra("playlist",playlist);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return playlists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView iv_playlist_list_item;
        TextView tv_playlist_list_item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_playlist_list_item = itemView.findViewById(R.id.iv_list_playlist);
            tv_playlist_list_item = itemView.findViewById(R.id.tv_list_playlist_item_name);
        }
    }


}

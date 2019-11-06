package com.example.musicapponline.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.musicapponline.Model.Playlist;
import com.example.musicapponline.R;
import com.example.musicapponline.Service.WebService;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlaylistAdapter extends ArrayAdapter<Playlist> {
    public PlaylistAdapter(@NonNull Context context, int resource,@NonNull List<Playlist> objects) {
        super(context, resource, objects);
    }

    class ViewHolder {
        TextView tv_playlistName;
        ImageView iv_backgroundPlaylist, iv_iconPlaylist;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView,@NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;

        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.playlist_base,null);
            viewHolder = new ViewHolder();

            viewHolder.tv_playlistName = convertView.findViewById(R.id.tv_playlist_name);
            viewHolder.iv_iconPlaylist = convertView.findViewById(R.id.iv_playlist_icon);
            viewHolder.iv_backgroundPlaylist = convertView.findViewById(R.id.iv_background_playlist);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Playlist playlist = getItem(position);
        Picasso.with(getContext()).load(WebService.getImageUrl(playlist.getBackground())).into(viewHolder.iv_backgroundPlaylist);
        Picasso.with(getContext()).load(WebService.getImageUrl(playlist.getIcon())).into(viewHolder.iv_iconPlaylist);
        viewHolder.tv_playlistName.setText(playlist.getName());


        return convertView;
    }
}

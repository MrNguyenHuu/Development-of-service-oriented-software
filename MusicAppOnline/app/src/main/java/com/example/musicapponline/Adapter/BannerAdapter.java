package com.example.musicapponline.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.musicapponline.Activity.ListSongActivity;
import com.example.musicapponline.Model.Advertisement;
import com.example.musicapponline.R;
import com.example.musicapponline.Service.WebService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BannerAdapter extends PagerAdapter {
    Context context;
    ArrayList<Advertisement> advertisements;

    public BannerAdapter(Context context, ArrayList<Advertisement> advertisements) {
        this.context = context;
        this.advertisements = advertisements;
    }

    @Override
    public int getCount() {
        return advertisements.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.banner_base,container,false);

        ImageView iv_background = (ImageView)view.findViewById(R.id.iv_background);
        ImageView iv_banner = (ImageView)view.findViewById(R.id.iv_banner);
        TextView tv_title = (TextView)view.findViewById(R.id.tv_title);
        TextView tv_content = (TextView)view.findViewById(R.id.tv_content);

        Picasso.with(context).load(WebService.getImageUrl(advertisements.get(position).getImage())).into(iv_background);
        Picasso.with(context).load(WebService.getImageUrl(advertisements.get(position).getSong().getImage())).into(iv_banner);
        tv_title.setText(advertisements.get(position).getSong().getName());
        tv_content.setText(advertisements.get(position).getContent());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ListSongActivity.class);
                intent.putExtra("banner",advertisements.get(position));
                context.startActivity(intent);
            }
        });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}

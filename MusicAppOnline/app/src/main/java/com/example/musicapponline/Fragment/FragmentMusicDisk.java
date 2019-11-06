package com.example.musicapponline.Fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import com.example.musicapponline.R;
import com.example.musicapponline.Service.WebService;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class FragmentMusicDisk extends Fragment {
    View view;
    CircleImageView iv_disk;
    ObjectAnimator animator;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_music_disk,container,false);
        iv_disk = view.findViewById(R.id.iv_music_disk);
        animator = ObjectAnimator.ofFloat(iv_disk,"rotation",0f,360f);
        animator.setDuration(10000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setInterpolator(new LinearInterpolator());
        return view;
    }

    public void playMusic(String image){
        Picasso.with(getActivity()).load(WebService.getImageUrl(image)).into(iv_disk);
        animator.start();
    }
}

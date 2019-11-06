package com.example.musicapponline.Activity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.musicapponline.Adapter.MusicPlayerViewPagerAdapter;
import com.example.musicapponline.Fragment.FragmentMusicDisk;
import com.example.musicapponline.Fragment.FragmentMusicPlayerList;
import com.example.musicapponline.Model.Song;
import com.example.musicapponline.R;
import com.example.musicapponline.Service.WebService;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class MusicPlayerActivity extends AppCompatActivity {

    Toolbar toolbar;
    static TextView tv_total_time;
    TextView tv_time_song;
    static SeekBar seekBar;
    ViewPager vp_music_player;
    ImageButton btn_shuffle, btn_prev, btn_play, btn_next, btn_repeat;

    public static ArrayList<Song> songs = new ArrayList<>();
    public static MusicPlayerViewPagerAdapter musicPlayerViewPagerAdapter;
    public static FragmentMusicDisk musicDiskFragment;
    public static FragmentMusicPlayerList musicListFragment;

    public static MediaPlayer mediaPlayer = new MediaPlayer();

    static SimpleDateFormat format = new SimpleDateFormat("mm:ss");
    public static int position;
    boolean repeat;
    boolean random;
    static boolean next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        setDefaultVariable();

        getDataFromIntent();
        init();

        eventClick();

    }

    private void setDefaultVariable() {
        position = 0;
        repeat = false;
        random = false;
        next = false;
    }

    private void eventClick() {
        //set first spinning disk
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (songs.size() > 0){
                    if (musicPlayerViewPagerAdapter.getItem(1) != null){
                        musicDiskFragment.playMusic(songs.get(0).getImage());
                        handler.removeCallbacks(this);
                    } else {
                        handler.postDelayed(this,500);
                    }
                }
            }
        },500);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (songs.size() > 0){
                    if (musicPlayerViewPagerAdapter.getItem(0) != null){
                        musicListFragment.addColor(position);
                        handler.removeCallbacks(this);
                    } else {
                        handler.postDelayed(this,500);
                    }
                }
            }
        },500);

        //event button play
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null){
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.pause();
                        btn_play.setImageResource(R.drawable.iconplay);
                    } else {
                        mediaPlayer.start();
                        btn_play.setImageResource(R.drawable.iconpause);
                    }
                }
            }
        });

        //event seekBar
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null){
                    if (mediaPlayer.isPlaying()) {
                        seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    }
                }
                if (next == true){
                    nextSong();
                    next = false;
                }
                handler.postDelayed(this, 500);
            }
        }, 500);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv_time_song.setText(format.format(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mediaPlayer.pause();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
                mediaPlayer.start();
            }
        });

        //event click repeat
        btn_repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (repeat == false) {
                    if (random == true) {
                        random = false;
                        btn_shuffle.setImageResource(R.drawable.iconsuffle);
                    }
                    repeat = true;
                    btn_repeat.setImageResource(R.drawable.iconsyned);
                } else {
                    repeat = false;
                    btn_repeat.setImageResource(R.drawable.iconrepeat);
                }
            }
        });

        //event click random
        btn_shuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (random == false) {
                    if (repeat == true) {
                        repeat = false;
                        btn_repeat.setImageResource(R.drawable.iconrepeat);
                    }
                    random = true;
                    btn_shuffle.setImageResource(R.drawable.iconshuffled);
                } else {
                    random = false;
                    btn_shuffle.setImageResource(R.drawable.iconsuffle);
                }
            }
        });

        //event btn next
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextSong();
                disableNextPrev();
            }
        });

        // event btn prev
        btn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (songs.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                        musicListFragment.removeColor(position);
                    }
                    if (position < songs.size()) {
                        btn_play.setImageResource(R.drawable.iconpause);
                        position--;

                        if (position < 0) {
                            position = songs.size() - 1;
                        }

                        if (repeat == true) {
                            if (position == songs.size() - 1) {
                                position = -1;
                            }
                            position++;
                        }

                        if (random == true && songs.size() > 1) {
                            Random random = new Random();
                            int index = random.nextInt(songs.size() - 1);

                            if (position == songs.size() - 1 && index != 0) {
                                position = index;
                            }

                            if ( position != songs.size() - 1 && index != position + 1) {
                                position = index;
                            }
                        }
                        playMusic(position);

                    }
                }
                disableNextPrev();
            }
        });


    }

    private void disableNextPrev() {
        btn_next.setClickable(false);
        btn_prev.setClickable(false);
        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run () {
                btn_next.setClickable(true);
                btn_prev.setClickable(true);
            }
        },5000);
    }

    private void playMusic(int position) {
        new PlayMp3().execute(WebService.getMusicUrl(songs.get(position).getUrl()));
        musicDiskFragment.playMusic(songs.get(position).getImage());
        getSupportActionBar().setTitle(songs.get(position).getName());
        musicListFragment.addColor(position);
    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        songs.clear();
        if (intent != null) {
            if (intent.hasExtra("song")) {
                Song song = intent.getParcelableExtra("song");
                songs.add(song);
            }

            if (intent.hasExtra("songs")) {
                songs = intent.getParcelableArrayListExtra("songs");
            }
        }
    }

    private void init() {
        tv_time_song = findViewById(R.id.tv_time_song);
        tv_total_time = findViewById(R.id.tv_total_time_song);
        seekBar = findViewById(R.id.seekbar);
        btn_next = findViewById(R.id.btn_next);
        btn_play = findViewById(R.id.btn_play);
        btn_prev = findViewById(R.id.btn_prev);
        btn_shuffle = findViewById(R.id.btn_suffle);
        btn_repeat = findViewById(R.id.btn_repeat);
        vp_music_player = findViewById(R.id.vp_music_player);
        toolbar = findViewById(R.id.toolbar_music_player);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Music player");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        musicPlayerViewPagerAdapter = new MusicPlayerViewPagerAdapter(getSupportFragmentManager());
        musicDiskFragment = new FragmentMusicDisk();
        musicListFragment = new FragmentMusicPlayerList();
        musicPlayerViewPagerAdapter.addFragment(musicListFragment);
        musicPlayerViewPagerAdapter.addFragment(musicDiskFragment);


        vp_music_player.setAdapter(musicPlayerViewPagerAdapter);
        vp_music_player.setCurrentItem(1);

        musicDiskFragment = (FragmentMusicDisk) musicPlayerViewPagerAdapter.getItem(1);
        musicListFragment = (FragmentMusicPlayerList)musicPlayerViewPagerAdapter.getItem(0);
        if (songs.size() > 0) {
            getSupportActionBar().setTitle(songs.get(position).getName());
            new PlayMp3().execute(WebService.getMusicUrl(songs.get(position).getUrl()));
            btn_play.setImageResource(R.drawable.iconpause);

        }
    }

    public static class PlayMp3 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }
        @Override
        protected void onPostExecute(String song) {
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.stop();
                        mp.reset();
                    }
                });
                mediaPlayer.setDataSource(song);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            timeSong();

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    next = true;
                }
            });
        }

    }

    private void nextSong(){
        if (songs.size() > 0) {
            if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
                musicListFragment.removeColor(position);
            }
            if (position < songs.size()) {
                btn_play.setImageResource(R.drawable.iconpause);
                position++;

                if (position == songs.size()) {
                    position = 0;
                }

                if (repeat == true) {
                    if (position == 0) {
                        position = songs.size();
                    }
                    position--;
                }

                if (random == true && songs.size() > 1) {
                    Random random = new Random();
                    int index = random.nextInt(songs.size() - 1);

                    if (position == 0 && index != songs.size() - 1) {
                        position = index;
                    }

                    if ( position != 0 && index != position - 1) {
                        position = index;
                    }
                }
                playMusic(position);
            }
        }

    }
    private static void timeSong() {
        tv_total_time.setText(format.format(mediaPlayer.getDuration()));
        seekBar.setMax(mediaPlayer.getDuration());
    }


    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayer.stop();
        songs.clear();
    }
}

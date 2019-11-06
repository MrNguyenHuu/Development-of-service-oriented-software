package com.example.musicapponline.Activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.musicapponline.Adapter.MainViewPagerAdapter;
import com.example.musicapponline.Fragment.FragmentHome;
import com.example.musicapponline.Fragment.FragmentSearch;
import com.example.musicapponline.R;

public class MainActivity extends AppCompatActivity {

    TabLayout tl_main;
    ViewPager vp_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getViewComponent();
        initAdapter();
    }

    private void initAdapter() {
        MainViewPagerAdapter adapter = new MainViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentHome(),"Home");
        adapter.addFragment(new FragmentSearch(),"Search");
        vp_main.setAdapter(adapter);
        tl_main.setupWithViewPager(vp_main);
        tl_main.getTabAt(0).setIcon(R.drawable.icontrangchu);
        tl_main.getTabAt(1).setIcon(R.drawable.iconsearch);
    }

    private void getViewComponent(){
        tl_main = (TabLayout)findViewById(R.id.tl_main);
        vp_main = (ViewPager)findViewById(R.id.vp_main);
    }

}

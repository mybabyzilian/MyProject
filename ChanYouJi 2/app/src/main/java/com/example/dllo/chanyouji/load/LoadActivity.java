package com.example.dllo.chanyouji.load;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.example.dllo.chanyouji.R;
import com.example.dllo.chanyouji.base.BaseActivity;

import java.util.ArrayList;

/**
 * Created by dllo on 16/7/6.
 */
public class LoadActivity extends BaseActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private LoadAdapter adapter;
    private ArrayList<Fragment> datas;
    private ImageView okIv;
    @Override
    public int setLayout() {
        return R.layout.activity_load;
    }

    @Override
    protected void initData() {

        okIv = (ImageView) findViewById(R.id.ok);
        viewPager = (ViewPager) findViewById(R.id.load_viewpager);
        tabLayout = (TabLayout) findViewById(R.id.load_tablayout);
        adapter = new LoadAdapter(getSupportFragmentManager());
        datas = new ArrayList<>();
        datas.add(new LoadFragment());
        datas.add(new RegisterFragment());
        adapter.setDatas(datas);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);



    }




}

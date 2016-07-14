package com.example.dllo.chanyouji.workbox;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.dllo.chanyouji.R;
import com.example.dllo.chanyouji.base.BaseActivity;

import java.util.ArrayList;

/**
 * Created by dllo on 16/7/7.
 */
public class ToolBoxActivitry extends BaseActivity {
    private ArrayList<Fragment> datas;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ToolBoxAdapter adapter;
    private MyBrodcastReceiver receiver;


    @Override
    public int setLayout() {
        return R.layout.activity_toolbox;
    }

    @Override
    protected void initData() {
        datas = new ArrayList<>();
        viewPager = (ViewPager) findViewById(R.id.tool_viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tool_tablayout);
        adapter = new ToolBoxAdapter(getSupportFragmentManager());
        datas.add(new ToolForeginFragment());
        datas.add(new ToolIslandFragment());
        adapter.setDatas(datas);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        receiver = new MyBrodcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.example.dllo.chanyouji");
        registerReceiver(receiver,filter);



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    class MyBrodcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            finish();
        }
    }
}

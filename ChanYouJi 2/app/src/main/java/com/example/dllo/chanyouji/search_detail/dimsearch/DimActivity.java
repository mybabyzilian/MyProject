package com.example.dllo.chanyouji.search_detail.dimsearch;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.dllo.chanyouji.R;
import com.example.dllo.chanyouji.base.BaseActivity;

import java.util.ArrayList;

/**
 * Created by dllo on 16/7/6.
 */
public class DimActivity extends BaseActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<Fragment> datas;
    private DimAdapter adapter;

    @Override
    public int setLayout() {
        return R.layout.activity_dim;
    }

    @Override
    protected void initData() {
        tabLayout = (TabLayout) findViewById(R.id.dim_tablayout);
        viewPager = (ViewPager) findViewById(R.id.dim_viewpager);
        datas = new ArrayList<>();
        adapter = new DimAdapter(getSupportFragmentManager());
        datas.add(new DimYouJiFragment());
        datas.add(new DimJourneyFragment());
        datas.add(new DimUserFragmnet());
        adapter.setDatas(datas);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


    }
}

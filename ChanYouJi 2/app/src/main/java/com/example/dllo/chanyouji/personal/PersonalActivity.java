package com.example.dllo.chanyouji.personal;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.example.dllo.chanyouji.R;
import com.example.dllo.chanyouji.base.BaseActivity;

import java.util.ArrayList;

/**
 * Created by dllo on 16/7/8.
 */
public class PersonalActivity extends BaseActivity {
    private ArrayList<Fragment> datas;
    private PersonalAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    public int setLayout() {
        return R.layout.activity_personal;
    }

    @Override
    protected void initData() {
        datas = new ArrayList<>();
        adapter = new PersonalAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.personal_viewpager);
        tabLayout = (TabLayout) findViewById(R.id.personal_tablayout);
        datas.add(new PersonYouJiFragment());
        datas.add(new PersonCollectionFragment());
        datas.add(new PersonLikeFragment());
        adapter.setDatas(datas);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}

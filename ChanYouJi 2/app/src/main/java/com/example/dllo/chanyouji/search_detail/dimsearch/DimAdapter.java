package com.example.dllo.chanyouji.search_detail.dimsearch;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/7/6.
 */
public class DimAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> datas;
    private String[] title = {"游记","旅行地","用户"};


    public void setDatas(ArrayList<Fragment> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public DimAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return datas.get(position);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}

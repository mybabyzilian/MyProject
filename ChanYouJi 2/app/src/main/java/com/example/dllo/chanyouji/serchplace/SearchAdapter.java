package com.example.dllo.chanyouji.serchplace;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/6/25.
 */
public class SearchAdapter extends FragmentPagerAdapter {
    private String[] title = {"国外","国内","四季"};
    private ArrayList<Fragment> datas;

    public void setDatas(ArrayList<Fragment> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public SearchAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return datas.get(position);
    }

    @Override
    public int getCount() {
        return datas.size() ;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}

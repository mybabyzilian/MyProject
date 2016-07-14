package com.example.dllo.chanyouji.personal;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/7/8.
 */
public class PersonalAdapter extends FragmentPagerAdapter {
    private String[] title = {"游记","收藏","喜欢"};
    private ArrayList<Fragment>  datas;

    public void setDatas(ArrayList<Fragment> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public PersonalAdapter(FragmentManager fm) {
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

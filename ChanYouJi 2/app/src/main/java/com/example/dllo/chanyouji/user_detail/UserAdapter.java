package com.example.dllo.chanyouji.user_detail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/6/27.
 */
public class UserAdapter extends FragmentPagerAdapter {
    private String[] title = {"游记","喜欢"};
    private ArrayList<Fragment> datas;

    public UserAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setDatas(ArrayList<Fragment> datas) {
        this.datas = datas;
        notifyDataSetChanged();
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

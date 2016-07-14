package com.example.dllo.chanyouji.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.dllo.chanyouji.R;
import com.example.dllo.chanyouji.base.BaseActivity;
import com.example.dllo.chanyouji.load.LoadActivity;
import com.example.dllo.chanyouji.personal.PersonalActivity;
import com.example.dllo.chanyouji.serchplace.SearchActivity;
import com.example.dllo.chanyouji.strategy.StrategyFragment;
import com.example.dllo.chanyouji.user_detail.UserActivity;
import com.example.dllo.chanyouji.youji.YouJiFragment;
import com.example.dllo.chanyouji.workbox.Work_boxFragment;

import java.util.ArrayList;

import cn.bmob.v3.BmobUser;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<Fragment> datas;
    private MainAdapter adapter;
    private ImageView titleMenu;
    private ImageView titlePersonal;
    private ImageView titleSearch;
    private PopupWindow popupWindow;
    private ImageView loadIv;
    private SharedPreferences sp;
    private View view;
    private TextView setTv;



    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        loadIv = (ImageView) findViewById(R.id.title_personal);
        viewPager = (ViewPager) findViewById(R.id.main_viewpager);
        tabLayout = (TabLayout) findViewById(R.id.main_tablayout);
        titleMenu = (ImageView) findViewById(R.id.title_menu);
        titleMenu.setOnClickListener(this);
        titlePersonal = (ImageView) findViewById(R.id.title_personal);
        titlePersonal.setOnClickListener(this);
        titleSearch = (ImageView) findViewById(R.id.title_search);
        titleSearch.setOnClickListener(this);
        datas = new ArrayList<>();
        datas.add(new YouJiFragment());
        datas.add(new StrategyFragment());
        datas.add(new Work_boxFragment());
        adapter = new MainAdapter(getSupportFragmentManager());
        adapter.setDatas(datas);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        loadIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 判断是否是是第一次登陆
                sp = getSharedPreferences("login",MODE_PRIVATE);
                int id = sp.getInt("user",0);
                if (id == 1){
                    // 如果id= 1 说明不是第一次登陆,跳转到个人界面
                    Intent intent = new Intent(MainActivity.this, PersonalActivity.class);
                    startActivity(intent);

                }else {
                    // 否则跳到登陆界面
                    Intent intent = new Intent(MainActivity.this, LoadActivity.class);
                    startActivity(intent);
                }



            }
        });
        // popuwindow布局
         view = LayoutInflater.from(this).inflate(R.layout.title_menu_popu,null);
        // 退出登陆的监听
        setTv = (TextView) view.findViewById(R.id.setting);
        setTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("login", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.commit();
                Intent intent = new Intent(MainActivity.this,LoadActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_menu:

        initPopup();


        break;
        case R.id.title_personal:

        break;
        case R.id.title_search:
            Intent intent = new Intent(MainActivity.this, SearchActivity.class);
            startActivity(intent);

        break;
    }

}
    private void initPopup(){
        popupWindow = new PopupWindow(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        popupWindow.setContentView(view);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
      //  popupWindow.showAsDropDown(view,915,252);
        popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, 915,252);


    }

}

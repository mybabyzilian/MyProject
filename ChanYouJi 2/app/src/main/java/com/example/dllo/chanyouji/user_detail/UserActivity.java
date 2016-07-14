package com.example.dllo.chanyouji.user_detail;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.example.dllo.chanyouji.R;
import com.example.dllo.chanyouji.base.BaseActivity;
import com.example.dllo.chanyouji.datarequest.SingleQueue;
import com.example.dllo.chanyouji.toolsclass.StringEvent;
import com.example.dllo.chanyouji.user_detail.user_bean.UserBean;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;


/**
 * Created by dllo on 16/6/27.
 */
public class UserActivity extends BaseActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ArrayList<Fragment> datas;
    private UserAdapter adapter;
    private ImageView headIv;
    private int id;
    private TextView userName;
    private TextView youjiCount;
    private ImageView sexIv;


    @Override
    public int setLayout() {
        return R.layout.user_detail_activity;

    }

    @Override
    protected void initData() {
        headIv = (ImageView) findViewById(R.id.user_head_image);
        userName = (TextView) findViewById(R.id.user_screen_name);
        youjiCount = (TextView) findViewById(R.id.user_youji_count);
        sexIv = (ImageView) findViewById(R.id.user_sex_iamge);
        Intent intent = getIntent();
        id = Integer.valueOf(intent.getStringExtra("id"));
        RequestQueue requestQueue = SingleQueue.getSingleQueue(this).getRequestQueue();
        StringRequest stringRequest = new StringRequest("http://chanyouji.com/api/users/"+id+".json?page=1" , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Gson gson = new Gson();
                UserBean datas = gson.fromJson(response,UserBean.class );
                int a = datas.getGender();
                String imageUrl = datas.getImage();
                SingleQueue.getSingleQueue(UserActivity.this).getImageLoader().get(imageUrl,
                        ImageLoader.getImageListener(headIv, R.mipmap.ic_launcher,
                                R.mipmap.ic_launcher));
                userName.setText(datas.getName());
                youjiCount.setText(datas.getTrips_count() + "篇游记");
                if (datas.getGender() == 0 ){
                    sexIv.setImageResource(R.mipmap.female);
                }else {
                    sexIv.setImageResource(R.mipmap.male);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("UserYouJiFragment", error.getMessage());
            }
        });
        requestQueue.add(stringRequest);
        viewPager = (ViewPager) findViewById(R.id.user_view_pager);
        tabLayout = (TabLayout) findViewById(R.id.user_tab_layout);
        adapter = new UserAdapter(getSupportFragmentManager());
        datas = new ArrayList<>();
        datas.add(new UserYouJiFragment());
        datas.add(new UserLikeFragment());
        adapter.setDatas(datas);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


    }

}

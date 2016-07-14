package com.example.dllo.chanyouji.user_detail;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.util.EventLog;
import android.util.Log;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dllo.chanyouji.R;
import com.example.dllo.chanyouji.base.BaseFragment;
import com.example.dllo.chanyouji.datarequest.SingleQueue;

import com.example.dllo.chanyouji.toolsclass.OnRefreshListener;
import com.example.dllo.chanyouji.toolsclass.RefreshListView;
import com.example.dllo.chanyouji.toolsclass.StringEvent;
import com.example.dllo.chanyouji.user_detail.adapter.UserYouJiAdapter;
import com.example.dllo.chanyouji.user_detail.user_bean.UserBean;
import com.example.dllo.chanyouji.youji.YouJiBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by dllo on 16/6/27.
 */
public class UserYouJiFragment extends BaseFragment implements OnRefreshListener {
    private RefreshListView listView;
    private UserBean datas;
    private UserYouJiAdapter adapter;
    private int id;
    private int num = 0;

    @Override
    public int setLayout() {
        return R.layout.user_youji_fragment;

    }

    @Override
    public void initView(View view) {
        listView = (RefreshListView) view.findViewById(R.id.user_youji_list_view);
        adapter = new UserYouJiAdapter(context);


    }

    @Override
    public void initData() {
        listView.setOnRefreshListener(this);

        Intent intent = getActivity().getIntent();
        id = Integer.valueOf(intent.getStringExtra("id"));
        RequestQueue requestQueue = SingleQueue.getSingleQueue(context).getRequestQueue();
        StringRequest stringRequest = new StringRequest("http://chanyouji.com/api/users/" + id + ".json?page=1", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Gson gson = new Gson();
                datas = gson.fromJson(response, UserBean.class);
                adapter.setDatas(datas);
                listView.setAdapter(adapter);
                listView.hideHeaderView();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDownPullRefresh() {

    }

    @Override
    public void onLoadingMore() {
        reshData();
    }

    public void reshData() {

            num++;


        RequestQueue requestQueue = SingleQueue.getSingleQueue(context).getRequestQueue();
        StringRequest stringRequest = new StringRequest("http://chanyouji.com/api/users/" + id + ".json?page=" + num, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Gson gson = new Gson();
                datas = gson.fromJson(response, UserBean.class);
                adapter.setDatas(datas);
                listView.setAdapter(adapter);
                listView.hideFooterView();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }
}

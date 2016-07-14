package com.example.dllo.chanyouji.user_detail;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dllo.chanyouji.R;
import com.example.dllo.chanyouji.base.BaseFragment;
import com.example.dllo.chanyouji.datarequest.SingleQueue;
import com.example.dllo.chanyouji.user_detail.adapter.UserLikeAdapter;
import com.example.dllo.chanyouji.user_detail.user_bean.UserBean;
import com.example.dllo.chanyouji.user_detail.user_bean.UserLikeBean;
import com.example.dllo.chanyouji.youji.YouJiBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by dllo on 16/6/27.
 */
public class UserLikeFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private UserLikeAdapter adapter;
    private ArrayList<UserLikeBean> datas;
    private int id;

    @Override
    public int setLayout() {
        return R.layout.user_like_fragment;

    }

    @Override
    public void initView(View view) {
        adapter = new UserLikeAdapter(context);
        recyclerView = (RecyclerView) view.findViewById(R.id.user_like_recycler_view);
        datas = new ArrayList<>();

    }

    @Override
    public void initData() {
        Intent intent = getActivity().getIntent();
        id = Integer.valueOf(intent.getStringExtra("id"));
        RequestQueue requestQueue = SingleQueue.getSingleQueue(context).getRequestQueue();

        StringRequest stringRequest = new StringRequest("http://chanyouji.com/api/users/likes/" + id + ".json?per_page=15&page=1", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<UserLikeBean>>() {
                }.getType();
                datas = gson.fromJson(response, type);
                adapter.setDatas(datas);
                recyclerView.setAdapter(adapter);
                GridLayoutManager manager = new GridLayoutManager(context,3);
                recyclerView.setLayoutManager(manager);



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        requestQueue.add(stringRequest);


    }
}

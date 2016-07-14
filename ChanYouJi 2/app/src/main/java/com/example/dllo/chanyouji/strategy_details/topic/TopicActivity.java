package com.example.dllo.chanyouji.strategy_details.topic;

import android.content.Intent;
import android.widget.BaseAdapter;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dllo.chanyouji.R;
import com.example.dllo.chanyouji.base.BaseActivity;
import com.example.dllo.chanyouji.datarequest.SingleQueue;
import com.example.dllo.chanyouji.strategy_details.journey.JourneyBean;
import com.example.dllo.chanyouji.toolsclass.RefreshListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by dllo on 16/7/6.
 */
public class TopicActivity extends BaseActivity {
    private ArrayList<TopicBean> datas;
    private RefreshListView listView;
    private TopicAdapter adapter;
    @Override
    public int setLayout() {
        return R.layout.activity_topic;
    }

    @Override
    protected void initData() {
        datas = new ArrayList<>();
        listView = (RefreshListView) findViewById(R.id.topic_list_view);
        adapter = new TopicAdapter(this);
        Intent intent = getIntent();
        int id = Integer.valueOf(intent.getStringExtra("di"));
        RequestQueue requestQueue = SingleQueue.getSingleQueue(this).getRequestQueue();
        StringRequest stringRequest = new StringRequest("http://chanyouji.com/api/articles.json?destination_id="+ id + "&page=1", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<TopicBean>>() {
                }.getType();
                datas = gson.fromJson(response, type);
                adapter.setDatas(datas);
                listView.setAdapter(adapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        requestQueue.add(stringRequest);


    }
}

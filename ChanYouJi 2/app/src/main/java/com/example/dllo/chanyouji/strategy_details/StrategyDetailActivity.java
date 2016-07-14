package com.example.dllo.chanyouji.strategy_details;

import android.content.Intent;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dllo.chanyouji.R;
import com.example.dllo.chanyouji.base.BaseActivity;
import com.example.dllo.chanyouji.datarequest.SingleQueue;
import com.example.dllo.chanyouji.youji.YouJiBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by dllo on 16/6/17.
 */
public class StrategyDetailActivity extends BaseActivity {
    private ArrayList<StrategyDetailsBean> datas;
    private StrategyDetailsAdapter adapter;
    private ListView listView;
    private TextView nameTv;
    @Override
    public int setLayout() {

        return R.layout.strategy_detail_activity;

    }

    @Override
    protected void initData() {
        listView = (ListView) findViewById(R.id.strategy_detail_list);
        nameTv = (TextView) findViewById(R.id.country_palce_strategy);
        datas = new ArrayList<>();
        Intent intent = getIntent();
        int id = Integer.valueOf(intent.getStringExtra("id"));
        String name = intent.getStringExtra("name");
        nameTv.setText(name + "攻略");
        nameTv.setTextSize(20);
        RequestQueue requestQueue = SingleQueue.getSingleQueue(this).getRequestQueue();

        StringRequest stringRequest = new StringRequest("http://chanyouji.com/api/destinations/"+ id +".json", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<StrategyDetailsBean>>() {
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
        adapter = new StrategyDetailsAdapter(this);


    }
}

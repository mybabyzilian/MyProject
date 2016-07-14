package com.example.dllo.chanyouji.strategy_details.journey;

import android.content.Intent;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dllo.chanyouji.R;
import com.example.dllo.chanyouji.base.BaseActivity;
import com.example.dllo.chanyouji.datarequest.SingleQueue;
import com.example.dllo.chanyouji.strategy_details.travel.TravelBean;
import com.example.dllo.chanyouji.toolsclass.RefreshListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by dllo on 16/7/6.
 */
public class JourneyActivity extends BaseActivity {
    private ArrayList<JourneyBean>  datas;
    private RefreshListView listView;
    private JourneyAdapter adapter;
    @Override
    public int setLayout() {
        return R.layout.activity_journey;
    }

    @Override
    protected void initData() {
        datas = new ArrayList<>();
        listView = (RefreshListView) findViewById(R.id.journey_list_view);
        adapter = new JourneyAdapter(this);
        Intent intent = getIntent();
        int id = Integer.valueOf(intent.getStringExtra("di"));
        RequestQueue requestQueue = SingleQueue.getSingleQueue(this).getRequestQueue();

        StringRequest stringRequest = new StringRequest("http://chanyouji.com/api/destinations/attractions/"+ id +".json?page=1", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<JourneyBean>>() {
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

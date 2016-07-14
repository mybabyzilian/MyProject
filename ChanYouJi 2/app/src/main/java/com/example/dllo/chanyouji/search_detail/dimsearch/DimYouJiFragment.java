package com.example.dllo.chanyouji.search_detail.dimsearch;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dllo.chanyouji.R;
import com.example.dllo.chanyouji.base.BaseFragment;
import com.example.dllo.chanyouji.datarequest.SingleQueue;
import com.example.dllo.chanyouji.search_detail.dimsearch.dimadapter.DimYouJiAdapter;
import com.example.dllo.chanyouji.search_detail.dimsearch.dimbean.DimYouJiBean;
import com.example.dllo.chanyouji.strategy_details.travel.TravelBean;
import com.example.dllo.chanyouji.toolsclass.OnRefreshListener;
import com.example.dllo.chanyouji.toolsclass.RefreshListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


/**
 * Created by dllo on 16/7/6.
 */
public class DimYouJiFragment extends BaseFragment implements OnRefreshListener {
    private ArrayList<DimYouJiBean> datas;
    private DimYouJiAdapter adapter;
    private RefreshListView listView;
    private int num = 1;
    private String id;

    @Override
    public int setLayout() {
        return R.layout.dim_youji_fragment;
    }

    @Override
    public void initView(View view) {
        datas = new ArrayList<>();
        adapter = new DimYouJiAdapter(context);
        listView = (RefreshListView) view.findViewById(R.id.dim_youji_listview);

    }

    @Override
    public void initData() {
        Intent intent = getActivity().getIntent();
        id = intent.getStringExtra("id");
        RequestQueue requestQueue = SingleQueue.getSingleQueue(context).getRequestQueue();

        StringRequest stringRequest = new StringRequest("http://chanyouji.com/api/search/trips.json?q=" + id + "&page=" + num, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<DimYouJiBean>>() {
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
        listView.setOnRefreshListener(this);


    }

    @Override
    public void onDownPullRefresh() {

    }

    @Override
    public void onLoadingMore() {

        updateData();


    }

    public void updateData() {
        num++;
        Log.d("++++", "num:" + num);
        RequestQueue requestQueue = SingleQueue.getSingleQueue(context).getRequestQueue();

        StringRequest stringRequest = new StringRequest("http://chanyouji.com/api/search/trips.json?q=" + id + "&page=" + num, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<DimYouJiBean>>() {
                }.getType();
                datas = gson.fromJson(response, type);
                adapter.setDatas(datas);



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        listView.hideFooterView();


        requestQueue.add(stringRequest);


    }
}

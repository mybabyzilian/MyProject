package com.example.dllo.chanyouji.workbox;

import android.view.View;
import android.widget.ExpandableListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dllo.chanyouji.R;
import com.example.dllo.chanyouji.base.BaseFragment;
import com.example.dllo.chanyouji.datarequest.SingleQueue;
import com.example.dllo.chanyouji.youji.YouJiBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by dllo on 16/7/7.
 */
public class ToolForeginFragment extends BaseFragment {
    private ExpandableListView listView;
    private ArrayList<ToolForeginBean> datas;
    private ToolForeginAdapter adapter;
    @Override
    public int setLayout() {
        return R.layout.tool_foregin_fragment;
    }

    @Override
    public void initView(View view) {
        listView = (ExpandableListView) view.findViewById(R.id.tool_foregin_listview);
        adapter = new ToolForeginAdapter(context);
        datas = new ArrayList<>();
    }

    @Override
    public void initData() {
        RequestQueue requestQueue = SingleQueue.getSingleQueue(context).getRequestQueue();

        StringRequest stringRequest = new StringRequest("http://chanyouji.com/api/wiki/destinations.json", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<ToolForeginBean>>() {
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

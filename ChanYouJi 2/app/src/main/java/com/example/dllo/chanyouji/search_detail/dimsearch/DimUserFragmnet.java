package com.example.dllo.chanyouji.search_detail.dimsearch;

import android.content.Intent;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dllo.chanyouji.R;
import com.example.dllo.chanyouji.base.BaseFragment;
import com.example.dllo.chanyouji.datarequest.SingleQueue;
import com.example.dllo.chanyouji.search_detail.dimsearch.dimadapter.DimUserAdapter;
import com.example.dllo.chanyouji.search_detail.dimsearch.dimbean.DimUserBean;
import com.example.dllo.chanyouji.search_detail.dimsearch.dimbean.DimYouJiBean;
import com.example.dllo.chanyouji.toolsclass.RefreshListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by dllo on 16/7/6.
 */
public class DimUserFragmnet extends BaseFragment {
    private ArrayList<DimUserBean> datas;
    private RefreshListView listView;
    private DimUserAdapter adapter;

    @Override
    public int setLayout() {
        return R.layout.dim_user_fragment;
    }

    @Override
    public void initView(View view) {
        datas = new ArrayList<>();
        listView = (RefreshListView) view.findViewById(R.id.dim_user_listview);
        adapter = new DimUserAdapter(context);

    }

    @Override
    public void initData() {
        Intent intent = getActivity().getIntent();
        String id = intent.getStringExtra("id");
        RequestQueue requestQueue = SingleQueue.getSingleQueue(context).getRequestQueue();

        StringRequest stringRequest = new StringRequest("http://chanyouji.com/api/search/users.json?q="+id+"&page=1", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<DimUserBean>>() {
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

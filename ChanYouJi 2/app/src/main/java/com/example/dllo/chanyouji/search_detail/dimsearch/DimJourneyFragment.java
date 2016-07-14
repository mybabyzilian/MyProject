package com.example.dllo.chanyouji.search_detail.dimsearch;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dllo.chanyouji.R;
import com.example.dllo.chanyouji.base.BaseFragment;
import com.example.dllo.chanyouji.datarequest.SingleQueue;
import com.example.dllo.chanyouji.search_detail.dimsearch.dimadapter.DimJourneyAdapter;
import com.example.dllo.chanyouji.search_detail.dimsearch.dimbean.DimJourneyBean;
import com.example.dllo.chanyouji.search_detail.dimsearch.dimbean.DimYouJiBean;
import com.example.dllo.chanyouji.strategy_details.journey.JourneyAdapter;
import com.example.dllo.chanyouji.strategy_details.journey.JourneyBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by dllo on 16/7/6.
 */
public class DimJourneyFragment extends BaseFragment {
    private ArrayList<DimJourneyBean> datas;
    private DimJourneyAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    public int setLayout() {
        return R.layout.dim_journey_fragment;
    }

    @Override
    public void initView(View view) {
        datas = new ArrayList<>();
        adapter = new DimJourneyAdapter(context);
        recyclerView = (RecyclerView) view.findViewById(R.id.journey_recycler_view);

    }

    @Override
    public void initData() {
        // https://chanyouji.com/api/search/attractions.json?q=日本&page=1
        Intent intent = getActivity().getIntent();
        String id = intent.getStringExtra("id");
        RequestQueue requestQueue = SingleQueue.getSingleQueue(context).getRequestQueue();

        StringRequest stringRequest = new StringRequest("http://chanyouji.com/api/search/attractions.json?q="+id+"&page=1", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<DimJourneyBean>>() {
                }.getType();
                datas = gson.fromJson(response, type);
                adapter.setDatas(datas);
                recyclerView.setAdapter(adapter);
                GridLayoutManager manager = new GridLayoutManager(context,2);
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

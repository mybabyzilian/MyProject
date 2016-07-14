package com.example.dllo.chanyouji.serchplace;

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
import com.example.dllo.chanyouji.inteface.SearchListener;
import com.example.dllo.chanyouji.serchplace.adapter.SeasonAdapter;
import com.example.dllo.chanyouji.serchplace.search_bean.SeansonActivity;
import com.example.dllo.chanyouji.serchplace.search_bean.SearchBean;
import com.example.dllo.chanyouji.serchplace.search_bean.SeasonBean;
import com.example.dllo.chanyouji.strategy.StrategyBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by dllo on 16/6/25.
 */
public class SeasonFragment extends BaseFragment implements SearchListener {
    private ArrayList<String> datas;
    private SeasonAdapter adapter;
    private RecyclerView recyclerView;


    @Override
    public int setLayout() {
        return R.layout.search_season_fragment;
    }

    @Override
    public void initView(View view) {
        datas = new ArrayList<>();
        adapter = new SeasonAdapter(context);
        recyclerView = (RecyclerView) view.findViewById(R.id.season_recycler_view);


    }

    @Override
    public void initData() {
        adapter.setListener(this);
        for (int i = 1; i < 13; i++) {
            datas.add(i + "月");

        }
        adapter.setDatas(datas);
        recyclerView.setAdapter(adapter);
        GridLayoutManager manager = new GridLayoutManager(context,3);
        recyclerView.setLayoutManager(manager);


    }

    @Override
    public void searchClick(int position) {
        String month = String.valueOf(datas.get(position).substring(0,1));
        Intent intent = new Intent(context, SeansonActivity.class);
        intent.putExtra("month",month);
        intent.putExtra("name", datas.get(position) + "游记");
        startActivity(intent);
    }
}




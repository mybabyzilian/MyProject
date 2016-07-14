package com.example.dllo.chanyouji.serchplace.search_bean;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dllo.chanyouji.R;
import com.example.dllo.chanyouji.base.BaseActivity;
import com.example.dllo.chanyouji.datarequest.SingleQueue;
import com.example.dllo.chanyouji.inteface.UserListener;
import com.example.dllo.chanyouji.serchplace.adapter.MainSeasonAdapter;
import com.example.dllo.chanyouji.toolsclass.OnRefreshListener;
import com.example.dllo.chanyouji.toolsclass.RefreshListView;
import com.example.dllo.chanyouji.user_detail.UserActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by dllo on 16/6/27.
 */
public class SeansonActivity extends BaseActivity implements OnRefreshListener, UserListener {
    private ArrayList<SeasonBean> datas;
    private MainSeasonAdapter adapter;
    private RefreshListView listView;
    private TextView monthTv;
    @Override
    public int setLayout() {

        return R.layout.season_detail_activity;


    }

    @Override
    protected void initData() {
        listView = (RefreshListView) findViewById(R.id.season_detail_list_view);
        monthTv = (TextView) findViewById(R.id.season_name);



        adapter = new MainSeasonAdapter(this);
        adapter.setListener(this);

        listView.setOnRefreshListener(this);
        Intent intent = getIntent();
        String month =  intent.getStringExtra("month");
        monthTv.setText(intent.getStringExtra("name"));
        int i = Integer.valueOf(month);
        RequestQueue requestQueue = SingleQueue.getSingleQueue(this).getRequestQueue();

        StringRequest stringRequest = new StringRequest("http://chanyouji.com/api/trips/month/" + i +".json?page=1", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<SeasonBean>>() {
                }.getType();
                datas = gson.fromJson(response,type );
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

    @Override
    public void onDownPullRefresh() {

    }

    @Override
    public void onLoadingMore() {

    }

    @Override
    public void userClick(int position) {
        String id = String.valueOf(datas.get(position).getUser().getId());
        Intent intent = new Intent(SeansonActivity.this, UserActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }
}

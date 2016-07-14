package com.example.dllo.chanyouji.search_detail;

import android.content.Intent;
import android.util.Log;
import android.widget.TextView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dllo.chanyouji.R;
import com.example.dllo.chanyouji.base.BaseActivity;
import com.example.dllo.chanyouji.datarequest.SingleQueue;
import com.example.dllo.chanyouji.inteface.UserListener;
import com.example.dllo.chanyouji.toolsclass.OnRefreshListener;
import com.example.dllo.chanyouji.toolsclass.RefreshListView;
import com.example.dllo.chanyouji.user_detail.UserActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;


/**
 * Created by dllo on 16/6/25.
 */
public class CountryActivity extends BaseActivity implements OnRefreshListener, UserListener {
    private RefreshListView listView;
    private ArrayList<CountryBean> datas;
    private CountryAdapter adapter;
    private int i;
    private TextView countryTv;
    private TextView countTv;
    @Override
    public int setLayout() {

        return R.layout.search_county_activity;

    }

    @Override
    protected void initData() {
        listView = (RefreshListView) findViewById(R.id.country_list_view);
        countTv = (TextView) findViewById(R.id.country_youji_count);
        countryTv = (TextView) findViewById(R.id.country_name);
        listView.setOnRefreshListener(this);
        adapter = new CountryAdapter(this);
        adapter.setListener(this);
        datas = new ArrayList<>();
        Intent intent = getIntent();
        String a = intent.getStringExtra("i");
        i = Integer.valueOf(a);
        String name = intent.getStringExtra("name");
        countryTv.setText(name + "游记");
        String count = intent.getStringExtra("count");
        countTv.setText(count + "篇游记");
        String url = "http://chanyouji.com/api/destinations/trips/" + i + ".json?page=1" ;
        RequestQueue requestQueue = SingleQueue.getSingleQueue(this).getRequestQueue();

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<CountryBean>>() {
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

    @Override
    public void onDownPullRefresh() {

    }

    @Override
    public void onLoadingMore() {

    }

    @Override
    public void userClick(int position) {
        Intent intent = new Intent(this, UserActivity.class);
        String id = String.valueOf(datas.get(position).getUser().getId());
        intent.putExtra("id",id);
        startActivity(intent);
    }
}

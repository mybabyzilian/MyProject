package com.example.dllo.chanyouji.strategy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dllo.chanyouji.R;
import com.example.dllo.chanyouji.base.BaseFragment;
import com.example.dllo.chanyouji.datarequest.SingleQueue;
import com.example.dllo.chanyouji.db.DBTools;
import com.example.dllo.chanyouji.inteface.StrategyListener;
import com.example.dllo.chanyouji.strategy.head.HeadAdapter;
import com.example.dllo.chanyouji.strategy.head.RecentLookBean;
import com.example.dllo.chanyouji.strategy_details.StrategyDetailActivity;
import com.example.dllo.chanyouji.toolsclass.OnRefreshListener;
import com.example.dllo.chanyouji.toolsclass.RefreshListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


/**
 * Created by dllo on 16/6/17.
 */
public class StrategyFragment extends BaseFragment implements OnRefreshListener, StrategyListener {

    private RefreshListView listView;
    private ArrayList<StrategyBean> datas;
    ArrayList<StrategyData> data;
    private StrategyAdapter adapter;
    private String url = "http://chanyouji.com/api/destinations.json?page=1";
    private ArrayList<String> placeData = new ArrayList<>();
    private LinearLayout headLayout;
    private HeadAdapter headAdapter;
    private RecyclerView recyclerView;
    private DBTools tools;





    @Override
    public int setLayout() {
        return R.layout.fragment_strategy;
    }

    @Override
    public void initView(View view) {

        listView = (RefreshListView) view.findViewById(R.id.strategy_listview);
        datas = new ArrayList<>();
        adapter = new StrategyAdapter(context);
        headAdapter = new HeadAdapter(context);
        View ringView = LayoutInflater.from(context).inflate(R.layout.strategy_list_head, null);
        headLayout = (LinearLayout) ringView.findViewById(R.id.strategy_linear_layout);
        recyclerView = (RecyclerView) ringView.findViewById(R.id.head_recycler_view);


        listView.addHeaderView(ringView);


    }

    @Override
    public void initData() {
        listView.setOnRefreshListener(this);
        updateDatas();
        adapter.setListener(this);

    }


    @Override
    public void onDownPullRefresh() {
        updateDatas();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadingMore() {
        listView.hideFooterView();
    }

    public void updateDatas() {
        RequestQueue requestQueue = SingleQueue.getSingleQueue(context).getRequestQueue();
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<StrategyBean>>() {
                }.getType();
                datas = gson.fromJson(response, type);
                data = new ArrayList<>();
                for (int i = 0; i < datas.size(); i++) {
                    StrategyData bean = new StrategyData();
                    bean.setCategory(datas.get(i).getCategory());
                    for (StrategyBean.DestinationsBean destination : datas.get(i).getDestinations()) {
                        if (!bean.hasBlank()) {
                            data.add(bean);
                            bean = new StrategyData();
                        }
                        bean.addData(destination);
                    }
                    if (bean.hasBlank()) {
                        data.add(bean);
                    }
                }
                adapter.setDatas(data);
                listView.setAdapter(adapter);
                listView.hideHeaderView();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }

    @Override
    public void strategyClick(int position) {
        String s = data.get(position).getLeftItem().getCountryCH();
        String id = String.valueOf(data.get(position).getLeftItem().getId());
        String name = data.get(position).getLeftItem().countryCH;
        recentView(s);
        Intent intent = new Intent(context, StrategyDetailActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("name",name);
        startActivity(intent);


    }

    @Override
    public void rightStrategyClick(int position) {


        String s = data.get(position).getRightItem().getCountryCH();
        String id = String.valueOf(data.get(position).getRightItem().getId());

        String name = data.get(position).getRightItem().countryCH;

        recentView(s);
        Intent intent = new Intent(context, StrategyDetailActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("name",name);
        startActivity(intent);
    }
    public void recentView(String s){
        headLayout.setVisibility(View.VISIBLE);


        if (placeData.contains(s)) {
            placeData.remove(s);
            placeData.add(0, s);
        } else {
            placeData.add(0, s);
        }
        if (placeData.size() > 6){
            placeData.remove(6);
        }
        headAdapter.setDatas(placeData);
        recyclerView.setAdapter(headAdapter);
        GridLayoutManager manager = new GridLayoutManager(context,3);
        recyclerView.setLayoutManager(manager);

    }

}

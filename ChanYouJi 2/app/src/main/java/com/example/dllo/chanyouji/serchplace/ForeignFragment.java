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
import com.example.dllo.chanyouji.main.MainActivity;
import com.example.dllo.chanyouji.search_detail.CountryActivity;
import com.example.dllo.chanyouji.serchplace.adapter.ForeignAdapter;
import com.example.dllo.chanyouji.serchplace.search_bean.SearchBean;
import com.google.gson.Gson;

/**
 * Created by dllo on 16/6/25.
 */
public class ForeignFragment extends BaseFragment implements SearchListener {
    private RecyclerView recyclerView;
    private ForeignAdapter adapter;
    private SearchBean foreignBean;

    @Override
    public int setLayout() {
        return R.layout.search_foreign_fragment;
    }

    @Override
    public void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.foreign_grid_view);

    }

    @Override
    public void initData() {
        adapter = new ForeignAdapter(context);
        adapter.setListener(this);


        RequestQueue requestQueue = SingleQueue.getSingleQueue(context).getRequestQueue();

        StringRequest stringRequest = new StringRequest("http://chanyouji.com/api/destinations/list.json", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Gson gson = new Gson();
                foreignBean = gson.fromJson(response,SearchBean.class );
                adapter.setDatas(foreignBean);
                recyclerView.setAdapter(adapter);
                GridLayoutManager manager = new GridLayoutManager(context,3);
                recyclerView.setLayoutManager(manager);



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);









    }

    @Override
    public void searchClick(int position) {
        String i = String.valueOf(foreignBean.getOther_destinations().get(position).getId());
        int counts = foreignBean.getOther_destinations().get(position).getDestination_trips_count();
        String count = String.valueOf(counts);
        String name = foreignBean.getOther_destinations().get(position).getName();
        Intent intent = new Intent(context, CountryActivity.class);
        intent.putExtra("i",i);
        intent.putExtra("name",name);
        intent.putExtra("count",count);
        startActivity(intent);
    }
}

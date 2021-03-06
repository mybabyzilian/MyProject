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
import com.example.dllo.chanyouji.search_detail.CountryActivity;
import com.example.dllo.chanyouji.serchplace.adapter.ForeignAdapter;
import com.example.dllo.chanyouji.serchplace.adapter.InlandAdapter;
import com.example.dllo.chanyouji.serchplace.search_bean.SearchBean;
import com.google.gson.Gson;

/**
 * Created by dllo on 16/6/25.
 */
public class InlandFragment extends BaseFragment implements SearchListener{
    private RecyclerView recyclerView;
    private SearchBean inlandBean;
    private InlandAdapter adapter;

    @Override
    public int setLayout() {
        return R.layout.search_inland_fragment;
    }

    @Override
    public void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.inland_recylcler_view);

    }

    @Override
    public void initData() {
        adapter = new InlandAdapter(context);
        adapter.setListener(this);


        RequestQueue requestQueue = SingleQueue.getSingleQueue(context).getRequestQueue();

        StringRequest stringRequest = new StringRequest("http://chanyouji.com/api/destinations/list.json", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Gson gson = new Gson();

                 inlandBean = gson.fromJson(response,SearchBean.class );



                adapter.setDatas(inlandBean);
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
        String i = String.valueOf(inlandBean.getChina_destinations().get(position).getId());
        Intent intent = new Intent(context, CountryActivity.class);
        intent.putExtra("i",i);
        String name = inlandBean.getChina_destinations().get(position).getName();
        intent.putExtra("name",name);
        int counts = inlandBean.getOther_destinations().get(position).getDestination_trips_count();
        String count = String.valueOf(counts);
        intent.putExtra("count",count);
        startActivity(intent);
    }
}

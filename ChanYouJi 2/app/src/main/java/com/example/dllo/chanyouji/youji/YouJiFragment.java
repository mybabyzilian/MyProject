package com.example.dllo.chanyouji.youji;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dllo.chanyouji.R;
import com.example.dllo.chanyouji.base.BaseFragment;
import com.example.dllo.chanyouji.datarequest.SingleQueue;
import com.example.dllo.chanyouji.inteface.UserListener;
import com.example.dllo.chanyouji.toolsclass.OnRefreshListener;
import com.example.dllo.chanyouji.toolsclass.RefreshListView;
import com.example.dllo.chanyouji.user_detail.UserActivity;
import com.example.dllo.chanyouji.youji_details.YouJiDetailsActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.youth.banner.Banner;

import java.lang.reflect.Type;
import java.util.ArrayList;


/**
 * Created by dllo on 16/6/17.
 */
public class YouJiFragment extends BaseFragment implements OnRefreshListener, UserListener {
    private Banner banner;
    private RefreshListView listView;
    private YouJiAdapter adapter;
    private ArrayList<YouJiBean> youJiBeanList;
    private int num = 1;
    private String url = "http://chanyouji.com/api/trips/featured.json?page";
    private String getUrl = "http://chanyouji.com/api/adverts.json?name=app_featured_banner_android";


    @Override
    public int setLayout() {

        return R.layout.fragment_youji;
    }

    @Override
    public void initView(View view) {
        listView = (RefreshListView) view.findViewById(R.id.youji_list_view);
        adapter = new YouJiAdapter(context);
        View ringView = LayoutInflater.from(context).inflate(R.layout.youji_list_head, null);
        banner = (Banner) ringView.findViewById(R.id.youji_banner);
        banner.setBannerStyle(Banner.CIRCLE_INDICATOR);
        banner.setIndicatorGravity(Banner.CENTER);
        banner.setOnBannerClickListener(new Banner.OnBannerClickListener() {
            @Override
            public void OnBannerClick(View view, int position) {
                Intent intent = new Intent(context, YouJiDetailsActivity.class);
                startActivity(intent);
            }
        });
        listView.addHeaderView(ringView);
        listView.setOnRefreshListener(this);


    }

    @Override
    public void initData() {


        RequestQueue requestQueue = SingleQueue.getSingleQueue(context).getRequestQueue();

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<YouJiBean>>() {
                }.getType();
                youJiBeanList = gson.fromJson(response, type);
                adapter.setDatas(youJiBeanList);
                listView.setAdapter(adapter);
                listView.hideHeaderView();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        requestQueue.add(stringRequest);

        StringRequest stringRequest1 = new StringRequest(getUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<WheelBean>>() {
                }.getType();
                ArrayList<WheelBean> been = gson.fromJson(response, type);
                String[] imageURL = new String[been.size()];
                for (int i = 0; i < been.size(); i++) {
                    imageURL[i] = been.get(i).getImage_url();

                }
                banner.setDelayTime(2000);

                banner.setImages(imageURL);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest1);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(context, YouJiDetailsActivity.class);
                String imgurl = youJiBeanList.get(position - 2).getFront_cover_photo_url();
                String detailId = String.valueOf(youJiBeanList.get(position - 2).getId());
                String days = String.valueOf(youJiBeanList.get(position - 2).getDays());
                String photoCounts = String.valueOf(youJiBeanList.get(position - 2).getPhotos_count());
                String userImage = youJiBeanList.get(position - 2).getUser().getImage();
                intent.putExtra("url", imgurl);
                intent.putExtra("id", detailId);
                intent.putExtra("days", days);
                intent.putExtra("counts", photoCounts);
                intent.putExtra("image", userImage);
                startActivity(intent);

            }
        });


        adapter.setListener(this);


    }


    @Override
    public void onDownPullRefresh() {

        updataData();


    }

    @Override
    public void onLoadingMore() {

        reshData();

    }

    public void reshData() {
        num++;
        RequestQueue requestQueue = SingleQueue.getSingleQueue(context).getRequestQueue();

        String updateUrl = "http://chanyouji.com/api/trips/featured.json?page=" + num;
        StringRequest stringRequest = new StringRequest(updateUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<YouJiBean>>() {
                }.getType();
                ArrayList<YouJiBean> beanArrayList = gson.fromJson(response, type);

                adapter.setDatas(beanArrayList);
                listView.hideFooterView();



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "加载中...", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequest);
    }
    public void updataData() {


        RequestQueue requestQueue = SingleQueue.getSingleQueue(context).getRequestQueue();

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<YouJiBean>>() {
                }.getType();
               ArrayList<YouJiBean> beanList = gson.fromJson(response, type);
                adapter.updateData(beanList);
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
    public void userClick(int position) {
        String id = String.valueOf(youJiBeanList.get(position).getUser().getId());
        String imageUrl = youJiBeanList.get(position).getUser().getImage();
        Intent intent = new Intent(context, UserActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("image", imageUrl);
        intent.putExtra("pos", position);
        startActivity(intent);
    }
}

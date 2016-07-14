package com.example.dllo.chanyouji.workbox;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dllo.chanyouji.R;
import com.example.dllo.chanyouji.base.BaseFragment;
import com.example.dllo.chanyouji.datarequest.SingleQueue;
import com.example.dllo.chanyouji.inteface.StrategyListener;
import com.example.dllo.chanyouji.user_detail.user_bean.UserBean;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

/**
 * Created by dllo on 16/6/17.
 */
public class Work_boxFragment extends BaseFragment {
    private ToolBoxBean datas;
    private TextView tempMinTv;
    private TextView tempMaxTv;
    private TextView placeTv;
    private TextView timeTv;
    private int id;
    private MyBrodcastReceiver receiver;
    private RelativeLayout relativeLayout;
    private LinearLayout layout;
    private String name;
    private Button toolBoxBtn;
    private ImageView exRateIv;



    @Override
    public int setLayout() {
        return R.layout.fragment_work_box;
    }

    @Override
    public void initView(View view) {
        exRateIv = (ImageView) view.findViewById(R.id.huilv_iv);
        toolBoxBtn = (Button) view.findViewById(R.id.toolbox_btn);
        layout = (LinearLayout) view.findViewById(R.id.box_linearlayout);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.box_relayout);
        placeTv = (TextView) view.findViewById(R.id.tool_box_name);
        tempMinTv = (TextView) view.findViewById(R.id.temper1);
        tempMaxTv = (TextView) view.findViewById(R.id.temper2);
        timeTv = (TextView) view.findViewById(R.id.tool_time_tv);
        receiver = new MyBrodcastReceiver();
        SharedPreferences sharedPreferences = context.getSharedPreferences("tool",Context.MODE_PRIVATE);
        if (sharedPreferences.getInt("1",0) == 1){
            relativeLayout.setVisibility(View.GONE);
            layout.setVisibility(View.VISIBLE);
        }


    }


    @Override
    public void initData() {
        toolBoxBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ToolBoxActivitry.class);
                context.startActivity(intent);
            }
        });
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.example.dllo.chanyouji");
        context.registerReceiver(receiver,filter);
        SharedPreferences sp = context.getSharedPreferences("tool", Context.MODE_PRIVATE);
        id = sp.getInt("id",0);
        name = sp.getString("name","");
        RequestQueue requestQueue = SingleQueue.getSingleQueue(context).getRequestQueue();
        StringRequest stringRequest = new StringRequest("http://chanyouji.com/api/wiki/destinations/infos/"+id+".json?page=1" , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Gson gson = new Gson();
                datas = gson.fromJson(response,ToolBoxBean.class );
                tempMaxTv .setText(String.valueOf(datas.getTemp_max()));
                tempMinTv.setText(String.valueOf(datas.getTemp_min()));
                timeTv.setText(datas.getCurrent_time());
                placeTv.setText(name);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
        placeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ToolBoxActivitry.class);
                context.startActivity(intent);
            }
        });
        exRateIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String exrateId =String.valueOf(id);

                Intent intent = new Intent(context,ExRateActivity.class);
                intent.putExtra("id",exrateId);
                context.startActivity(intent);
            }
        });
    }
    class MyBrodcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            initData();
            relativeLayout.setVisibility(View.GONE);
            layout.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        context.unregisterReceiver(receiver);
    }
}


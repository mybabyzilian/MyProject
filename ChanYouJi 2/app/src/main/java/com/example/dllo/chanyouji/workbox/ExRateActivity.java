package com.example.dllo.chanyouji.workbox;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dllo.chanyouji.R;
import com.example.dllo.chanyouji.base.BaseActivity;
import com.example.dllo.chanyouji.datarequest.SingleQueue;
import com.example.dllo.chanyouji.workbox.boxbean.ExRatEBean;
import com.example.dllo.chanyouji.youji.YouJiBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * Created by dllo on 16/7/9.
 */
public class ExRateActivity extends BaseActivity implements View.OnClickListener {

    private int ids[] = {R.id.key_0_iv, R.id.key_1_iv,
            R.id.key_2_iv, R.id.key_3_iv, R.id.key_4_iv,
            R.id.key_5_iv, R.id.key_6_iv, R.id.key_7_iv,
            R.id.key_8_iv, R.id.key_9_iv, R.id.key_point_iv,
            R.id.key_del_iv};
    String number = "";
    private TextView countryExRateTv;
    private ToolBoxBean datas;
    private TextView countryName;
    private TextView countryCoin;
    private ArrayList<ExRatEBean> beans;
    private TextView zhExRateTv;
    private TextView usExRateTv;
    private TextView euExRateTv;

    @Override
    public int setLayout() {
        return R.layout.activity_exrate;
    }

    @Override
    protected void initData() {
        usExRateTv = (TextView) findViewById(R.id.country_exrate_number_us);
        zhExRateTv = (TextView) findViewById(R.id.country_exrate_number_zh);
        euExRateTv = (TextView) findViewById(R.id.country_exrate_number_en);
        countryCoin = (TextView) findViewById(R.id.country_zh_one);
        countryName = (TextView) findViewById(R.id.en_name_one);
        countryExRateTv = (TextView) findViewById(R.id.country_exrate_number);
        for (int i = 0; i < ids.length; i++) {
            findViewById(ids[i]).setOnClickListener(this);
        }
        datas = new ToolBoxBean();
        beans = new ArrayList<>();
        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");

        RequestQueue requestQueue = SingleQueue.getSingleQueue(this).getRequestQueue();
        StringRequest stringRequest = new StringRequest("http://chanyouji.com/api/wiki/destinations/infos/" + id + ".json?page=1", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Gson gson = new Gson();
                datas = gson.fromJson(response, ToolBoxBean.class);
                countryName.setText(datas.getCurrency_code());
                countryCoin.setText(datas.getCurrency_display());


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);


        StringRequest stringRequest1 = new StringRequest("http://chanyouji.com/api/currency_exchanges.json", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<ExRatEBean>>() {
                }.getType();
                beans = gson.fromJson(response, type);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        requestQueue.add(stringRequest1);


    }

    @Override
    public void onClick(View v) {
        for (int i = 0; i < 10; i++) {
            if (v.getId() == ids[i]) {
                if(number.equals("0")){
                    number = "";
                } else if(number.startsWith("0")){
                    number = number.replace("0","");
                }
                number += i;
                break;
            }
        }
        switch (v.getId()) {
            case R.id.key_point_iv:
                if (number.contains(".")){
                    return;
                }else {

                    number += ".";
                }
                break;

            case R.id.key_del_iv:

                //删除
                if (number.length() >1 && number != "0" ) {

                    number = number.substring(0, number.length() - 1);

                }else {

                    number = String.valueOf(0);

                }
                break;
        }
        countryExRateTv.setText(number);
        for (int i = 0; i < beans.size(); i++) {
            if (beans.get(i).getCurrency_from().equals(countryName.getText().toString()) && beans.get(i).getCurrency_to().equals("CNY")) {
                double a = Double.parseDouble(countryExRateTv.getText().toString());
                double b = Double.parseDouble(beans.get(i).getExchange_rate());
                DecimalFormat df = new DecimalFormat("0.00");
                zhExRateTv.setText(df.format(a * b));

            }
        }

        for (int i = 0; i < beans.size(); i++) {
            if (beans.get(i).getCurrency_from().equals(countryName.getText().toString()) && beans.get(i).getCurrency_to().equals("USD")) {
                double a = Double.parseDouble(countryExRateTv.getText().toString());
                double b = Double.parseDouble(beans.get(i).getExchange_rate());
                DecimalFormat df = new DecimalFormat("0.00");
                usExRateTv.setText(df.format(b * a));

            }
        }
        for (int i = 0; i < beans.size(); i++) {
            if (beans.get(i).getCurrency_from().equals(countryName.getText().toString()) && beans.get(i).getCurrency_to().equals("EUR")) {
                double a = Double.parseDouble(countryExRateTv.getText().toString());
                double b = Double.parseDouble(beans.get(i).getExchange_rate());
                DecimalFormat df = new DecimalFormat("0.00");
                euExRateTv.setText(df.format(b * a));
            }
        }


    }
}

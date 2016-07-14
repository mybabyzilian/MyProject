package com.example.dllo.chanyouji.strategy_details;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dllo.chanyouji.R;
import com.example.dllo.chanyouji.strategy_details.journey.JourneyActivity;
import com.example.dllo.chanyouji.strategy_details.topic.TopicActivity;
import com.example.dllo.chanyouji.strategy_details.travel.TravelActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by dllo on 16/6/17.
 */
public class StrategyDetailsAdapter extends BaseAdapter {

    private ArrayList<StrategyDetailsBean> datas;
    private Context context;

    public StrategyDetailsAdapter(Context context) {

        this.context = context;

    }

    public void setDatas(ArrayList<StrategyDetailsBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas.size() > 0 && datas != null ? datas.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return datas.size() > 0 && datas != null ? datas.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        DetailsViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.strategy_detail_item, parent, false);
            holder = new DetailsViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (DetailsViewHolder) convertView.getTag();
        }
        holder.nameTv.setText(datas.get(position).getName_zh_cn());
        holder.usNameTv.setText(datas.get(position).getName_en());
        Picasso.with(context).load(datas.get(position).getImage_url()).resize(720,480).into(holder.imageIv);
        holder.travelLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = String.valueOf(datas.get(position).getId());
                Intent intent = new Intent(context, TravelActivity.class);
                intent.putExtra("di",id);
                context.startActivity(intent);
            }
        });
        holder.journeyLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = String.valueOf(datas.get(position).getId());
                Intent intent = new Intent(context, JourneyActivity.class);
                intent.putExtra("di",id);
                context.startActivity(intent);
            }
        });
        holder.topicsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = String.valueOf(datas.get(position).getId());
                Intent intent = new Intent(context, TopicActivity.class);
                intent.putExtra("di",id);
                context.startActivity(intent);

            }
        });

        return convertView;
    }

    class DetailsViewHolder {
        TextView nameTv;
        TextView usNameTv;
        ImageView imageIv;
        LinearLayout strategyLayout;
        LinearLayout travelLayout;
        LinearLayout journeyLayout;
        LinearLayout topicsLayout;


        public DetailsViewHolder(View view) {
            nameTv = (TextView) view.findViewById(R.id.chinese_name_tv);
            usNameTv = (TextView) view.findViewById(R.id.us_name_tv);
            imageIv = (ImageView) view.findViewById(R.id.strategy_detail_Iv);
            strategyLayout = (LinearLayout) view.findViewById(R.id.strategy_layout);
            travelLayout = (LinearLayout) view.findViewById(R.id.travel_layout);
            journeyLayout = (LinearLayout) view.findViewById(R.id.journey_layout);
            topicsLayout = (LinearLayout) view.findViewById(R.id.topics_layout);

        }


    }
}

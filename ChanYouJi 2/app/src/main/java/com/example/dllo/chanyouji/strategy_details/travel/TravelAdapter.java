package com.example.dllo.chanyouji.strategy_details.travel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.chanyouji.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by dllo on 16/7/6.
 */
public class TravelAdapter extends BaseAdapter {
    private ArrayList<TravelBean> datas;
    private Context context;


    public TravelAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(ArrayList<TravelBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TravelViewHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_travel_item,parent,false);
            holder = new TravelViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (TravelViewHolder) convertView.getTag();
        }
        holder.daysTv.setText(String.valueOf(datas.get(position).getPlan_days_count()) + "天");
        holder.travelDetailTv.setText(datas.get(position).getDescription());
        holder.placeCountTv.setText(String.valueOf(datas.get(position).getPlan_nodes_count()) + "旅行地");
        holder.travelNameTv.setText(datas.get(position).getName());
        Picasso.with(context).load(datas.get(position).getImage_url()).into(holder.travelIv);
        return convertView;
    }
    class TravelViewHolder{
        ImageView travelIv;
        TextView travelNameTv;
        TextView daysTv;
        TextView placeCountTv;
        TextView travelDetailTv;


        public TravelViewHolder(View view){
            travelIv = (ImageView) view.findViewById(R.id.travel_cover_image);
            travelNameTv = (TextView) view.findViewById(R.id.travel_detail_name);
            daysTv = (TextView) view.findViewById(R.id.travel_days);
            placeCountTv = (TextView) view.findViewById(R.id.travel_place_count);
            travelDetailTv = (TextView) view.findViewById(R.id.travel_detail);

        }
    }
}

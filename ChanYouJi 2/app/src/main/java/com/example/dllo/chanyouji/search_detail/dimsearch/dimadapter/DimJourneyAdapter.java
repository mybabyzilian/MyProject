package com.example.dllo.chanyouji.search_detail.dimsearch.dimadapter;

import android.content.Context;
import android.content.pm.LabeledIntent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.chanyouji.R;
import com.example.dllo.chanyouji.search_detail.dimsearch.dimbean.DimJourneyBean;
import com.example.dllo.chanyouji.strategy_details.journey.JourneyBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by dllo on 16/7/6.
 */
public class DimJourneyAdapter extends RecyclerView.Adapter<DimJourneyAdapter.MyVH> {
    private ArrayList<DimJourneyBean> datas;
    private Context context;


    public DimJourneyAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(ArrayList<DimJourneyBean> datas) {
        this.datas = datas;
        Log.d("DimJourneyAdapter", "datas:" + datas);
        notifyDataSetChanged();
    }

    @Override
    public MyVH onCreateViewHolder(ViewGroup parent, int viewType) {
        MyVH holder = null;
        View view = LayoutInflater.from(context).inflate(R.layout.dim_journey_item,parent,false);
        holder = new MyVH(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyVH holder, int position) {
        holder.placeTv.setText(datas.get(position).getName());
        holder.countTv.setText(String.valueOf(datas.get(position).getAttraction_trips_count()) + "旅行地");
        Picasso.with(context).load(datas.get(position).getImage_url()).into(holder.journeyIv);

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MyVH extends RecyclerView.ViewHolder {
        ImageView journeyIv;
        TextView countTv;
        TextView placeTv;

        public MyVH(View itemView) {
            super(itemView);
            journeyIv = (ImageView) itemView.findViewById(R.id.dim_journey_iv);
            countTv = (TextView) itemView.findViewById(R.id.dim_journey_count);
            placeTv = (TextView) itemView.findViewById(R.id.journey_place_tv);
        }
    }
}

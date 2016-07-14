package com.example.dllo.chanyouji.strategy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.example.dllo.chanyouji.R;
import com.example.dllo.chanyouji.datarequest.SingleQueue;
import com.example.dllo.chanyouji.inteface.StrategyListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.IdentityHashMap;

/**
 * Created by dllo on 16/6/17.
 */
public class StrategyAdapter extends BaseAdapter {
    private ArrayList<StrategyData> datas;
    private Context context;
    private StrategyListener listener;

    public void setListener(StrategyListener listener) {
        this.listener = listener;
    }

    public StrategyAdapter(Context context) {
        this.context = context;

    }

    public void setDatas(ArrayList<StrategyData> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas == null ? null : datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final StyViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_strategy_item, parent, false);
            holder = new StyViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (StyViewHolder) convertView.getTag();
        }
        holder.setPos(position);
        StrategyData data = datas.get(position);
        holder.leftENTv.setText(data.getLeftItem().getCountryEN());
        holder.leftCHTv.setText(data.getLeftItem().getCountryCH());
        holder.leftCountTv.setText(String.valueOf(data.getLeftItem().getCount()) + "旅行地");
        Picasso.with(context).load(data.getLeftItem().getImagUrl()).fit().into(holder.leftIv);


        if (data.getCategory() != null) {
            holder.titlTv.setText(data.getCategory());
            holder.titlTv.setVisibility(View.VISIBLE);
            holder.lineTv.setVisibility(View.VISIBLE);
        } else {
            holder.titlTv.setVisibility(View.GONE);
            holder.lineTv.setVisibility(View.GONE);
        }


        if (data.hasBlank()) {
            holder.rightRl.setVisibility(View.INVISIBLE);
        } else {
            holder.rightRl.setVisibility(View.VISIBLE);
            holder.RightCountTv.setText(String.valueOf(data.getRightItem().getCount()) + "旅行地");
            holder.rightCHTv.setText(data.getRightItem().getCountryCH());
            holder.rightENTv.setText(data.getRightItem().getCountryEN());
            Picasso.with(context).load(data.getRightItem().getImagUrl()).fit().into(holder.rightIv);
        }
        holder.leftRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getLayoutPosition();
                listener.strategyClick(position);
            }
        });
        holder.rightRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getLayoutPosition();
                listener.rightStrategyClick(position);
            }
        });


        return convertView;
    }

    class StyViewHolder {
        TextView titlTv, leftCHTv, rightCHTv, leftENTv, rightENTv, leftCountTv, RightCountTv,lineTv;
        RelativeLayout rightRl, leftRl;
        ImageView leftIv, rightIv;
        private int pos;
        public void setPos(int pos){
            this.pos = pos;
        }

        public int getLayoutPosition(){
            return pos;
        }

        public StyViewHolder(View view) {
            titlTv = (TextView) view.findViewById(R.id.item_title_tv);
            leftCHTv = (TextView) view.findViewById(R.id.left_name_tv);
            rightCHTv = (TextView) view.findViewById(R.id.right_name_tv);
            leftENTv = (TextView) view.findViewById(R.id.left_en_name_tv);
            rightENTv = (TextView) view.findViewById(R.id.right_en_name_tv);
            leftCountTv = (TextView) view.findViewById(R.id.left_count_tv);
            RightCountTv = (TextView) view.findViewById(R.id.right_count_tv);
            leftIv = (ImageView) view.findViewById(R.id.left_iv);
            rightIv = (ImageView) view.findViewById(R.id.right_iv);
            rightRl = (RelativeLayout) view.findViewById(R.id.content_right_rl);
            leftRl = (RelativeLayout) view.findViewById(R.id.content_left_rl);
            lineTv = (TextView) view.findViewById(R.id.line);
        }
    }
}



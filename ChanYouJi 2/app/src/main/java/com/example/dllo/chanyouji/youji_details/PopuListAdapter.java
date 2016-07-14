package com.example.dllo.chanyouji.youji_details;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.chanyouji.R;
import com.example.dllo.chanyouji.inteface.StrategyListener;

import java.util.ArrayList;

/**
 * Created by dllo on 16/7/5.
 */
public class PopuListAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<DetailBean> datas;
    private static final int TPYE_ONE = 1;
    private static final int TPYE_TWO = 2;
    private StrategyListener listener;

    public void setListener(StrategyListener listener) {
        this.listener = listener;
    }

    public PopuListAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(ArrayList<DetailBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }


    @Override
    public int getItemViewType(int position) {


        if (datas.get(position).getName() == null && datas.get(position).getDays() != 0) {
            return TPYE_ONE;
        } else {
            return TPYE_TWO;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case 1:
                View view1 = LayoutInflater.from(context).inflate(R.layout.popu_recyclerview_item, parent, false);
                holder = new MyViewHdOne(view1);
                break;
            case 2:
                View view2 = LayoutInflater.from(context).inflate(R.layout.popu_item_recyclerview, parent, false);
                holder = new MyViewHdTwo(view2);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        int viewType = getItemViewType(position);
        switch (viewType) {
            case 1:

                final MyViewHdOne holder1 = (MyViewHdOne) holder;
                holder1.daysTv.setTextSize(18);
                if (String.valueOf(datas.get(position).getDays()) != null) {
                    holder1.daysTv.setVisibility(View.VISIBLE);
                    holder1.daysTv.setText("DAY" + String.valueOf(datas.get(position).getDays()));

                }
                holder1.daysTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = holder1.getLayoutPosition();
                        listener.strategyClick(position);

                    }
                });

                break;
            case 2:
                MyViewHdTwo holder2 = (MyViewHdTwo) holder;
                if (datas.get(position).getName() != null) {
                    holder2.smallIv.setVisibility(View.VISIBLE);
                    holder2.placeTv.setVisibility(View.VISIBLE);
                    holder2.placeTv.setText(datas.get(position).getName());

                }
                break;
        }

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MyViewHdOne extends RecyclerView.ViewHolder {
        TextView daysTv;

        public MyViewHdOne(View itemView) {
            super(itemView);
            daysTv = (TextView) itemView.findViewById(R.id.popu_detail_tv);
        }
    }

    class MyViewHdTwo extends RecyclerView.ViewHolder {
        TextView placeTv;
        ImageView smallIv;

        public MyViewHdTwo(View itemView) {
            super(itemView);
            smallIv = (ImageView) itemView.findViewById(R.id.smalldot);
            placeTv = (TextView) itemView.findViewById(R.id.popu_detail_tv_);
        }
    }
}

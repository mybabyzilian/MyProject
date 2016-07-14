package com.example.dllo.chanyouji.strategy.head;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.dllo.chanyouji.R;

import java.util.ArrayList;

/**
 * Created by dllo on 16/6/28.
 */
public class HeadAdapter extends RecyclerView.Adapter<HeadAdapter.HeadViewHolder> {
    private ArrayList<String> datas;
    private Context context;

    public HeadAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(ArrayList<String> datas) {
        this.datas = datas;
        Log.d("HeadAdapter", "datas:" + datas);
        notifyDataSetChanged();
    }

    @Override
    public HeadViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        HeadViewHolder holder = null;
        View view = LayoutInflater.from(context).inflate(R.layout.head_item,parent,false);
        holder = new HeadViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(HeadViewHolder holder, int position) {
        holder.headTv.setText(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class HeadViewHolder extends RecyclerView.ViewHolder{
        Button headTv;
        public HeadViewHolder(View itemView) {
            super(itemView);
            headTv = (Button) itemView.findViewById(R.id.head_tv);
        }
    }
}

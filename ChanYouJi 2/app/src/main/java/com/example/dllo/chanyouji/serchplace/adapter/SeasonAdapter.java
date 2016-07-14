package com.example.dllo.chanyouji.serchplace.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dllo.chanyouji.R;
import com.example.dllo.chanyouji.inteface.SearchListener;


import java.util.ArrayList;

/**
 * Created by dllo on 16/6/27.
 */
public class SeasonAdapter extends RecyclerView.Adapter<SeasonAdapter.theViewHolder> {
    private ArrayList<String> datas;
    private Context context;
    private SearchListener listener;

    public SeasonAdapter(Context context) {
        this.context = context;
    }

    public void setListener(SearchListener listener) {
        this.listener = listener;
    }

    public void setDatas(ArrayList<String> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public theViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        theViewHolder holder = null;
        View view = LayoutInflater.from(context).inflate(R.layout.search_foriegn_item,parent,false);
        holder = new theViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final theViewHolder holder, int position) {
        holder.seasonTv.setText(datas.get(position));
        holder.seasonTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getLayoutPosition();
                listener.searchClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class theViewHolder extends RecyclerView.ViewHolder {
        Button seasonTv;

        public theViewHolder(View itemView) {
            super(itemView);
            seasonTv = (Button) itemView.findViewById(R.id.foreign_item);
        }
    }
}

package com.example.dllo.chanyouji.serchplace.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.dllo.chanyouji.R;
import com.example.dllo.chanyouji.inteface.SearchListener;
import com.example.dllo.chanyouji.serchplace.search_bean.SearchBean;

/**
 * Created by dllo on 16/6/25.
 */
public class InlandAdapter extends RecyclerView.Adapter<InlandAdapter.theViewHolder> {

    private SearchBean datas;
    private Context context;
    private SearchListener listener;

    public void setListener(SearchListener listener) {
        this.listener = listener;
    }

    public InlandAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(SearchBean datas) {
        this.datas = datas;
        Log.d("*****", "datas:" + datas);
        notifyDataSetChanged();
    }


    @Override
    public theViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        theViewHolder holder = null;
        View view = LayoutInflater.from(context).inflate(R.layout.search_foriegn_item, null);
        holder = new theViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final theViewHolder holder, int position) {
        holder.inlandTv.setText(datas.getChina_destinations().get(position).getName());
        holder.inlandTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getLayoutPosition();
                listener.searchClick(position);

            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.getChina_destinations().size();
    }

    class theViewHolder extends RecyclerView.ViewHolder {
        Button inlandTv;

        public theViewHolder(View itemView) {
            super(itemView);
            inlandTv = (Button) itemView.findViewById(R.id.foreign_item);
        }
    }
}


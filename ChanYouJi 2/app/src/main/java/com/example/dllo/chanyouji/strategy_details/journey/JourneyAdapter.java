package com.example.dllo.chanyouji.strategy_details.journey;

import android.content.Context;
import android.util.Log;
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
public class JourneyAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<JourneyBean> datas;


    public JourneyAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(ArrayList<JourneyBean> datas) {
        this.datas = datas;
        Log.d("JourneyAdapter", "datas:" + datas);
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
        ViewHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_journey_item,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.countTv.setText(String.valueOf(datas.get(position).getAttraction_trips_count()) + "旅行地");
        holder.nameTv.setText(datas.get(position).getName());
        holder.contentTv.setText(datas.get(position).getDescription());
        Picasso.with(context).load(datas.get(position).getImage_url()).into(holder.journeyIv);
        return convertView;
    }

    class ViewHolder {
        ImageView journeyIv;
        TextView countTv;
        TextView nameTv;
        ImageView starIv;
        TextView contentTv;


        public ViewHolder(View view) {
            journeyIv = (ImageView) view.findViewById(R.id.journey_cover_iv);
            countTv = (TextView) view.findViewById(R.id.journey_count_tv);
            nameTv = (TextView) view.findViewById(R.id.journey_name_tv);
            starIv = (ImageView) view.findViewById(R.id.journey_star_iv);
            contentTv = (TextView) view.findViewById(R.id.journey_content_tv);

        }
    }
}

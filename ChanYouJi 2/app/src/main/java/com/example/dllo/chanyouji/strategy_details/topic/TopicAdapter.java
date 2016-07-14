package com.example.dllo.chanyouji.strategy_details.topic;

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
public class TopicAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<TopicBean> datas;


    public TopicAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(ArrayList<TopicBean> datas) {
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
        ViewHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_topic_item,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.contractTv.setText(datas.get(position).getTitle());
        holder.nameTv.setText(datas.get(position).getName());
        Picasso.with(context).load(datas.get(position).getImage_url()).into(holder.topicIv);
        return convertView;
    }

    class ViewHolder {
        ImageView topicIv;
        TextView nameTv;
        TextView contractTv;


        public ViewHolder(View view) {
            topicIv = (ImageView) view.findViewById(R.id.topic_cover_iv);
            nameTv = (TextView) view.findViewById(R.id.topic_name_tv);
            contractTv = (TextView) view.findViewById(R.id.topic_contract_tv);

        }
    }
}

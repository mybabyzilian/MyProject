package com.example.dllo.chanyouji.search_detail.dimsearch.dimadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.chanyouji.R;
import com.example.dllo.chanyouji.search_detail.dimsearch.dimbean.DimUserBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by dllo on 16/7/6.
 */
public class DimUserAdapter extends BaseAdapter {
    private ArrayList<DimUserBean> datas;
    private Context context;


    public DimUserAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(ArrayList<DimUserBean> datas) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.dim_user_item,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tripNameTv.setText(datas.get(position).getLatest_publish_trip_name());
        holder.userNameTv.setText(datas.get(position).getName());
        Picasso.with(context).load(datas.get(position).getImage()).into(holder.userIv);
        return convertView;
    }
    class ViewHolder{
        ImageView userIv;
        TextView userNameTv;
        TextView tripNameTv;

        public ViewHolder(View view){
            userIv = (ImageView) view.findViewById(R.id.dim_user_iv);
            userNameTv = (TextView) view.findViewById(R.id.user_name_tv);
            tripNameTv = (TextView) view.findViewById(R.id.dim_trip_name);


        }
    }
}

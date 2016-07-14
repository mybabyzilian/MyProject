package com.example.dllo.chanyouji.personal;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.chanyouji.R;
import com.example.dllo.chanyouji.base.BaseActivity;
import com.example.dllo.chanyouji.youji_details.CollectionBean;
import com.example.dllo.chanyouji.youji_details.YouJiDetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by dllo on 16/7/9.
 */
public class CollectionAdapter extends BaseAdapter {
    private ArrayList<CollectionBean> datas;
    private Context context;


    public CollectionAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(ArrayList<CollectionBean> datas) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.collection_item,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.collectionTv.setText(datas.get(position).getNameTv());
        Picasso.with(context).load(datas.get(position).getImageUrl()).resize(300,100).into(holder.collectionIv);
        holder.collectionIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = String.valueOf(datas.get(position).getId());
                Intent intent = new Intent(context, YouJiDetailsActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("url",datas.get(position).getImageUrl());
                context.startActivity(intent);

            }
        });
        return convertView;
    }
    class ViewHolder{
        ImageView collectionIv;
        TextView collectionTv;

       public ViewHolder(View view){
           collectionIv = (ImageView) view.findViewById(R.id.collection_iv);
           collectionTv = (TextView) view.findViewById(R.id.collection_name);

       }

    }
}

package com.example.dllo.chanyouji.search_detail.dimsearch.dimadapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.example.dllo.chanyouji.R;
import com.example.dllo.chanyouji.datarequest.SingleQueue;
import com.example.dllo.chanyouji.inteface.UserListener;
import com.example.dllo.chanyouji.search_detail.dimsearch.dimbean.DimYouJiBean;
import com.example.dllo.chanyouji.user_detail.UserActivity;
import com.example.dllo.chanyouji.youji_details.YouJiDetailsActivity;

import java.util.ArrayList;

/**
 * Created by dllo on 16/7/6.
 */
public class DimYouJiAdapter extends BaseAdapter {
    private ArrayList<DimYouJiBean> datas;
    private Context context;



    public DimYouJiAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(ArrayList<DimYouJiBean> datas) {
        if (this.datas == null) {
            this.datas = datas;

        } else {
            this.datas.addAll(datas);
        }
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
        final YouJiViewHolder holder ;

        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.dim_youji_item, parent, false);
            holder = new YouJiViewHolder(convertView);
            convertView.setTag(holder);


        } else {


            holder = (YouJiViewHolder) convertView.getTag();


        }

        holder.nameTv.setText(datas.get(position).getName());
        if (datas.get(position).getStart_date() != null) {
            holder.dataTv.setText(datas.get(position).getStart_date() + " / ");
        } else {
            holder.dataTv.setText(" ");
        }
        holder.daysTv.setText(datas.get(position).getDays() + "天");
        holder.countTv.setText(datas.get(position).getPhotos_count() + "图");
        SingleQueue.getSingleQueue(context).getImageLoader().get(datas.get(position).getFront_cover_photo_url(),
                ImageLoader.getImageListener(holder.photoIv, R.mipmap.ic_launcher,
                        R.mipmap.ic_launcher));
        if (datas.get(position).isFeatured()){

            holder.userLabelIv.setImageResource(R.mipmap.best);
            holder.userLabelIv.setVisibility(View.VISIBLE);
        }else {
            holder.userLabelIv.setVisibility(View.GONE);
        }
        SingleQueue.getSingleQueue(context).getImageLoader().get(datas.get(position).getUser().getImage(),
                ImageLoader.getImageListener(holder.userIv, R.mipmap.ic_launcher,
                        R.mipmap.ic_launcher));

        holder.userIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = String.valueOf(datas.get(position).getUser().getId());

                Intent intent = new Intent(context, UserActivity.class);
                intent.putExtra("id",id);
                context.startActivity(intent);
            }
        });
        holder.photoIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, YouJiDetailsActivity.class);
                String id = String.valueOf(datas.get(position).getId());
                intent.putExtra("id",id);
                intent.putExtra("image",datas.get(position).getUser().getImage());
                intent.putExtra("url",datas.get(position).getFront_cover_photo_url());
                context.startActivity(intent);
            }
        });


        return convertView;

    }
    class YouJiViewHolder {
        TextView nameTv;
        TextView dataTv;
        TextView daysTv;
        TextView countTv;
        ImageView photoIv;
        ImageView userIv;
        ImageView userLabelIv;

        public YouJiViewHolder(View view) {
            nameTv = (TextView) view.findViewById(R.id.dim_youji_name_tv);
            dataTv = (TextView) view.findViewById(R.id.dim_youji_data_tv);
            daysTv = (TextView) view.findViewById(R.id.dim_youji_days_tv);
            countTv = (TextView) view.findViewById(R.id.dim_youji_photos_count_tv);
            photoIv = (ImageView) view.findViewById(R.id.dim_front_cover_photo);
            userIv = (ImageView) view.findViewById(R.id.dim_youji_user_iv);
            userLabelIv = (ImageView) view.findViewById(R.id.dim_youji_user_label);
        }

    }
}

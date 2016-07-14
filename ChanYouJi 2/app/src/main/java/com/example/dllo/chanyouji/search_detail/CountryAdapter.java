package com.example.dllo.chanyouji.search_detail;

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
import com.example.dllo.chanyouji.youji_details.YouJiDetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by dllo on 16/6/25.
 */
public class CountryAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<CountryBean> datas;
    private UserListener listener;


    public void setListener(UserListener listener) {
        this.listener = listener;
    }

    public CountryAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(ArrayList<CountryBean> datas) {
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

        final CountryViewHolder holder ;

        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.search_country_item, parent, false);
            holder = new CountryViewHolder(convertView);
            convertView.setTag(holder);


        } else {


            holder = (CountryViewHolder) convertView.getTag();


        }
        holder.setPos(position);
        holder.nameTv.setText(datas.get(position).getName());
        if (datas.get(position).getStart_date() != null) {
            holder.dataTv.setText(datas.get(position).getStart_date() + " / ");
        } else {
            holder.dataTv.setText(" ");
        }
        holder.daysTv.setText(datas.get(position).getDays() + "天");
        holder.countTv.setText(datas.get(position).getPhotos_count() + "图");
        Picasso.with(context).load(datas.get(position).getFront_cover_photo_url()).resize(720,480).into(holder.photoIv);
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
                int position = holder.getLayoutPosition();
                listener.userClick(position);
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

    class CountryViewHolder {
        TextView nameTv;
        TextView dataTv;
        TextView daysTv;
        TextView countTv;
        ImageView photoIv;
        ImageView userIv;
        ImageView userLabelIv;
        int pos;
        public void setPos(int pos){
            this.pos = pos;
        }

        public int getLayoutPosition(){
            return pos;
        }

        public CountryViewHolder(View view) {
            nameTv = (TextView) view.findViewById(R.id.country_name_tv);
            dataTv = (TextView) view.findViewById(R.id.country_data_tv);
            daysTv = (TextView) view.findViewById(R.id.country_days_tv);
            countTv = (TextView) view.findViewById(R.id.country_photos_count_tv);
            photoIv = (ImageView) view.findViewById(R.id.country_cover_photo);
            userIv = (ImageView) view.findViewById(R.id.country_user_iv);
            userLabelIv = (ImageView) view.findViewById(R.id.country_user_label);
        }

    }

}

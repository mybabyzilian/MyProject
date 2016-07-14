package com.example.dllo.chanyouji.user_detail.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.example.dllo.chanyouji.R;
import com.example.dllo.chanyouji.datarequest.SingleQueue;
import com.example.dllo.chanyouji.user_detail.user_bean.UserBean;
import com.example.dllo.chanyouji.youji_details.YouJiDetailsActivity;
import com.squareup.picasso.Picasso;


/**
 * Created by dllo on 16/6/27.
 */
public class UserYouJiAdapter extends BaseAdapter {
    private UserBean datas;
    private Context context;


    public UserYouJiAdapter(Context context) {

        this.context = context;

    }

    public void setDatas(UserBean datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {

      return datas.getTrips().size();
    }

    @Override
    public Object getItem(int position) {
        return datas.getTrips().get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        UserViewHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.user_youji_item,parent,false);
            holder = new UserViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (UserViewHolder) convertView.getTag();
        }

        holder.nameTv.setText(datas.getTrips().get(position).getName());
        if (datas.getTrips().get(position).getStart_date() != null) {
            holder.dataTv.setText(datas.getTrips().get(position).getStart_date() + " / ");
        } else {
            holder.dataTv.setText(" ");
        }
        holder.daysTv.setText(datas.getTrips().get(position).getDays() + "天");
        holder.countTv.setText(datas.getTrips().get(position).getPhotos_count() + "图");
        Picasso.with(context).load(datas.getTrips().get(position).getFront_cover_photo_url()).resize(720,480).into(holder.photoIv);
        holder.photoIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, YouJiDetailsActivity.class);
                String id = String.valueOf(datas.getTrips().get(position).getId());
                intent.putExtra("id",id);
               // intent.putExtra("image",datas.getTrips().get(position).);
                intent.putExtra("url",datas.getTrips().get(position).getFront_cover_photo_url());
                context.startActivity(intent);
            }
        });


        return convertView;
    }

    class UserViewHolder {
        TextView nameTv;
        TextView dataTv;
        TextView daysTv;
        TextView countTv;
        ImageView photoIv;

        public UserViewHolder(View view) {
            nameTv = (TextView) view.findViewById(R.id.user_youji_name_tv);
            dataTv = (TextView) view.findViewById(R.id.user_youji_data_tv);
            daysTv = (TextView) view.findViewById(R.id.user_youji_days_tv);
            countTv = (TextView) view.findViewById(R.id.user_youji_photos_count_tv);
            photoIv = (ImageView) view.findViewById(R.id.user_front_cover_photo);

        }

    }


}


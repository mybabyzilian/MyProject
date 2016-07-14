package com.example.dllo.chanyouji.youji;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.example.dllo.chanyouji.R;
import com.example.dllo.chanyouji.datarequest.SingleQueue;
import com.example.dllo.chanyouji.inteface.UserListener;
import com.example.dllo.chanyouji.toolsclass.StringEvent;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Created by dllo on 16/6/17.
 */
public class YouJiAdapter extends BaseAdapter {
    private ArrayList<YouJiBean> datas;
    private Context context;
    private UserListener listener;

    public YouJiAdapter(Context context) {

        this.context = context;

    }

    public void setListener(UserListener listener) {
        this.listener = listener;
    }

    public void setDatas(ArrayList<YouJiBean> datas) {
        if (this.datas == null) {
            this.datas = datas;

        } else {
            this.datas.addAll(datas);
        }
        notifyDataSetChanged();
    }
    public void updateData(ArrayList<YouJiBean> datas){

        this.datas = datas;
    }

    @Override
    public int getCount() {

        return datas.size() > 0 && datas != null ? datas.size() : 0;

    }

    @Override
    public Object getItem(int position) {
        return datas.size() > 0 && datas != null ? datas.get(position) : null;
    }

    @Override
    public long getItemId(int position) {

        return position;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        final YouJiViewHolder holder ;

        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_youji_item, parent, false);
            holder = new YouJiViewHolder(convertView);
            convertView.setTag(holder);


        } else {


            holder = (YouJiViewHolder) convertView.getTag();


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
        Picasso.with(context).load(datas.get(position).getFront_cover_photo_url()).resize(600,400).into(holder.photoIv);
        if (datas.get(position).isFeatured()){

            holder.userLabelIv.setImageResource(R.mipmap.best);
            holder.userLabelIv.setVisibility(View.VISIBLE);
        }else {
            holder.userLabelIv.setVisibility(View.GONE);
        }
        Picasso.with(context).load(datas.get(position).getUser().getImage()).into(holder.userIv);


        holder.userIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getLayoutPosition();

                listener.userClick(position);
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
        int pos;
        public void setPos(int pos){
            this.pos = pos;
        }

        public int getLayoutPosition(){
            return pos;
        }

        public YouJiViewHolder(View view) {
            nameTv = (TextView) view.findViewById(R.id.youji_name_tv);
            dataTv = (TextView) view.findViewById(R.id.youji_data_tv);
            daysTv = (TextView) view.findViewById(R.id.youji_days_tv);
            countTv = (TextView) view.findViewById(R.id.youji_photos_count_tv);
            photoIv = (ImageView) view.findViewById(R.id.front_cover_photo);
            userIv = (ImageView) view.findViewById(R.id.youji_user_iv);
            userLabelIv = (ImageView) view.findViewById(R.id.youji_user_label);
        }

    }


}

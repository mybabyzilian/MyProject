package com.example.dllo.chanyouji.user_detail.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.example.dllo.chanyouji.R;
import com.example.dllo.chanyouji.datarequest.SingleQueue;
import com.example.dllo.chanyouji.user_detail.user_bean.UserLikeBean;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Created by dllo on 16/6/28.
 */
public class UserLikeAdapter extends RecyclerView.Adapter<UserLikeAdapter.LikeViewHolder> {
    private ArrayList<UserLikeBean> datas;
    private Context context;


    public UserLikeAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(ArrayList<UserLikeBean> datas) {

        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public LikeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LikeViewHolder holder = null;
        View view = LayoutInflater.from(context).inflate(R.layout.user_like_item,parent,false);
        holder = new LikeViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(LikeViewHolder holder, int position) {
        SingleQueue.getSingleQueue(context).getImageLoader().get(datas.get(position).getPhoto_url(),
                ImageLoader.getImageListener(holder.likeIv, R.mipmap.ic_launcher,
                        R.mipmap.ic_launcher));

    }

    @Override
    public int getItemCount()
    {
        return datas.size() != 0 ? datas.size():0;

    }

    class LikeViewHolder extends RecyclerView.ViewHolder{
        ImageView likeIv;
        public LikeViewHolder(View itemView) {
            super(itemView);
            likeIv = (ImageView) itemView.findViewById(R.id.user_like_image);
        }
    }
}

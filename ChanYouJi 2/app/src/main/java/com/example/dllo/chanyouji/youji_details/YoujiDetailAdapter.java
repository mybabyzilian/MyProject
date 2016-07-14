package com.example.dllo.chanyouji.youji_details;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dllo.chanyouji.R;
import com.example.dllo.chanyouji.inteface.StrategyListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/7/2.
 */
public class YoujiDetailAdapter extends RecyclerView.Adapter implements com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter<YoujiDetailAdapter.MyVhd> {
    private Context context;
    private YouJidetailsBean data;
    private List<DetailBean> datas;
    private static final int TYPE_ONE = 1;
    private static final int TYPE_TWO = 2;



    public YoujiDetailAdapter(Context context) {
        this.context = context;
    }

    public void setData(YouJidetailsBean data) {
        this.data = data;
        datas = new ArrayList<>();


        for (int i = 0; i < data.getTrip_days().size(); i++) {
            int id = data.getTrip_days().get(i).getId();
            int days = data.getTrip_days().get(i).getDay();
            String tripData = data.getTrip_days().get(i).getTrip_date();
            String commentCount = null;
            String placeCount = null;
            try {
                commentCount = String.valueOf(data.getNotes_likes_comments().get(i).getComments_count());
                placeCount = String.valueOf(data.getNotes_likes_comments().get(i).getLikes_count());
            } catch (Exception e) {

            }
            for (int j = 0; j < data.getTrip_days().get(i).getNodes().size(); j++) {
                DetailBean detailBean = new DetailBean();
                String name = data.getTrip_days().get(i).getNodes().get(j).getEntry_name();
                String comment = data.getTrip_days().get(i).getNodes().get(j).getComment();
                String entryName = data.getTrip_days().get(i).getNodes().get(j).getEntry_name();
                detailBean.setTripData(tripData);
                detailBean.setName(name);
                detailBean.setComment(comment);
                detailBean.setId(id);
                detailBean.setDays(days);
                detailBean.setEntry(entryName);
                detailBean.setCommentCount(commentCount);
                detailBean.setPlaceCount(placeCount);
                datas.add(detailBean);
                for (int k = 0; k < data.getTrip_days().get(i).getNodes().get(j).getNotes().size(); k++) {
                    DetailBean bean = new DetailBean();
                    String content = data.getTrip_days().get(i).getNodes().get(j).getNotes().get(k).getDescription();
                    String imageUrl = null;
                    try {
                        imageUrl = data.getTrip_days().get(i).getNodes().get(j).getNotes().get(k).getPhoto().getUrl();
                    } catch (Exception e) {

                    }
                    bean.setEntry(entryName);
                    bean.setTripData(tripData);
                    bean.setPlaceCount(placeCount);
                    bean.setCommentCount(commentCount);
                    bean.setImageUrl(imageUrl);
                    bean.setId(id);
                    bean.setDays(days);
                    bean.setContent(content);
                    datas.add(bean);
                }
            }
        }
        notifyDataSetChanged();


    }

    @Override
    public int getItemViewType(int position) {
        if (datas.get(position).getName() != null) {
            return TYPE_ONE;
        } else {
            return TYPE_TWO;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case 1:
                View view1 = LayoutInflater.from(context).inflate(R.layout.youji_detail_item, parent, false);
                holder = new MyVhd(view1);
                break;
            case 2:
                View view2 = LayoutInflater.from(context).inflate(R.layout.youji_detail_item_, parent, false);
                holder = new MyHd(view2);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        int viewType = getItemViewType(position);
        switch (viewType) {
            case 1:

                MyVhd holder1 = (MyVhd) holder;
                if (datas.get(position).getComment() != null) {
                    holder1.commentTv.setVisibility(View.VISIBLE);
                    holder1.commentTv.setText(datas.get(position).getComment());
                }
                holder1.nameTv.setText(datas.get(position).getName());
                break;
            case 2:
                MyHd holder2 = (MyHd) holder;
                holder2.locationTv.setText(datas.get(position).getEntry());
                String content = datas.get(position).getContent();
                if (content != null) {
                    holder2.detailTv.setVisibility(View.VISIBLE);
                    holder2.detailTv.setText(datas.get(position).getContent());
                }
                if (datas.get(position).getCommentCount() != null) {
                    holder2.reLayout.setVisibility(View.VISIBLE);
                    try {
                        holder2.placeTv.setText(String.valueOf(data.getNotes_likes_comments().get(position-1).getLikes_count()));
                        holder2.commentCountTv.setText(String.valueOf(data.getNotes_likes_comments().get(position-1).getComments_count()));

                    } catch (Exception e) {

                    }

                }
                String imageUrl = datas.get(position).getImageUrl();
                if (imageUrl != null) {
                    holder2.photoIv.setVisibility(View.VISIBLE);
                    holder2.detailTv.setVisibility(View.GONE);
                    Picasso.with(context).load(imageUrl).resize(720, 480).into(holder2.photoIv);
                } else {
                    holder2.photoIv.setVisibility(View.GONE);
                }
                if (datas.get(position).getContent() == null && imageUrl == null) {
                    holder.itemView.setVisibility(View.GONE);
                    ((MyHd) holder).reLayout.setVisibility(View.GONE);
                    ((MyHd) holder).detailTv.setVisibility(View.GONE);

                } else {
                    holder.itemView.setVisibility(View.VISIBLE);
                }
                break;

        }


    }

    @Override
    public long getHeaderId(int position) {

        return datas.get(position).getId();

    }

    @Override
    public MyVhd onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.youji_detail_item, parent, false);
        MyVhd holder = new MyVhd(view);
        return holder;

    }

    @Override
    public void onBindHeaderViewHolder(MyVhd holder, int position) {
        holder.nameTv.setText("DAY" + datas.get(position).getDays());

        holder.nameTv.setTextSize(25);
        holder.tripDataTv.setText(datas.get(position).getTripData());
        holder.tripDataTv.setTextSize(17);

        holder.layout.setBackgroundColor(Color.WHITE);
        holder.commentTv.setVisibility(View.GONE);
        holder.lines.setVisibility(View.VISIBLE);
        holder.icTripsIv.setVisibility(View.GONE);
        holder.itemView.setVisibility(View.GONE);

    }


    @Override
    public int getItemCount() {

        return datas.size();
    }




    class MyVhd extends RecyclerView.ViewHolder {

        TextView commentTv;
        TextView nameTv;
        LinearLayout layout;
        TextView lines;
        ImageView icTripsIv;
        TextView tripDataTv;


        public MyVhd(View itemView) {
            super(itemView);
            commentTv = (TextView) itemView.findViewById(R.id.comment_tv);
            nameTv = (TextView) itemView.findViewById(R.id.detail_zh_name);
            layout = (LinearLayout) itemView.findViewById(R.id.detail_layout);
            lines = (TextView) itemView.findViewById(R.id.lines);
            icTripsIv = (ImageView) itemView.findViewById(R.id.ic_trips);
            tripDataTv = (TextView) itemView.findViewById(R.id.trip_data);

        }
    }

    class MyHd extends RecyclerView.ViewHolder {
        TextView detailTv, placeTv, commentCountTv, locationTv;
        LinearLayout layoutTv;
        ImageView photoIv;
        RelativeLayout reLayout;

        public MyHd(View itemView) {
            super(itemView);
            detailTv = (TextView) itemView.findViewById(R.id.youji_detail_item_tv_);
            layoutTv = (LinearLayout) itemView.findViewById(R.id.layout_2);
            photoIv = (ImageView) itemView.findViewById(R.id.detail_photo);
            placeTv = (TextView) itemView.findViewById(R.id.like_count);
            commentCountTv = (TextView) itemView.findViewById(R.id.comment_count);
            reLayout = (RelativeLayout) itemView.findViewById(R.id.re_layout);
            locationTv = (TextView) itemView.findViewById(R.id.place_tv);


        }
    }
}

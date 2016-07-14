package com.example.dllo.chanyouji.workbox;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import com.example.dllo.chanyouji.R;
import com.example.dllo.chanyouji.main.MainActivity;
import com.example.dllo.chanyouji.workbox.boxbean.FBean;
import com.example.dllo.chanyouji.workbox.boxbean.IBean;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Created by dllo on 16/7/8.
 */
public class ToolInslanAdapter implements ExpandableListAdapter {
    private Context context;
    private ArrayList<ToolForeginBean> datas;


    public void setDatas(ArrayList<ToolForeginBean> datas) {
        this.datas = datas;

    }

    public ToolInslanAdapter(Context context) {
        this.context = context;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getGroupCount() {

        return datas.get(4).getDestinations().size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return datas.get(4).getDestinations().get(groupPosition).getChildren().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return datas.get(4).getDestinations().size();
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return datas.get(4).getDestinations().get(groupPosition).getChildren().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupVh holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.forregin_group_item,parent,false);
            holder = new GroupVh(convertView);
            convertView.setTag(holder);
        }else {
            holder = (GroupVh) convertView.getTag();
        }
        holder.groupTv.setText(datas.get(4).getDestinations().get(groupPosition).getName_zh_cn());
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildrenVh holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.foregin_children_item,parent,false);
            holder = new ChildrenVh(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ChildrenVh) convertView.getTag();
        }
        holder.childrenTv.setText(datas.get(4).getDestinations().get(groupPosition).getChildren().get(childPosition).getName_zh_cn());
        holder.childrenTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = datas.get(4).getDestinations().get(groupPosition).getChildren().get(childPosition).getName_zh_cn();
                int id = datas.get(4).getDestinations().get(groupPosition).getChildren().get(childPosition).getId();
                SharedPreferences sp = context.getSharedPreferences("tool", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt("id", id);
                editor.putString("name",name);
                editor.putInt("1",1);
                editor.commit();
                Intent intent = new Intent("com.example.dllo.chanyouji");
                context.sendBroadcast(intent);

            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }

    class GroupVh {
        TextView groupTv;

        public GroupVh(View view) {
            groupTv = (TextView) view.findViewById(R.id.group_tv);
        }

    }

    class ChildrenVh {
        TextView childrenTv;

        public ChildrenVh(View view) {
            childrenTv = (TextView) view.findViewById(R.id.children_tv);
        }
    }

}

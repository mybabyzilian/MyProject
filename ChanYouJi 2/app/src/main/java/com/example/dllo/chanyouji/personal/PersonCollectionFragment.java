package com.example.dllo.chanyouji.personal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dllo.chanyouji.R;
import com.example.dllo.chanyouji.base.BaseFragment;
import com.example.dllo.chanyouji.serchplace.InlandFragment;
import com.example.dllo.chanyouji.youji_details.CollectionBean;
import com.example.dllo.chanyouji.youji_details.YouJiDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.DeleteListener;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by dllo on 16/7/8.
 */
public class PersonCollectionFragment extends BaseFragment {
    private ListView listView;
    private ArrayList<CollectionBean> datas;
    private CollectionAdapter adapter;
    private boolean has = false;
    @Override
    public int setLayout() {
        return R.layout.person_collection_fragment;
    }

    @Override
    public void initView(View view) {
        datas = new ArrayList<>();
        adapter = new CollectionAdapter(context);
        listView = (ListView) view.findViewById(R.id.collection_list);


    }

    @Override
    public void initData() {
        SharedPreferences sp = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        String userName = sp.getString("userName"," ");
        final BmobQuery<CollectionBean> beanBmobQuery = new BmobQuery<>();
        beanBmobQuery.addWhereEqualTo("userName",userName);
        beanBmobQuery.findObjects(context, new FindListener<CollectionBean>() {
            @Override
            public void onSuccess(List<CollectionBean> list) {
                for (int i = 0; i <list.size() ; i++) {
                    datas.add(list.get(i));

                }
                adapter.setDatas(datas);
                listView.setAdapter(adapter);


            }

            @Override
            public void onError(int i, String s) {

            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                BmobQuery<CollectionBean> bmobQuery = new BmobQuery<>();
                bmobQuery.addWhereEqualTo("id",datas.get(position).getId());
                bmobQuery.findObjects(context, new FindListener<CollectionBean>() {
                    @Override
                    public void onSuccess(List<CollectionBean> list) {
                        for (CollectionBean b : list){
                            b.delete(context);
                        }
                    }

                    @Override
                    public void onError(int i, String s) {

                    }
                });
                datas.remove(position);
                adapter.notifyDataSetChanged();
                return false;
            }
        });

    }
}

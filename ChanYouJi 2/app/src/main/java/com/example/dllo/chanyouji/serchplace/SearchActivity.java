package com.example.dllo.chanyouji.serchplace;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

import com.example.dllo.chanyouji.R;
import com.example.dllo.chanyouji.base.BaseActivity;
import com.example.dllo.chanyouji.search_detail.dimsearch.DimActivity;

import java.util.ArrayList;

/**
 * Created by dllo on 16/6/25.
 */
public class SearchActivity extends BaseActivity {
    private SearchView searchView;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ArrayList<Fragment> datas;
    private SearchAdapter adapter;




    @Override
    public int setLayout() {
        return R.layout.search_activity;
    }

    @Override
    protected void initData() {
        searchView = (SearchView) findViewById(R.id.search_view);
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("搜索游记、旅行地与用户");
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(SearchActivity.this,"您选择的是:" + query,Toast.LENGTH_SHORT).show();
                String id = query;
                Intent intent = new Intent(SearchActivity.this, DimActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {



                return false;
            }
        });


        viewPager = (ViewPager) findViewById(R.id.search_view_pager);
        tabLayout = (TabLayout) findViewById(R.id.search_tab_layout);
        adapter = new SearchAdapter(getSupportFragmentManager());
        datas = new ArrayList<>();
        datas.add(new ForeignFragment());
        datas.add(new InlandFragment());
        datas.add(new SeasonFragment());
        adapter.setDatas(datas);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}

package com.example.dllo.chanyouji.youji_details;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;


import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.example.dllo.chanyouji.R;
import com.example.dllo.chanyouji.base.BaseActivity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by dllo on 16/6/17.
 */

import android.graphics.Rect;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dllo.chanyouji.datarequest.SingleQueue;
import com.example.dllo.chanyouji.inteface.StrategyListener;
import com.example.dllo.chanyouji.toolsclass.DisplayUtil;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.DeleteListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

import static android.view.ViewGroup.*;
import cn.bmob.v3.listener.SaveListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;


public class YouJiDetailsActivity extends BaseActivity implements StrategyListener {
    private ArrayList<DetailBean> datas;
    private YouJidetailsBean bean;
    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView bottomIv;
    private ImageView topIv;
    int max;
    private RecyclerView recyclerView;
    private YoujiDetailAdapter adapter;
    private String url;
    private Bitmap bitmap;
    private TextView nameTv;
    private TextView dataTv;
    private TextView daysTv;
    private TextView photoTv;
    private ImageView userIv;
    private ImageView drawerIv;
    private PopuListAdapter popuAdapter;
    private PopupWindow popupWindow;
    private int from = 0;
    private View popuView;
    private RecyclerView popuRecyclerView;
    private ImageView collectionIv,navbarIv;
    private boolean has = false;




    @Override
    public int setLayout() {
        return R.layout.youji_details_activity;
    }


    @Override
    protected void initData() {
        popuView = LayoutInflater.from(this).inflate(R.layout.youji_detail_popu, null);
        collectionIv = (ImageView) findViewById(R.id.collection);
        drawerIv = (ImageView) findViewById(R.id.drawer_iv);
        navbarIv = (ImageView) findViewById(R.id.navbar);
        nameTv = (TextView) findViewById(R.id.youji_detail_tv);
        dataTv = (TextView) findViewById(R.id.youji_detail_data);
        daysTv = (TextView) findViewById(R.id.youji_detail_days);
        photoTv = (TextView) findViewById(R.id.youji_detail_count);
        userIv = (ImageView) findViewById(R.id.youji_detail_user_iv);
        popuRecyclerView = (RecyclerView) popuView.findViewById(R.id.popu_recycler_view);
        popuAdapter = new PopuListAdapter(this);
        popuAdapter.setListener(this);

        datas = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.main_rv);
        adapter = new YoujiDetailAdapter(this);
        navbarIv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                    ShareSDK.initSDK(YouJiDetailsActivity.this);
                    OnekeyShare oks = new OnekeyShare();
                    //关闭sso授权
                    oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
                    //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
                    // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
                 //   oks.setTitle(getString(R.string.share));
                    // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
                    oks.setTitleUrl("http://chanyouji.com");
                    // text是分享文本，所有平台都需要这个字段
                    oks.setText("我是分享文本");
                    // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
                    //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
                    // url仅在微信（包括好友和朋友圈）中使用
                    oks.setUrl("http://chanyouji.com");
                    // comment是我对这条分享的评论，仅在人人网和QQ空间使用
                    oks.setComment("我是测试评论文本");
                    // site是分享此内容的网站名称，仅在QQ空间使用
                    oks.setSite(getString(R.string.app_name));
                    // siteUrl是分享此内容的网站地址，仅在QQ空间使用
                    oks.setSiteUrl("http://chanyouji.com");

// 启动分享GUI
                    oks.show(YouJiDetailsActivity.this);
                }

        });





        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        int id = Integer.valueOf(intent.getStringExtra("id"));
        final String days = intent.getStringExtra("days");
        final String count = intent.getStringExtra("counts");
        String imageUrl = intent.getStringExtra("image");
        Picasso.with(this).load(imageUrl).fit().into(userIv);
        drawerIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                from = Location.LEFT.ordinal();

                initPopupWindow();
                drawerIv.setVisibility(INVISIBLE);

            }

        });

        final RequestQueue requestQueue = SingleQueue.getSingleQueue(this).getRequestQueue();

        StringRequest stringRequest = new StringRequest("http://chanyouji.com/api/trips/" + id + ".json", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                bean = gson.fromJson(response, YouJidetailsBean.class);
                adapter.setData(bean);
                recyclerView.setAdapter(adapter);
                LinearLayoutManager manager = new LinearLayoutManager(YouJiDetailsActivity.this);
                recyclerView.setLayoutManager(manager);
                nameTv.setText(bean.getName());
                dataTv.setText(bean.getStart_date() + "  |");
                daysTv.setText(days + "天");
                photoTv.setText(count + "图");


                for (int i = 0; i < bean.getTrip_days().size(); i++) {
                    DetailBean beans = new DetailBean();
                    int day = bean.getTrip_days().get(i).getDay();
                    beans.setDays(day);
                    datas.add(beans);

                    for (int j = 0; j < bean.getTrip_days().get(i).getNodes().size(); j++) {
                        DetailBean detailBean = new DetailBean();
                        String name = bean.getTrip_days().get(i).getNodes().get(j).getEntry_name();

                        detailBean.setName(name);

                        datas.add(detailBean);


                        for (int k = 0; k < bean.getTrip_days().get(i).getNodes().get(j).getNotes().size(); k++) {


                        }
                    }
                }
                popuAdapter.setDatas(datas);

                popuRecyclerView.setAdapter(popuAdapter);
                LinearLayoutManager manager1 = new LinearLayoutManager(YouJiDetailsActivity.this);
                popuRecyclerView.setLayoutManager(manager1);
                collectionIv.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences sp = getSharedPreferences("login",MODE_PRIVATE);
                        final int login = sp.getInt("user",0);
                        String userName = sp.getString("userName"," ");
                        if (login == 1){
                            final CollectionBean collectionBean = new CollectionBean();
                            collectionBean.setId(bean.getId());
                            collectionBean.setImageUrl(bean.getFront_cover_photo_url());
                            collectionBean.setNameTv(bean.getName());
                            collectionBean.setUserName(userName);

                            final BmobQuery<CollectionBean> beanBmobQuery = new BmobQuery<>();
                            beanBmobQuery.addWhereEqualTo("userName",userName);
                            beanBmobQuery.findObjects(YouJiDetailsActivity.this, new FindListener<CollectionBean>() {
                                @Override
                                public void onSuccess(List<CollectionBean> list) {
                                    for (int i = 0; i <list.size() ; i++) {
                                        if (list.get(i).getId() == bean.getId()){
                                            has = true;
                                        }
                                    }
                                    if (has){

                                        Toast.makeText(YouJiDetailsActivity.this, "该内容您已经收藏过了,到个人收藏中查看吧", Toast.LENGTH_SHORT).show();

                                    }else {
                                        collectionBean.save(YouJiDetailsActivity.this, new SaveListener() {
                                            @Override
                                            public void onSuccess() {
                                                Toast.makeText(YouJiDetailsActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                                            }

                                            @Override
                                            public void onFailure(int i, String s) {
                                                Toast.makeText(YouJiDetailsActivity.this, "收藏失败,请检查您的网络连接是否正常", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }

                                }

                                @Override
                                public void onError(int i, String s) {

                                }
                            });


                        }else{
                            Toast.makeText(YouJiDetailsActivity.this, "请先登录,然后收藏", Toast.LENGTH_SHORT).show();
                        }




                    }
                });


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);


        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.toolbar_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        bottomIv = (ImageView) findViewById(R.id.bottom_iv);
        topIv = (ImageView) findViewById(R.id.top_iv);
        Picasso.with(this).load(url).resize(720, 480).into(bottomIv);
        ImageLoader imageLoader = SingleQueue.getSingleQueue(this).getImageLoader();
        imageLoader.get(url, new ImageLoader.ImageListener() {

            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                bitmap = response.getBitmap();

                if (response.getBitmap() != null && !bitmap.isRecycled()) {

                    topIv.setImageBitmap(FastBlur.doBlur(bitmap, 15));

                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        topIv.setAlpha(0f);
        appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                max = appBarLayout.getHeight() - toolbar.getHeight() - getStatusBarHeight();
                topIv.setAlpha((-verticalOffset) / (float) max);

            }
        });


        recyclerView.addItemDecoration(new StickyRecyclerHeadersDecoration(adapter));
    }


    //获得状态栏的高度
    private int getStatusBarHeight() {
        Rect rect = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return rect.top;
    }

    protected void initPopupWindow() {

        if (Location.LEFT.ordinal() == from) {
            popupWindow = new PopupWindow(popuView, LayoutParams.WRAP_CONTENT, DisplayUtil.dip2px(this, 602), true);
        }

        ColorDrawable dw = new ColorDrawable();
        popupWindow.setBackgroundDrawable(dw);


        if (Location.LEFT.ordinal() == from) {
            popupWindow.showAtLocation(getLayoutInflater().inflate(R.layout.activity_main, null), Gravity.LEFT, 0, 200);
        }

        backgroundAlpha(1f);

        //关闭事件
        popupWindow.setOnDismissListener(new popupDismissListener());

        popuView.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {


                return false;
            }
        });


    }

    @Override
    public void strategyClick(int position) {
        for (int i = 0; i < position - 3; i++) {
            bean.getTrip_days().remove(i);

        }
        adapter.setData(bean);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void rightStrategyClick(int position) {

    }


    public enum Location {

        LEFT,


    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }

    class popupDismissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {

            drawerIv.setVisibility(VISIBLE);


        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}






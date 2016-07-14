package com.example.dllo.chanyouji.main;

import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.chanyouji.R;
import com.example.dllo.chanyouji.base.BaseActivity;
import com.squareup.picasso.Picasso;


import java.util.Random;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;

/**
 * Created by dllo on 16/6/17.
 */
public class WelcomActivity extends BaseActivity {
    private CountDownTimer timer;
    private ImageView welcomTv;
    private TextView jumpTv;


    @Override
    public int setLayout() {
        return R.layout.activity_welcom;
    }

    @Override
    protected void initData() {

        Bmob.initialize(this,"2ff5b2c5d57e5adf18cea1968077e8cd");

        welcomTv = (ImageView) findViewById(R.id.welcom_tv);
        Random random = new Random();
        final int i = random.nextInt(38) + 1;
        jumpTv = (TextView) findViewById(R.id.jumpTv);
        timer = new CountDownTimer(3000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Picasso.with(WelcomActivity.this).load("http://cyjm.qiniudn.com/mail/launch_images/568-" + i + ".jpg").into(welcomTv);

            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(WelcomActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }.start();
    }
}

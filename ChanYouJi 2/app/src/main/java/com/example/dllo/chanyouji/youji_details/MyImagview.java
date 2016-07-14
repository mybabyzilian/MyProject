package com.example.dllo.chanyouji.youji_details;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by dllo on 16/6/30.
 */
public class MyImagview extends ImageView {

    public MyImagview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        try {
            super.onDraw(canvas);
        } catch (Exception e) {

        }
    }
}


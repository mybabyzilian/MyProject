package com.example.dllo.chanyouji.datarequest;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by dllo on 16/6/21.
 */
public class SingleQueue {
    private static SingleQueue singleQueue;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }
    private SingleQueue(Context context){
        requestQueue = Volley.newRequestQueue(context);
        imageLoader = new ImageLoader(requestQueue,new MemoryCache());
    }
    public synchronized static SingleQueue getSingleQueue(Context context){
        if (singleQueue == null){
            singleQueue = new SingleQueue(context);
        }
        return singleQueue;
    }
}

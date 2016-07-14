package com.example.dllo.chanyouji.datarequest;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by dllo on 16/6/21.
 */
public class MemoryCache implements ImageLoader.ImageCache {
    private LruCache<String, Bitmap> caches;

    public MemoryCache() {
        initMemoryCache();
    }

    private void initMemoryCache() {
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        int cacheSize = maxMemory / 4;
        caches = new LruCache<String, Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };

    }

    @Override
    public Bitmap getBitmap(String url) {
        return caches.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        caches.put(url,bitmap);
    }
}

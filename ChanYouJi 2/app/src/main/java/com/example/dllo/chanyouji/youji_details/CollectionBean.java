package com.example.dllo.chanyouji.youji_details;

import android.view.View;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by dllo on 16/7/9.
 */
public class CollectionBean extends BmobObject {
    String imageUrl;
    String nameTv;
    int id;
    String userName;

    public CollectionBean() {
    }

    public CollectionBean(String imageUrl, String nameTv, int id) {
        this.imageUrl = imageUrl;
        this.nameTv = nameTv;
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getNameTv() {
        return nameTv;
    }

    public void setNameTv(String nameTv) {
        this.nameTv = nameTv;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}

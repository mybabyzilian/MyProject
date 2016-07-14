package com.example.dllo.chanyouji.strategy.head;

/**
 * Created by dllo on 16/7/12.
 */
public class RecentLookBean {
    String place;
    String id;


    public RecentLookBean() {
    }

    public RecentLookBean(String place, String id) {
        this.place = place;
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

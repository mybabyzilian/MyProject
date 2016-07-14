package com.example.dllo.chanyouji.workbox.boxbean;

/**
 * Created by dllo on 16/7/8.
 */
public class IBean {
    private int id;
    private String IName;

    public IBean() {
    }

    public IBean(int id, String IName) {
        this.id = id;
        this.IName = IName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIName() {
        return IName;
    }

    public void setIName(String IName) {
        this.IName = IName;
    }
}

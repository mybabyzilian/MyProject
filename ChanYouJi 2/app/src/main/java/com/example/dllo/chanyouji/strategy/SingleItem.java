package com.example.dllo.chanyouji.strategy;

/**
 * Created by dllo on 16/6/22.
 */
public class SingleItem {
    String countryCH;
    String countryEN;
    String imagUrl;
    int id;
    int count;

    public SingleItem(StrategyBean.DestinationsBean data) {
        countryCH = data.getName_zh_cn();
        countryEN = data.getName_en();
        imagUrl = data.getImage_url();
        count = data.getPoi_count();
        id = data.getId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryCH() {
        return countryCH;
    }

    public void setCountryCH(String countryCH) {
        this.countryCH = countryCH;
    }

    public String getCountryEN() {
        return countryEN;
    }

    public void setCountryEN(String countryEN) {
        this.countryEN = countryEN;
    }

    public String getImagUrl() {
        return imagUrl;
    }

    public void setImagUrl(String imagUrl) {
        this.imagUrl = imagUrl;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

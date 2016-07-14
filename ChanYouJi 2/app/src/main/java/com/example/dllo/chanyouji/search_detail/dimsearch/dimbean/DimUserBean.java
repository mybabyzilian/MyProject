package com.example.dllo.chanyouji.search_detail.dimsearch.dimbean;

/**
 * Created by dllo on 16/7/6.
 */
public class DimUserBean {

    /**
     * id : 1425
     * name : 王小水_在日本
     * latest_publish_trip_id : 165592
     * publish_trips_count : 4
     * latest_publish_trip_name : 夏天冲绳潜海去-离岛篇
     * image : http://a.chanyouji.cn/1425/1411147990.jpg
     */

    private int id;
    private String name;
    private int latest_publish_trip_id;
    private int publish_trips_count;
    private String latest_publish_trip_name;
    private String image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLatest_publish_trip_id() {
        return latest_publish_trip_id;
    }

    public void setLatest_publish_trip_id(int latest_publish_trip_id) {
        this.latest_publish_trip_id = latest_publish_trip_id;
    }

    public int getPublish_trips_count() {
        return publish_trips_count;
    }

    public void setPublish_trips_count(int publish_trips_count) {
        this.publish_trips_count = publish_trips_count;
    }

    public String getLatest_publish_trip_name() {
        return latest_publish_trip_name;
    }

    public void setLatest_publish_trip_name(String latest_publish_trip_name) {
        this.latest_publish_trip_name = latest_publish_trip_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

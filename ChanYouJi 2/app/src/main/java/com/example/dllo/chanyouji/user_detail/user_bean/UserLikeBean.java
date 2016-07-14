package com.example.dllo.chanyouji.user_detail.user_bean;

/**
 * Created by dllo on 16/6/28.
 */
public class UserLikeBean {

    /**
     * id : 12463245
     * description : 布拉格城堡夜景
     * width : 1600
     * height : 1067
     * photo_url : http://p.chanyouji.cn/322038/1444705347066p1a1fia1bliqe1jv2ui111ov1anj1m.jpg
     * trip_id : 322038
     */

    private int id;
    private String description;
    private int width;
    private int height;
    private String photo_url;
    private int trip_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public int getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(int trip_id) {
        this.trip_id = trip_id;
    }
}

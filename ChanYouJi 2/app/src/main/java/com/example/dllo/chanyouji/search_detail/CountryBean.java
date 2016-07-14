package com.example.dllo.chanyouji.search_detail;

/**
 * Created by dllo on 16/6/25.
 */
public class CountryBean {


    /**
     * id : 545733
     * name : 霓虹之中，绿野仙踪-C小晶的夏天…
     * photos_count : 298
     * start_date : 2016-06-03
     * end_date : 2016-06-11
     * days : 9
     * level : 3
     * views_count : 1031
     * comments_count : 19
     * likes_count : 23
     * source : app
     * front_cover_photo_url : http://p.chanyouji.cn/1465361370/08B421FA-F4E6-4EE8-8755-A8A5BBBBF471.jpg
     * featured : false
     * user : {"id":577930,"name":"小C小晶","image":"http://tva3.sinaimg.cn/crop.0.0.512.512.50/7a37a9ebjw8er1motx6k8j20e80e8dgl.jpg"}
     */

    private int id;
    private String name;
    private int photos_count;
    private String start_date;
    private String end_date;
    private int days;
    private int level;
    private int views_count;
    private int comments_count;
    private int likes_count;
    private String source;
    private String front_cover_photo_url;
    private boolean featured;
    /**
     * id : 577930
     * name : 小C小晶
     * image : http://tva3.sinaimg.cn/crop.0.0.512.512.50/7a37a9ebjw8er1motx6k8j20e80e8dgl.jpg
     */

    private UserBean user;

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

    public int getPhotos_count() {
        return photos_count;
    }

    public void setPhotos_count(int photos_count) {
        this.photos_count = photos_count;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getViews_count() {
        return views_count;
    }

    public void setViews_count(int views_count) {
        this.views_count = views_count;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public int getLikes_count() {
        return likes_count;
    }

    public void setLikes_count(int likes_count) {
        this.likes_count = likes_count;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getFront_cover_photo_url() {
        return front_cover_photo_url;
    }

    public void setFront_cover_photo_url(String front_cover_photo_url) {
        this.front_cover_photo_url = front_cover_photo_url;
    }

    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean {
        private int id;
        private String name;
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

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}

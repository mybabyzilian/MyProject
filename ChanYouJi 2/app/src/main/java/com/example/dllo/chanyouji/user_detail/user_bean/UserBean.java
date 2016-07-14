package com.example.dllo.chanyouji.user_detail.user_bean;

import java.util.List;

/**
 * Created by dllo on 16/6/27.
 */
public class UserBean {

    /**
     * id : 443679
     * name : 醉美精灵之一
     * gender : 0
     * image : http://tp4.sinaimg.cn/3018640455/180/5735107036/0
     * favorites_count : 1
     * likes_count : 9
     * attraction_favorites_count : 0
     * poi_favorites_count : 0
     * friends_count : 4
     * fans_count : 48
     * trips_count : 8
     * plans_count : 0
     * current_user_friend : false
     * trips : [{"id":537512,"name":"伊朗：我在蔷薇园里的欢歌","photos_count":126,"start_date":"2016-04-29","end_date":"2016-05-07","days":9,"level":4,"privacy":false,"views_count":3285,"comments_count":4,"likes_count":25,"source":"web","password":null,"front_cover_photo_url":"http://p.chanyouji.cn/537512/1463205157479p1aimt353f1ghu1t791u51ngb8dq8.jpg"},{"id":360663,"name":"Singapore: 新加坡，有一点心情","photos_count":62,"start_date":"2016-02-22","end_date":"2016-02-22","days":1,"level":2,"privacy":false,"views_count":420,"comments_count":0,"likes_count":0,"source":"web","password":null,"front_cover_photo_url":"http://p.chanyouji.cn/360663/1456737697524p1acm54mglos8cti13mciei21o.jpg"},{"id":360636,"name":"新西兰-比天还蓝，比海甚欢","photos_count":128,"start_date":"2016-02-12","end_date":"2016-02-20","days":9,"level":3,"privacy":false,"views_count":4751,"comments_count":9,"likes_count":92,"source":"web","serial_id":360636,"serial_position":0,"password":null,"front_cover_photo_url":"http://p.chanyouji.cn/360636/1456735190396p1acm2tvbb12qn9ic1buac5e1kga2.jpg"},{"id":300510,"name":"在缅甸，属于我们一缕阳光的日子 ","photos_count":95,"start_date":"2014-03-01","end_date":"2014-03-08","days":8,"level":4,"privacy":false,"views_count":11172,"comments_count":16,"likes_count":115,"source":"web","password":null,"front_cover_photo_url":"http://p.chanyouji.cn/300510/1436956238943p19q8k5a7bf43r60u205mu1f4kj.jpg"},{"id":299409,"name":"流淌在心里的兰卡","photos_count":71,"start_date":"2013-09-28","end_date":"2013-10-08","days":11,"level":3,"privacy":false,"views_count":2112,"comments_count":3,"likes_count":43,"source":"web","serial_id":360636,"serial_position":1,"password":null,"front_cover_photo_url":"http://p.chanyouji.cn/299409/1436434360792p19pp2e6ml10nb7smp9k1qvj17kul.jpg"},{"id":299225,"name":"拾 \u2022 念之印度 \u2014 下","photos_count":90,"start_date":"2014-09-11","end_date":"2014-09-21","days":11,"level":3,"privacy":false,"views_count":4655,"comments_count":3,"likes_count":85,"source":"web","password":null,"front_cover_photo_url":"http://p.chanyouji.cn/299225/1436354638042p19pmmgs641o281cp1ma915fsk532.jpg"},{"id":298987,"name":"拾 \u2022 念之印度 \u2014 上","photos_count":123,"start_date":"2014-08-28","end_date":"2014-09-10","days":14,"level":3,"privacy":false,"views_count":4246,"comments_count":18,"likes_count":74,"source":"web","serial_id":360636,"serial_position":2,"password":null,"front_cover_photo_url":"http://p.chanyouji.cn/298987/1436254958732p19pjne2dhbih164egs71lko1u2o8.jpg"},{"id":298672,"name":"落花满庭之土耳其","photos_count":115,"start_date":"2015-06-04","end_date":"2015-06-17","days":14,"level":2,"privacy":false,"views_count":565,"comments_count":4,"likes_count":14,"source":"web","serial_id":360636,"serial_position":3,"password":null,"front_cover_photo_url":"http://p.chanyouji.cn/298672/1436109848862p19pfd1b8o9nv6r6ai515og1to0f.jpg"}]
     */

    private int id;
    private String name;
    private int gender;
    private String image;
    private int favorites_count;
    private int likes_count;
    private int attraction_favorites_count;
    private int poi_favorites_count;
    private int friends_count;
    private int fans_count;
    private int trips_count;
    private int plans_count;
    private boolean current_user_friend;
    /**
     * id : 537512
     * name : 伊朗：我在蔷薇园里的欢歌
     * photos_count : 126
     * start_date : 2016-04-29
     * end_date : 2016-05-07
     * days : 9
     * level : 4
     * privacy : false
     * views_count : 3285
     * comments_count : 4
     * likes_count : 25
     * source : web
     * password : null
     * front_cover_photo_url : http://p.chanyouji.cn/537512/1463205157479p1aimt353f1ghu1t791u51ngb8dq8.jpg
     */

    private List<TripsBean> trips;

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

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getFavorites_count() {
        return favorites_count;
    }

    public void setFavorites_count(int favorites_count) {
        this.favorites_count = favorites_count;
    }

    public int getLikes_count() {
        return likes_count;
    }

    public void setLikes_count(int likes_count) {
        this.likes_count = likes_count;
    }

    public int getAttraction_favorites_count() {
        return attraction_favorites_count;
    }

    public void setAttraction_favorites_count(int attraction_favorites_count) {
        this.attraction_favorites_count = attraction_favorites_count;
    }

    public int getPoi_favorites_count() {
        return poi_favorites_count;
    }

    public void setPoi_favorites_count(int poi_favorites_count) {
        this.poi_favorites_count = poi_favorites_count;
    }

    public int getFriends_count() {
        return friends_count;
    }

    public void setFriends_count(int friends_count) {
        this.friends_count = friends_count;
    }

    public int getFans_count() {
        return fans_count;
    }

    public void setFans_count(int fans_count) {
        this.fans_count = fans_count;
    }

    public int getTrips_count() {
        return trips_count;
    }

    public void setTrips_count(int trips_count) {
        this.trips_count = trips_count;
    }

    public int getPlans_count() {
        return plans_count;
    }

    public void setPlans_count(int plans_count) {
        this.plans_count = plans_count;
    }

    public boolean isCurrent_user_friend() {
        return current_user_friend;
    }

    public void setCurrent_user_friend(boolean current_user_friend) {
        this.current_user_friend = current_user_friend;
    }

    public List<TripsBean> getTrips() {
        return trips;
    }

    public void setTrips(List<TripsBean> trips) {
        this.trips = trips;
    }

    public static class TripsBean {
        private int id;
        private String name;
        private int photos_count;
        private String start_date;
        private String end_date;
        private int days;
        private int level;
        private boolean privacy;
        private int views_count;
        private int comments_count;
        private int likes_count;
        private String source;
        private Object password;
        private String front_cover_photo_url;

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

        public boolean isPrivacy() {
            return privacy;
        }

        public void setPrivacy(boolean privacy) {
            this.privacy = privacy;
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

        public Object getPassword() {
            return password;
        }

        public void setPassword(Object password) {
            this.password = password;
        }

        public String getFront_cover_photo_url() {
            return front_cover_photo_url;
        }

        public void setFront_cover_photo_url(String front_cover_photo_url) {
            this.front_cover_photo_url = front_cover_photo_url;
        }
    }
}

package com.example.dllo.chanyouji.search_detail.dimsearch.dimbean;

/**
 * Created by dllo on 16/7/6.
 */
public class DimJourneyBean {

    /**
     * id : 35443
     * name : 京都清水寺
     * user_score : 4.31
     * attraction_trips_count : 845
     * description : 清水寺依山势而建的、靠数百根巨大的圆木撑起的大殿露台“清水舞台”。
     * image_url : http://m.chanyouji.cn/attractions/35443.jpg
     */

    private int id;
    private String name;
    private String user_score;
    private int attraction_trips_count;
    private String description;
    private String image_url;

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

    public String getUser_score() {
        return user_score;
    }

    public void setUser_score(String user_score) {
        this.user_score = user_score;
    }

    public int getAttraction_trips_count() {
        return attraction_trips_count;
    }

    public void setAttraction_trips_count(int attraction_trips_count) {
        this.attraction_trips_count = attraction_trips_count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}

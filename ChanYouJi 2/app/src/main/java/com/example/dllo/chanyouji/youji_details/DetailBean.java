package com.example.dllo.chanyouji.youji_details;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/6/30.
 */
public class DetailBean {


    private String content;
    private int id;
    private int days;
    private String comment;
    private String name;
    private String imageUrl;
    private String placeCount;
    private String commentCount;
    private String tripData;
    private String entry;






    public DetailBean() {


    }

    public DetailBean(String content,String name, int id, int days,String imageUrl, String comment) {
        this.content = content;
        this.id = id;
        this.days = days;
        this.comment = comment;
        this.name = name;
        this.imageUrl = imageUrl;
    }


    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public String getTripData() {
        return tripData;
    }

    public void setTripData(String tripData) {
        this.tripData = tripData;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public String getPlaceCount() {
        return placeCount;
    }

    public void setPlaceCount(String placeCount) {
        this.placeCount = placeCount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

package com.example.dllo.chanyouji.workbox;

/**
 * Created by dllo on 16/7/8.
 */
public class StringEvent {
    String name;
    int id;

    public StringEvent(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

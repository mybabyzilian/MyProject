package com.example.dllo.chanyouji.workbox.boxbean;

import java.util.ArrayList;

/**
 * Created by dllo on 16/7/8.
 */
public class FBean {
    private String name;
    private ArrayList<IBean> childBean;

    public FBean() {
    }

    public FBean(String name) {
        this.name = name;
    }

    public ArrayList<IBean> getChildBean() {
        return childBean;
    }

    public void setChildBean(ArrayList<IBean> childBean) {
        this.childBean = childBean;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

}

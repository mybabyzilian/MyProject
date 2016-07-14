package com.example.dllo.chanyouji.strategy;

/**
 * Created by dllo on 16/6/22.
 */
public class StrategyData {
    private SingleItem leftItem,rightItem;
    private String category; // 国内 亚洲

    public String getCategory() {
        return category;
    }

    public SingleItem getRightItem() {
        return rightItem;
    }

    public void setRightItem(SingleItem rightItem) {

        this.rightItem = rightItem;
    }

    public SingleItem getLeftItem() {
        return leftItem;
    }

    public void setLeftItem(SingleItem leftItem) {

        this.leftItem = leftItem;

    }
    public void setCategory(String s){
        switch (s){
            case "1":
                category = "国外 · 亚洲";
                break;
            case "2":
                category = "国外 · 欧洲";
                break;
            case "3":
                category =  "美洲 丶 大洋洲 丶 非洲与南极洲";
                break;
            case "99":
                category = "国内 · 港澳台";
                break;
            case "999":
                category = "国内 · 大陆";
                break;
        }
    }
    public boolean hasBlank(){
        if (leftItem == null || rightItem == null){
            return true;
        }
        return false;
    }
    public boolean addData(StrategyBean.DestinationsBean data){
        if (leftItem == null){
            leftItem = new SingleItem(data);
            return true;
        }else {
            rightItem = new SingleItem(data);
            return false;
        }
    }

}

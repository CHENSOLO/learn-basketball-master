package com.example.administrator.ball_ball;

/**
 * Created by Administrator on 2017/11/13.
 */

public class Data {

    private String icon;
    private String title;
    private String tags;
    private String time;

    public Data(){}

    public Data(String  icon, String title, String tags,String time) {
        this.icon = icon;
        this.title = title;
        this.tags = tags;
        this.time = time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return this.title + this.tags + this.time;
    }


}

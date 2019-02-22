package net.people.lifecycle_android.database;

import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

@Entity(primaryKeys = {"ctime", "picUrl"})
public class DbCityBean {
    /**
     * ctime : 2017-06-06 16:00
     * title : 模式思考：为什么星巴克成了印钞机？
     * description : 创业新闻
     * picUrl : http://pic.chinaz.com/thumb/2017/0606/2017060614515724.jpg
     * url : http://www.chinaz.com/start/2017/0606/720004.shtml
     */

    @NonNull
    private String ctime;
    private String title;
    private String description;
    @NonNull
    private String picUrl;
    private String url;

    private String city_name;
    private String last_update;
    private Integer news_id;
    private String news_con;
    private String news_cont;

    public String getNews_cont() {
        return news_cont;
    }

    public void setNews_cont(String news_cont) {
        this.news_cont = news_cont;
    }

    public String getNews_con() {
        return news_con;
    }

    public void setNews_con(String news_con) {
        this.news_con = news_con;
    }

    public Integer getNews_id() {
        return news_id;
    }

    public void setNews_id(Integer news_id) {
        this.news_id = news_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getLast_update() {
        return last_update;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }

    @NonNull
    public String getCtime() {
        return ctime;
    }

    public void setCtime(@NonNull String ctime) {
        this.ctime = ctime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NonNull
    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(@NonNull String picUrl) {
        this.picUrl = picUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "NewsBean{" +
                "ctime='" + ctime + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}

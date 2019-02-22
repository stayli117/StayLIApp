package com.stayli.app.model.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yhgao on 2018/2/9.
 */

public class DBBook {
    /**
     * rating : {"max":10,"numRaters":368,"average":"7.3","min":0}
     * subtitle :
     * author : ["[日] 片山恭一"]
     * pubdate : 2005-1
     * tags : [{"count":144,"name":"片山恭一","title":"片山恭一"},{"count":69,"name":"日本","title":"日本"},{"count":65,"name":"日本文学","title":"日本文学"},{"count":39,"name":"小说","title":"小说"},{"count":33,"name":"满月之夜白鲸现","title":"满月之夜白鲸现"},{"count":17,"name":"爱情","title":"爱情"},{"count":10,"name":"純愛","title":"純愛"},{"count":9,"name":"外国文学","title":"外国文学"}]
     * origin_title :
     * image : https://img3.doubanio.com/mpic/s1747553.jpg
     * binding : 平装
     * translator : ["豫人"]
     * catalog :

     * pages : 180
     * images : {"small":"https://img3.doubanio.com/spic/s1747553.jpg","large":"https://img3.doubanio.com/lpic/s1747553.jpg","medium":"https://img3.doubanio.com/mpic/s1747553.jpg"}
     * alt : https://book.douban.com/subject/1220562/
     * id : 1220562
     * publisher : 青岛出版社
     * isbn10 : 7543632608
     * isbn13 : 9787543632608
     * title : 满月之夜白鲸现
     * url : https://api.douban.com/v2/book/1220562
     * alt_title :
     * author_intro :
     * summary : 那一年，是听莫扎特、钓鲈鱼和家庭破裂的一年。说到家庭破裂，母亲怪自己当初没有找到好男人，父亲则认为当时是被狐狸精迷住了眼，失常的是母亲，但出问题的是父亲……。
     * price : 15.00元
     */

    @SerializedName("rating")
    public RatingBean rating;
    @SerializedName("subtitle")
    public String subtitle;
    @SerializedName("pubdate")
    public String pubdate;
    @SerializedName("origin_title")
    public String originTitle;
    @SerializedName("image")
    public String image;
    @SerializedName("binding")
    public String binding;
    @SerializedName("catalog")
    public String catalog;
    @SerializedName("pages")
    public String pages;
    @SerializedName("images")
    public ImagesBean images;
    @SerializedName("alt")
    public String alt;
    @SerializedName("id")
    public String id;
    @SerializedName("publisher")
    public String publisher;
    @SerializedName("isbn10")
    public String isbn10;
    @SerializedName("isbn13")
    public String isbn13;
    @SerializedName("title")
    public String title;
    @SerializedName("url")
    public String url;
    @SerializedName("alt_title")
    public String altTitle;
    @SerializedName("author_intro")
    public String authorIntro;
    @SerializedName("summary")
    public String summary;
    @SerializedName("price")
    public String price;
    @SerializedName("author")
    public List<String> author;
    @SerializedName("tags")
    public List<TagsBean> tags;
    @SerializedName("translator")
    public List<String> translator;

    public static class RatingBean {
        /**
         * max : 10
         * numRaters : 368
         * average : 7.3
         * min : 0
         */

        @SerializedName("max")
        public int max;
        @SerializedName("numRaters")
        public int numRaters;
        @SerializedName("average")
        public String average;
        @SerializedName("min")
        public int min;
    }

    public static class ImagesBean {
        /**
         * small : https://img3.doubanio.com/spic/s1747553.jpg
         * large : https://img3.doubanio.com/lpic/s1747553.jpg
         * medium : https://img3.doubanio.com/mpic/s1747553.jpg
         */

        @SerializedName("small")
        public String small;
        @SerializedName("large")
        public String large;
        @SerializedName("medium")
        public String medium;
    }

    public static class TagsBean {
        /**
         * count : 144
         * name : 片山恭一
         * title : 片山恭一
         */

        @SerializedName("count")
        public int count;
        @SerializedName("name")
        public String name;
        @SerializedName("title")
        public String title;
    }
}

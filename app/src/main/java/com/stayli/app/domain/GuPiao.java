package com.stayli.app.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GuPiao {


    /**
     * resultcode : 200
     * reason : SUCCESSED!
     * result : [{"data":{"buyFive":"200","buyFivePri":"83.500","buyFour":"500","buyFourPri":"83.520","buyOne":"400","buyOnePri":"83.610","buyThree":"100","buyThreePri":"83.530","buyTwo":"100","buyTwoPri":"83.600","competitivePri":"83.610","date":"2018-07-06","gid":"sh603096","increPer":"-0.88","increase":"-0.750","name":"新经典","nowPri":"84.150","reservePri":"84.200","sellFive":"100","sellFivePri":"84.520","sellFour":"400","sellFourPri":"84.480","sellOne":"4000","sellOnePri":"84.200","sellThree":"200","sellThreePri":"84.380","sellTwo":"700","sellTwoPri":"84.370","time":"15:00:00","todayMax":"86.100","todayMin":"82.670","todayStartPri":"83.380","traAmount":"17130223.000","traNumber":"2030","yestodEndPri":"84.900"},"dapandata":{"dot":"84.150","name":"新经典","nowPic":"-0.750","rate":"-0.88","traAmount":"1713","traNumber":"2030"},"gopicture":{"minurl":"http://image.sinajs.cn/newchart/min/n/sh603096.gif","dayurl":"http://image.sinajs.cn/newchart/daily/n/sh603096.gif","weekurl":"http://image.sinajs.cn/newchart/weekly/n/sh603096.gif","monthurl":"http://image.sinajs.cn/newchart/monthly/n/sh603096.gif"}}]
     * error_code : 0
     */

    @SerializedName("resultcode")
    public String resultcode;
    @SerializedName("reason")
    public String reason;
    @SerializedName("error_code")
    public int errorCode;
    @SerializedName("result")
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * data : {"buyFive":"200","buyFivePri":"83.500","buyFour":"500","buyFourPri":"83.520","buyOne":"400","buyOnePri":"83.610","buyThree":"100","buyThreePri":"83.530","buyTwo":"100","buyTwoPri":"83.600","competitivePri":"83.610","date":"2018-07-06","gid":"sh603096","increPer":"-0.88","increase":"-0.750","name":"新经典","nowPri":"84.150","reservePri":"84.200","sellFive":"100","sellFivePri":"84.520","sellFour":"400","sellFourPri":"84.480","sellOne":"4000","sellOnePri":"84.200","sellThree":"200","sellThreePri":"84.380","sellTwo":"700","sellTwoPri":"84.370","time":"15:00:00","todayMax":"86.100","todayMin":"82.670","todayStartPri":"83.380","traAmount":"17130223.000","traNumber":"2030","yestodEndPri":"84.900"}
         * dapandata : {"dot":"84.150","name":"新经典","nowPic":"-0.750","rate":"-0.88","traAmount":"1713","traNumber":"2030"}
         * gopicture : {"minurl":"http://image.sinajs.cn/newchart/min/n/sh603096.gif","dayurl":"http://image.sinajs.cn/newchart/daily/n/sh603096.gif","weekurl":"http://image.sinajs.cn/newchart/weekly/n/sh603096.gif","monthurl":"http://image.sinajs.cn/newchart/monthly/n/sh603096.gif"}
         */

        @SerializedName("data")
        public DataBean data;
        @SerializedName("dapandata")
        public DapandataBean dapandata;
        @SerializedName("gopicture")
        public GopictureBean gopicture;

        public static class DataBean {
            /**
             * buyFive : 200
             * buyFivePri : 83.500
             * buyFour : 500
             * buyFourPri : 83.520
             * buyOne : 400
             * buyOnePri : 83.610
             * buyThree : 100
             * buyThreePri : 83.530
             * buyTwo : 100
             * buyTwoPri : 83.600
             * competitivePri : 83.610
             * date : 2018-07-06
             * gid : sh603096
             * increPer : -0.88
             * increase : -0.750
             * name : 新经典
             * nowPri : 84.150
             * reservePri : 84.200
             * sellFive : 100
             * sellFivePri : 84.520
             * sellFour : 400
             * sellFourPri : 84.480
             * sellOne : 4000
             * sellOnePri : 84.200
             * sellThree : 200
             * sellThreePri : 84.380
             * sellTwo : 700
             * sellTwoPri : 84.370
             * time : 15:00:00
             * todayMax : 86.100
             * todayMin : 82.670
             * todayStartPri : 83.380
             * traAmount : 17130223.000
             * traNumber : 2030
             * yestodEndPri : 84.900
             */

            @SerializedName("buyFive")
            public String buyFive;
            @SerializedName("buyFivePri")
            public String buyFivePri;
            @SerializedName("buyFour")
            public String buyFour;
            @SerializedName("buyFourPri")
            public String buyFourPri;
            @SerializedName("buyOne")
            public String buyOne;
            @SerializedName("buyOnePri")
            public String buyOnePri;
            @SerializedName("buyThree")
            public String buyThree;
            @SerializedName("buyThreePri")
            public String buyThreePri;
            @SerializedName("buyTwo")
            public String buyTwo;
            @SerializedName("buyTwoPri")
            public String buyTwoPri;
            @SerializedName("competitivePri")
            public String competitivePri;
            @SerializedName("date")
            public String date;
            @SerializedName("gid")
            public String gid;
            @SerializedName("increPer")
            public String increPer;
            @SerializedName("increase")
            public String increase;
            @SerializedName("name")
            public String name;
            @SerializedName("nowPri")
            public String nowPri;
            @SerializedName("reservePri")
            public String reservePri;
            @SerializedName("sellFive")
            public String sellFive;
            @SerializedName("sellFivePri")
            public String sellFivePri;
            @SerializedName("sellFour")
            public String sellFour;
            @SerializedName("sellFourPri")
            public String sellFourPri;
            @SerializedName("sellOne")
            public String sellOne;
            @SerializedName("sellOnePri")
            public String sellOnePri;
            @SerializedName("sellThree")
            public String sellThree;
            @SerializedName("sellThreePri")
            public String sellThreePri;
            @SerializedName("sellTwo")
            public String sellTwo;
            @SerializedName("sellTwoPri")
            public String sellTwoPri;
            @SerializedName("time")
            public String time;
            @SerializedName("todayMax")
            public String todayMax;
            @SerializedName("todayMin")
            public String todayMin;
            @SerializedName("todayStartPri")
            public String todayStartPri;
            @SerializedName("traAmount")
            public String traAmount;
            @SerializedName("traNumber")
            public String traNumber;
            @SerializedName("yestodEndPri")
            public String yestodEndPri;
        }

        public static class DapandataBean {
            /**
             * dot : 84.150
             * name : 新经典
             * nowPic : -0.750
             * rate : -0.88
             * traAmount : 1713
             * traNumber : 2030
             */

            @SerializedName("dot")
            public String dot;
            @SerializedName("name")
            public String name;
            @SerializedName("nowPic")
            public String nowPic;
            @SerializedName("rate")
            public String rate;
            @SerializedName("traAmount")
            public String traAmount;
            @SerializedName("traNumber")
            public String traNumber;
        }

        public static class GopictureBean {
            /**
             * minurl : http://image.sinajs.cn/newchart/min/n/sh603096.gif
             * dayurl : http://image.sinajs.cn/newchart/daily/n/sh603096.gif
             * weekurl : http://image.sinajs.cn/newchart/weekly/n/sh603096.gif
             * monthurl : http://image.sinajs.cn/newchart/monthly/n/sh603096.gif
             */

            @SerializedName("minurl")
            public String minurl;
            @SerializedName("dayurl")
            public String dayurl;
            @SerializedName("weekurl")
            public String weekurl;
            @SerializedName("monthurl")
            public String monthurl;
        }
    }
}

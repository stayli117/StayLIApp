package com.stayli.app.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DateLunar {


    /**
     * status : 200
     * message : success
     * data : {"year":2018,"month":6,"day":21,"lunarYear":2018,"lunarMonth":5,"lunarDay":8,"cnyear":"贰零壹捌 ","cnmonth":"五","cnday":"初八","hyear":"戊戌","cyclicalYear":"戊戌","cyclicalMonth":"戊午","cyclicalDay":"甲申","suit":"开光,求嗣,出行,冠笄,嫁娶,伐木,架马,开柱眼,修造,移徙,入宅,开市,交易,立券,出行,安香,出火,挂匾,起基,修造,开生坟,合寿木,入殓,除服,成服,移柩,安葬","taboo":"安床,出货财,作灶,动土,破土","animal":"狗","week":"Thursday","festivalList":[],"jieqi":{"6":"芒种","22":"夏至"},"maxDayInMonth":29,"leap":false,"lunarYearString":"戊戌","bigMonth":false}
     */

    @SerializedName("status")
    public int status;
    @SerializedName("message")
    public String message;
    @SerializedName("data")
    public DataBean data;

    public static class DataBean {
        /**
         * year : 2018
         * month : 6
         * day : 21
         * lunarYear : 2018
         * lunarMonth : 5
         * lunarDay : 8
         * cnyear : 贰零壹捌
         * cnmonth : 五
         * cnday : 初八
         * hyear : 戊戌
         * cyclicalYear : 戊戌
         * cyclicalMonth : 戊午
         * cyclicalDay : 甲申
         * suit : 开光,求嗣,出行,冠笄,嫁娶,伐木,架马,开柱眼,修造,移徙,入宅,开市,交易,立券,出行,安香,出火,挂匾,起基,修造,开生坟,合寿木,入殓,除服,成服,移柩,安葬
         * taboo : 安床,出货财,作灶,动土,破土
         * animal : 狗
         * week : Thursday
         * festivalList : []
         * jieqi : {"6":"芒种","22":"夏至"}
         * maxDayInMonth : 29
         * leap : false
         * lunarYearString : 戊戌
         * bigMonth : false
         */

        @SerializedName("year")
        public int year;
        @SerializedName("month")
        public int month;
        @SerializedName("day")
        public int day;
        @SerializedName("lunarYear")
        public int lunarYear;
        @SerializedName("lunarMonth")
        public int lunarMonth;
        @SerializedName("lunarDay")
        public int lunarDay;
        @SerializedName("cnyear")
        public String cnyear;
        @SerializedName("cnmonth")
        public String cnmonth;
        @SerializedName("cnday")
        public String cnday;
        @SerializedName("hyear")
        public String hyear;
        @SerializedName("cyclicalYear")
        public String cyclicalYear;
        @SerializedName("cyclicalMonth")
        public String cyclicalMonth;
        @SerializedName("cyclicalDay")
        public String cyclicalDay;
        @SerializedName("suit")
        public String suit;
        @SerializedName("taboo")
        public String taboo;
        @SerializedName("animal")
        public String animal;
        @SerializedName("week")
        public String week;
        @SerializedName("jieqi")
        public JieqiBean jieqi;
        @SerializedName("maxDayInMonth")
        public int maxDayInMonth;
        @SerializedName("leap")
        public boolean leap;
        @SerializedName("lunarYearString")
        public String lunarYearString;
        @SerializedName("bigMonth")
        public boolean bigMonth;
        @SerializedName("festivalList")
        public List<?> festivalList;

        public static class JieqiBean {
            /**
             * 6 : 芒种
             * 22 : 夏至
             */

            @SerializedName("6")
            public String $6;
            @SerializedName("22")
            public String $22;
        }
    }
}

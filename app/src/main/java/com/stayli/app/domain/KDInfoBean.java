package com.stayli.app.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yhgao on 2018/3/11.
 */

public class KDInfoBean {

    /**
     * resultcode : 200
     * reason : 查询物流信息成功
     * result : {"company":"邮政国内（挂号信）","com":"yzgn","no":"NF29863920144","status":"0","list":[{"datetime":"2018-03-10 09:49:45","remark":"【广东省珠海市邮政局金邦达邮局】已收寄","zone":""},{"datetime":"2018-03-10 18:04:45","remark":"离开【珠海中心】，下一站【广州中心】","zone":""},{"datetime":"2018-03-10 22:14:00","remark":"到达【广州中心】","zone":""},{"datetime":"2018-03-10 22:53:43","remark":"离开【珠海市邮政局金邦达邮局】，下一站【珠海中心】","zone":""},{"datetime":"2018-03-11 05:48:15","remark":"离开【广州中心】，下一站【北京中心】","zone":""}]}
     * error_code : 0
     */

    @SerializedName("resultcode")
    public String resultcode;
    @SerializedName("reason")
    public String reason;
    @SerializedName("result")
    public ResultBean result;
    @SerializedName("error_code")
    public int errorCode;

    @Override
    public String toString() {
        return "KDInfoBean{" +
                "resultcode='" + resultcode + '\'' +
                ", reason='" + reason + '\'' +
                ", result=" + result +
                ", errorCode=" + errorCode +
                '}';
    }

    public static class ResultBean {
        /**
         * company : 邮政国内（挂号信）
         * com : yzgn
         * no : NF29863920144
         * status : 0
         * list : [{"datetime":"2018-03-10 09:49:45","remark":"【广东省珠海市邮政局金邦达邮局】已收寄","zone":""},{"datetime":"2018-03-10 18:04:45","remark":"离开【珠海中心】，下一站【广州中心】","zone":""},{"datetime":"2018-03-10 22:14:00","remark":"到达【广州中心】","zone":""},{"datetime":"2018-03-10 22:53:43","remark":"离开【珠海市邮政局金邦达邮局】，下一站【珠海中心】","zone":""},{"datetime":"2018-03-11 05:48:15","remark":"离开【广州中心】，下一站【北京中心】","zone":""}]
         */

        @SerializedName("company")
        public String company;
        @SerializedName("com")
        public String com;
        @SerializedName("no")
        public String no;
        @SerializedName("status")
        public String status;
        @SerializedName("list")
        public List<ListBean> list;

        @Override
        public String toString() {
            return "ResultBean{" +
                    "company='" + company + '\'' +
                    ", com='" + com + '\'' +
                    ", no='" + no + '\'' +
                    ", status='" + status + '\'' +
                    ", list=" + list +
                    '}';
        }

        public static class ListBean {
            /**
             * datetime : 2018-03-10 09:49:45
             * remark : 【广东省珠海市邮政局金邦达邮局】已收寄
             * zone :
             */

            @SerializedName("datetime")
            public String datetime="";
            @SerializedName("remark")
            public String remark="";
            @SerializedName("zone")
            public String zone="";

            @Override
            public String toString() {
                return "ListBean{" +
                        "datetime='" + datetime + '\'' +
                        ", remark='" + remark + '\'' +
                        ", zone='" + zone + '\'' +
                        '}';
            }
        }
    }
}

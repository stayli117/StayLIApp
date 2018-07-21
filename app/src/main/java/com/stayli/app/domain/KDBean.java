package com.stayli.app.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yhgao on 2018/3/11.
 */

public class KDBean {

    /**
     * resultcode : 200
     * reason : 查询支持的快递公司成功
     * result : [{"com":"顺丰","no":"sf"},{"com":"申通","no":"sto"},{"com":"圆通","no":"yt"},{"com":"韵达","no":"yd"},{"com":"天天","no":"tt"},{"com":"EMS","no":"ems"},{"com":"中通","no":"zto"},{"com":"汇通","no":"ht"},{"com":"全峰","no":"qf"},{"com":"德邦","no":"db"},{"com":"国通","no":"gt"},{"com":"如风达","no":"rfd"},{"com":"京东快递","no":"jd"},{"com":"宅急送","no":"zjs"},{"com":"EMS国际","no":"emsg"},{"com":"Fedex国际","no":"fedex"},{"com":"邮政国内（挂号信）","no":"yzgn"},{"com":"UPS国际快递","no":"ups"},{"com":"中铁快运","no":"ztky"},{"com":"佳吉快运","no":"jiaji"},{"com":"速尔快递","no":"suer"},{"com":"信丰物流","no":"xfwl"},{"com":"优速快递","no":"yousu"},{"com":"中邮物流","no":"zhongyou"},{"com":"天地华宇","no":"tdhy"},{"com":"安信达快递","no":"axd"},{"com":"快捷速递","no":"kuaijie"}]
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
         * com : 顺丰
         * no : sf
         */

        @SerializedName("com")
        public String com;
        @SerializedName("no")
        public String no;

        @Override
        public String toString() {
            return "ResultBean{" +
                    "com='" + com + '\'' +
                    ", no='" + no + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "KDBean{" +
                "resultcode='" + resultcode + '\'' +
                ", reason='" + reason + '\'' +
                ", errorCode=" + errorCode +
                ", result=" + result +
                '}';
    }
}

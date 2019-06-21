package com.stayli.app.model.domain;

import com.google.gson.annotations.SerializedName;

public class GPZhiShu {


    /**
     * error_code : 0
     * reason : SUCCESSED!
     * result : {"dealNum":"18606299786","dealPri":"208214304154.859","highPri":"9035.454","increPer":"0.55","increase":"49.164","lowpri":"8736.355","name":"深证成指","nowpri":"8911.342","openPri":"8873.899","time":"2018-07-06 15:19:03","yesPri":"8862.178"}
     */

    @SerializedName("error_code")
    public int errorCode;
    @SerializedName("reason")
    public String reason;
    @SerializedName("result")
    public ResultBean result;
    public String type; // 指数类型 沪深 0 ，上证 1

    public static class ResultBean {
        /**
         * dealNum : 18606299786
         * dealPri : 208214304154.859
         * highPri : 9035.454
         * increPer : 0.55
         * increase : 49.164
         * lowpri : 8736.355
         * name : 深证成指
         * nowpri : 8911.342
         * openPri : 8873.899
         * time : 2018-07-06 15:19:03
         * yesPri : 8862.178
         */

        @SerializedName("dealNum")
        public String dealNum;
        @SerializedName("dealPri")
        public String dealPri;
        @SerializedName("highPri")
        public String highPri;
        @SerializedName("increPer")
        public String increPer;
        @SerializedName("increase")
        public String increase;
        @SerializedName("lowpri")
        public String lowpri;
        @SerializedName("name")
        public String name;
        @SerializedName("nowpri")
        public String nowpri;
        @SerializedName("openPri")
        public String openPri;
        @SerializedName("time")
        public String time;
        @SerializedName("yesPri")
        public String yesPri;

        @Override
        public String toString() {
            return "ResultBean{" +
                    "dealNum='" + dealNum + '\'' +
                    ", dealPri='" + dealPri + '\'' +
                    ", highPri='" + highPri + '\'' +
                    ", increPer='" + increPer + '\'' +
                    ", increase='" + increase + '\'' +
                    ", lowpri='" + lowpri + '\'' +
                    ", name='" + name + '\'' +
                    ", nowpri='" + nowpri + '\'' +
                    ", openPri='" + openPri + '\'' +
                    ", time='" + time + '\'' +
                    ", yesPri='" + yesPri + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "GPZhiShu{" +
                "errorCode=" + errorCode +
                ", reason='" + reason + '\'' +
                ", result=" + result +
                ", type='" + type + '\'' +
                '}';
    }
}

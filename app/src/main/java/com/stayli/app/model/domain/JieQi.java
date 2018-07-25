package com.stayli.app.model.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JieQi {


    /**
     * status : 0
     * msg : ok
     * result : {"now":{"name":"芒种","time":"2018-06-06 01:29:04","lunar":["2018","四月","廿三","戊戌","4","23","狗","0"]},"list":[{"jieqiid":"23","name":"小寒","pic":"http://m.46644.com/jieqi/static/images/jieqi/xh.png","time":"2018-01-05 17:48:41"},{"jieqiid":"24","name":"大寒","pic":"http://m.46644.com/jieqi/static/images/jieqi/dh.png","time":"2018-01-20 11:08:58"},{"jieqiid":"1","name":"立春","pic":"http://m.46644.com/jieqi/static/images/jieqi/lc.png","time":"2018-02-04 05:28:25"},{"jieqiid":"2","name":"雨水","pic":"http://m.46644.com/jieqi/static/images/jieqi/ys.png","time":"2018-02-19 01:17:57"},{"jieqiid":"3","name":"惊蛰","pic":"http://m.46644.com/jieqi/static/images/jieqi/jz.png","time":"2018-03-05 23:28:06"},{"jieqiid":"4","name":"春分","pic":"http://m.46644.com/jieqi/static/images/jieqi/cf.png","time":"2018-03-21 00:15:24"},{"jieqiid":"5","name":"清明","pic":"http://m.46644.com/jieqi/static/images/jieqi/qm.png","time":"2018-04-05 04:12:43"},{"jieqiid":"6","name":"谷雨","pic":"http://m.46644.com/jieqi/static/images/jieqi/gy.png","time":"2018-04-20 11:12:29"},{"jieqiid":"7","name":"立夏","pic":"http://m.46644.com/jieqi/static/images/jieqi/lx.png","time":"2018-05-05 21:25:18"},{"jieqiid":"8","name":"小满","pic":"http://m.46644.com/jieqi/static/images/jieqi/xm.png","time":"2018-05-21 10:14:33"},{"jieqiid":"9","name":"芒种","pic":"http://m.46644.com/jieqi/static/images/jieqi/mz.png","time":"2018-06-06 01:29:04"},{"jieqiid":"10","name":"夏至","pic":"http://m.46644.com/jieqi/static/images/jieqi/xz.png","time":"2018-06-21 18:07:12"},{"jieqiid":"11","name":"小暑","pic":"http://m.46644.com/jieqi/static/images/jieqi/xs.png","time":"2018-07-07 11:41:47"},{"jieqiid":"12","name":"大暑","pic":"http://m.46644.com/jieqi/static/images/jieqi/ds.png","time":"2018-07-23 05:00:16"},{"jieqiid":"13","name":"立秋","pic":"http://m.46644.com/jieqi/static/images/jieqi/lq.png","time":"2018-08-07 21:30:34"},{"jieqiid":"14","name":"处暑","pic":"http://m.46644.com/jieqi/static/images/jieqi/cs.png","time":"2018-08-23 12:08:30"},{"jieqiid":"15","name":"白露","pic":"http://m.46644.com/jieqi/static/images/jieqi/bl.png","time":"2018-09-08 00:29:37"},{"jieqiid":"16","name":"秋分","pic":"http://m.46644.com/jieqi/static/images/jieqi/qf.png","time":"2018-09-23 09:54:01"},{"jieqiid":"17","name":"寒露","pic":"http://m.46644.com/jieqi/static/images/jieqi/hl.png","time":"2018-10-08 16:14:37"},{"jieqiid":"18","name":"霜降","pic":"http://m.46644.com/jieqi/static/images/jieqi/sj.png","time":"2018-10-23 19:22:18"},{"jieqiid":"19","name":"立冬","pic":"http://m.46644.com/jieqi/static/images/jieqi/ld.png","time":"2018-11-07 19:31:39"},{"jieqiid":"20","name":"小雪","pic":"http://m.46644.com/jieqi/static/images/jieqi/xx.png","time":"2018-11-22 17:01:24"},{"jieqiid":"21","name":"大雪","pic":"http://m.46644.com/jieqi/static/images/jieqi/dx.png","time":"2018-12-07 12:25:48"},{"jieqiid":"22","name":"冬至","pic":"http://m.46644.com/jieqi/static/images/jieqi/dz.png","time":"2018-12-22 06:22:38"}],"song":"春雨惊春清谷天，夏满芒夏暑相连。秋处露秋寒霜降，冬雪雪冬小大寒。"}
     */

    @SerializedName("status")
    public String status;
    @SerializedName("msg")
    public String msg;
    @SerializedName("result")
    public ResultBean result;

    public static class ResultBean {
        /**
         * now : {"name":"芒种","time":"2018-06-06 01:29:04","lunar":["2018","四月","廿三","戊戌","4","23","狗","0"]}
         * list : [{"jieqiid":"23","name":"小寒","pic":"http://m.46644.com/jieqi/static/images/jieqi/xh.png","time":"2018-01-05 17:48:41"},{"jieqiid":"24","name":"大寒","pic":"http://m.46644.com/jieqi/static/images/jieqi/dh.png","time":"2018-01-20 11:08:58"},{"jieqiid":"1","name":"立春","pic":"http://m.46644.com/jieqi/static/images/jieqi/lc.png","time":"2018-02-04 05:28:25"},{"jieqiid":"2","name":"雨水","pic":"http://m.46644.com/jieqi/static/images/jieqi/ys.png","time":"2018-02-19 01:17:57"},{"jieqiid":"3","name":"惊蛰","pic":"http://m.46644.com/jieqi/static/images/jieqi/jz.png","time":"2018-03-05 23:28:06"},{"jieqiid":"4","name":"春分","pic":"http://m.46644.com/jieqi/static/images/jieqi/cf.png","time":"2018-03-21 00:15:24"},{"jieqiid":"5","name":"清明","pic":"http://m.46644.com/jieqi/static/images/jieqi/qm.png","time":"2018-04-05 04:12:43"},{"jieqiid":"6","name":"谷雨","pic":"http://m.46644.com/jieqi/static/images/jieqi/gy.png","time":"2018-04-20 11:12:29"},{"jieqiid":"7","name":"立夏","pic":"http://m.46644.com/jieqi/static/images/jieqi/lx.png","time":"2018-05-05 21:25:18"},{"jieqiid":"8","name":"小满","pic":"http://m.46644.com/jieqi/static/images/jieqi/xm.png","time":"2018-05-21 10:14:33"},{"jieqiid":"9","name":"芒种","pic":"http://m.46644.com/jieqi/static/images/jieqi/mz.png","time":"2018-06-06 01:29:04"},{"jieqiid":"10","name":"夏至","pic":"http://m.46644.com/jieqi/static/images/jieqi/xz.png","time":"2018-06-21 18:07:12"},{"jieqiid":"11","name":"小暑","pic":"http://m.46644.com/jieqi/static/images/jieqi/xs.png","time":"2018-07-07 11:41:47"},{"jieqiid":"12","name":"大暑","pic":"http://m.46644.com/jieqi/static/images/jieqi/ds.png","time":"2018-07-23 05:00:16"},{"jieqiid":"13","name":"立秋","pic":"http://m.46644.com/jieqi/static/images/jieqi/lq.png","time":"2018-08-07 21:30:34"},{"jieqiid":"14","name":"处暑","pic":"http://m.46644.com/jieqi/static/images/jieqi/cs.png","time":"2018-08-23 12:08:30"},{"jieqiid":"15","name":"白露","pic":"http://m.46644.com/jieqi/static/images/jieqi/bl.png","time":"2018-09-08 00:29:37"},{"jieqiid":"16","name":"秋分","pic":"http://m.46644.com/jieqi/static/images/jieqi/qf.png","time":"2018-09-23 09:54:01"},{"jieqiid":"17","name":"寒露","pic":"http://m.46644.com/jieqi/static/images/jieqi/hl.png","time":"2018-10-08 16:14:37"},{"jieqiid":"18","name":"霜降","pic":"http://m.46644.com/jieqi/static/images/jieqi/sj.png","time":"2018-10-23 19:22:18"},{"jieqiid":"19","name":"立冬","pic":"http://m.46644.com/jieqi/static/images/jieqi/ld.png","time":"2018-11-07 19:31:39"},{"jieqiid":"20","name":"小雪","pic":"http://m.46644.com/jieqi/static/images/jieqi/xx.png","time":"2018-11-22 17:01:24"},{"jieqiid":"21","name":"大雪","pic":"http://m.46644.com/jieqi/static/images/jieqi/dx.png","time":"2018-12-07 12:25:48"},{"jieqiid":"22","name":"冬至","pic":"http://m.46644.com/jieqi/static/images/jieqi/dz.png","time":"2018-12-22 06:22:38"}]
         * song : 春雨惊春清谷天，夏满芒夏暑相连。秋处露秋寒霜降，冬雪雪冬小大寒。
         */

        @SerializedName("now")
        public NowBean now;
        @SerializedName("song")
        public String song;
        @SerializedName("list")
        public List<ListBean> list;

        public static class NowBean {
            /**
             * name : 芒种
             * time : 2018-06-06 01:29:04
             * lunar : ["2018","四月","廿三","戊戌","4","23","狗","0"]
             */

            @SerializedName("name")
            public String name;
            @SerializedName("time")
            public String time;
            @SerializedName("lunar")
            public List<String> lunar;
        }

        public static class ListBean {
            /**
             * jieqiid : 23
             * name : 小寒
             * pic : http://m.46644.com/jieqi/static/images/jieqi/xh.png
             * time : 2018-01-05 17:48:41
             */

            @SerializedName("jieqiid")
            public String jieqiid;
            @SerializedName("name")
            public String name;
            @SerializedName("pic")
            public String pic;
            @SerializedName("time")
            public String time;
        }
    }
}

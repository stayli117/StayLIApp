package com.stayli.app.app;

import android.app.Application;

import com.huawei.android.hms.agent.HMSAgent;
import com.stayli.app.api.NetAPIManager;
import com.stayli.app.utils.Util;

/**
 * Created by yhgao on 2018/2/8.
 */

public class StayApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        Util.init(this);
        NetAPIManager.getInstance().init();
        // 推送
        HMSAgent.init(this);

    }

}

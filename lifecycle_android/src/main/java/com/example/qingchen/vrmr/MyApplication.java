package com.example.qingchen.vrmr;

import android.app.Application;
import android.content.Context;

import net.people.lifecycle_android.database.CityDataBase;

/**
 * @author qingchen
 * @date 17-11-12
 */

public class MyApplication extends Application {

    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;



    }
}

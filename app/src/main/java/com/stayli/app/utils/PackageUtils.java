/*
 * Copyright (c) 2016 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package com.stayli.app.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Toast;

public class PackageUtils {

    private PackageUtils() {}

    public static boolean isInstalled(String packageName, Context context) {
        try {
            context.getPackageManager().getPackageInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static boolean hasMinimumVersion(String packageName, int minimumVersionCode,
                                            Context context) {
        try {
            return context.getPackageManager().getPackageInfo(packageName, 0).versionCode
                    >= minimumVersionCode;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static void install(String packageName, Context context) {
        try {
            context.startActivity(IntentUtils.makeViewAppInMarket(packageName));
        } catch (ActivityNotFoundException e) {
            try {
                context.startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=" + packageName)));
            } catch (ActivityNotFoundException e2) {
                Toast.makeText(context, "请安装官方豆瓣应用", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

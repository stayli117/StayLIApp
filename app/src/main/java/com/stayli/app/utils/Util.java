package com.stayli.app.utils;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.PersistableBundle;
import android.os.SystemClock;

import com.stayli.app.listener.AlarmService;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by yhgao on 2018/2/8.
 */

public class Util {

    private static Application mApplication;

    public static void init(Application application) {
        mApplication = application;
    }

    public static Context getApplication() {
        return mApplication;
    }

    public static final int JOB_ID = 1000000;

    public static boolean isServiceWorking(Context context, String className) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (activityManager == null) return false;
        List<ActivityManager.RunningServiceInfo> services = activityManager.getRunningServices(Integer.MAX_VALUE);
        if (services == null || services.size() < 1) return false;
        for (ActivityManager.RunningServiceInfo service : services) {
            if (service.service.getClassName().equals(className)) {
                return true;
            }
        }


        return false;
    }


    public static boolean isJobPollServiceOn(Context context, int job_id) {
        JobScheduler scheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        if (scheduler == null) return false;
        List<JobInfo> allPendingJobs = scheduler.getAllPendingJobs();
        if (allPendingJobs.size() < 1) return false;

        for (JobInfo jobInfo : allPendingJobs) {
            if (jobInfo.getId() == job_id) {
                return true;
            }
        }
        return false;

    }

    public static final String FROM = "from";

    public static JobInfo createJob(Context context, PersistableBundle ex) {
        JobInfo jobInfo = new JobInfo.Builder(Util.JOB_ID, new ComponentName(context, AlarmService.class))
                .setPersisted(true) // 设备重启 任务继续执行
//                .setRequiresDeviceIdle(true) // 设置手机空闲条件
//                .setRequiresCharging(true)// 充电
//                .setPeriodic(3000)//间隔时间
                .setMinimumLatency(1000)
                .setExtras(ex)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .build();
        return jobInfo;
    }


    public static void set8Alarm(Context context, int timeclock, PendingIntent pendingIntent) {
        long firstTime = SystemClock.elapsedRealtime();    //获取系统当前时间
        long systemTime = System.currentTimeMillis();//java.lang.System.currentTimeMillis()，它返回从 UTC 1970 年 1 月 1 日午夜开始经过的毫秒数。

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8")); //  这里时区需要设置一下，不然会有8个小时的时间差
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, timeclock);//设置为8：00点提醒
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        //选择的定时时间
        long selectTime = calendar.getTimeInMillis();    //计算出设定的时间

        //  如果当前时间大于设置的时间，那么就从第二天的设定时间开始
        if (systemTime > selectTime) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            selectTime = calendar.getTimeInMillis();
        }

        long time = selectTime - systemTime;// 计算现在时间到设定时间的时间差
        long my_Time = firstTime + time;//系统 当前的时间+时间差

        // 进行闹铃注册
        AlarmManager am = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        if (am == null) return;

        am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, my_Time, AlarmManager.INTERVAL_DAY, pendingIntent);

    }

    public static void cancle8Alarm(Context context, PendingIntent pendingIntent) {
        AlarmManager am = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        if (am == null) return;
        am.cancel(pendingIntent);
    }

}

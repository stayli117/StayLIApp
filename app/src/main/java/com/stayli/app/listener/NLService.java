package com.stayli.app.listener;

import android.app.Notification;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

import com.stayli.app.utils.Util;

/**
 * 通知使用权
 */
public class NLService extends NotificationListenerService {

    private static final String TAG = "NLService";


    public NLService() {
    }

    @Override
    public void onListenerConnected() {
        super.onListenerConnected();
        JobScheduler scheduler = (JobScheduler) getApplication().getSystemService(Context.JOB_SCHEDULER_SERVICE);
        // 先判断服务是否已经开启再启动
        // 这里放弃普通service启动后台任务 ， 使用job
        if (scheduler == null) return;
        if (!Util.isJobPollServiceOn(getApplication(), Util.JOB_ID)) {
            PersistableBundle bundle = new PersistableBundle();
            bundle.putInt(Util.FROM, 2);
            JobInfo jobInfo = Util.createJob(getApplication(), bundle);
            scheduler.schedule(jobInfo);
        }

        Log.e(TAG, "onListenerConnected: ");
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);
        Notification notification = sbn.getNotification();
        Bundle extras = notification.extras;
        String title = extras.getString(Notification.EXTRA_TITLE);
        CharSequence charSequence = extras.getCharSequence(Notification.EXTRA_TEXT);

        Log.e(TAG, "onNotificationPosted: " + title + " 内容 " + charSequence);
//        Toast.makeText(this, ""+title, Toast.LENGTH_SHORT).show();
        JobScheduler scheduler = (JobScheduler) getApplication().getSystemService(Context.JOB_SCHEDULER_SERVICE);
        // 先判断服务是否已经开启再启动
        // 这里放弃普通service启动后台任务 ， 使用job
        if (scheduler == null) return;
        boolean serviceOn = Util.isJobPollServiceOn(getApplication(), Util.JOB_ID);
        if (!serviceOn) {
            PersistableBundle bundle = new PersistableBundle();
            bundle.putInt(Util.FROM, 2);
            JobInfo jobInfo = Util.createJob(getApplication(), bundle);
            scheduler.schedule(jobInfo);
        }
        Log.e(TAG, serviceOn + "onNotificationPosted: " + title + " 内容 " + charSequence);
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        super.onNotificationRemoved(sbn);
    }

}

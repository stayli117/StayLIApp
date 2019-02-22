package com.stayli.app.listener;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.util.Log;

import com.stayli.app.MainActivity;
import com.stayli.app.R;
import com.stayli.app.utils.Util;

import static android.content.Context.NOTIFICATION_SERVICE;

public class BootReceiver extends BroadcastReceiver {

    private static final String ACTION_BOOT = "android.intent.action.BOOT_COMPLETED";
    private static final String ACTION_ALARM = "android.intent.action.ALARM";
    private static final String TAG = "AlarmService";

    private static final String KEY_SETTING_AUTO_SUBMIT = "sm10009";
    private static final String KEY_SETTING_AUTO_SUBMIT_TIME = "sm10009time";
    private static final int ALARM_REPLENISH_STOCK_CODE = 11;
    private static final int ALARM_SYNCHRONIZE_CODE = 12;

    @Override
    public void onReceive(Context context, Intent intent) {
        // 设置闹钟
//        startAlarm();
        // 开启后台service
        if (intent != null) {
            JobScheduler scheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
            String action = intent.getAction();
            Log.e(TAG, "onReceive: -------> " + action);
            if (ACTION_BOOT.equals(action)) {
                // 先判断服务是否已经开启再启动
                // 这里放弃普通service启动后台任务 ， 使用job
                if (scheduler == null) return;
                if (!Util.isJobPollServiceOn(context, Util.JOB_ID)) {
                    PersistableBundle bundle = new PersistableBundle();
                    bundle.putInt(Util.FROM, 1);
                    JobInfo jobInfo = Util.createJob(context, bundle);
                    scheduler.schedule(jobInfo);
                }

            } else if (ACTION_ALARM.equals(action)) {

                NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
                noty(notificationManager, context, intent);

            }
        }
    }

    private void noty(NotificationManager notificationManager, Context context, Intent intent) {
        Notification notification = null;

        String today = intent.getStringExtra("today");
        String tomorrow = intent.getStringExtra("tomorrow");
//        String week = intent.getStringExtra("week");
        String dayForMonth = intent.getStringExtra("dayForMonth");
        String dis = intent.getStringExtra("dis");
        String jieqi = intent.getStringExtra("jieqi");

        StringBuilder contnet = new StringBuilder();

        contnet
//                .append(today).append(" ")
//                .append(tomorrow).append(" ")
//                .append(week).append(" ")
                .append(dayForMonth).append(" ")
                .append(dis).append(" ");
//                .append(jieqi).append(" ");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel("ch", "chname", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setSound(Uri.withAppendedPath(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, "2"), new AudioAttributes.Builder().build());

            notificationManager.createNotificationChannel(notificationChannel);
            notification = new Notification.Builder(context, notificationChannel.getId())
                    .setSmallIcon(R.mipmap.ic_alipay_card)
                    .setAutoCancel(true)
                    .setContentTitle("今日 " + today)
                    .setChannelId("ch")
                    //调用系统多媒体裤内的铃声(Uri.withAppendedPath(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, "2"), new AudioAttributes.Builder().build())
                    .setContentText(contnet).build();
        } else {
            notification = new Notification.Builder(context)
                    .setSmallIcon(R.mipmap.ic_alipay_card)
                    .setAutoCancel(true)
                    .setContentTitle("今日 " + jieqi + " " +today)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setContentText(contnet).build();
        }
        notification.contentIntent = PendingIntent.getActivity(context, 1, new Intent(context, MainActivity.class), 0);
        notificationManager.notify(123, notification);
    }

}

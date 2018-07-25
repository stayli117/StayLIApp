package com.stayli.app.listener;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.PersistableBundle;
import android.provider.CalendarContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;

import com.stayli.app.MainActivity;
import com.stayli.app.R;
import com.stayli.app.model.database.DBJieQi;
import com.stayli.app.model.database.DbManager;
import com.stayli.app.utils.DateUtils;
import com.stayli.app.utils.Util;

import java.util.List;

public class AlarmService extends JobService {

    private static final String TAG = "AlarmService";

    private static final String calanderURL = "content://com.android.calendar/calendars";
    private static final String calanderEventURL = "content://com.android.calendar/events";
    private static final String calanderRemiderURL = "content://com.android.calendar/reminders";

    public AlarmService() {
    }

    PendingIntent pendingIntent = null;

    @Override
    public boolean onStartJob(final JobParameters params) {
        // 此处为主线程 开始执行任务
        Log.e(TAG, "onStartJob: --------->");
        //设置8闹钟
        final Intent intent = new Intent(this, BootReceiver.class);
        intent.setAction("android.intent.action.ALARM");
        PersistableBundle extras = params.getExtras();
        Log.e(TAG, "onStartJob: --------->" + extras);
        if (extras.size() > 0) {
            final int from = extras.getInt(Util.FROM);
            Log.e(TAG, "onStartJob: --------->" + from);
            if (from == 0 || from == 1) { // splash界面启动 开始查询日历事件 // 启动广播

                String today = DateUtils.getDateString();
                String tomorrow = DateUtils.getTomorrow();
                String week = DateUtils.getWeekString();

                String dayForMonth = DateUtils.getDayForMonth();

                String dis = DateUtils.dayFromDate(1);

                Log.e(TAG, "onStartJob: " + today);
                Log.e(TAG, "onStartJob: " + tomorrow);
                Log.e(TAG, "onStartJob: " + week);
                Log.e(TAG, "onStartJob: " + dayForMonth);
                Log.e(TAG, "onStartJob: " + dis);

                intent.putExtra("today", today);
                intent.putExtra("tomorrow", tomorrow);
                intent.putExtra("week", week);
                intent.putExtra("dayForMonth", dayForMonth);
                intent.putExtra("dis", dis);

                // 查询节气
                final int year = DateUtils.getYear();
                LiveData<List<DBJieQi>> liveData = DbManager.getInstance().loadYear(String.valueOf(year));
                liveData.observeForever(new Observer<List<DBJieQi>>() {
                    @Override
                    public void onChanged(@Nullable List<DBJieQi> dbJieQis) {
                        Log.e(TAG, "onStartJob: " + dbJieQis);
                        if (dbJieQis != null && dbJieQis.size() > 0) {
                            String s = DateUtils.StringData();
                            Log.e(TAG, "onStartJob: " + s);
                            for (DBJieQi dbJieQi : dbJieQis) {
                                String jtime = dbJieQi.getJtime();
                                boolean contains = jtime.contains(s);
                                Log.e(TAG, "onStartJobJtime: " + jtime + " : " + contains);
                                if (contains) {
                                    intent.putExtra("jieqi", dbJieQi.getName());
                                    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                                    noty(notificationManager, AlarmService.this, intent);
                                } else {
                                    intent.putExtra("jieqi", "");
                                }
                                pendingIntent = PendingIntent.getBroadcast(AlarmService.this, from, intent, 0);


                                Util.set8Alarm(AlarmService.this, 8, pendingIntent);

                                getEvent();

                                // 任务完成需要调用 jobFinish 接口
                                //当为true 表示下次任务需要重新安排
                                jobFinished(params, false);

                            }
                        }
                    }
                });

            }

            if (from == 2) { // 通知服务
//                pendingIntent = PendingIntent.getBroadcast(AlarmService.this, from, intent, 0);

            }


        }


        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        // 服务结束时回调接口

        // 返回 true 表示 任务下次继续 false 表示不继续

        return true;
    }

    public void getEvent() {

        ContentResolver cr = getContentResolver();
        // 日历里面相应的Event的URI
        Uri uri = Uri.parse(calanderEventURL);
        Cursor cursor = cr.query(uri, null, null, null, null);
        while (cursor != null && cursor.moveToNext()) {

            String string = cursor.getString(cursor
                    .getColumnIndex(CalendarContract.Events.CALENDAR_ID));
            String string1 = cursor.getString(cursor.getColumnIndex(CalendarContract.Events.TITLE));
            String string2 = cursor.getString(cursor
                    .getColumnIndex(CalendarContract.Events.DTSTART));
            String string3 = cursor.getString(cursor.getColumnIndex(CalendarContract.Events.DTEND));
            String string4 = cursor.getString(cursor
                    .getColumnIndex(CalendarContract.Events.DURATION));
            String string5 = cursor.getString(cursor.getColumnIndex(CalendarContract.Events.RRULE));
            String string6 = cursor.getString(cursor
                    .getColumnIndex(CalendarContract.Events.EVENT_TIMEZONE));

            Log.e(TAG, "getEvent: " + string + "\n"
                    + string1 + "\n"
                    + string2 + "\n"
                    + string3 + "\n"
                    + string4 + "\n"
                    + string5 + "\n"
                    + string6);

        }

        if (cursor != null)
            cursor.close();
    }


    private void noty(NotificationManager notificationManager, Context context, Intent intent) {
        Notification notification = null;

        String today = intent.getStringExtra("today");
        String tomorrow = intent.getStringExtra("tomorrow");
        String week = intent.getStringExtra("week");
        String dayForMonth = intent.getStringExtra("dayForMonth");
        String dis = intent.getStringExtra("dis");
        String jieqi = intent.getStringExtra("jieqi");

        StringBuilder contnet = new StringBuilder();

        Log.e(TAG, "noty: " + contnet);
        contnet.append(today).append(" ")
                .append(tomorrow).append(" ")
                .append(week).append(" ")
                .append(dayForMonth).append(" ")
                .append(dis).append(" ")
                .append(jieqi).append(" ");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel("chs", "chnames", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setSound(Uri.withAppendedPath(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, "2"), new AudioAttributes.Builder().build());

            notificationManager.createNotificationChannel(notificationChannel);
            notification = new Notification.Builder(context, notificationChannel.getId())
                    .setSmallIcon(R.mipmap.ic_alipay_card)
                    .setAutoCancel(true)
                    .setContentTitle("今日")
                    .setChannelId("chs")
                    //调用系统多媒体裤内的铃声(Uri.withAppendedPath(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, "2"), new AudioAttributes.Builder().build())
                    .setContentText(contnet).build();
        } else {
            notification = new Notification.Builder(context)
                    .setSmallIcon(R.mipmap.ic_alipay_card)
                    .setAutoCancel(true)
                    .setContentTitle("今日")
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setContentText(contnet).build();
        }
        notification.contentIntent = PendingIntent.getActivity(context, 1, new Intent(context, MainActivity.class), 0);
        notificationManager.notify(1234, notification);
    }

}

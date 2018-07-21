package com.stayli.app.listener;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.huawei.hms.support.api.push.PushReceiver;

import java.io.UnsupportedEncodingException;

public class HuaweiPushRevicer extends PushReceiver {
    private static final String TAG = "HuaweiPushRevicer";

    @Override
    public void onToken(Context context, String token) {
        super.onToken(context, token);
        Log.e(TAG, "onToken: " + token);
    }

    @Override
    public void onPushState(Context context, boolean pushState) {
        super.onPushState(context, pushState);
        Log.e(TAG, "onToken: " + pushState);
    }

    @Override
    public void onPushMsg(Context context, byte[] msg, String token) {
        super.onPushMsg(context, msg, token);
        try {
            Log.e(TAG, "onPushMsg: " + new String(msg, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onEvent(Context context, Event event, Bundle extras) {

        Log.e(TAG, "onEvent: " + event);

        if (Event.NOTIFICATION_OPENED.equals(event) || Event.NOTIFICATION_CLICK_BTN.equals(event)) {
            int notifyId = extras.getInt(BOUND_KEY.pushNotifyId, 0);
            Log.i(TAG, "收到通知栏消息点击事件,notifyId:" + notifyId);
            if (0 != notifyId) {
                NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                manager.cancel(notifyId);
            }
        }

        String message = extras.getString(BOUND_KEY.pushMsgKey);

        super.onEvent(context, event, extras);
    }
}

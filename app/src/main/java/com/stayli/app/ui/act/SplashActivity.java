package com.stayli.app.ui.act;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.huawei.android.hms.agent.HMSAgent;
import com.huawei.android.hms.agent.common.handler.ConnectHandler;
import com.huawei.android.hms.agent.push.handler.GetPushStateHandler;
import com.huawei.android.hms.agent.push.handler.GetTokenHandler;
import com.stayli.app.MainActivity;
import com.stayli.app.R;
import com.stayli.app.listener.NLService;
import com.stayli.app.model.api.NetAPIManager;
import com.stayli.app.utils.CountDownTimerUtils;
import com.stayli.app.utils.DoubanUtils;
import com.stayli.app.utils.PermissionHelper;
import com.stayli.app.utils.PermissionInterface;
import com.stayli.app.utils.SPUtils;
import com.stayli.app.utils.Util;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashActivity extends AppCompatActivity implements PermissionInterface {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };
    private CountDownTimerUtils countDownTimer;

    private PermissionHelper mPermissionHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);
        //初始化并发起权限申请
        mPermissionHelper = new PermissionHelper(this, this);
        mPermissionHelper.requestPermissions();
        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content);

        // Set up the user interaction to manually show or hide the system UI.
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle();
            }
        });

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);

        FrameLayout mflBg = findViewById(R.id.fl_bg_splash);
        mflBg.setBackgroundResource(R.drawable.bitmap_splash_bg);
        String apiV2ApiKey = SPUtils.getInstance().getString("apiV2ApiKey");
        if (TextUtils.isEmpty(apiV2ApiKey)) {
            if (DoubanUtils.isInstalled(this)) {
                //获取豆瓣apiKey
                DoubanUtils.GetApiKeyAndSecretReturnValue apiKeyAndSecret =
                        DoubanUtils.getApiKeyAndSecret(this);
                Log.d(TAG, "initData: " + apiKeyAndSecret);
                apiV2ApiKey = apiKeyAndSecret.apiV2ApiKey;
                if (!TextUtils.isEmpty(apiV2ApiKey)) {
                    SPUtils.getInstance().put("apiV2ApiKey", apiV2ApiKey);
                }
            } else {
                DoubanUtils.installApp(this);
            }
        }
        Log.d(TAG, "initData: apiKey " + apiV2ApiKey);
        if (TextUtils.isEmpty(apiV2ApiKey)) {
            Toast.makeText(this, "豆瓣 apikey 获取失败", Toast.LENGTH_SHORT).show();
            return;
        }
        NetAPIManager.getInstance().setDoubanApiKey(apiV2ApiKey);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);


        countDownTimer = CountDownTimerUtils.getCountDownTimer();
        countDownTimer
                .setCountDownInterval(1000)
                .setMillisInFuture(3000)
                .setTickDelegate(new CountDownTimerUtils.TickDelegate() {
                    @Override
                    public void onTick(long pMillisUntilFinished) {
                        toggle();

                    }
                })
                .setFinishDelegate(new CountDownTimerUtils.FinishDelegate() {
                    @Override
                    public void onFinish() {
                        startAct();
                    }
                }).start();


        String listeners = Settings.Secure.getString(getContentResolver(), "enabled_notification_listeners");
        if (!listeners.contains(NLService.class.getName())) {
            gotoNotificationAccessSetting(this);
        }


        JobScheduler scheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        if (scheduler == null) return;


        if (!Util.isJobPollServiceOn(this, Util.JOB_ID)) {
            PersistableBundle bundle = new PersistableBundle();
            bundle.putInt(Util.FROM, 0);
            JobInfo jobInfo = Util.createJob(this, bundle);
            scheduler.schedule(jobInfo);
        }
        // 连接推送服务
        HMSAgent.connect(this, new ConnectHandler() {
            @Override
            public void onConnect(int rst) {
                if (rst == 0) {
                    showLog("推送连接成功");

                    // 连接成功获取token 并打开通道服务
                    getToken();
                } else {
                    showLog("推送连接失败");
                }

            }
        });

    }

    private static final String TAG = "SplashActivity";
    private static int count = 0;

    private void startAct() {
//        Log.e(TAG, "startAct: count " +count );
//        if (count != 1) {
//            count++;
//            return;
//        }
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean gotoNotificationAccessSetting(Context context) {
        try {
            Intent intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            return true;
        } catch (ActivityNotFoundException e) {
            try {
                Intent intent = new Intent();
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ComponentName cn = new ComponentName("com.android.settings", "com.android.settings.Settings$NotificationAccessSettingsActivity");
                intent.setComponent(cn);
                intent.putExtra(":settings:show_fragment", "NotificationAccessSettings");
                context.startActivity(intent);
                return true;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return false;
        }
    }


    /**
     * 获取token
     */
    private void getToken() {
        HMSAgent.Push.getToken(new GetTokenHandler() {
            @Override
            public void onResult(int rst) {
                if (rst == 0) {
                    showLog("Token get success");
                    // 获取
                    getPushStatus();
                } else {
                    showLog("Token get  fail" + rst);
                }

            }

        });
    }


    /**
     * 获取push状态 | Get Push State
     */
    private void getPushStatus() {
        showLog("getPushState:begin");
        HMSAgent.Push.getPushState(new GetPushStateHandler() {
            @Override
            public void onResult(int rst) {
                if (rst == 0) {
                    showLog("getPushState: success");
                } else {
                    showLog("getPushState: fail");
                }
            }
        });
    }

    private void showLog(String s) {
        Toast.makeText(this, "" + s, Toast.LENGTH_SHORT).show();
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
//        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in delay milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (mPermissionHelper.requestPermissionsResult(requestCode, permissions, grantResults)) {
            //权限请求结果，并已经处理了该回调
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public int getPermissionsRequestCode() {
        return 100000;
    }

    @Override
    public String[] getPermissions() {
        return new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.REQUEST_INSTALL_PACKAGES,
        };
    }

    @Override
    public void requestPermissionsSuccess() {
        //startAct();
    }

    @Override
    public void requestPermissionsFail() {
        finish();
    }
}

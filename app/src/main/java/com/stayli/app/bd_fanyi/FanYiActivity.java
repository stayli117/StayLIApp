package com.stayli.app.bd_fanyi;

import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.stayli.app.R;
import com.stayli.app.api.ApiObserverCallBack;
import com.stayli.app.api.BDFanYi;
import com.stayli.app.api.NetAPIManager;
import com.stayli.app.databinding.ActivityFanYiBinding;
import com.stayli.pb.PairCircleProgressBar;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class FanYiActivity extends AppCompatActivity {
    private static final String TAG = "FanYiActivity";
    protected ActivityFanYiBinding bind;

    private Map<String, String> srcMap = new LinkedHashMap<>();
    private BDFanYi mFanYi;
    private WindowManager mWM;
    private WindowManager.LayoutParams wl;
    private PairCircleProgressBar mPCbar;

    {

        srcMap.put("auto", "自动检测");
        srcMap.put("en", "英语");
        srcMap.put("zh", "中文");
        srcMap.put("cht", "繁体中文");
        srcMap.put("yue", "粤语");

        srcMap.put("wyw", "文言文");
        srcMap.put("jp", "日语");
        srcMap.put("kor", "韩语");
        srcMap.put("fra", "法语");
        srcMap.put("spa", "西班牙语");
        srcMap.put("th", "泰语");
        srcMap.put("ara", "阿拉伯语");
        srcMap.put("ru", "俄语");
        srcMap.put("pt", "葡萄牙语");
        srcMap.put("de", "德语");
        srcMap.put("it", "意大利语");
        srcMap.put("el", "希腊语");
        srcMap.put("nl", "荷兰语");
        srcMap.put("pl", "波兰语");
        srcMap.put("bul", "保加利亚语");
        srcMap.put("est", "爱沙尼亚语");
        srcMap.put("dan", "丹麦语");
        srcMap.put("fin", "芬兰语");
        srcMap.put("cs", "捷克语");
        srcMap.put("rom", "罗马尼亚语");
        srcMap.put("slo", "斯洛文尼亚语");
        srcMap.put("swe", "瑞典语");
        srcMap.put("hu", "匈牙利语");
        srcMap.put("vie", "越南语");
    }

    private int mSrcIndex = 0;
    private int mDestIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_fan_yi);
        mFanYi = NetAPIManager.getInstance().create(BDFanYi.class);


        final Resources resources = getResources();
        String[] yuyan = resources.getStringArray(R.array.Yuyan);
        String[] list = new String[yuyan.length - 1];
        System.arraycopy(yuyan, 1, list, 0, yuyan.length - 1);

        SpinnerAdapter adapter = new ArrayAdapter<>(this, R.layout.item_kd_com, yuyan);

        bind.spSrc.setAdapter(adapter);
        adapter = new ArrayAdapter<>(this, R.layout.item_kd_com, list);
        bind.spDest.setAdapter(adapter);

        bind.spSrc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSrcIndex = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        bind.spDest.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mDestIndex = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mWM = getWindow().getWindowManager();
        mPCbar = new PairCircleProgressBar(this);
        mPCbar.setBackgroundColor(Color.WHITE);
        wl = new WindowManager.LayoutParams();
        Point point = new Point();
        mWM.getDefaultDisplay().getRealSize(point);
        wl.height = point.y / 4;
        wl.width = point.x * 9 / 10;
        wl.alpha = 0.5f;
        wl.gravity = Gravity.CENTER;

        mPCbar.setLoadingViewListener(new PairCircleProgressBar.LoadingViewListener() {
            @Override
            public void animationFinish() {

            }
        });
        bind.btnFanyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWM.addView(mPCbar, wl);
                mPCbar.startAnimation();
                String s = bind.etSrc.getText().toString().trim();
                final String[] yuyanAlias = resources.getStringArray(R.array.Yuyan_alias);
                Log.e(TAG, "onClick: " + mSrcIndex + ":" + mDestIndex);
                String from = yuyanAlias[mSrcIndex];
                String to = yuyanAlias[mDestIndex + 1];
                Log.e(TAG, "onClick: " + from + ":" + to);
                fanyi(s, from, to);
            }
        });
    }

    private void fanyi(String needToTransString, String from, String to) {

        try {
            Map<String, String> map = buildParams(
                    new String(needToTransString.getBytes(), "utf-8"), from, to);
            Observable<ResponseBody> translate = mFanYi.translate(map);
            translate.
                    subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new ApiObserverCallBack<ResponseBody>() {
                        @Override
                        public void onSuccess(ResponseBody responseBody) {
                            try {
                                String string = responseBody.string();
                                Log.e(TAG, "onSuccess: " + string);
                                JsonElement p = new JsonParser().parse(string);
                                JsonObject object = p.getAsJsonObject();
                                String dst = object.get("trans_result").getAsJsonArray().get(0).getAsJsonObject().get("dst").getAsString();
                                bind.tvDest.setText(dst);
                                mPCbar.cancleAnimation();

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFinal() {
                            mPCbar.endAnimation();
                            mWM.removeView(mPCbar);
                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private Map<String, String> buildParams(String query, String from, String to) {
        Map<String, String> params = new HashMap<>();
        params.put("q", query);
        params.put("from", from);
        params.put("to", to);
        // 随机数
        String salt = String.valueOf(System.currentTimeMillis());
        params.put("salt", salt);

        // 签名
        String src = BDFanYi.APP_ID + query + salt + BDFanYi.SECRET_KEY; // 加密前的原文
        params.put("sign", MD5.md5(src));

        return params;
    }
}

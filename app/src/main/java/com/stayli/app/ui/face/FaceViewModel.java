package com.stayli.app.ui.face;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import com.google.gson.Gson;
import com.stayli.app.model.api.ApiObserverCallBack;
import com.stayli.app.model.api.ITencentFace;
import com.stayli.app.model.api.NetAPIManager;
import com.stayli.app.model.domain.FaceInfo;
import com.stayli.app.ui.bd_fanyi.MD5;
import com.stayli.app.utils.AessUtil;
import com.stayli.app.utils.Util;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by  yahuigao
 * Date: 2019/1/23
 * Time: 15:21
 * Description:
 */
public class FaceViewModel extends ViewModel {

    private static final String TAG = "FaceViewModel";
    private MutableLiveData<FaceInfo> mFaceInfo;
    private ITencentFace mITencentFace;
    @SuppressLint("StaticFieldLeak")
    private Context context;

    public FaceViewModel() {
        super();
    }

    public LiveData<FaceInfo> getFaceInfo() {
        if (mFaceInfo == null) {
            mITencentFace = NetAPIManager.getInstance().create(ITencentFace.class);
            mFaceInfo = new MutableLiveData<>();
            context = Util.getApplication();
        }
        return mFaceInfo;
    }

    public void loadFaceInfo(InputStream inputStream, final Observer<Bitmap> observer) {
        Observable.just(inputStream)
                .observeOn(Schedulers.io())
                .map(new Function<InputStream, String>() {
                    @Override
                    public String apply(InputStream inputStream) throws Exception {
                        byte[] bytes = AessUtil.getFromAssetsImage(inputStream);
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        observer.onChanged(bitmap);
                        byte[] bytes1 = AessUtil.compressImage(bitmap);
                        String faceData = Base64.encodeToString(bytes1, Base64.NO_WRAP);
                        if (TextUtils.isEmpty(faceData)) {
                            return null;
                        }
                        return faceData;
                    }
                }).map(new Function<String, Map<String, String>>() {
            @Override
            public Map<String, String> apply(String image) throws Exception {
                return buildParams(image);
            }
        }).concatMap(new Function<Map<String, String>, ObservableSource<ResponseBody>>() {
            @Override
            public Observable<ResponseBody> apply(Map<String, String> map) throws Exception {
                return mITencentFace.getFaceInfo(map);
            }
        }).map(new Function<ResponseBody, FaceInfo>() {
            @Override
            public FaceInfo apply(ResponseBody responseBody) throws Exception {
                Gson gson = new Gson();
                String string = responseBody.string();
                Log.e(TAG, "apply: " + string);
                return gson.fromJson(string, FaceInfo.class);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new ApiObserverCallBack<FaceInfo>() {
            @Override
            public void onSuccess(FaceInfo faceInfo) {
                mFaceInfo.setValue(faceInfo);
            }

            @Override
            public void onFinal() {

            }
        });
    }

    public void loadFaceInfo() {
        Observable.just("face.png")
                .observeOn(Schedulers.io())
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String fileName) throws Exception {
                        String faceData = AessUtil.getFromAssetsImage(context, fileName);
                        if (TextUtils.isEmpty(faceData)) {
                            return null;
                        }
                        return faceData;
                    }
                }).map(new Function<String, Map<String, String>>() {
            @Override
            public Map<String, String> apply(String image) throws Exception {
                return buildParams(image);
            }
        }).concatMap(new Function<Map<String, String>, ObservableSource<ResponseBody>>() {
            @Override
            public Observable<ResponseBody> apply(Map<String, String> map) throws Exception {
                return mITencentFace.getFaceInfo(map);
            }
        }).map(new Function<ResponseBody, FaceInfo>() {
            @Override
            public FaceInfo apply(ResponseBody responseBody) throws Exception {
                Gson gson = new Gson();
                String string = responseBody.string();
                Log.e(TAG, "apply: " + string);
                return gson.fromJson(string, FaceInfo.class);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new ApiObserverCallBack<FaceInfo>() {
            @Override
            public void onSuccess(FaceInfo faceInfo) {
                mFaceInfo.setValue(faceInfo);
            }

            @Override
            public void onFinal() {

            }
        });
    }

    @NonNull
    private Map<String, String> buildParams(String image) throws UnsupportedEncodingException {
        Map<String, String> map = new TreeMap<>();
        long l = System.currentTimeMillis();
        UUID uuid = UUID.randomUUID();
        String uuidS = uuid.toString().trim().replaceAll("-", "");
        map.put("app_id", ITencentFace.APP_ID);
        map.put("image", image);
        map.put("mode", "0");
        map.put("nonce_str", uuidS);
        map.put("time_stamp", TimeUnit.MILLISECONDS.toSeconds(l) + "");
        String sign = getReqSign(map);
        map.put("sign", sign);
        return map;
    }


    private String getReqSign(Map<String, String> resultMap) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        Set<Map.Entry<String, String>> entries = resultMap.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            String value = entry.getValue();
            String key = entry.getKey();
            String val = URLEncoder.encode(value + "", "utf-8");
            sb.append(key).append("=").append(val).append("&");
        }
        sb.append("app_key").append("=").append(ITencentFace.SECRET_KEY);
        return MD5.md5(sb.toString()).toUpperCase();
    }


    @Override
    protected void onCleared() {
        super.onCleared();
    }

    /**
     * 使用 Map按key进行排序
     *
     * @param map x
     * @return x
     */
    public Map<String, String> sortMapByKey(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }

        Map<String, String> sortMap = new TreeMap<>(
                new MapKeyComparator());

        sortMap.putAll(map);

        return sortMap;
    }

    class MapKeyComparator implements Comparator<String> {

        @Override
        public int compare(String str1, String str2) {

            return str1.compareTo(str2);
        }
    }
}

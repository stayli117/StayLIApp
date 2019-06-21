package com.stayli.app.model.api;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stayli.app.anno.BaseUrl;

import java.io.IOException;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ConnectionPool;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yhgao on 2018/2/9.
 */

public class NetAPIManager {


    private Gson gson;
    private OkHttpClient client;
    private String mDoubanApiKey;


    private static interface Signal {
        NetAPIManager MANAGER = new NetAPIManager();
    }

    private NetAPIManager() {
    }

    public static NetAPIManager getInstance() {
        return Signal.MANAGER;
    }

    private Retrofit mRetrofit;

    public void init() {
        //配置你的Gson
        gson = new GsonBuilder()
                //配置你的Gson
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NonNull String message) {
                Log.i("NetAPIManager", "message -> " + message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        ConnectionPool pool = new ConnectionPool(5, 30, TimeUnit.SECONDS);
        client = new OkHttpClient.Builder()
                .connectionPool(pool)
                .addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(new Interceptor() {
                    @NonNull
                    @Override
                    public Response intercept(@NonNull Chain chain) throws IOException {
                        Request request = chain.request();
                        HttpUrl url = request.url();
                        String host = url.host();
                        boolean equals = DBInterface.HOST.equals(host);
                        if (equals) {
                            url = url.newBuilder()
                                    .addQueryParameter("apiKey", getDoubanApiKey())
                                    .build();
                            request = request.newBuilder().url(url).build();
                        }
                        return chain.proceed(request);
                    }
                })
                .build();

        mRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("http://www.baidu.com")
                .client(client)
                .build();
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }

    public Retrofit getRetrofit(String baseUrl) {
        return mRetrofit.newBuilder().addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseUrl)
                .client(client)
                .build();
    }

    private final Map<Class, Proxy> serviceCache = new ConcurrentHashMap<>();

    public <T> T create(Class<T> clazz) {
        if (!clazz.isInterface()) {
            throw new IllegalArgumentException("API declarations must be interfaces.");
        }
        BaseUrl annotation = clazz.getAnnotation(BaseUrl.class);
        if (annotation == null) {
            throw new IllegalArgumentException("No BaseUrl annotation exists");
        }
        String baseUrl = annotation.value();
        if (TextUtils.isEmpty(baseUrl)) {
            throw new IllegalArgumentException("BaseUrl is null");
        }


        Proxy result = serviceCache.get(clazz);
        if (result != null) return (T) result;
        synchronized (serviceCache) {
            result = serviceCache.get(clazz);
            if (result == null) {
                Retrofit retrofit = mRetrofit.newBuilder()
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .baseUrl(baseUrl)
                        .client(client)
                        .build();
                result = (Proxy) retrofit.create(clazz);
                serviceCache.put(clazz, result);
            }
        }
//        CloudFragment t = getT(CloudFragment.class);

        return (T) result;
    }

    public void setDoubanApiKey(String apiKey) {
        mDoubanApiKey = apiKey;
    }

    public String getDoubanApiKey() {
        if (TextUtils.isEmpty(mDoubanApiKey)) {
            mDoubanApiKey = DBInterface.API_KEY;
        }
        return mDoubanApiKey;
    }
    //    public static <K extends T,T extends BaseFragment> K getT(Class<K> clazz){
//
//        T t = (T) new BaseFragment();
//
//        K k = null;
//        String name =clazz.getSimpleName();
//        if ("MusicFragment".equals(name)){
//            k= (K) new MusicFragment();
//        }
//
//        Bundle args = new Bundle();
//
//        t.setArguments(args);
//        return k;
//
//    }

    /**
     * 插入观察者-泛型
     *
     * @param observable
     * @param observer
     * @param <T>
     */
    public static <T> void setSubscribe(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}

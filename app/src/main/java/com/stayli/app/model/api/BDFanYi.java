package com.stayli.app.model.api;

import com.stayli.app.anno.BaseUrl;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * 百度翻译
 */
@BaseUrl("http://api.fanyi.baidu.com")
public interface BDFanYi {

    //申请者开发者id，实际使用时请修改成开发者自己的appid
    String APP_ID = "20180522000163944";
    //申请成功后的证书token，实际使用时请修改成开发者自己的token (密钥)
    String SECRET_KEY = "Ps24do0M7gPjNStSccd2";




    // post get 都可以
    @GET("/api/trans/vip/translate?appid=" + APP_ID)
    Observable<ResponseBody> translate(@Query(value = "q") String q,
                                       @Query(value = "from") String from,
                                       @Query(value = "to") String to,
                                       @Query(value = "sign") String sign
    );

    @GET("/api/trans/vip/translate?appid=" + APP_ID)
    Observable<ResponseBody> translate(@QueryMap() Map<String, String> map);


}

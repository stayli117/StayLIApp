package com.stayli.app.model.api;

import com.stayli.app.anno.BaseUrl;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by  yahuigao
 * Date: 2019/1/23
 * Time: 15:00
 * Description:
 * 腾讯人脸识别
 */
@BaseUrl("https://api.ai.qq.com")
public interface ITencentFace {

    //申请者开发者id，实际使用时请修改成开发者自己的appid
    String APP_ID = "2111683672";
    //申请成功后的证书token，实际使用时请修改成开发者自己的token (密钥)
    String SECRET_KEY = "PJc0rlPzsvA8asiq";

    @FormUrlEncoded
    @POST("/fcgi-bin/face/face_detectface")
    Observable<ResponseBody> getFaceInfo(@FieldMap Map<String, String> map);
}

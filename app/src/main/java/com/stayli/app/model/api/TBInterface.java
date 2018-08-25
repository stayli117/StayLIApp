package com.stayli.app.model.api;

import com.stayli.app.model.domain.TbProject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by yhgao on 2018/2/9.
 */

public interface TBInterface {

     static final String URL = "https://suggest.taobao.com/sug?code=utf-8&q=卫衣";
     static final String BASE_URL = "https://suggest.taobao.com/";
    @GET("sug")
    Call<TbProject> query(@Query("code") String code, @Query("q") String q);
}

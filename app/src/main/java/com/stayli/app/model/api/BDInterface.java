package com.stayli.app.model.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by yhgao on 2018/2/9.
 */

public interface BDInterface {

    @GET("ak/{ak}")
    Call<ResponseBody> getBlog(@Path("ak") int id);
}

package com.stayli.app.model.api;

import com.stayli.app.anno.BaseUrl;
import com.stayli.app.model.domain.JieQi;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

@BaseUrl("http://api.jisuapi.com")
public interface LunarJIeQi {


    @GET("jieqi/query?appkey=a49db10a36bd75f8")
    Observable<JieQi> getJieQi(@Query(value = "year") String year);

    @GET("jieqi/detail?appkey=a49db10a36bd75f8")
    Call<ResponseBody> getJieQi(@Query(value = "jieqiid")String jieqiid, @Query(value = "year")String year);

}

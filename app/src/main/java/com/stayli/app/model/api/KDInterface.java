package com.stayli.app.model.api;

import com.stayli.app.anno.BaseUrl;
import com.stayli.app.model.domain.KDBean;
import com.stayli.app.model.domain.KDInfoBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by yhgao on 2018/3/11.
 * 快递
 */
@BaseUrl("http://v.juhe.cn")
public interface KDInterface {


    @GET("/exp/com")
    Observable<KDBean> getKDNO(@Query(value = "key") String appkey);

    //com=yzgn&no=NF29863920144&dtype=&key=5e301e043c702575280f5443c71fd5e5
    @GET("/exp/index")
    Observable<KDInfoBean> getKDInfo(@Query(value = "key") String appkey,
                                     @Query(value = "com") String com,
                                     @Query(value = "no") String no,
                                     @Query(value = "type") String type
    );

    @GET("/exp/index?com=yzgn&no=NF29863920144&dtype=&key=5e301e043c702575280f5443c71fd5e5")
    Observable<KDInfoBean> getKDInfo();


}

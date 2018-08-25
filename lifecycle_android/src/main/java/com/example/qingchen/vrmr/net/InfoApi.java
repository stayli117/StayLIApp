package com.example.qingchen.vrmr.net;

import com.example.qingchen.vrmr.bean.InfoBean;

import retrofit2.http.GET;
import rx.Observable;

/**
 * @author qingchen
 * @date 17-11-10
 */

public interface InfoApi {
    /**
     * 获取信息
     *
     * @return 创业新闻
     */
    @GET("/startup/?key=f5eda3e22aad14bf2cb880fc9845c3f0&num=30")
    Observable<InfoBean> gettartupInfo();

    @GET("/tiyu/?key=f5eda3e22aad14bf2cb880fc9845c3f0&num=30")
    Observable<InfoBean> getInfo();


}

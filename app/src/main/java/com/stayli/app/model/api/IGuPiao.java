package com.stayli.app.model.api;

import com.stayli.app.anno.BaseUrl;
import com.stayli.app.model.domain.GPZhiShu;
import com.stayli.app.model.domain.GuPiao;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

@BaseUrl("http://web.juhe.cn:8080")
public interface IGuPiao {

    /**
     * 沪深股市 指数
     *
     * @param type 0代表上证指数，1代表深证指数
     * @return
     */
    @GET("/finance/stock/hs?key=e4058cdd0c2a9fa7cc98a8636543f975")
    Observable<GPZhiShu> getGuPiao(@Query(value = "type")String type);

    /**
     * 沪深股市 股票 查询
     *
     * @param type 0代表上证指数，1代表深证指数
     * @param gid  股票编号，上海股市以sh开头，深圳股市以sz开头如：sh601009（type为0或者1时gid不是必须）
     * @return
     */
    @GET("/finance/stock/hs?key=e4058cdd0c2a9fa7cc98a8636543f975")
    Observable<GuPiao> getGuPiao(@Query(value = "type")String type, @Query(value = "gid")String gid);


}

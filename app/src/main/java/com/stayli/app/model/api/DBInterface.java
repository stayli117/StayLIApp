package com.stayli.app.model.api;

import com.stayli.app.anno.BaseUrl;
import com.stayli.app.model.domain.DBBook;
import com.stayli.app.model.domain.DBBookCollection;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by yhgao on 2018/2/9.
 */

@BaseUrl("https://api.douban.com/")
public interface DBInterface {
    static final String URL = "https://api.douban.com/v2/book/1220562";
    static final String BASE_URL = "https://api.douban.com/";
    static final String BASE_URL_BOOK_Con = "https://api.douban.com/v2/book/user/jinger/collections";

    @GET("v2/book/{bookId}")
    Call<DBBook> getBook(@Path("bookId") String bookId);

    @GET("{bookId}")
    Call<DBBook> getTestBook(@Path("bookId") String bookId);


    @GET("/v2/book/user/{userName}/collections")
    Call<DBBookCollection> getUserBookCollections(@Path("userName") String userName);

    //默认是20条
    @GET("/v2/book/user/{userName}/collections")
    Observable<DBBookCollection> getUser2BookCollections(@Path("userName") String userName);

    // 指定参数刷新
    @GET("/v2/book/user/{userName}/collections")
    Observable<DBBookCollection> getUser2BookCollections(@Path("userName") String userName,
                                                         @Query(value = "start") String start,
                                                         @Query(value = "count") String count);

    // 指定参数刷新
    @GET("/v2/book/user/{userName}/collections")
    Observable<DBBookCollection> getUser2BookCollections(@Path("userName") String userName, @QueryMap Map<String, String> query);


    //    GET  https://api.douban.com/v2/book/:id/annotations
    @GET("v2/book/{bookId}/annotations")
    Call<ResponseBody> getBookAnnotations(@Path("bookId") String bookId);

    @FormUrlEncoded
    @PUT("v2/book/{bookId}/collection")
    Call<ResponseBody> putBookCollection(@Path("bookId") String bookId,
                                         @Field("status") String status,
                                         @Field("tags") String tags,
                                         @Field("comment") String comment,
                                         @Field("privacy") String privacy,
                                         @Field("rating") String rating
    );

    @FormUrlEncoded
    @PUT("v2/book/{bookId}/collection")
    Call<ResponseBody> putBookCollection(@Path("bookId") String bookId,
                                         @Field("status") String status
    );


}

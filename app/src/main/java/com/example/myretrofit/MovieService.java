package com.example.myretrofit;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

//可以封装不同的接口
public interface MovieService {
    //文件下载
    @Streaming//注解大文件
    @GET
    Observable<movieTopReq> retrofitDownloadFile(@Url String fileUrl);

    @GET("index")
    Observable<movieTopReq> getMovicTop(@Query("cityname") String cityname, @Query("dtype") String dtype,@Query("format") int format,@Query("key") String key);
}

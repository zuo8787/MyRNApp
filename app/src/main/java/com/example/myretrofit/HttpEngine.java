package com.example.myretrofit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HttpEngine {

    private static MovieService movieService = RetrofitServiceManager.getInstance().create(MovieService.class);

    /*
     * 获取天气预报
     * **/
    public static void getDuoBanTop(String cityname, String dtype,int format,String key,Observer<movieTopReq> observer) {
        setSubscribe(movieService.getMovicTop(cityname, dtype,format,key), observer);

    }

    private static <T> void setSubscribe(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                .subscribe(observer);
    }
}
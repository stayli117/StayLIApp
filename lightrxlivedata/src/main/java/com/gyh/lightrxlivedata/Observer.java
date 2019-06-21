package com.gyh.lightrxlivedata;

/**
 * Created by  yahuigao
 * Date: 2019-05-11
 * Time: 17:28
 * Description:
 */
public interface Observer<T> {
    void onCompleted(T t);

    void onError(Throwable e);
}

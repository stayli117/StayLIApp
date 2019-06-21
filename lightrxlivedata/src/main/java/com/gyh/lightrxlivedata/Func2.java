package com.gyh.lightrxlivedata;



/**
 * Created by  yahuigao
 * Date: 2019-05-11
 * Time: 17:25
 * Description:
 */
public interface Func2<T, R> {
    R call(T t) throws InterruptException;
}

package com.stayli.app.model.api;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by yhgao on 2018/2/10.
 */

public abstract class ApiObserverCallBack<T> implements Observer<T> {


    @Override
    public void onSubscribe(Disposable d) {
        //发生异常时，作为成员变量可解除订阅
    }

    @Override
    public void onNext(T o) {
        onSuccess(o);
    }

    @Override
    public void onError(Throwable e) {
        onFailure(e);
    }

    @Override
    public void onComplete() {
        // 所以订阅操作完成
        onFinal();
    }

    public abstract void onSuccess(T t);

    public abstract void onFinal();

    public void onFailure(Throwable t) {
        t.printStackTrace();
    }
}

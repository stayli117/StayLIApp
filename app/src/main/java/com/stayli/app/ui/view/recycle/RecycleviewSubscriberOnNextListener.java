package com.stayli.app.ui.view.recycle;

/**
 * Created by liukun on 16/3/10.
 */
public interface RecycleviewSubscriberOnNextListener<T> {
    void onNext(T t);

    void onErr(int drawable, String msg);
}

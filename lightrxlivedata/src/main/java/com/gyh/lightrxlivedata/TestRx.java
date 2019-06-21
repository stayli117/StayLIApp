package com.gyh.lightrxlivedata;

/**
 * Created by  yahuigao
 * Date: 2019-05-11
 * Time: 17:34
 * Description:
 */
public class TestRx {


    public void test() {
        Observable.create(new Func1<String>() {
            @Override
            public String call() {
                return null;
            }
        }).subscribeOn(AndroidHandlers.ioThreadHandler())
                .map(new Func2<String, Integer>() {
                    @Override
                    public Integer call(String s) throws InterruptException {
                        return null;
                    }
                }).map(new Func2<Integer, String>() {
            @Override
            public String call(Integer integer) throws InterruptException {
                return null;
            }
        }).observeOn(AndroidHandlers.mainThreadHandler())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted(String s) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
}

package com.stayli.app.ui.fragment.music.gupiao.Repo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.stayli.app.model.api.ApiObserverCallBack;
import com.stayli.app.model.api.IGuPiao;
import com.stayli.app.model.api.NetAPIManager;
import com.stayli.app.model.domain.GPZhiShu;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class GuPiaoRepo {


    private final IGuPiao mGuPiao;

    private final MutableLiveData<String> mRepoLiveData;

    public GuPiaoRepo() {
        mGuPiao = NetAPIManager.getInstance().create(IGuPiao.class);
        mRepoLiveData = new MutableLiveData<>();
    }


    public LiveData<String> getPostalCode(String input) {
        Observable<GPZhiShu> guPiao = mGuPiao.getGuPiao(0);
        guPiao.map(new Function<GPZhiShu, String>() {
            @Override
            public String apply(GPZhiShu gpZhiShu) throws Exception {
                return gpZhiShu.reason;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new ApiObserverCallBack<String>() {
            @Override
            public void onSuccess(String s) {

            }

            @Override
            public void onFinal() {

            }
        });


        return mRepoLiveData;
    }
}

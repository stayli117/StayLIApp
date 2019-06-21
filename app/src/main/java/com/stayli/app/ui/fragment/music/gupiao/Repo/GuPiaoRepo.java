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

    private final MutableLiveData<GPZhiShu> mRepoLiveData;

    public GuPiaoRepo() {
        mGuPiao = NetAPIManager.getInstance().create(IGuPiao.class);
        mRepoLiveData = new MutableLiveData<>();
    }


    public LiveData<GPZhiShu> getPostalCode(final String input) {
        Observable<GPZhiShu> guPiao = mGuPiao.getGuPiao(input);
        guPiao.map(new Function<GPZhiShu, GPZhiShu>() {
            @Override
            public GPZhiShu apply(GPZhiShu gpZhiShu) throws Exception {
                gpZhiShu.type = input;
                return gpZhiShu;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new ApiObserverCallBack<GPZhiShu>() {
            @Override
            public void onSuccess(GPZhiShu s) {
                mRepoLiveData.setValue(s);
            }

            @Override
            public void onFinal() {

            }
        });


        return mRepoLiveData;
    }
}

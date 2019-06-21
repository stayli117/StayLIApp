package com.stayli.app.ui.fragment.music.gupiao.vm;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.stayli.app.model.domain.GPZhiShu;
import com.stayli.app.ui.fragment.music.gupiao.Repo.GuPiaoRepo;

public class GuPiaoViewM extends ViewModel {


    private GuPiaoRepo repository;
    private final MutableLiveData<String> addressInput = new MutableLiveData<>();
    private final LiveData<GPZhiShu> postalCode =
            Transformations.switchMap(addressInput, new Function<String, LiveData<GPZhiShu>>() {
                @Override
                public LiveData<GPZhiShu> apply(String input) {
                    return repository.getPostalCode(input);
                }
            });


    public GuPiaoViewM() {
        repository = new GuPiaoRepo();

    }

    // 股票 指数监听
    public LiveData<GPZhiShu> getPostalCode() {
        return postalCode;
    }

    //  0 上证指数 1 沪深指数
    public void setInput(String type) {
        addressInput.setValue(type);
    }


}

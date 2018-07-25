package com.stayli.app.ui.fragment.music.gupiao.vm;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.stayli.app.ui.fragment.music.gupiao.Repo.GuPiaoRepo;

public class GuPiaoViewM extends ViewModel {


    private GuPiaoRepo repository;
    private final MutableLiveData<String> addressInput = new MutableLiveData<>();
    private final LiveData<String> postalCode =
            Transformations.switchMap(addressInput, new Function<String, LiveData<String>>() {
                @Override
                public LiveData<String> apply(String input) {

                    return repository.getPostalCode(input);
                }
            });


    public GuPiaoViewM() {
        repository = new GuPiaoRepo();

    }

    private LiveData<String> setInput(String address) {
        addressInput.setValue(address);
        return postalCode;
    }


}

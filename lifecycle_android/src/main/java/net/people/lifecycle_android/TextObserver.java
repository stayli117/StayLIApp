package net.people.lifecycle_android;

import android.arch.lifecycle.GenericLifecycleObserver;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;

public class TextObserver implements GenericLifecycleObserver {

    private boolean enabled;

    @Override
    public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
        switch (event) {
            case ON_CREATE:

                break;
        }
    }


}

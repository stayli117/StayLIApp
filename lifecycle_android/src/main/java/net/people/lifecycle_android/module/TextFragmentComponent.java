package net.people.lifecycle_android.module;

import net.people.lifecycle_android.TextFragment;

import dagger.Component;

@Component(modules = CityModule.class)
public interface TextFragmentComponent {
    void inject(TextFragment textFragment);
}

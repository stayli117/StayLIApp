package net.people.lifecycle_android.module;

import com.example.qingchen.vrmr.MyApplication;

import net.people.lifecycle_android.database.CityDao;
import net.people.lifecycle_android.database.CityDao_Impl;
import net.people.lifecycle_android.database.CityDataBase;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class CityModule {
    @Provides
    @Named("CityDao")
    CityDao provideCityDao() {
        return new CityDao_Impl(CityDataBase.getInstance(MyApplication.getContext()));
    }
}

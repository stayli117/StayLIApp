package net.people.lifecycle_android.repo;

import android.arch.lifecycle.LiveData;

import com.example.qingchen.vrmr.bean.InfoBean;
import com.example.qingchen.vrmr.net.NetWork;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.people.lifecycle_android.database.CityDao;
import net.people.lifecycle_android.database.DbCityBean;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observer;
import rx.schedulers.Schedulers;

public class CityRepo {
    public CityDao infoDao;

    @Inject
    public CityRepo(@Named("CityDao") CityDao infoDao) {
        this.infoDao = infoDao;
        //        updateInfo();
    }

    public LiveData<List<DbCityBean>> getInfo() {
        return infoDao.getDescAll();
    }


    public LiveData<DbCityBean> queryNewByTime() {
        return infoDao.queryNewByTime();
    }

    public LiveData<List<DbCityBean>> queryAllByTime() {
        return infoDao.queryByTime();
    }


    public void updateInfo() {
        NetWork.getInfo().getInfo().subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new Observer<InfoBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(InfoBean infoBean) {
                //不能在主线程使用
                Gson gson = new Gson();
                String temp = gson.toJson(infoBean.getNewslist());
                List<DbCityBean> list = gson.fromJson(temp, new TypeToken<List<DbCityBean>>() {
                }.getType());
                infoDao.insertAll(list);
            }
        });
    }




}

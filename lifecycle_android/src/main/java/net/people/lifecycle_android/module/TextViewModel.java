package net.people.lifecycle_android.module;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import net.people.lifecycle_android.DateUtils;
import net.people.lifecycle_android.bean.CityBean;
import net.people.lifecycle_android.database.DbCityBean;
import net.people.lifecycle_android.repo.CityRepo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class TextViewModel extends ViewModel {


    private int count = 0;

    private CityRepo mCityRepo;

    @Inject
    public TextViewModel(CityRepo cityRepo) {
        this.mCityRepo = cityRepo;
    }

    public LiveData<List<DbCityBean>> getLiveData() {
        return mCityRepo.getInfo();
    }

    public LiveData<DbCityBean> getLiveDataNewByTime() {
        return mCityRepo.queryNewByTime();
    }


    public LiveData<List<DbCityBean>> getLiveDataAllByTime() {
        return mCityRepo.queryAllByTime();
    }

    List<CityBean.CityInfoBean> list = new ArrayList<>();
    Gson gson = new Gson();

    public void start() {
        count = 0;
        Log.e("abc", "start: ------------->");
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (count <= 10) {
                    try {
                        //不能在主线程使用
                        DbCityBean cityBean = new DbCityBean();

                        String date = DateUtils.getStringDate();
                        cityBean.setCtime(date);
                        cityBean.setDescription("abc");
                        cityBean.setPicUrl("http://www.baidu.com" + count);
                        cityBean.setTitle("当前时间: " + date);
                        cityBean.setUrl("http://www.baidu.com");
                        cityBean.setCity_name("北京");
                        cityBean.setLast_update("" + count * 100);
                        cityBean.setNews_id(count);
                        cityBean.setNews_con("今天是个好日子");
                        cityBean.setNews_cont("shide");
                        mCityRepo.infoDao.insert(cityBean);

                        count++;
                        Log.e("abc", "start: ------------->" + count);
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
//                String temp = gson.toJson(list);
//                List<DbCityBean> list = gson.fromJson(temp, new TypeToken<List<DbCityBean>>() {
//                }.getType());
//
//                infoDao.insertAll(list);
            }
        }).start();


    }

    class TextLiveData<T> extends AbstractLiveData<T> {

        @Override
        public void first(final T s) {
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    postValue(s);
                }
            });
        }
    }


    abstract class AbstractLiveData<T> extends LiveData<T> {


        public abstract void first(T t);


    }


}

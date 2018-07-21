package com.stayli.app.databsae;

import android.arch.lifecycle.LiveData;

import com.stayli.app.utils.Util;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DbManager {


    private final JieQIDao jieQiDao;




    private static interface HOLER {
        DbManager MANAGER = new DbManager();
    }

    private DbManager() {
        jieQiDao = createJieQiDao();
    }


    public static DbManager getInstance() {
        return HOLER.MANAGER;
    }

    private final Map<Class, Cloneable> serviceCache = new ConcurrentHashMap<>();

    private JieQIDao createJieQiDao() {
        Cloneable result = serviceCache.get(JieQIDao.class);
        if (result != null) return (JieQIDao) result;
        synchronized (serviceCache) {
            result = serviceCache.get(JieQIDao.class);
            if (result == null) {
                result = new JieQIDao_Impl(JieQiDataBase.getInstance(Util.getApplication()));
                serviceCache.put(JieQIDao.class, result);
            }
        }
        return (JieQIDao) result;
    }

    public LiveData<List<DBJieQi>> getInfo() {

        return jieQiDao.getDescAll();
    }


    public LiveData<DBJieQi> queryNewByTime() {
        return jieQiDao.queryNewByTime();
    }

    public LiveData<List<DBJieQi>> queryAllByTime() {
        return jieQiDao.queryByTime();
    }


    public LiveData<List<DBJieQi>> loadYear(String year) {
        return jieQiDao.loadYear(year);
    }

    public void insertJieQi(List<DBJieQi> jieQi) {

        jieQiDao.insertAll(jieQi);

    }
}

package com.stayli.app.databsae;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface JieQIDao extends Cloneable {

    @Query("SELECT * FROM dbjieqi")
    LiveData<List<DBJieQi>> getAll();

    // 时间 倒序
    @Query("SELECT * FROM dbjieqi ORDER By ctime DESC")
    LiveData<List<DBJieQi>> getDescAll();

    // 时间正序
    @Query("SELECT * FROM dbjieqi ORDER By ctime")
    LiveData<List<DBJieQi>> queryByTime();


    @Insert(onConflict = REPLACE)
    void save(DBJieQi info);

    @Query("SELECT * FROM dbjieqi WHERE ctime = :ctime")
    LiveData<DBJieQi> load(String ctime);

    @Query("SELECT * FROM dbjieqi WHERE year = :year")
    LiveData<List<DBJieQi>> loadYear(String year);


    // 查询最新的一条数据
    @Query("SELECT * FROM dbjieqi ORDER By ctime DESC LIMIT 1,1")
    LiveData<DBJieQi> queryNewByTime();


    @Insert
    void insertAll(List<DBJieQi> list);

    @Insert
    void insert(DBJieQi bean);

    @Delete
    void delete(DBJieQi list);
}

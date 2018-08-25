package net.people.lifecycle_android.database;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface CityDao {

    @Query("SELECT * FROM dbcitybean")
    LiveData<List<DbCityBean>> getAll();

    // 时间 倒序
    @Query("SELECT * FROM dbcitybean ORDER By ctime DESC")
    LiveData<List<DbCityBean>> getDescAll();

    // 时间正序
    @Query("SELECT * FROM dbcitybean ORDER By ctime")
    LiveData<List<DbCityBean>> queryByTime();

    @Insert(onConflict = REPLACE)
    void save(DbCityBean info);

    @Query("SELECT * FROM dbcitybean WHERE ctime = :ctime")
    LiveData<DbCityBean> load(String ctime);


    // 查询最新的一条数据
    @Query("SELECT * FROM dbcitybean ORDER By ctime DESC LIMIT 1,1")
    LiveData<DbCityBean> queryNewByTime();


    @Insert
    void insertAll(List<DbCityBean> list);

    @Insert
    void insert(DbCityBean bean);

    @Delete
    void delete(DbCityBean list);
}

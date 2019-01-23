package net.people.lifecycle_android.database;

import android.arch.lifecycle.ComputableLiveData;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.InvalidationTracker.Observer;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import android.support.annotation.NonNull;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unchecked")
public class CityDao_Impl implements CityDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfDbCityBean;

  private final EntityInsertionAdapter __insertionAdapterOfDbCityBean_1;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfDbCityBean;

  public CityDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDbCityBean = new EntityInsertionAdapter<DbCityBean>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `DbCityBean`(`ctime`,`title`,`description`,`picUrl`,`url`,`city_name`,`last_update`,`news_id`,`news_con`,`news_cont`) VALUES (?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DbCityBean value) {
        if (value.getCtime() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getCtime());
        }
        if (value.getTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitle());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDescription());
        }
        if (value.getPicUrl() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getPicUrl());
        }
        if (value.getUrl() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getUrl());
        }
        if (value.getCity_name() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getCity_name());
        }
        if (value.getLast_update() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getLast_update());
        }
        if (value.getNews_id() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindLong(8, value.getNews_id());
        }
        if (value.getNews_con() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getNews_con());
        }
        if (value.getNews_cont() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getNews_cont());
        }
      }
    };
    this.__insertionAdapterOfDbCityBean_1 = new EntityInsertionAdapter<DbCityBean>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `DbCityBean`(`ctime`,`title`,`description`,`picUrl`,`url`,`city_name`,`last_update`,`news_id`,`news_con`,`news_cont`) VALUES (?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DbCityBean value) {
        if (value.getCtime() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getCtime());
        }
        if (value.getTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitle());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDescription());
        }
        if (value.getPicUrl() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getPicUrl());
        }
        if (value.getUrl() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getUrl());
        }
        if (value.getCity_name() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getCity_name());
        }
        if (value.getLast_update() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getLast_update());
        }
        if (value.getNews_id() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindLong(8, value.getNews_id());
        }
        if (value.getNews_con() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getNews_con());
        }
        if (value.getNews_cont() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getNews_cont());
        }
      }
    };
    this.__deletionAdapterOfDbCityBean = new EntityDeletionOrUpdateAdapter<DbCityBean>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `DbCityBean` WHERE `ctime` = ? AND `picUrl` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DbCityBean value) {
        if (value.getCtime() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getCtime());
        }
        if (value.getPicUrl() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getPicUrl());
        }
      }
    };
  }

  @Override
  public void save(DbCityBean info) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfDbCityBean.insert(info);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAll(List<DbCityBean> list) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfDbCityBean_1.insert(list);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insert(DbCityBean bean) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfDbCityBean_1.insert(bean);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(DbCityBean list) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfDbCityBean.handle(list);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<DbCityBean>> getAll() {
    final String _sql = "SELECT * FROM dbcitybean";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new ComputableLiveData<List<DbCityBean>>() {
      private Observer _observer;

      @Override
      protected List<DbCityBean> compute() {
        if (_observer == null) {
          _observer = new Observer("dbcitybean") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfCtime = _cursor.getColumnIndexOrThrow("ctime");
          final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
          final int _cursorIndexOfDescription = _cursor.getColumnIndexOrThrow("description");
          final int _cursorIndexOfPicUrl = _cursor.getColumnIndexOrThrow("picUrl");
          final int _cursorIndexOfUrl = _cursor.getColumnIndexOrThrow("url");
          final int _cursorIndexOfCityName = _cursor.getColumnIndexOrThrow("city_name");
          final int _cursorIndexOfLastUpdate = _cursor.getColumnIndexOrThrow("last_update");
          final int _cursorIndexOfNewsId = _cursor.getColumnIndexOrThrow("news_id");
          final int _cursorIndexOfNewsCon = _cursor.getColumnIndexOrThrow("news_con");
          final int _cursorIndexOfNewsCont = _cursor.getColumnIndexOrThrow("news_cont");
          final List<DbCityBean> _result = new ArrayList<DbCityBean>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final DbCityBean _item;
            _item = new DbCityBean();
            final String _tmpCtime;
            _tmpCtime = _cursor.getString(_cursorIndexOfCtime);
            _item.setCtime(_tmpCtime);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            _item.setTitle(_tmpTitle);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            _item.setDescription(_tmpDescription);
            final String _tmpPicUrl;
            _tmpPicUrl = _cursor.getString(_cursorIndexOfPicUrl);
            _item.setPicUrl(_tmpPicUrl);
            final String _tmpUrl;
            _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            _item.setUrl(_tmpUrl);
            final String _tmpCity_name;
            _tmpCity_name = _cursor.getString(_cursorIndexOfCityName);
            _item.setCity_name(_tmpCity_name);
            final String _tmpLast_update;
            _tmpLast_update = _cursor.getString(_cursorIndexOfLastUpdate);
            _item.setLast_update(_tmpLast_update);
            final Integer _tmpNews_id;
            if (_cursor.isNull(_cursorIndexOfNewsId)) {
              _tmpNews_id = null;
            } else {
              _tmpNews_id = _cursor.getInt(_cursorIndexOfNewsId);
            }
            _item.setNews_id(_tmpNews_id);
            final String _tmpNews_con;
            _tmpNews_con = _cursor.getString(_cursorIndexOfNewsCon);
            _item.setNews_con(_tmpNews_con);
            final String _tmpNews_cont;
            _tmpNews_cont = _cursor.getString(_cursorIndexOfNewsCont);
            _item.setNews_cont(_tmpNews_cont);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }

  @Override
  public LiveData<List<DbCityBean>> getDescAll() {
    final String _sql = "SELECT * FROM dbcitybean ORDER By ctime DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new ComputableLiveData<List<DbCityBean>>() {
      private Observer _observer;

      @Override
      protected List<DbCityBean> compute() {
        if (_observer == null) {
          _observer = new Observer("dbcitybean") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfCtime = _cursor.getColumnIndexOrThrow("ctime");
          final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
          final int _cursorIndexOfDescription = _cursor.getColumnIndexOrThrow("description");
          final int _cursorIndexOfPicUrl = _cursor.getColumnIndexOrThrow("picUrl");
          final int _cursorIndexOfUrl = _cursor.getColumnIndexOrThrow("url");
          final int _cursorIndexOfCityName = _cursor.getColumnIndexOrThrow("city_name");
          final int _cursorIndexOfLastUpdate = _cursor.getColumnIndexOrThrow("last_update");
          final int _cursorIndexOfNewsId = _cursor.getColumnIndexOrThrow("news_id");
          final int _cursorIndexOfNewsCon = _cursor.getColumnIndexOrThrow("news_con");
          final int _cursorIndexOfNewsCont = _cursor.getColumnIndexOrThrow("news_cont");
          final List<DbCityBean> _result = new ArrayList<DbCityBean>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final DbCityBean _item;
            _item = new DbCityBean();
            final String _tmpCtime;
            _tmpCtime = _cursor.getString(_cursorIndexOfCtime);
            _item.setCtime(_tmpCtime);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            _item.setTitle(_tmpTitle);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            _item.setDescription(_tmpDescription);
            final String _tmpPicUrl;
            _tmpPicUrl = _cursor.getString(_cursorIndexOfPicUrl);
            _item.setPicUrl(_tmpPicUrl);
            final String _tmpUrl;
            _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            _item.setUrl(_tmpUrl);
            final String _tmpCity_name;
            _tmpCity_name = _cursor.getString(_cursorIndexOfCityName);
            _item.setCity_name(_tmpCity_name);
            final String _tmpLast_update;
            _tmpLast_update = _cursor.getString(_cursorIndexOfLastUpdate);
            _item.setLast_update(_tmpLast_update);
            final Integer _tmpNews_id;
            if (_cursor.isNull(_cursorIndexOfNewsId)) {
              _tmpNews_id = null;
            } else {
              _tmpNews_id = _cursor.getInt(_cursorIndexOfNewsId);
            }
            _item.setNews_id(_tmpNews_id);
            final String _tmpNews_con;
            _tmpNews_con = _cursor.getString(_cursorIndexOfNewsCon);
            _item.setNews_con(_tmpNews_con);
            final String _tmpNews_cont;
            _tmpNews_cont = _cursor.getString(_cursorIndexOfNewsCont);
            _item.setNews_cont(_tmpNews_cont);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }

  @Override
  public LiveData<List<DbCityBean>> queryByTime() {
    final String _sql = "SELECT * FROM dbcitybean ORDER By ctime";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new ComputableLiveData<List<DbCityBean>>() {
      private Observer _observer;

      @Override
      protected List<DbCityBean> compute() {
        if (_observer == null) {
          _observer = new Observer("dbcitybean") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfCtime = _cursor.getColumnIndexOrThrow("ctime");
          final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
          final int _cursorIndexOfDescription = _cursor.getColumnIndexOrThrow("description");
          final int _cursorIndexOfPicUrl = _cursor.getColumnIndexOrThrow("picUrl");
          final int _cursorIndexOfUrl = _cursor.getColumnIndexOrThrow("url");
          final int _cursorIndexOfCityName = _cursor.getColumnIndexOrThrow("city_name");
          final int _cursorIndexOfLastUpdate = _cursor.getColumnIndexOrThrow("last_update");
          final int _cursorIndexOfNewsId = _cursor.getColumnIndexOrThrow("news_id");
          final int _cursorIndexOfNewsCon = _cursor.getColumnIndexOrThrow("news_con");
          final int _cursorIndexOfNewsCont = _cursor.getColumnIndexOrThrow("news_cont");
          final List<DbCityBean> _result = new ArrayList<DbCityBean>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final DbCityBean _item;
            _item = new DbCityBean();
            final String _tmpCtime;
            _tmpCtime = _cursor.getString(_cursorIndexOfCtime);
            _item.setCtime(_tmpCtime);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            _item.setTitle(_tmpTitle);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            _item.setDescription(_tmpDescription);
            final String _tmpPicUrl;
            _tmpPicUrl = _cursor.getString(_cursorIndexOfPicUrl);
            _item.setPicUrl(_tmpPicUrl);
            final String _tmpUrl;
            _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            _item.setUrl(_tmpUrl);
            final String _tmpCity_name;
            _tmpCity_name = _cursor.getString(_cursorIndexOfCityName);
            _item.setCity_name(_tmpCity_name);
            final String _tmpLast_update;
            _tmpLast_update = _cursor.getString(_cursorIndexOfLastUpdate);
            _item.setLast_update(_tmpLast_update);
            final Integer _tmpNews_id;
            if (_cursor.isNull(_cursorIndexOfNewsId)) {
              _tmpNews_id = null;
            } else {
              _tmpNews_id = _cursor.getInt(_cursorIndexOfNewsId);
            }
            _item.setNews_id(_tmpNews_id);
            final String _tmpNews_con;
            _tmpNews_con = _cursor.getString(_cursorIndexOfNewsCon);
            _item.setNews_con(_tmpNews_con);
            final String _tmpNews_cont;
            _tmpNews_cont = _cursor.getString(_cursorIndexOfNewsCont);
            _item.setNews_cont(_tmpNews_cont);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }

  @Override
  public LiveData<DbCityBean> load(String ctime) {
    final String _sql = "SELECT * FROM dbcitybean WHERE ctime = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (ctime == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, ctime);
    }
    return new ComputableLiveData<DbCityBean>() {
      private Observer _observer;

      @Override
      protected DbCityBean compute() {
        if (_observer == null) {
          _observer = new Observer("dbcitybean") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfCtime = _cursor.getColumnIndexOrThrow("ctime");
          final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
          final int _cursorIndexOfDescription = _cursor.getColumnIndexOrThrow("description");
          final int _cursorIndexOfPicUrl = _cursor.getColumnIndexOrThrow("picUrl");
          final int _cursorIndexOfUrl = _cursor.getColumnIndexOrThrow("url");
          final int _cursorIndexOfCityName = _cursor.getColumnIndexOrThrow("city_name");
          final int _cursorIndexOfLastUpdate = _cursor.getColumnIndexOrThrow("last_update");
          final int _cursorIndexOfNewsId = _cursor.getColumnIndexOrThrow("news_id");
          final int _cursorIndexOfNewsCon = _cursor.getColumnIndexOrThrow("news_con");
          final int _cursorIndexOfNewsCont = _cursor.getColumnIndexOrThrow("news_cont");
          final DbCityBean _result;
          if(_cursor.moveToFirst()) {
            _result = new DbCityBean();
            final String _tmpCtime;
            _tmpCtime = _cursor.getString(_cursorIndexOfCtime);
            _result.setCtime(_tmpCtime);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            _result.setTitle(_tmpTitle);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            _result.setDescription(_tmpDescription);
            final String _tmpPicUrl;
            _tmpPicUrl = _cursor.getString(_cursorIndexOfPicUrl);
            _result.setPicUrl(_tmpPicUrl);
            final String _tmpUrl;
            _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            _result.setUrl(_tmpUrl);
            final String _tmpCity_name;
            _tmpCity_name = _cursor.getString(_cursorIndexOfCityName);
            _result.setCity_name(_tmpCity_name);
            final String _tmpLast_update;
            _tmpLast_update = _cursor.getString(_cursorIndexOfLastUpdate);
            _result.setLast_update(_tmpLast_update);
            final Integer _tmpNews_id;
            if (_cursor.isNull(_cursorIndexOfNewsId)) {
              _tmpNews_id = null;
            } else {
              _tmpNews_id = _cursor.getInt(_cursorIndexOfNewsId);
            }
            _result.setNews_id(_tmpNews_id);
            final String _tmpNews_con;
            _tmpNews_con = _cursor.getString(_cursorIndexOfNewsCon);
            _result.setNews_con(_tmpNews_con);
            final String _tmpNews_cont;
            _tmpNews_cont = _cursor.getString(_cursorIndexOfNewsCont);
            _result.setNews_cont(_tmpNews_cont);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }

  @Override
  public LiveData<DbCityBean> queryNewByTime() {
    final String _sql = "SELECT * FROM dbcitybean ORDER By ctime DESC LIMIT 1,1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new ComputableLiveData<DbCityBean>() {
      private Observer _observer;

      @Override
      protected DbCityBean compute() {
        if (_observer == null) {
          _observer = new Observer("dbcitybean") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfCtime = _cursor.getColumnIndexOrThrow("ctime");
          final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
          final int _cursorIndexOfDescription = _cursor.getColumnIndexOrThrow("description");
          final int _cursorIndexOfPicUrl = _cursor.getColumnIndexOrThrow("picUrl");
          final int _cursorIndexOfUrl = _cursor.getColumnIndexOrThrow("url");
          final int _cursorIndexOfCityName = _cursor.getColumnIndexOrThrow("city_name");
          final int _cursorIndexOfLastUpdate = _cursor.getColumnIndexOrThrow("last_update");
          final int _cursorIndexOfNewsId = _cursor.getColumnIndexOrThrow("news_id");
          final int _cursorIndexOfNewsCon = _cursor.getColumnIndexOrThrow("news_con");
          final int _cursorIndexOfNewsCont = _cursor.getColumnIndexOrThrow("news_cont");
          final DbCityBean _result;
          if(_cursor.moveToFirst()) {
            _result = new DbCityBean();
            final String _tmpCtime;
            _tmpCtime = _cursor.getString(_cursorIndexOfCtime);
            _result.setCtime(_tmpCtime);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            _result.setTitle(_tmpTitle);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            _result.setDescription(_tmpDescription);
            final String _tmpPicUrl;
            _tmpPicUrl = _cursor.getString(_cursorIndexOfPicUrl);
            _result.setPicUrl(_tmpPicUrl);
            final String _tmpUrl;
            _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            _result.setUrl(_tmpUrl);
            final String _tmpCity_name;
            _tmpCity_name = _cursor.getString(_cursorIndexOfCityName);
            _result.setCity_name(_tmpCity_name);
            final String _tmpLast_update;
            _tmpLast_update = _cursor.getString(_cursorIndexOfLastUpdate);
            _result.setLast_update(_tmpLast_update);
            final Integer _tmpNews_id;
            if (_cursor.isNull(_cursorIndexOfNewsId)) {
              _tmpNews_id = null;
            } else {
              _tmpNews_id = _cursor.getInt(_cursorIndexOfNewsId);
            }
            _result.setNews_id(_tmpNews_id);
            final String _tmpNews_con;
            _tmpNews_con = _cursor.getString(_cursorIndexOfNewsCon);
            _result.setNews_con(_tmpNews_con);
            final String _tmpNews_cont;
            _tmpNews_cont = _cursor.getString(_cursorIndexOfNewsCont);
            _result.setNews_cont(_tmpNews_cont);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }
}

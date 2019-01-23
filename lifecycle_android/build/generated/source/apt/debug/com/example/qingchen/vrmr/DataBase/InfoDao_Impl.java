package com.example.qingchen.vrmr.DataBase;

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
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unchecked")
public class InfoDao_Impl implements InfoDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfNewsBean;

  private final EntityInsertionAdapter __insertionAdapterOfNewsBean_1;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfNewsBean;

  public InfoDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfNewsBean = new EntityInsertionAdapter<NewsBean>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `NewsBean`(`ctime`,`title`,`description`,`picUrl`,`url`) VALUES (?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, NewsBean value) {
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
      }
    };
    this.__insertionAdapterOfNewsBean_1 = new EntityInsertionAdapter<NewsBean>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `NewsBean`(`ctime`,`title`,`description`,`picUrl`,`url`) VALUES (?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, NewsBean value) {
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
      }
    };
    this.__deletionAdapterOfNewsBean = new EntityDeletionOrUpdateAdapter<NewsBean>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `NewsBean` WHERE `ctime` = ? AND `picUrl` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, NewsBean value) {
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
  public void save(NewsBean info) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfNewsBean.insert(info);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAll(List<NewsBean> list) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfNewsBean_1.insert(list);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(NewsBean list) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfNewsBean.handle(list);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<NewsBean>> getAll() {
    final String _sql = "SELECT * FROM newsbean";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new ComputableLiveData<List<NewsBean>>() {
      private Observer _observer;

      @Override
      protected List<NewsBean> compute() {
        if (_observer == null) {
          _observer = new Observer("newsbean") {
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
          final List<NewsBean> _result = new ArrayList<NewsBean>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final NewsBean _item;
            _item = new NewsBean();
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
  public List<NewsBean> getAl() {
    final String _sql = "SELECT * FROM newsbean";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfCtime = _cursor.getColumnIndexOrThrow("ctime");
      final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
      final int _cursorIndexOfDescription = _cursor.getColumnIndexOrThrow("description");
      final int _cursorIndexOfPicUrl = _cursor.getColumnIndexOrThrow("picUrl");
      final int _cursorIndexOfUrl = _cursor.getColumnIndexOrThrow("url");
      final List<NewsBean> _result = new ArrayList<NewsBean>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final NewsBean _item;
        _item = new NewsBean();
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
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public LiveData<NewsBean> load(String ctime) {
    final String _sql = "SELECT * FROM newsbean WHERE ctime = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (ctime == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, ctime);
    }
    return new ComputableLiveData<NewsBean>() {
      private Observer _observer;

      @Override
      protected NewsBean compute() {
        if (_observer == null) {
          _observer = new Observer("newsbean") {
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
          final NewsBean _result;
          if(_cursor.moveToFirst()) {
            _result = new NewsBean();
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

package com.example.qingchen.vrmr.DataBase;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Callback;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.RoomOpenHelper.Delegate;
import android.arch.persistence.room.util.TableInfo;
import android.arch.persistence.room.util.TableInfo.Column;
import android.arch.persistence.room.util.TableInfo.ForeignKey;
import android.arch.persistence.room.util.TableInfo.Index;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public class InfoDataBase_Impl extends InfoDataBase {
  private volatile InfoDao _infoDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `NewsBean` (`ctime` TEXT NOT NULL, `title` TEXT, `description` TEXT, `picUrl` TEXT NOT NULL, `url` TEXT, PRIMARY KEY(`ctime`, `picUrl`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"15580536feefcb1e4bae2de5a6daad89\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `NewsBean`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsNewsBean = new HashMap<String, TableInfo.Column>(5);
        _columnsNewsBean.put("ctime", new TableInfo.Column("ctime", "TEXT", true, 1));
        _columnsNewsBean.put("title", new TableInfo.Column("title", "TEXT", false, 0));
        _columnsNewsBean.put("description", new TableInfo.Column("description", "TEXT", false, 0));
        _columnsNewsBean.put("picUrl", new TableInfo.Column("picUrl", "TEXT", true, 2));
        _columnsNewsBean.put("url", new TableInfo.Column("url", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysNewsBean = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesNewsBean = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoNewsBean = new TableInfo("NewsBean", _columnsNewsBean, _foreignKeysNewsBean, _indicesNewsBean);
        final TableInfo _existingNewsBean = TableInfo.read(_db, "NewsBean");
        if (! _infoNewsBean.equals(_existingNewsBean)) {
          throw new IllegalStateException("Migration didn't properly handle NewsBean(com.example.qingchen.vrmr.DataBase.NewsBean).\n"
                  + " Expected:\n" + _infoNewsBean + "\n"
                  + " Found:\n" + _existingNewsBean);
        }
      }
    }, "15580536feefcb1e4bae2de5a6daad89", "6f68d2bb108479f607c44f352ff3386c");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "NewsBean");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `NewsBean`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public InfoDao infoDao() {
    if (_infoDao != null) {
      return _infoDao;
    } else {
      synchronized(this) {
        if(_infoDao == null) {
          _infoDao = new InfoDao_Impl(this);
        }
        return _infoDao;
      }
    }
  }
}

package net.people.lifecycle_android.database;

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
public class CityDataBase_Impl extends CityDataBase {
  private volatile CityDao _cityDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(6) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `DbCityBean` (`ctime` TEXT NOT NULL, `title` TEXT, `description` TEXT, `picUrl` TEXT NOT NULL, `url` TEXT, `city_name` TEXT, `last_update` TEXT, `news_id` INTEGER, `news_con` TEXT, `news_cont` TEXT, PRIMARY KEY(`ctime`, `picUrl`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"c969b81f9a4df45f37b9c9fc5c6a1bd7\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `DbCityBean`");
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
        final HashMap<String, TableInfo.Column> _columnsDbCityBean = new HashMap<String, TableInfo.Column>(10);
        _columnsDbCityBean.put("ctime", new TableInfo.Column("ctime", "TEXT", true, 1));
        _columnsDbCityBean.put("title", new TableInfo.Column("title", "TEXT", false, 0));
        _columnsDbCityBean.put("description", new TableInfo.Column("description", "TEXT", false, 0));
        _columnsDbCityBean.put("picUrl", new TableInfo.Column("picUrl", "TEXT", true, 2));
        _columnsDbCityBean.put("url", new TableInfo.Column("url", "TEXT", false, 0));
        _columnsDbCityBean.put("city_name", new TableInfo.Column("city_name", "TEXT", false, 0));
        _columnsDbCityBean.put("last_update", new TableInfo.Column("last_update", "TEXT", false, 0));
        _columnsDbCityBean.put("news_id", new TableInfo.Column("news_id", "INTEGER", false, 0));
        _columnsDbCityBean.put("news_con", new TableInfo.Column("news_con", "TEXT", false, 0));
        _columnsDbCityBean.put("news_cont", new TableInfo.Column("news_cont", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDbCityBean = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDbCityBean = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDbCityBean = new TableInfo("DbCityBean", _columnsDbCityBean, _foreignKeysDbCityBean, _indicesDbCityBean);
        final TableInfo _existingDbCityBean = TableInfo.read(_db, "DbCityBean");
        if (! _infoDbCityBean.equals(_existingDbCityBean)) {
          throw new IllegalStateException("Migration didn't properly handle DbCityBean(net.people.lifecycle_android.database.DbCityBean).\n"
                  + " Expected:\n" + _infoDbCityBean + "\n"
                  + " Found:\n" + _existingDbCityBean);
        }
      }
    }, "c969b81f9a4df45f37b9c9fc5c6a1bd7", "32f74335bc3642fb699211dc56a99dc9");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "DbCityBean");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `DbCityBean`");
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
  public CityDao cityDao() {
    if (_cityDao != null) {
      return _cityDao;
    } else {
      synchronized(this) {
        if(_cityDao == null) {
          _cityDao = new CityDao_Impl(this);
        }
        return _cityDao;
      }
    }
  }
}

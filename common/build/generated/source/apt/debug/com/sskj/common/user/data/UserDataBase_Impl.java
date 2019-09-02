package com.sskj.common.user.data;

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
public class UserDataBase_Impl extends UserDataBase {
  private volatile UserDao _userDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `user_data` (`userid` INTEGER NOT NULL, `account` TEXT, `grade` TEXT, `mobile` TEXT, `realname` TEXT, `upic` TEXT, `wallone` TEXT, `idcard` TEXT, `type` TEXT, `status` TEXT, `is_tpwd` INTEGER NOT NULL, PRIMARY KEY(`userid`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"30899848540ee674153008f49bbe870c\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `user_data`");
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
        final HashMap<String, TableInfo.Column> _columnsUserData = new HashMap<String, TableInfo.Column>(11);
        _columnsUserData.put("userid", new TableInfo.Column("userid", "INTEGER", true, 1));
        _columnsUserData.put("account", new TableInfo.Column("account", "TEXT", false, 0));
        _columnsUserData.put("grade", new TableInfo.Column("grade", "TEXT", false, 0));
        _columnsUserData.put("mobile", new TableInfo.Column("mobile", "TEXT", false, 0));
        _columnsUserData.put("realname", new TableInfo.Column("realname", "TEXT", false, 0));
        _columnsUserData.put("upic", new TableInfo.Column("upic", "TEXT", false, 0));
        _columnsUserData.put("wallone", new TableInfo.Column("wallone", "TEXT", false, 0));
        _columnsUserData.put("idcard", new TableInfo.Column("idcard", "TEXT", false, 0));
        _columnsUserData.put("type", new TableInfo.Column("type", "TEXT", false, 0));
        _columnsUserData.put("status", new TableInfo.Column("status", "TEXT", false, 0));
        _columnsUserData.put("is_tpwd", new TableInfo.Column("is_tpwd", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUserData = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUserData = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUserData = new TableInfo("user_data", _columnsUserData, _foreignKeysUserData, _indicesUserData);
        final TableInfo _existingUserData = TableInfo.read(_db, "user_data");
        if (! _infoUserData.equals(_existingUserData)) {
          throw new IllegalStateException("Migration didn't properly handle user_data(com.sskj.common.user.data.UserBean).\n"
                  + " Expected:\n" + _infoUserData + "\n"
                  + " Found:\n" + _existingUserData);
        }
      }
    }, "30899848540ee674153008f49bbe870c", "0f73563d95360f42d66dc01f4fd675b9");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "user_data");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `user_data`");
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
  public UserDao getUserDao() {
    if (_userDao != null) {
      return _userDao;
    } else {
      synchronized(this) {
        if(_userDao == null) {
          _userDao = new UserDao_Impl(this);
        }
        return _userDao;
      }
    }
  }
}

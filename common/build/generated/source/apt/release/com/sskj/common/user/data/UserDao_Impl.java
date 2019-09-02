package com.sskj.common.user.data;

import android.arch.lifecycle.ComputableLiveData;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.InvalidationTracker.Observer;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
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
public class UserDao_Impl implements UserDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfUserBean;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfUserBean;

  private final SharedSQLiteStatement __preparedStmtOfClear;

  public UserDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUserBean = new EntityInsertionAdapter<UserBean>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `user_data`(`userid`,`account`,`grade`,`mobile`,`realname`,`upic`,`wallone`,`idcard`,`type`,`status`,`is_tpwd`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, UserBean value) {
        if (value.getUserid() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getUserid());
        }
        if (value.getAccount() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getAccount());
        }
        if (value.getGrade() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getGrade());
        }
        if (value.getMobile() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getMobile());
        }
        if (value.getRealname() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getRealname());
        }
        if (value.getUpic() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getUpic());
        }
        if (value.getWallone() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getWallone());
        }
        if (value.getIdcard() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getIdcard());
        }
        if (value.getType() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getType());
        }
        if (value.getStatus() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getStatus());
        }
        stmt.bindLong(11, value.getIs_tpwd());
      }
    };
    this.__updateAdapterOfUserBean = new EntityDeletionOrUpdateAdapter<UserBean>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR REPLACE `user_data` SET `userid` = ?,`account` = ?,`grade` = ?,`mobile` = ?,`realname` = ?,`upic` = ?,`wallone` = ?,`idcard` = ?,`type` = ?,`status` = ?,`is_tpwd` = ? WHERE `userid` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, UserBean value) {
        if (value.getUserid() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getUserid());
        }
        if (value.getAccount() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getAccount());
        }
        if (value.getGrade() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getGrade());
        }
        if (value.getMobile() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getMobile());
        }
        if (value.getRealname() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getRealname());
        }
        if (value.getUpic() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getUpic());
        }
        if (value.getWallone() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getWallone());
        }
        if (value.getIdcard() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getIdcard());
        }
        if (value.getType() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getType());
        }
        if (value.getStatus() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getStatus());
        }
        stmt.bindLong(11, value.getIs_tpwd());
        if (value.getUserid() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindLong(12, value.getUserid());
        }
      }
    };
    this.__preparedStmtOfClear = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "delete from user_data";
        return _query;
      }
    };
  }

  @Override
  public void insert(UserBean userBean) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfUserBean.insert(userBean);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(UserBean userBean) {
    __db.beginTransaction();
    try {
      __updateAdapterOfUserBean.handle(userBean);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void clear() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfClear.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfClear.release(_stmt);
    }
  }

  @Override
  public LiveData<List<UserBean>> getUsers() {
    final String _sql = "select * from user_data";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new ComputableLiveData<List<UserBean>>() {
      private Observer _observer;

      @Override
      protected List<UserBean> compute() {
        if (_observer == null) {
          _observer = new Observer("user_data") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfUserid = _cursor.getColumnIndexOrThrow("userid");
          final int _cursorIndexOfAccount = _cursor.getColumnIndexOrThrow("account");
          final int _cursorIndexOfGrade = _cursor.getColumnIndexOrThrow("grade");
          final int _cursorIndexOfMobile = _cursor.getColumnIndexOrThrow("mobile");
          final int _cursorIndexOfRealname = _cursor.getColumnIndexOrThrow("realname");
          final int _cursorIndexOfUpic = _cursor.getColumnIndexOrThrow("upic");
          final int _cursorIndexOfWallone = _cursor.getColumnIndexOrThrow("wallone");
          final int _cursorIndexOfIdcard = _cursor.getColumnIndexOrThrow("idcard");
          final int _cursorIndexOfType = _cursor.getColumnIndexOrThrow("type");
          final int _cursorIndexOfStatus = _cursor.getColumnIndexOrThrow("status");
          final int _cursorIndexOfIsTpwd = _cursor.getColumnIndexOrThrow("is_tpwd");
          final List<UserBean> _result = new ArrayList<UserBean>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final UserBean _item;
            _item = new UserBean();
            final Integer _tmpUserid;
            if (_cursor.isNull(_cursorIndexOfUserid)) {
              _tmpUserid = null;
            } else {
              _tmpUserid = _cursor.getInt(_cursorIndexOfUserid);
            }
            _item.setUserid(_tmpUserid);
            final String _tmpAccount;
            _tmpAccount = _cursor.getString(_cursorIndexOfAccount);
            _item.setAccount(_tmpAccount);
            final String _tmpGrade;
            _tmpGrade = _cursor.getString(_cursorIndexOfGrade);
            _item.setGrade(_tmpGrade);
            final String _tmpMobile;
            _tmpMobile = _cursor.getString(_cursorIndexOfMobile);
            _item.setMobile(_tmpMobile);
            final String _tmpRealname;
            _tmpRealname = _cursor.getString(_cursorIndexOfRealname);
            _item.setRealname(_tmpRealname);
            final String _tmpUpic;
            _tmpUpic = _cursor.getString(_cursorIndexOfUpic);
            _item.setUpic(_tmpUpic);
            final String _tmpWallone;
            _tmpWallone = _cursor.getString(_cursorIndexOfWallone);
            _item.setWallone(_tmpWallone);
            final String _tmpIdcard;
            _tmpIdcard = _cursor.getString(_cursorIndexOfIdcard);
            _item.setIdcard(_tmpIdcard);
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            _item.setType(_tmpType);
            final String _tmpStatus;
            _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            _item.setStatus(_tmpStatus);
            final int _tmpIs_tpwd;
            _tmpIs_tpwd = _cursor.getInt(_cursorIndexOfIsTpwd);
            _item.setIs_tpwd(_tmpIs_tpwd);
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
  public LiveData<UserBean> getUser() {
    final String _sql = "select * from user_data limit 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new ComputableLiveData<UserBean>() {
      private Observer _observer;

      @Override
      protected UserBean compute() {
        if (_observer == null) {
          _observer = new Observer("user_data") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfUserid = _cursor.getColumnIndexOrThrow("userid");
          final int _cursorIndexOfAccount = _cursor.getColumnIndexOrThrow("account");
          final int _cursorIndexOfGrade = _cursor.getColumnIndexOrThrow("grade");
          final int _cursorIndexOfMobile = _cursor.getColumnIndexOrThrow("mobile");
          final int _cursorIndexOfRealname = _cursor.getColumnIndexOrThrow("realname");
          final int _cursorIndexOfUpic = _cursor.getColumnIndexOrThrow("upic");
          final int _cursorIndexOfWallone = _cursor.getColumnIndexOrThrow("wallone");
          final int _cursorIndexOfIdcard = _cursor.getColumnIndexOrThrow("idcard");
          final int _cursorIndexOfType = _cursor.getColumnIndexOrThrow("type");
          final int _cursorIndexOfStatus = _cursor.getColumnIndexOrThrow("status");
          final int _cursorIndexOfIsTpwd = _cursor.getColumnIndexOrThrow("is_tpwd");
          final UserBean _result;
          if(_cursor.moveToFirst()) {
            _result = new UserBean();
            final Integer _tmpUserid;
            if (_cursor.isNull(_cursorIndexOfUserid)) {
              _tmpUserid = null;
            } else {
              _tmpUserid = _cursor.getInt(_cursorIndexOfUserid);
            }
            _result.setUserid(_tmpUserid);
            final String _tmpAccount;
            _tmpAccount = _cursor.getString(_cursorIndexOfAccount);
            _result.setAccount(_tmpAccount);
            final String _tmpGrade;
            _tmpGrade = _cursor.getString(_cursorIndexOfGrade);
            _result.setGrade(_tmpGrade);
            final String _tmpMobile;
            _tmpMobile = _cursor.getString(_cursorIndexOfMobile);
            _result.setMobile(_tmpMobile);
            final String _tmpRealname;
            _tmpRealname = _cursor.getString(_cursorIndexOfRealname);
            _result.setRealname(_tmpRealname);
            final String _tmpUpic;
            _tmpUpic = _cursor.getString(_cursorIndexOfUpic);
            _result.setUpic(_tmpUpic);
            final String _tmpWallone;
            _tmpWallone = _cursor.getString(_cursorIndexOfWallone);
            _result.setWallone(_tmpWallone);
            final String _tmpIdcard;
            _tmpIdcard = _cursor.getString(_cursorIndexOfIdcard);
            _result.setIdcard(_tmpIdcard);
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            _result.setType(_tmpType);
            final String _tmpStatus;
            _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            _result.setStatus(_tmpStatus);
            final int _tmpIs_tpwd;
            _tmpIs_tpwd = _cursor.getInt(_cursorIndexOfIsTpwd);
            _result.setIs_tpwd(_tmpIs_tpwd);
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

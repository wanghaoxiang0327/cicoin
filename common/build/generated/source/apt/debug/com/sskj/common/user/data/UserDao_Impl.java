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
        return "INSERT OR REPLACE INTO `user_data`(`uid`,`mobile`,`mail`,`nickname`,`tgno`,`tpwd`,`isBindGoogle`,`userLevel`,`registerType`,`isStartGoogle`,`isStartSms`,`isBindMail`,`rank`,`is_ds`,`ttl_usable`,`ttl_frost`,`ttl_money`,`ttl_cnymoney`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, UserBean value) {
        if (value.getUid() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getUid());
        }
        if (value.getMobile() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getMobile());
        }
        if (value.getMail() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getMail());
        }
        if (value.getNickname() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getNickname());
        }
        if (value.getTgno() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getTgno());
        }
        if (value.getTpwd() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getTpwd());
        }
        stmt.bindLong(7, value.getIsBindGoogle());
        if (value.getUserLevel() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getUserLevel());
        }
        if (value.getRegisterType() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getRegisterType());
        }
        stmt.bindLong(10, value.getIsStartGoogle());
        stmt.bindLong(11, value.getIsStartSms());
        stmt.bindLong(12, value.getIsBindMail());
        stmt.bindLong(13, value.getRank());
        stmt.bindLong(14, value.getIs_ds());
        final UserBean.ZcTotalBean _tmpZcTotal = value.getZcTotal();
        if(_tmpZcTotal != null) {
          stmt.bindDouble(15, _tmpZcTotal.getTtl_usable());
          stmt.bindDouble(16, _tmpZcTotal.getTtl_frost());
          stmt.bindDouble(17, _tmpZcTotal.getTtl_money());
          if (_tmpZcTotal.getTtl_cnymoney() == null) {
            stmt.bindNull(18);
          } else {
            stmt.bindString(18, _tmpZcTotal.getTtl_cnymoney());
          }
        } else {
          stmt.bindNull(15);
          stmt.bindNull(16);
          stmt.bindNull(17);
          stmt.bindNull(18);
        }
      }
    };
    this.__updateAdapterOfUserBean = new EntityDeletionOrUpdateAdapter<UserBean>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR REPLACE `user_data` SET `uid` = ?,`mobile` = ?,`mail` = ?,`nickname` = ?,`tgno` = ?,`tpwd` = ?,`isBindGoogle` = ?,`userLevel` = ?,`registerType` = ?,`isStartGoogle` = ?,`isStartSms` = ?,`isBindMail` = ?,`rank` = ?,`is_ds` = ?,`ttl_usable` = ?,`ttl_frost` = ?,`ttl_money` = ?,`ttl_cnymoney` = ? WHERE `uid` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, UserBean value) {
        if (value.getUid() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getUid());
        }
        if (value.getMobile() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getMobile());
        }
        if (value.getMail() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getMail());
        }
        if (value.getNickname() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getNickname());
        }
        if (value.getTgno() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getTgno());
        }
        if (value.getTpwd() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getTpwd());
        }
        stmt.bindLong(7, value.getIsBindGoogle());
        if (value.getUserLevel() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getUserLevel());
        }
        if (value.getRegisterType() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getRegisterType());
        }
        stmt.bindLong(10, value.getIsStartGoogle());
        stmt.bindLong(11, value.getIsStartSms());
        stmt.bindLong(12, value.getIsBindMail());
        stmt.bindLong(13, value.getRank());
        stmt.bindLong(14, value.getIs_ds());
        final UserBean.ZcTotalBean _tmpZcTotal = value.getZcTotal();
        if(_tmpZcTotal != null) {
          stmt.bindDouble(15, _tmpZcTotal.getTtl_usable());
          stmt.bindDouble(16, _tmpZcTotal.getTtl_frost());
          stmt.bindDouble(17, _tmpZcTotal.getTtl_money());
          if (_tmpZcTotal.getTtl_cnymoney() == null) {
            stmt.bindNull(18);
          } else {
            stmt.bindString(18, _tmpZcTotal.getTtl_cnymoney());
          }
        } else {
          stmt.bindNull(15);
          stmt.bindNull(16);
          stmt.bindNull(17);
          stmt.bindNull(18);
        }
        if (value.getUid() == null) {
          stmt.bindNull(19);
        } else {
          stmt.bindString(19, value.getUid());
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
          final int _cursorIndexOfUid = _cursor.getColumnIndexOrThrow("uid");
          final int _cursorIndexOfMobile = _cursor.getColumnIndexOrThrow("mobile");
          final int _cursorIndexOfMail = _cursor.getColumnIndexOrThrow("mail");
          final int _cursorIndexOfNickname = _cursor.getColumnIndexOrThrow("nickname");
          final int _cursorIndexOfTgno = _cursor.getColumnIndexOrThrow("tgno");
          final int _cursorIndexOfTpwd = _cursor.getColumnIndexOrThrow("tpwd");
          final int _cursorIndexOfIsBindGoogle = _cursor.getColumnIndexOrThrow("isBindGoogle");
          final int _cursorIndexOfUserLevel = _cursor.getColumnIndexOrThrow("userLevel");
          final int _cursorIndexOfRegisterType = _cursor.getColumnIndexOrThrow("registerType");
          final int _cursorIndexOfIsStartGoogle = _cursor.getColumnIndexOrThrow("isStartGoogle");
          final int _cursorIndexOfIsStartSms = _cursor.getColumnIndexOrThrow("isStartSms");
          final int _cursorIndexOfIsBindMail = _cursor.getColumnIndexOrThrow("isBindMail");
          final int _cursorIndexOfRank = _cursor.getColumnIndexOrThrow("rank");
          final int _cursorIndexOfIsDs = _cursor.getColumnIndexOrThrow("is_ds");
          final int _cursorIndexOfTtlUsable = _cursor.getColumnIndexOrThrow("ttl_usable");
          final int _cursorIndexOfTtlFrost = _cursor.getColumnIndexOrThrow("ttl_frost");
          final int _cursorIndexOfTtlMoney = _cursor.getColumnIndexOrThrow("ttl_money");
          final int _cursorIndexOfTtlCnymoney = _cursor.getColumnIndexOrThrow("ttl_cnymoney");
          final List<UserBean> _result = new ArrayList<UserBean>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final UserBean _item;
            final UserBean.ZcTotalBean _tmpZcTotal;
            if (! (_cursor.isNull(_cursorIndexOfTtlUsable) && _cursor.isNull(_cursorIndexOfTtlFrost) && _cursor.isNull(_cursorIndexOfTtlMoney) && _cursor.isNull(_cursorIndexOfTtlCnymoney))) {
              _tmpZcTotal = new UserBean.ZcTotalBean();
              final double _tmpTtl_usable;
              _tmpTtl_usable = _cursor.getDouble(_cursorIndexOfTtlUsable);
              _tmpZcTotal.setTtl_usable(_tmpTtl_usable);
              final double _tmpTtl_frost;
              _tmpTtl_frost = _cursor.getDouble(_cursorIndexOfTtlFrost);
              _tmpZcTotal.setTtl_frost(_tmpTtl_frost);
              final double _tmpTtl_money;
              _tmpTtl_money = _cursor.getDouble(_cursorIndexOfTtlMoney);
              _tmpZcTotal.setTtl_money(_tmpTtl_money);
              final String _tmpTtl_cnymoney;
              _tmpTtl_cnymoney = _cursor.getString(_cursorIndexOfTtlCnymoney);
              _tmpZcTotal.setTtl_cnymoney(_tmpTtl_cnymoney);
            }  else  {
              _tmpZcTotal = null;
            }
            _item = new UserBean();
            final String _tmpUid;
            _tmpUid = _cursor.getString(_cursorIndexOfUid);
            _item.setUid(_tmpUid);
            final String _tmpMobile;
            _tmpMobile = _cursor.getString(_cursorIndexOfMobile);
            _item.setMobile(_tmpMobile);
            final String _tmpMail;
            _tmpMail = _cursor.getString(_cursorIndexOfMail);
            _item.setMail(_tmpMail);
            final String _tmpNickname;
            _tmpNickname = _cursor.getString(_cursorIndexOfNickname);
            _item.setNickname(_tmpNickname);
            final String _tmpTgno;
            _tmpTgno = _cursor.getString(_cursorIndexOfTgno);
            _item.setTgno(_tmpTgno);
            final String _tmpTpwd;
            _tmpTpwd = _cursor.getString(_cursorIndexOfTpwd);
            _item.setTpwd(_tmpTpwd);
            final int _tmpIsBindGoogle;
            _tmpIsBindGoogle = _cursor.getInt(_cursorIndexOfIsBindGoogle);
            _item.setIsBindGoogle(_tmpIsBindGoogle);
            final String _tmpUserLevel;
            _tmpUserLevel = _cursor.getString(_cursorIndexOfUserLevel);
            _item.setUserLevel(_tmpUserLevel);
            final String _tmpRegisterType;
            _tmpRegisterType = _cursor.getString(_cursorIndexOfRegisterType);
            _item.setRegisterType(_tmpRegisterType);
            final int _tmpIsStartGoogle;
            _tmpIsStartGoogle = _cursor.getInt(_cursorIndexOfIsStartGoogle);
            _item.setIsStartGoogle(_tmpIsStartGoogle);
            final int _tmpIsStartSms;
            _tmpIsStartSms = _cursor.getInt(_cursorIndexOfIsStartSms);
            _item.setIsStartSms(_tmpIsStartSms);
            final int _tmpIsBindMail;
            _tmpIsBindMail = _cursor.getInt(_cursorIndexOfIsBindMail);
            _item.setIsBindMail(_tmpIsBindMail);
            final int _tmpRank;
            _tmpRank = _cursor.getInt(_cursorIndexOfRank);
            _item.setRank(_tmpRank);
            final int _tmpIs_ds;
            _tmpIs_ds = _cursor.getInt(_cursorIndexOfIsDs);
            _item.setIs_ds(_tmpIs_ds);
            _item.setZcTotal(_tmpZcTotal);
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
          final int _cursorIndexOfUid = _cursor.getColumnIndexOrThrow("uid");
          final int _cursorIndexOfMobile = _cursor.getColumnIndexOrThrow("mobile");
          final int _cursorIndexOfMail = _cursor.getColumnIndexOrThrow("mail");
          final int _cursorIndexOfNickname = _cursor.getColumnIndexOrThrow("nickname");
          final int _cursorIndexOfTgno = _cursor.getColumnIndexOrThrow("tgno");
          final int _cursorIndexOfTpwd = _cursor.getColumnIndexOrThrow("tpwd");
          final int _cursorIndexOfIsBindGoogle = _cursor.getColumnIndexOrThrow("isBindGoogle");
          final int _cursorIndexOfUserLevel = _cursor.getColumnIndexOrThrow("userLevel");
          final int _cursorIndexOfRegisterType = _cursor.getColumnIndexOrThrow("registerType");
          final int _cursorIndexOfIsStartGoogle = _cursor.getColumnIndexOrThrow("isStartGoogle");
          final int _cursorIndexOfIsStartSms = _cursor.getColumnIndexOrThrow("isStartSms");
          final int _cursorIndexOfIsBindMail = _cursor.getColumnIndexOrThrow("isBindMail");
          final int _cursorIndexOfRank = _cursor.getColumnIndexOrThrow("rank");
          final int _cursorIndexOfIsDs = _cursor.getColumnIndexOrThrow("is_ds");
          final int _cursorIndexOfTtlUsable = _cursor.getColumnIndexOrThrow("ttl_usable");
          final int _cursorIndexOfTtlFrost = _cursor.getColumnIndexOrThrow("ttl_frost");
          final int _cursorIndexOfTtlMoney = _cursor.getColumnIndexOrThrow("ttl_money");
          final int _cursorIndexOfTtlCnymoney = _cursor.getColumnIndexOrThrow("ttl_cnymoney");
          final UserBean _result;
          if(_cursor.moveToFirst()) {
            final UserBean.ZcTotalBean _tmpZcTotal;
            if (! (_cursor.isNull(_cursorIndexOfTtlUsable) && _cursor.isNull(_cursorIndexOfTtlFrost) && _cursor.isNull(_cursorIndexOfTtlMoney) && _cursor.isNull(_cursorIndexOfTtlCnymoney))) {
              _tmpZcTotal = new UserBean.ZcTotalBean();
              final double _tmpTtl_usable;
              _tmpTtl_usable = _cursor.getDouble(_cursorIndexOfTtlUsable);
              _tmpZcTotal.setTtl_usable(_tmpTtl_usable);
              final double _tmpTtl_frost;
              _tmpTtl_frost = _cursor.getDouble(_cursorIndexOfTtlFrost);
              _tmpZcTotal.setTtl_frost(_tmpTtl_frost);
              final double _tmpTtl_money;
              _tmpTtl_money = _cursor.getDouble(_cursorIndexOfTtlMoney);
              _tmpZcTotal.setTtl_money(_tmpTtl_money);
              final String _tmpTtl_cnymoney;
              _tmpTtl_cnymoney = _cursor.getString(_cursorIndexOfTtlCnymoney);
              _tmpZcTotal.setTtl_cnymoney(_tmpTtl_cnymoney);
            }  else  {
              _tmpZcTotal = null;
            }
            _result = new UserBean();
            final String _tmpUid;
            _tmpUid = _cursor.getString(_cursorIndexOfUid);
            _result.setUid(_tmpUid);
            final String _tmpMobile;
            _tmpMobile = _cursor.getString(_cursorIndexOfMobile);
            _result.setMobile(_tmpMobile);
            final String _tmpMail;
            _tmpMail = _cursor.getString(_cursorIndexOfMail);
            _result.setMail(_tmpMail);
            final String _tmpNickname;
            _tmpNickname = _cursor.getString(_cursorIndexOfNickname);
            _result.setNickname(_tmpNickname);
            final String _tmpTgno;
            _tmpTgno = _cursor.getString(_cursorIndexOfTgno);
            _result.setTgno(_tmpTgno);
            final String _tmpTpwd;
            _tmpTpwd = _cursor.getString(_cursorIndexOfTpwd);
            _result.setTpwd(_tmpTpwd);
            final int _tmpIsBindGoogle;
            _tmpIsBindGoogle = _cursor.getInt(_cursorIndexOfIsBindGoogle);
            _result.setIsBindGoogle(_tmpIsBindGoogle);
            final String _tmpUserLevel;
            _tmpUserLevel = _cursor.getString(_cursorIndexOfUserLevel);
            _result.setUserLevel(_tmpUserLevel);
            final String _tmpRegisterType;
            _tmpRegisterType = _cursor.getString(_cursorIndexOfRegisterType);
            _result.setRegisterType(_tmpRegisterType);
            final int _tmpIsStartGoogle;
            _tmpIsStartGoogle = _cursor.getInt(_cursorIndexOfIsStartGoogle);
            _result.setIsStartGoogle(_tmpIsStartGoogle);
            final int _tmpIsStartSms;
            _tmpIsStartSms = _cursor.getInt(_cursorIndexOfIsStartSms);
            _result.setIsStartSms(_tmpIsStartSms);
            final int _tmpIsBindMail;
            _tmpIsBindMail = _cursor.getInt(_cursorIndexOfIsBindMail);
            _result.setIsBindMail(_tmpIsBindMail);
            final int _tmpRank;
            _tmpRank = _cursor.getInt(_cursorIndexOfRank);
            _result.setRank(_tmpRank);
            final int _tmpIs_ds;
            _tmpIs_ds = _cursor.getInt(_cursorIndexOfIsDs);
            _result.setIs_ds(_tmpIs_ds);
            _result.setZcTotal(_tmpZcTotal);
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

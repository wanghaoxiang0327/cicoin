package com.sskj.common.user.respositroy;

import android.arch.lifecycle.LiveData;
import android.text.TextUtils;

import com.lzy.okgo.OkGo;
import com.sskj.common.App;
import com.sskj.common.BaseApplication;
import com.sskj.common.CommonConfig;
import com.sskj.common.http.BaseHttpConfig;
import com.sskj.common.http.HttpConfig;
import com.sskj.common.http.HttpResult;
import com.sskj.common.http.JsonCallBack;
import com.sskj.common.user.data.UserBean;
import com.sskj.common.user.data.UserDao;
import com.sskj.common.user.data.UserDataBase;
import com.sskj.common.utils.SpUtil;

import io.reactivex.schedulers.Schedulers;

/**
 * @author Hey
 */
public class UserRepository {


    private UserDao userDao;


    public UserRepository() {
        UserDataBase userDataBase = UserDataBase.getINSTANCE(App.INSTANCE);
        userDao = userDataBase.getUserDao();
    }

    public LiveData<UserBean> getUser() {
        return userDao.getUser();
    }

    public void update() {

        if (!TextUtils.isEmpty(SpUtil.getString(CommonConfig.ACCOUNT,""))){

            OkGo.<HttpResult<UserBean>>get(BaseHttpConfig.BASE_URL + HttpConfig.USER_INFO)
                    .execute(new JsonCallBack<HttpResult<UserBean>>() {
                        @Override
                        protected void onNext(HttpResult<UserBean> result) {
                            Schedulers.newThread().scheduleDirect(() -> {
                                userDao.insert(result.getData());
                            });

                        }
                    });
        }

    }

    public void clear(){
        Schedulers.newThread().scheduleDirect(() -> {
            userDao.clear();
        });

    }
}

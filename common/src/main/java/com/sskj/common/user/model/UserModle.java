package com.sskj.common.user.model;

import com.sskj.common.App;
import com.sskj.common.user.data.UserDao;
import com.sskj.common.user.data.UserDao_Impl;
import com.sskj.common.user.data.UserDataBase;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class UserModle {
    @Provides
    @Named("UserDao")
    UserDao provideUserDao() {
        return new UserDao_Impl(UserDataBase.getINSTANCE(App.INSTANCE));
    }



}

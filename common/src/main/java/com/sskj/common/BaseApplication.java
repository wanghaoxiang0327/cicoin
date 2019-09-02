package com.sskj.common;

import android.content.Context;
import android.content.res.Configuration;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hjq.toast.ToastUtils;
import com.hjq.toast.style.ToastQQStyle;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.model.HttpHeaders;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.shizhefei.mvc.MVCHelper;
import com.squareup.leakcanary.LeakCanary;
import com.sskj.common.http.HttpsUtil;
import com.sskj.common.language.LocalManageUtil;
import com.sskj.common.mvc.LoadViewFactory;
import com.sskj.common.utils.Cockroach;
import com.sskj.common.utils.SpUtil;
import com.zzhoujay.richtext.RichText;

import java.util.logging.Level;

import okhttp3.OkHttpClient;

public class BaseApplication extends MultiDexApplication {


    @Override
    public void onCreate() {
        super.onCreate();
        initHttp();
        MultiDex.install(this);
        ToastUtils.initStyle(new ToastQQStyle());
        ToastUtils.init(this);
        initHeader();
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> new ClassicsFooter(this));
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> new ClassicsHeader(this));
        MVCHelper.setLoadViewFractory(new LoadViewFactory());
        if (BuildConfig.DEBUG) {
            ARouter.openDebug();
            ARouter.openLog();
            LeakCanary.install(this);
        } else {
            Cockroach.install(new Cockroach.ExceptionHandler() {
                @Override
                public void handlerException(Thread thread, Throwable throwable) {

                }
            });
        }
        ARouter.init(this);
        RichText.initCacheDir(this);
        LocalManageUtil.setApplicationLanguage(this);
    }

    private void initHeader() {
        String account = SpUtil.getString(CommonConfig.ACCOUNT, "");
        HttpHeaders httpHeaders = new HttpHeaders();
        if (!TextUtils.isEmpty(account)) {
            httpHeaders.put(CommonConfig.ACCOUNT, account);
        }
        String token = SpUtil.getString(CommonConfig.TOKEN, "");
        if (!TextUtils.isEmpty(token)) {
            httpHeaders.put(CommonConfig.TOKEN, token);
        }
        OkGo.getInstance().addCommonHeaders(httpHeaders);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //保存系统选择语言
        LocalManageUtil.onConfigurationChanged(getApplicationContext());
    }

    @Override
    protected void attachBaseContext(Context base) {
        //保存系统选择语言
        LocalManageUtil.saveSystemCurrentLanguage(base);
        super.attachBaseContext(LocalManageUtil.setLocal(base));
    }


    private void initHttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("http");
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        loggingInterceptor.setColorLevel(Level.INFO);
        builder.addInterceptor(loggingInterceptor);
        builder.sslSocketFactory(HttpsUtil.getSslSocketFactory(), HttpsUtil.UnSafeTrustManager);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.put("account", BaseApplication.getAccount());
        httpHeaders.put("token", BaseApplication.getToken());
        OkGo.getInstance().init(this)
                .setOkHttpClient(builder.build())
                .setRetryCount(3)
                .addCommonHeaders(httpHeaders);


    }


    public static String getMobile() {
        return SpUtil.getString(CommonConfig.MOBILE, "");
    }

    public static String getAccount() {
        return SpUtil.getString(CommonConfig.ACCOUNT, "");
    }

    public static String getToken() {
        return SpUtil.getString(CommonConfig.TOKEN, "");
    }

    public static Boolean isLogin() {
        return SpUtil.getBoolean(CommonConfig.LOGIN, false);
    }


}

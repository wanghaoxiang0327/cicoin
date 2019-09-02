package com.sskj.common.interceptor;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sskj.common.BaseApplication;
import com.sskj.common.router.RoutePath;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * @author Hey
 * @date 2019年7月9日11:17:58
 */
@Interceptor(priority = 1, name = "登录拦截器")
public class LoginInterceptor implements IInterceptor {
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        if (postcard.getExtra() == RoutePath.NEED_LOGIN) {
            if (!BaseApplication.isLogin()) {
                AndroidSchedulers.mainThread().scheduleDirect(() -> {
                    ARouter.getInstance().build(RoutePath.APP_LOGIN)
                            .greenChannel()
                            .navigation();
                });
                callback.onInterrupt(null);
            } else {
                callback.onContinue(postcard);
            }
        }else {
            callback.onContinue(postcard);
        }

    }

    @Override
    public void init(Context context) {

    }
}

package com.sskj.cicoin;

import com.hjq.toast.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.sskj.common.base.BasePresenter;
import com.sskj.common.data.VersionBean;
import com.sskj.common.http.ApiException;
import com.sskj.common.http.BaseHttpConfig;
import com.sskj.common.http.HttpConfig;
import com.sskj.common.http.HttpResult;
import com.sskj.common.http.JsonCallBack;

public class MainPresenter extends BasePresenter<MainActivity> {
    public void checkVersion(String versionName) {
        OkGo.<HttpResult<VersionBean>>post(BaseHttpConfig.BASE_URL + HttpConfig.CHECK_VERSION)
                .params("version", versionName)
                .params("type", 1)
                .execute(new JsonCallBack<HttpResult<VersionBean>>() {
                    @Override
                    protected void onNext(HttpResult<VersionBean> result) {
                        mView.checkVersion(result);
                    }

                    @Override
                    public void onError(Response<HttpResult<VersionBean>> response) {
                        if (response.getException() instanceof ApiException) {
                            if (((ApiException) response.getException()).getCode() != 0) {
                                ToastUtils.show(((ApiException) response.getException()).getMsg());
                            }
                        }
                    }
                });
    }
}

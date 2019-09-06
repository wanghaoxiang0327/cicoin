package com.sskj.contract.login;

import com.lzy.okgo.OkGo;
import com.sskj.common.base.BasePresenter;
import com.sskj.common.http.HttpConfig;
import com.sskj.common.http.HttpResult;
import com.sskj.common.http.JsonCallBack;
import com.sskj.contract.login.bean.LoginBean;

import java.util.Map;


/**
 * @author Hey
 * Create at  2019/06/20
 */
public class LoginPresenter extends BasePresenter<LoginActivity> {

    public void login(String mobile, String opwd, String googleCode) {
        OkGo.<HttpResult<LoginBean>>post(HttpConfig.BASE_URL + HttpConfig.LOGIN)
                .params("mobile", mobile)
                .params("opwd", opwd)
                .params("googleCode", googleCode)
                .execute(new JsonCallBack<HttpResult<LoginBean>>(this) {
                    @Override
                    protected void onNext(HttpResult<LoginBean> result) {
                        mView.loginSuccess(result.getData());
                    }
                });
    }

    public void login(String mobile, String pwd) {
        OkGo.<HttpResult<LoginBean>>post(HttpConfig.BASE_URL + HttpConfig.LOGIN)
                .params("mobile", mobile)
                .params("opwd", pwd)
                .execute(new JsonCallBack<HttpResult<LoginBean>>(this) {
                    @Override
                    protected void onNext(HttpResult<LoginBean> result) {
                        mView.loginSuccess(result.getData());
                    }
                });
    }

    public void isGoogleCheck(String mobile, String opwd) {
        OkGo.<HttpResult>post(HttpConfig.BASE_URL + HttpConfig.GOOGLE_CHECK)
                .params("mobile", mobile)
                .params("dyGoodleCommand", opwd)
                .execute(new JsonCallBack<HttpResult>(this) {
                    @Override
                    protected void onNext(HttpResult result) {
                        mView.verify();
//                        if (result.getData().get("is_start_google") == null || result.getData().get("is_start_google").equals("0")) {
//                            login(mobile, opwd, null);
//                        } else {
//                            mView.showCheckGoogle(mobile, opwd);
//                        }
                    }
                });
    }
}

package com.sskj.mine;

import com.hjq.toast.ToastUtils;
import com.lzy.okgo.OkGo;
import com.sskj.common.base.BasePresenter;
import com.sskj.common.http.BaseHttpConfig;
import com.sskj.common.http.HttpConfig;
import com.sskj.common.http.HttpResult;
import com.sskj.common.http.JsonCallBack;


/**
 * @author Hey
 * Create at  2019/06/26
 */
public class ResetPasswordPresenter extends BasePresenter<ResetPasswordActivity> {


    /**
     * 重置登录密码
     *
     * @param
     */
    public void resetLoginPs(String oldpwd,String opwd,String opwd1,String smsCode) {
        OkGo.<HttpResult<Object>>post(BaseHttpConfig.BASE_URL + HttpConfig.RESET_LOGIN_PS)
                .params("oldpwd", oldpwd)
                .params("opwd", opwd)
                .params("opwd1", opwd1)
                .params("code", smsCode)
                .execute(new JsonCallBack<HttpResult<Object>>(this) {
                    @Override
                    protected void onNext(HttpResult<Object> result) {
                        ToastUtils.show(result.getMsg());
                        mView.resetLoginPsSuccess();
                    }
                });
    }

    /**
     * @param mobile type 1注册 （2 重置 3 安全验证 4 资金密码设置 5 提币）
     * @param
     */
    public void sendSms(String mobile, String validate) {
        OkGo.<HttpResult>post(HttpConfig.BASE_URL + HttpConfig.SEND_SMS)
                .params("mobile", mobile)
                .params("type", 2)
                .params("validate", validate)
                .execute(new JsonCallBack<HttpResult>(this) {
                    @Override
                    protected void onNext(HttpResult result) {
                        mView.sendVerifyCodeSuccess();
                    }
                });
    }

    public void sendEmail(String email,String validate) {
        OkGo.<HttpResult>post(HttpConfig.BASE_URL + HttpConfig.SEND_EMAIL)
                .params("email", email)
                .params("type", "2")
                .params("validate", validate)
                .execute(new JsonCallBack<HttpResult>(this) {
                    @Override
                    protected void onNext(HttpResult result) {
                        mView.sendVerifyCodeSuccess();
                    }
                });
    }

}

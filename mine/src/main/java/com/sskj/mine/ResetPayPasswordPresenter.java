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
public class ResetPayPasswordPresenter extends BasePresenter<ResetPayPasswordActivity> {
    /**
     * 重置资金密码
     *
     */
    public void resetLoginPs(String opwd,String opwd1,String smsCode,String mobile) {
        OkGo.<HttpResult<Object>>post(BaseHttpConfig.BASE_URL + HttpConfig.RESET_PAY_PS)
                .params("tpwd", opwd)
                .params("tpwd1", opwd1)
                .params("code", smsCode)
                .params("mobile", mobile)
                .execute(new JsonCallBack<HttpResult<Object>>(this) {
                    @Override
                    protected void onNext(HttpResult<Object> result) {
                        ToastUtils.show(result.getMsg());
                        mView.resetPsSuccess();
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
                .params("type", 4)
                .params("validate", validate)
                .execute(new JsonCallBack<HttpResult>(this) {
                    @Override
                    protected void onNext(HttpResult result) {
                        mView.sendVerifyCodeSuccess();
                        ToastUtils.show(result.getMsg());
                    }
                });
    }

    public void sendEmail(String email,String validate) {
        OkGo.<HttpResult>post(HttpConfig.BASE_URL + HttpConfig.SEND_EMAIL)
                .params("email", email)
                .params("type", "4")
                .params("validate", validate)
                .execute(new JsonCallBack<HttpResult>(this) {
                    @Override
                    protected void onNext(HttpResult result) {
                        mView.sendVerifyCodeSuccess();
                        ToastUtils.show(result.getMsg());
                    }
                });
    }
}

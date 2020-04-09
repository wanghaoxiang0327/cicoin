package com.sskj.mine;

import com.hjq.toast.ToastUtils;
import com.lzy.okgo.OkGo;
import com.sskj.common.CommonConfig;
import com.sskj.common.base.BasePresenter;
import com.sskj.common.http.BaseHttpConfig;
import com.sskj.common.http.HttpConfig;
import com.sskj.common.http.HttpResult;
import com.sskj.common.http.JsonCallBack;
import com.sskj.common.http.Page;
import com.sskj.common.utils.SpUtil;
import com.sskj.mine.BindMobileOrEmailActivity;


/**
 * @author Hey
 * Create at  2019/06/25
 */
public class BindMobileOrEmailPresenter extends BasePresenter<BindMobileOrEmailActivity> {


    public void bindEmail(String email, String code, String tpwd) {
        OkGo.<HttpResult<Object>>post(BaseHttpConfig.BASE_URL + HttpConfig.BIND_EMAIL)
                .params("email", email)
                .params("code", code)
                .params("tpwd", tpwd)
                .execute(new JsonCallBack<HttpResult<Object>>(this) {
                    @Override
                    protected void onNext(HttpResult<Object> result) {
                        ToastUtils.show(result.getMsg());
                        mView.bindSuccess(result.getData());
                    }
                });
    }

    public void bindMobile(String mobile, String code, String tpwd) {
        OkGo.<HttpResult<Object>>post(BaseHttpConfig.BASE_URL + HttpConfig.BIND_MOBILE)
                .params("mobile", mobile)
                .params("mcode", "86")
                .params("code", code)
                .params("tpwd", tpwd)
                .execute(new JsonCallBack<HttpResult<Object>>(this) {
                    @Override
                    protected void onNext(HttpResult<Object> result) {
                        mView.bindSuccess(result.getData());
                        ToastUtils.show(result.getMsg());
                        SpUtil.put(CommonConfig.MOBILE, mobile);
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
                .params("type", 3)
                .params("validate", validate)
                .execute(new JsonCallBack<HttpResult>(this) {
                    @Override
                    protected void onNext(HttpResult result) {
                        ToastUtils.show(result.getMsg());
                        mView.sendVerifyCodeSuccess();
                    }
                });
    }

    public void sendEmail(String email,String validate) {
        OkGo.<HttpResult>post(HttpConfig.BASE_URL + HttpConfig.SEND_EMAIL)
                .params("email", email)
                .params("type", 3)
                .params("validate", validate)
                .execute(new JsonCallBack<HttpResult>(this) {
                    @Override
                    protected void onNext(HttpResult result) {
                        ToastUtils.show(result.getMsg());
                        mView.sendVerifyCodeSuccess();
                    }
                });
    }


}

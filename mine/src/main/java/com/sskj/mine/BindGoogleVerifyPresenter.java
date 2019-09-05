package com.sskj.mine;

import com.hjq.toast.ToastUtils;
import com.lzy.okgo.OkGo;
import com.sskj.common.base.BasePresenter;
import com.sskj.common.http.BaseHttpConfig;
import com.sskj.common.http.HttpConfig;
import com.sskj.common.http.HttpResult;
import com.sskj.common.http.JsonCallBack;
import com.sskj.mine.BindGoogleVerifyActivity;
import com.sskj.mine.data.GoogleInfo;


/**
 * @author Hey
 * Create at  2019/06/25
 */
public class BindGoogleVerifyPresenter extends BasePresenter<BindGoogleVerifyActivity> {


    /**
     * 获取谷歌信息
     *
     */
    public void getGoogleInfo(String code) {
        OkGo.<HttpResult<GoogleInfo>>post(BaseHttpConfig.BASE_URL + HttpConfig.GET_GOOGLE_INFO)
                .params("code",code)
                .execute(new JsonCallBack<HttpResult<GoogleInfo>>(this) {
                    @Override
                    protected void onNext(HttpResult<GoogleInfo> result) {
                        mView.setGoogleInfo(result.getData());
                    }
                });
    }
    /**
     * 设置短信验证开关
     *
     */
    public void bindGoogle(String code,String smdCode) {
        OkGo.<HttpResult<Object>>post(BaseHttpConfig.BASE_URL + HttpConfig.BIND_GOOGLE)
                .params("dyGoodleCommand",code)
                .params("code",smdCode)
                .execute(new JsonCallBack<HttpResult<Object>>(this) {
                    @Override
                    protected void onNext(HttpResult<Object> result) {
                        ToastUtils.show(result.getMsg());
                        mView.bindGoogleSuccess();
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
                        mView.sendVerifyCodeSuccess();
                    }
                });
    }

    /**
     * 1注册 2 （重置/修改） 3 安全验证 4 资金密码设置 5 提币
     * @param email
     * @param validate
     */
    public void sendEmail(String email, String validate) {
        OkGo.<HttpResult>post(HttpConfig.BASE_URL + HttpConfig.SEND_EMAIL)
                .params("email", email)
                .params("type", "3")
                .params("validate", validate)
                .execute(new JsonCallBack<HttpResult>(this) {
                    @Override
                    protected void onNext(HttpResult result) {
                        mView.sendVerifyCodeSuccess();
                    }
                });
    }
}

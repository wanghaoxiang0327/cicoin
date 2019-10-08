package com.sskj.common.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hjq.toast.ToastUtils;
import com.lzy.okgo.OkGo;
import com.netease.nis.captcha.Captcha;
import com.netease.nis.captcha.CaptchaConfiguration;
import com.netease.nis.captcha.CaptchaListener;
import com.sskj.common.App;
import com.sskj.common.BaseApplication;
import com.sskj.common.BuildConfig;
import com.sskj.common.CommonConfig;
import com.sskj.common.http.HttpConfig;
import com.sskj.common.http.HttpResult;
import com.sskj.common.http.JsonCallBack;

/**
 * 项目包名：com.sskj.common.utils
 * 项目所属模块：
 * 作者：布兜兜不打豆豆
 * 创建时间：2019年09月04日
 * 类描述：
 * 备注：
 */
public class CapUtils {


    public static void registerCheck(Context context, String type, validateInterface validateInterface) {
        CaptchaConfiguration configuration = new CaptchaConfiguration.Builder()
                .captchaId(BuildConfig.captchaId)
                // 验证码业务id
//                    .mode(CaptchaConfiguration.ModeType.MODE_INTELLIGENT_NO_SENSE) // 验证码类型，默认为普通验证码，如果要使用无感知请设置该类型，否则无需设置
                .listener(new CaptchaListener() {
                    @Override
                    public void onReady() {

                    }

                    @Override
                    public void onValidate(String result, String validate, String msg) {

                        if (!TextUtils.isEmpty(validate)) {
                            if (validateInterface != null) {
                                validateInterface.validateSuccess();
                                SpUtil.getString(CommonConfig.EMAIL, "");
                                if (TextUtils.isEmpty(SpUtil.getString(CommonConfig.MOBILE, ""))) {
                                    //发送邮箱验证码
                                    OkGo.<HttpResult>post(HttpConfig.BASE_URL + HttpConfig.SEND_EMAIL)
                                            .params("email", BaseApplication.getMobile())
                                            .params("validate", validate)
                                            .params("type", type)
                                            .execute(new JsonCallBack<HttpResult>() {
                                                @Override
                                                protected void onNext(HttpResult result) {
                                                    ToastUtils.show(result.getMsg());


                                                }
                                            });
                                } else {
                                    //发送手机验证码
                                    OkGo.<HttpResult>post(HttpConfig.BASE_URL + HttpConfig.SEND_SMS)
                                            .params("mobile", BaseApplication.getMobile())
                                            .params("type", type)
                                            .params("validate", validate)
                                            .execute(new JsonCallBack<HttpResult>() {
                                                @Override
                                                protected void onNext(HttpResult result) {
                                                    ToastUtils.show(result.getMsg());
                                                }
                                            });
                                }
//                                validateInterface.validateSuccess(validate);

                            }
                        } else {

                            Toast.makeText(context, "验证失败", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        Toast.makeText(context, "验证出错：" + msg, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancel() {
                    }

                    @Override
                    public void onClose() {

                    }
                })
                // 验证码回调监听器
                .timeout(1000 * 20)
                // 超时时间，一般无需设置
                .debug(true)
                // 是否启用debug模式，一般无需设置
                .position(-1, -1, 0, 0)
                // 设置验证码框的位置和宽度，一般无需设置，不推荐设置宽高，后面将会将逐步废弃该接口
                .build(context);
        // Context，请使用Activity实例的Context
        Captcha captcha = Captcha.getInstance().init(configuration);
        captcha.validate();

    }


    public interface validateInterface {

        void validateSuccess();


    }
}

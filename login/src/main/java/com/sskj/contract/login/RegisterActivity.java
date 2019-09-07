package com.sskj.contract.login;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.InputType;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.allen.library.SuperButton;
import com.gyf.barlibrary.ImmersionBar;
import com.hjq.toast.ToastUtils;
import com.jakewharton.rxbinding3.view.RxView;
import com.jakewharton.rxbinding3.widget.RxTextView;
import com.jakewharton.rxbinding3.widget.TextViewAfterTextChangeEvent;
import com.jakewharton.rxbinding3.widget.TextViewTextChangeEvent;
import com.netease.nis.captcha.Captcha;
import com.netease.nis.captcha.CaptchaConfiguration;
import com.netease.nis.captcha.CaptchaListener;
import com.sskj.common.App;
import com.sskj.common.BuildConfig;
import com.sskj.common.CommonConfig;
import com.sskj.common.WebActivity;
import com.sskj.common.base.BaseActivity;
import com.sskj.common.tab.TabItem;
import com.sskj.common.utils.ClickUtil;
import com.sskj.common.utils.EditUtil;
import com.sskj.common.utils.PatternUtils;
import com.sskj.common.utils.ScreenUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @author Hey
 * Create at  2019/06/21
 */
public class RegisterActivity extends BaseActivity<RegisterPresenter> {


    @BindView(R2.id.back)
    ImageView back;
    @BindView(R2.id.mobile)
    TextView mobile;
    @BindView(R2.id.mobile_line)
    View mobileLine;
    @BindView(R2.id.email)
    TextView email;
    @BindView(R2.id.email_line)
    View emailLine;
    @BindView(R2.id.t1)
    TextView t1;
    @BindView(R2.id.etNum)
    EditText etNum;
    @BindView(R2.id.iv_close)
    ImageView ivClose;
    @BindView(R2.id.etCode)
    EditText etCode;
    @BindView(R2.id.tvCode)
    TextView tvCode;
    @BindView(R2.id.etPwd1)
    EditText etPwd1;
    @BindView(R2.id.iv_pwd1)
    ImageView ivPwd1;
    @BindView(R2.id.etPwd2)
    EditText etPwd2;
    @BindView(R2.id.iv_pwd2)
    ImageView ivPwd2;
    @BindView(R2.id.etInvite)
    EditText etInvite;
    @BindView(R2.id.read_rules)
    CheckBox readRules;
    @BindView(R2.id.register)
    SuperButton register;
    @BindView(R2.id.login)
    TextView login;
    private ArrayList<TabItem> typeTabs = new ArrayList<>();
    private RegisterType registerType = RegisterType.MOBILE;
    private DisposableSubscriber<Long> disposableSubscriber;


    private String va;

    @Override
    public int getLayoutId() {
        return R.layout.login_activity_register;
    }

    @Override
    public RegisterPresenter getPresenter() {
        return new RegisterPresenter();
    }

    @Override
    public void initView() {
        //进入默认展示
        changeType();
    }

    @Override
    public void initData() {
        //返回
        ClickUtil.click(back, view -> finish());
        //手机号注册
        ClickUtil.click(mobile, view -> {

            registerType = RegisterType.MOBILE;
            changeType();
        });
        //邮箱注册
        ClickUtil.click(email, view -> {
            registerType = RegisterType.EMAIL;
            changeType();
        });
        ClickUtil.click(login, view -> finish());
        ClickUtil.click(ivClose, view -> etNum.getText().clear());
        //显示密码
        ClickUtil.click(10,ivPwd1, view -> EditUtil.togglePs(etPwd1, ivPwd1));
        //再次显示密码
        ClickUtil.click(10,ivPwd2, view -> EditUtil.togglePs(etPwd2, ivPwd2));
        SpannableString reg = new SpannableString(getString(R.string.login_registerActivity3));
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                WebActivity.start(RegisterActivity.this, 1);
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {

            }
        };
        reg.setSpan(new ForegroundColorSpan(Color.parseColor("#255bfc")), reg.toString().indexOf("《"), reg.toString().indexOf("》"), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        reg.setSpan(clickableSpan, reg.toString().indexOf("《"), reg.toString().indexOf("》"), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        readRules.setText(reg);
        readRules.setMovementMethod(new LinkMovementMethod());
        Disposable text = RxTextView.textChangeEvents(etNum)
                .map(textViewTextChangeEvent -> textViewTextChangeEvent.getText().toString())
                .subscribe(s -> {
                    if (TextUtils.isEmpty(s)) {
                        ivClose.setVisibility(View.INVISIBLE);
                        register.setShapeSolidColor(Color.parseColor("#c0c1c2")).setUseShape();
                    } else {
                        ivClose.setVisibility(View.VISIBLE);
                        register.setShapeSolidColor(Color.parseColor("#255bfc")).setUseShape();

                    }
                });
//
//        //注册
        ClickUtil.click(register, view -> {
            if (isEmptyShow(etNum)) {
                return;
            }

            if (registerType == RegisterType.MOBILE) {
                if (!PatternUtils.isMobile(getText(etNum))) {
                    return;
                }
            } else {
                if (!PatternUtils.isEmail(getText(etNum))) {
                    return;
                }
            }

            if (isEmptyShow(etCode)) {
                return;
            }

            if (isEmptyShow(etPwd1)) {
                return;
            }

            if (!PatternUtils.isLoginPs(getText(etPwd1))) {
                return;
            }

            if (isEmptyShow(etPwd2)) {
                return;
            }

            if (!getText(etPwd1).equals(getText(etPwd2))) {
                ToastUtils.show(getString(R.string.login_forgetPsActivity3));
                return;
            }

            if (isEmptyShow(etInvite)) {
                return;
            }
            if (!readRules.isChecked()) {
                ToastUtils.show(getString(R.string.login_registerActivity5));
                return;
            }
            mPresenter.register(etNum.getText().toString(),
                    etCode.getText().toString(),
                    registerType == RegisterType.MOBILE ? "86" : null,
                    etPwd1.getText().toString(),
                    etPwd2.getText().toString(),
                    etInvite.getText().toString());

        });

//        //发送验证码
        ClickUtil.click(tvCode, view -> {
            if (registerType == RegisterType.MOBILE) {
                if (!PatternUtils.isMobile(getText(etNum))) {
                    return;
                }
            } else {
                if (!PatternUtils.isEmail(getText(etNum))) {
                    return;
                }
            }

            registerCheck();
        });
    }


    /**
     * 切换注册类型
     */
    public void changeType() {
        etNum.getText().clear();
        etCode.getText().clear();
        etPwd1.getText().clear();
        etPwd2.getText().clear();
        etInvite.getText().clear();
        if (registerType == RegisterType.MOBILE) {
            etNum.setHint(getString(R.string.login_input_mobile));
            etNum.setInputType(InputType.TYPE_CLASS_PHONE);
            t1.setText(getString(R.string.login_register_phone_number));
            mobileLine.setVisibility(View.VISIBLE);
            emailLine.setVisibility(View.INVISIBLE);
            mobile.setTextColor(Color.parseColor("#255bfc"));
            email.setTextColor(Color.parseColor("#c0c1c2"));
            mobile.setTextSize(16);
            email.setTextSize(14);
        } else {
            t1.setText(getString(R.string.login_register_email_number));
            etNum.setHint(getString(R.string.login_input_email));
            etNum.setInputType(InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE);
            mobileLine.setVisibility(View.INVISIBLE);
            emailLine.setVisibility(View.VISIBLE);
            email.setTextColor(Color.parseColor("#255bfc"));
            mobile.setTextColor(Color.parseColor("#c0c1c2"));
            email.setTextSize(16);
            mobile.setTextSize(14);
        }
    }

    /**
     * 发送验证码成功
     */
    public void sendVerifyCodeSuccess() {
        ToastUtils.show(getString(R.string.login_msg_send_success_des));
    }

    public void registerCheck() {
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
                            if (registerType == RegisterType.EMAIL) {
                                mPresenter.sendEmail(etNum.getText().toString(), validate);
                            } else {
                                mPresenter.sendSms(etNum.getText().toString(), validate);
                            }
                            startTimeDown(tvCode);
                        } else {
                            Toast.makeText(getApplicationContext(), getString(R.string.login_check_fail), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        Toast.makeText(getApplicationContext(), getString(R.string.login_check_fail) + msg, Toast.LENGTH_LONG).show();
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
                .build(this);
        // Context，请使用Activity实例的Context
        Captcha captcha = Captcha.getInstance().init(configuration);
        captcha.validate();

    }


    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this)
                //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .transparentStatusBar()
                .statusBarDarkFont(true, 0.2f)
                .init();

    }


    public static void start(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    public void registerSuccess(String mobile) {
        Intent intent = new Intent();
        intent.putExtra(CommonConfig.MOBILE, mobile);
        setResult(RESULT_OK, intent);
        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}

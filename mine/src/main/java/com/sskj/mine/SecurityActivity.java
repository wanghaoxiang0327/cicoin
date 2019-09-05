package com.sskj.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.allen.library.SuperTextView;
import com.hjq.toast.ToastUtils;
import com.sskj.common.base.BaseActivity;
import com.sskj.common.dialog.TipDialog;
import com.sskj.common.dialog.VerifyPasswordDialog;
import com.sskj.common.router.RoutePath;
import com.sskj.common.utils.ClickUtil;
import com.sskj.mine.data.GoogleInfo;
import com.sskj.mine.data.Verify;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Hey
 * Create at  2019/06/24
 */
@Route(path = RoutePath.SECURITY)
public class SecurityActivity extends BaseActivity<SecurityPresenter> {
    @BindView(R2.id.menu_sms_verify)
    SuperTextView menuSmsVerify;
    @BindView(R2.id.menu_google_verify)
    SuperTextView menuGoogleVerify;
    @BindView(R2.id.menu_email_verify)
    SuperTextView menuEmailVerify;
    @BindView(R2.id.menu_login_ps)
    SuperTextView menuLoginPs;
    @BindView(R2.id.menu_pay_ps)
    SuperTextView menuPayPs;
    @BindView(R2.id.menu_user_verify)
    SuperTextView menu_user_verify;
    @BindView(R2.id.gradle)
    TextView gradle;
    @BindView(R2.id.jb)
    ProgressBar jb;

    private boolean setPayPs;

    private boolean startGoogle;
    private boolean startSms;

    private boolean bindEmail;
    //是否绑定手机号码
    private boolean bindSMS;
    private boolean bindGoogle;
    private String emailAddress;

    @Override
    public int getLayoutId() {
        return R.layout.mine_activity_security;
    }

    @Override
    public SecurityPresenter getPresenter() {
        return new SecurityPresenter();
    }

    @Override
    public void initView() {

        userViewModel.getUser().observe(this, userBean -> {
            if (userBean != null) {
                //资金密码
                setPayPs = userBean.getTpwd().equals("false");
                if (setPayPs) {
                    menuPayPs.setRightString(getString(R.string.mine_securityActivity1));
                } else {
                    menuPayPs.setRightString(getString(R.string.mine_change));
                }
                if (userBean.getIsBindMail() == 1) {
                    emailAddress = userBean.getMail();
                    menuEmailVerify.setRightString(getString(R.string.mine_securityActivity2));
                    bindEmail = true;
                } else {
                    bindEmail = false;
                    menuEmailVerify.setRightString(getString(R.string.mine_securityActivity1));
                }

                if (userBean.getIsStartGoogle() == 1) {
                    startGoogle = true;
                    bindGoogle = true;
                    menuGoogleVerify.setRightString(getString(R.string.mine_securityActivity3));
                } else {
                    startGoogle = false;
                    if (userBean.getIsBindGoogle() == 1) {
                        bindGoogle = true;
                        menuGoogleVerify.setRightString(getString(R.string.mine_securityActivity4));
                    } else {
                        bindGoogle = false;
                        menuGoogleVerify.setRightString(getString(R.string.mine_securityActivity1));
                    }
                }
                if (!TextUtils.isEmpty(userBean.getMobile())) {
                    menuSmsVerify.setRightString("已绑定");
                    bindSMS = true;
                } else {
                    menuSmsVerify.setRightString(getString(R.string.mine_securityActivity1));
                    bindSMS = false;
                }
                String jb = "低";
                int proress = 33;
                switch (userBean.getUserLevel()) {
                    case "1":
                        jb = "低";
                        proress = 33;
                        break;
                    case "2":
                        jb = "中";
                        proress = 66;
                        break;
                    case "3":
                        jb = "高";
                        proress = 100;
                        break;
                    default:
                        jb = "低";
                        proress = 33;
                        break;
                }
                gradle.setText("安全级别：" + jb);
                this.jb.setProgress(proress);
            }
        });
    }

    @Override
    public void initData() {
        ClickUtil.click(menuSmsVerify, view -> {
            if (!bindSMS) {
                BindMobileOrEmailActivity.start(this, Verify.SMS);
            } else {
                ToastUtils.show("已绑定");
            }
        });
        ClickUtil.click(menuEmailVerify, view -> {
            if (bindEmail) {
                VerifySettingActivity.start(this, Verify.EMAIL);
            } else {
                BindMobileOrEmailActivity.start(this, Verify.EMAIL);
            }
        });

        ClickUtil.click(menu_user_verify, view -> {
            VerifyHomeActivity.start(this);
        });
        ClickUtil.click(menuGoogleVerify, view -> {
            mPresenter.getGoogleInfo();
        });
        ClickUtil.click(menuLoginPs, view -> {
            ResetPasswordActivity.start(this);
        });
        ClickUtil.click(menuPayPs, view -> {
            if (setPayPs) {
                //设置资金密码
                SettingPasswordActivity.start(this);
            } else {
                //修改资金密码
                ResetPayPasswordActivity.start(this);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        userViewModel.update();
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, SecurityActivity.class);
        context.startActivity(intent);
    }


    public void startGoogle(GoogleInfo data) {
        BindGoogleVerifyActivity.start(this, data);
    }
}

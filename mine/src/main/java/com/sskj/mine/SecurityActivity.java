package com.sskj.mine;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.allen.library.SuperTextView;
import com.hjq.toast.ToastUtils;
import com.sskj.common.BaseApplication;
import com.sskj.common.base.BaseActivity;
import com.sskj.common.dialog.TipsGogleDialog;
import com.sskj.common.router.RoutePath;
import com.sskj.common.utils.ClickUtil;
import com.sskj.mine.data.GoogleInfo;
import com.sskj.mine.data.Verify;

import butterknife.BindView;

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
                setPayPs = TextUtils.isEmpty(userBean.getTpwd());
                if (setPayPs) {
                    menuPayPs.setRightString(getString(R.string.mine_securityActivity1));
                } else {
                    menuPayPs.setRightString(getString(R.string.mine_change));
                }
                if (!TextUtils.isEmpty(userBean.getEmail())) {
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
                    menuSmsVerify.setRightString(getString(R.string.mine_verifySettingActivity2));
                    bindSMS = true;
                } else {
                    menuSmsVerify.setRightString(getString(R.string.mine_securityActivity1));
                    bindSMS = false;
                }
                String jb = getString(R.string.mine_low);
                int proress = 33;
                switch (TextUtils.isEmpty(userBean.getUserLevel()) ? "" : userBean.getUserLevel()) {
                    case "1":
                        jb = getString(R.string.mine_low);
                        proress = 33;
                        break;
                    case "2":
                        jb = getString(R.string.mine_middle);
                        proress = 66;
                        break;
                    case "3":
                        jb = getString(R.string.mine_high);
                        proress = 100;
                        break;
                    default:
                        jb = getString(R.string.mine_low);
                        proress = 33;
                        break;
                }
                gradle.setText(getString(R.string.mine_security_level) + jb);
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
                ToastUtils.show(getString(R.string.mine_verifySettingActivity2));
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
            if (!bindGoogle) {
                mPresenter.getGoogleInfo();
            } else {
                new TipsGogleDialog(this, startGoogle)
                        .setOnConfirmListener((dialog, gogole, mobile, code) -> {
                            mPresenter.switchGogle(gogole, BaseApplication.getMobile(), code, startGoogle ? "0" : "1", userViewModel);
                            dialog.dismiss();
                        })
                        .show();

            }
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

    public void switchSuccess() {
        userViewModel.getUser().observe(this, userBean -> {

            assert userBean != null;
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
        });
    }
}

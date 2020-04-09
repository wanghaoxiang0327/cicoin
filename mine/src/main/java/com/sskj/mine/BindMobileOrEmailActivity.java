package com.sskj.mine;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.netease.nis.captcha.Captcha;
import com.netease.nis.captcha.CaptchaConfiguration;
import com.netease.nis.captcha.CaptchaListener;
import com.sskj.common.App;
import com.sskj.common.BuildConfig;
import com.sskj.common.base.BaseActivity;
import com.sskj.common.utils.CapUtils;
import com.sskj.common.utils.ClickUtil;
import com.sskj.common.utils.PatternUtils;
import com.sskj.mine.data.Verify;

import butterknife.BindView;

/**
 * 绑定手机号或邮箱
 *
 * @author Hey
 * Create at  2019/06/25
 */
public class BindMobileOrEmailActivity extends BaseActivity<BindMobileOrEmailPresenter> {
    @BindView(R2.id.verify_name)
    TextView verifyName;
    @BindView(R2.id.verify_account_edt)
    EditText verifyAccountEdt;
    @BindView(R2.id.verify_code_name)
    TextView verifyCodeName;
    @BindView(R2.id.get_code_tv)
    TextView getCode;
    @BindView(R2.id.edt_verify_code)
    EditText edtVerifyCode;
    @BindView(R2.id.verify_money_edt)
    EditText verify_money_edt;
    Verify verify;
    @BindView(R2.id.submit)
    Button submit;

    @Override
    public int getLayoutId() {
        return R.layout.mine_activity_bind_mobile_or_email;
    }

    @Override
    public BindMobileOrEmailPresenter getPresenter() {
        return new BindMobileOrEmailPresenter();
    }

    @Override
    public void initView() {
        verify = (Verify) getIntent().getSerializableExtra("type");
        verifyName.setText(verify.getName());
        switch (verify) {
            case EMAIL:
                mToolBarLayout.setTitle(getString(R.string.mine_bindMobileOrEmailActivity101));
                verifyCodeName.setText(getString(R.string.mine_bindMobileOrEmailActivity1));
                verifyAccountEdt.setHint(getString(R.string.mine_bindMobileOrEmailActivity201));
                break;
            case SMS:
                mToolBarLayout.setTitle(getString(R.string.mine_mine_activity_bind_mobile_or_email20));
                verifyCodeName.setText(getString(R.string.mine_bindMobileOrEmailActivity2));
                verifyAccountEdt.setHint(getString(R.string.mine_mine_activity_bind_mobile_or_email60));
                break;
            default:
                break;
        }

    }

    @Override
    public void initData() {
        ClickUtil.click(submit, view -> {
            if (isEmptyShow(verifyAccountEdt)) {
                return;
            }
            if (verify == Verify.EMAIL) {
                if (!PatternUtils.isEmail(getText(verifyAccountEdt))) {
                    return;
                }
            } else {
                if (!PatternUtils.isMobile(getText(verifyAccountEdt))) {
                    return;
                }
            }
            if (isEmptyShow(edtVerifyCode)) {
                return;
            }
            if (isEmptyShow(verify_money_edt)) {
                return;
            }
            if (verify == Verify.EMAIL) {
                mPresenter.bindEmail(getText(verifyAccountEdt), getText(edtVerifyCode), getText(verify_money_edt));
            } else {
                mPresenter.bindMobile(getText(verifyAccountEdt), getText(edtVerifyCode), getText(verify_money_edt));
            }
        });

        ClickUtil.click(getCode, view -> {
            if (isEmptyShow(verifyAccountEdt)) {
                return;
            }
            if (verify == Verify.EMAIL) {
                if (!PatternUtils.isEmail(getText(verifyAccountEdt))) {
                    return;
                }
            } else {
                if (!PatternUtils.isMobile(getText(verifyAccountEdt))) {
                    return;
                }
            }
            registerCheck();

        });
    }


    public  void registerCheck() {
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
                            if (verify == Verify.EMAIL) {
                                mPresenter.sendEmail(getText(verifyAccountEdt), validate);
                            } else {
                                mPresenter.sendSms(getText(verifyAccountEdt), validate);
                            }
                        } else {
                            Toast.makeText(App.INSTANCE, "验证失败", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        Toast.makeText(App.INSTANCE, "验证出错：" + msg, Toast.LENGTH_LONG).show();
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
    public static void start(Context context, Verify verify) {
        Intent intent = new Intent(context, BindMobileOrEmailActivity.class);
        intent.putExtra("type", verify);
        context.startActivity(intent);
    }


    public void bindSuccess(Object data) {
        finish();
    }


    public void sendVerifyCodeSuccess() {
        startTimeDown(getCode);
    }
}

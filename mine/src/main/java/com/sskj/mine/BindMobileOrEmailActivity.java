package com.sskj.mine;

import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
            CapUtils.registerCheck(this, validate -> {
                if (verify == Verify.EMAIL) {
                    mPresenter.sendEmail(getText(verifyAccountEdt), validate);
                } else {
                    mPresenter.sendSms(getText(verifyAccountEdt), validate);
                }

            });
        });
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

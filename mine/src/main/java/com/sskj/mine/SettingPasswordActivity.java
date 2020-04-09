package com.sskj.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hjq.toast.ToastUtils;
import com.sskj.common.CommonConfig;
import com.sskj.common.base.BaseActivity;
import com.sskj.common.dialog.VerifyPasswordDialog;
import com.sskj.common.router.RoutePath;
import com.sskj.common.utils.CapUtils;
import com.sskj.common.utils.ClickUtil;
import com.sskj.common.utils.EditUtil;
import com.sskj.common.utils.PatternUtils;
import com.sskj.common.utils.SpUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 设置支付密码
 *
 * @author Hey
 * Create at  2019/06/25
 */
@Route(path = RoutePath.TPWD)
public class SettingPasswordActivity extends BaseActivity<SettingPasswordPresenter> {
    @BindView(R2.id.ps_edt)
    EditText psEdt;
    @BindView(R2.id.show_ps_img)
    ImageView showPsImg;
    @BindView(R2.id.ps_repeat_edt)
    EditText psRepeatEdt;
    @BindView(R2.id.ps_code_edt)
    EditText ps_code_edt;
    @BindView(R2.id.tvCode)
    TextView tvCode;
    @BindView(R2.id.show_repeat_ps_img)
    ImageView showRepeatPsImg;
    @BindView(R2.id.submit)
    Button submit;
    private String mobile, email;

    @Override
    public int getLayoutId() {
        return R.layout.mine_activity_setting_password;
    }

    @Override
    public SettingPasswordPresenter getPresenter() {
        return new SettingPasswordPresenter();
    }

    @Override
    public void initView() {
        userViewModel.getUser().observe(this, userBean -> {
            if (userBean != null) {
                mobile = SpUtil.getString(CommonConfig.MOBILE, "");
                email = SpUtil.getString(CommonConfig.EMAIL, "");
            }
        });
    }

    @Override
    public void initData() {
        showPsImg.setOnClickListener(v -> {
            EditUtil.togglePs(psEdt, showPsImg);
        });
        showRepeatPsImg.setOnClickListener(v -> {
            EditUtil.togglePs(psRepeatEdt, showRepeatPsImg);
        });
        ClickUtil.click(tvCode, view -> {
            if (isEmptyShow(psEdt)) {
                return;
            }
            if (isEmptyShow(psRepeatEdt)) {
                return;
            }
            CapUtils.registerCheck(this,"4",()->{
                startTimeDown(tvCode);
            });

        });
        ClickUtil.click(submit, view -> {
            if (isEmptyShow(psEdt)) {
                return;
            }
            if (isEmptyShow(psRepeatEdt)) {
                return;
            }
            if (!PatternUtils.isLoginPs(getText(psEdt))) {
                return;
            }
            if (!getText(psRepeatEdt).equals(getText(psEdt))) {
                ToastUtils.show(getString(R.string.mine_resetPasswordActivity2));
                return;
            }
            if (isEmpty(ps_code_edt)) {
                return;
            }
            mPresenter.resetLoginPs(getText(psEdt), getText(psRepeatEdt), getText(ps_code_edt), TextUtils.isEmpty(mobile) ? email : mobile);
        });
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, SettingPasswordActivity.class);
        context.startActivity(intent);
    }


    public void setPsSuccess() {
        finish();
    }

    public void sendVerifyCodeSuccess() {

    }
}

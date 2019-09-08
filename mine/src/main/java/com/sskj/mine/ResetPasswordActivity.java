package com.sskj.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hjq.toast.ToastUtils;
import com.sskj.common.AppManager;
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
 * 修改登录密码
 *
 * @author Hey
 * Create at  2019/06/26
 */
public class ResetPasswordActivity extends BaseActivity<ResetPasswordPresenter> {
    @BindView(R2.id.ps_edt)
    EditText psEdt;
    @BindView(R2.id.new_ps_edt)
    EditText newPsEdt;
    @BindView(R2.id.show_new_ps_img)
    ImageView showNewPsImg;
    @BindView(R2.id.ps_repeat_edt)
    EditText psRepeatEdt;
    @BindView(R2.id.show_repeat_ps_img)
    ImageView showRepeatPsImg;
    @BindView(R2.id.show_ps_edt_img)
    ImageView show_ps_edt_img;
    @BindView(R2.id.submit)
    Button submit;
    @BindView(R2.id.ps_code_edt)
    EditText ps_code_edt;
    @BindView(R2.id.tvCode)
    TextView tvCode;
    private String mobile, email;

    @Override
    public int getLayoutId() {
        return R.layout.mine_activity_reset_password;
    }

    @Override
    public ResetPasswordPresenter getPresenter() {
        return new ResetPasswordPresenter();
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
        showNewPsImg.setOnClickListener(v -> {
            EditUtil.togglePs(newPsEdt, showNewPsImg);
        });
        showRepeatPsImg.setOnClickListener(v -> {
            EditUtil.togglePs(psRepeatEdt, showRepeatPsImg);
        });
        show_ps_edt_img.setOnClickListener(v -> EditUtil.togglePs(psEdt,show_ps_edt_img));
        ClickUtil.click(tvCode, view -> {
            if (isEmptyShow(psEdt)) {
                return;
            }
            if (isEmptyShow(newPsEdt)) {
                return;
            }
            if (isEmptyShow(psRepeatEdt)) {
                return;
            }
            CapUtils.registerCheck(this, "2", () -> startTimeDown(tvCode));

        });
        ClickUtil.click(submit, view -> {
            if (isEmptyShow(psEdt)) {
                return;
            }
            if (isEmptyShow(newPsEdt)) {
                return;
            }
            if (isEmptyShow(psRepeatEdt)) {
                return;
            }
            if (!PatternUtils.isLoginPs(getText(newPsEdt))) {
                return;
            }
            if (!getText(psRepeatEdt).equals(getText(newPsEdt))) {
                ToastUtils.show(getString(R.string.mine_resetPasswordActivity2));
                return;
            }
            if (isEmptyShow(ps_code_edt)) {
                return;
            }
            mPresenter.resetLoginPs(getText(psEdt), getText(newPsEdt), getText(psRepeatEdt), getText(ps_code_edt));
        });
    }

    @Override
    public void loadData() {
        userViewModel.update();
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, ResetPasswordActivity.class);
        context.startActivity(intent);
    }


    public void resetLoginPsSuccess() {
        ARouter.getInstance().build(RoutePath.LOGIN_LOGIN).navigation();
        userViewModel.clear();
        SpUtil.clear();
        AppManager.getInstance().finishAllLogin();
    }

    public void sendVerifyCodeSuccess() {

    }
}

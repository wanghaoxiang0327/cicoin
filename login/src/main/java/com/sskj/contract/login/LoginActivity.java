package com.sskj.contract.login;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.allen.library.SuperButton;
import com.gyf.barlibrary.ImmersionBar;
import com.hjq.toast.ToastUtils;
import com.jakewharton.rxbinding3.widget.RxTextView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.sskj.common.BaseApplication;
import com.sskj.common.CommonConfig;
import com.sskj.common.base.BaseActivity;
import com.sskj.common.dialog.VerifyPasswordDialog;
import com.sskj.common.router.RoutePath;
import com.sskj.common.rxbus.RxBus;
import com.sskj.common.tab.TabItem;
import com.sskj.common.user.model.UserViewModel;
import com.sskj.common.utils.ClickUtil;
import com.sskj.common.utils.EditUtil;
import com.sskj.common.utils.PatternUtils;
import com.sskj.common.utils.SpUtil;
import com.sskj.contract.login.bean.LoginBean;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;

/**
 * @author Hey
 * Create at  2019/06/20
 */
@Route(path = RoutePath.LOGIN_LOGIN)
public class LoginActivity extends BaseActivity<LoginPresenter> {
    @BindView(R2.id.back)
    ImageView back;
    @BindView(R2.id.title)
    TextView title;
    @BindView(R2.id.etNum)
    EditText etNum;
    @BindView(R2.id.iv_close)
    ImageView ivClose;
    @BindView(R2.id.etPwd)
    EditText etPwd;
    @BindView(R2.id.iv_pwd)
    ImageView ivPwd;
    @BindView(R2.id.login)
    SuperButton login;
    @BindView(R2.id.forget)
    TextView forget;
    @BindView(R2.id.tips)
    TextView tips;
    @BindView(R2.id.register)
    TextView register;
    private RegisterType registerType = RegisterType.MOBILE;
    VerifyPasswordDialog verifyPasswordDialog;

    @Override
    public int getLayoutId() {
        return R.layout.login_activity_login;
    }

    @Override
    public LoginPresenter getPresenter() {
        return new LoginPresenter();
    }

    @Override
    public void initView() {
        Disposable text = RxTextView.textChangeEvents(etNum)
                .map(textViewTextChangeEvent -> textViewTextChangeEvent.getText().toString())
                .subscribe(s -> {
                    if (TextUtils.isEmpty(s)) {
                        ivClose.setVisibility(View.INVISIBLE);
                        login.setShapeSolidColor(Color.parseColor("#c0c1c2")).setUseShape();
                    } else {
                        ivClose.setVisibility(View.VISIBLE);
                        login.setShapeSolidColor(Color.parseColor("#255bfc")).setUseShape();
                    }
                });
    }

    @Override
    public void initData() {
        ClickUtil.click(back, view -> finish());
        ClickUtil.click(ivClose, view -> etNum.getText().clear());
        ClickUtil.click(register, view -> RegisterActivity.start(this));
        ClickUtil.click(forget, view -> ForgetPsActivity.start(this));
        ClickUtil.click(ivPwd, view -> EditUtil.togglePs(etPwd, ivPwd));
//        //登录
        ClickUtil.click(login, view -> {
            if (isEmptyShow(etNum)) {
                return;
            }
            if (isEmptyShow(etPwd)) {
                return;
            }
            mPresenter.login(etNum.getText().toString(), etPwd.getText().toString());
        });
    }


    public void loginSuccess(LoginBean loginBean) {
        SpUtil.put(CommonConfig.ACCOUNT, loginBean.getAccount());
        SpUtil.put(CommonConfig.TOKEN, loginBean.getToken());
        String regex = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        if (getText(etNum).matches(regex)) {
            SpUtil.put(CommonConfig.EMAIL, etNum.getText().toString());
        } else {
            SpUtil.put(CommonConfig.MOBILE, etNum.getText().toString());
        }
        SpUtil.put(CommonConfig.LOGIN, true);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.put(CommonConfig.ACCOUNT, loginBean.getAccount());
        httpHeaders.put(CommonConfig.TOKEN, loginBean.getToken());
        OkGo.getInstance().addCommonHeaders(httpHeaders);
        userViewModel.update();
        userViewModel.getUser().observe(this, userBean -> {
            if (userBean != null) {
                if (userBean.getIsStartGoogle() == 1) {
                    //开启了
                    //已开启，gogle验证
                    VerifyPasswordDialog pa = new VerifyPasswordDialog(this, false, true, false, 4)
                            .setOnConfirmListener((dialog, ps, sms, google) -> {
                                mPresenter.isGoogleCheck(etNum.getText().toString(), google);
                            });
                    if (!pa.isShowing()) {
                        pa.show();
                    }
                } else {
                    if (userBean.getIsStartGoogle() == 0) {
                        ARouter.getInstance().build(RoutePath.MAIN).navigation();
                        finish();
                    }
                }
            }
        });

    }


    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }


    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this)
                .transparentStatusBar()
                .statusBarDarkFont(true)
                .init();
    }


    public void verify() {
        ARouter.getInstance().build(RoutePath.MAIN).navigation();
        finish();
    }


}

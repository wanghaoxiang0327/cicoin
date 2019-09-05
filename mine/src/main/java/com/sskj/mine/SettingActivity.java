package com.sskj.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.allen.library.SuperButton;
import com.allen.library.SuperTextView;
import com.azhon.appupdate.utils.ApkUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.sskj.common.App;
import com.sskj.common.AppManager;
import com.sskj.common.base.BaseActivity;
import com.sskj.common.data.LanguageType;
import com.sskj.common.data.VersionBean;
import com.sskj.common.dialog.SelectLanguageDialog;
import com.sskj.common.dialog.VersionUpdateDialog;
import com.sskj.common.http.HttpResult;
import com.sskj.common.language.LanguageSPUtil;
import com.sskj.common.language.LocalManageUtil;
import com.sskj.common.router.RoutePath;
import com.sskj.common.rxbus.BusCode;
import com.sskj.common.rxbus.RxBus;
import com.sskj.common.utils.ClickUtil;
import com.sskj.common.utils.SpUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Hey
 * Create at  2019/09/05 13:37:06
 */
public class SettingActivity extends BaseActivity<SettingPresenter> {


    @BindView(R2.id.language)
    SuperTextView language;
    @BindView(R2.id.version)
    SuperTextView version;
    @BindView(R2.id.submit)
    SuperButton submit;
    private List<LanguageType> data;

    @Override
    public int getLayoutId() {
        return R.layout.mine_activity_setting;
    }

    @Override
    public SettingPresenter getPresenter() {
        return new SettingPresenter();
    }

    @Override
    public void initView() {
        userViewModel.getUser().observe(this, bean -> {
            if (bean == null) {
                submit.setVisibility(View.GONE);
            } else {
                submit.setVisibility(View.VISIBLE);
            }
        });
        language.setRightString(LocalManageUtil.dealLanguage(App.INSTANCE));
        version.setRightString(String.format(App.INSTANCE.getString(R.string.mine_settingActivity41), ApkUtil.getVersionName(App.INSTANCE)));

    }

    @Override
    public void initData() {
        ClickUtil.click(version, view -> {
            mPresenter.checkVersion(ApkUtil.getVersionName(App.INSTANCE));
        });

        ClickUtil.click(language, view -> {
            LaunguageActivity.start(this);
        });


        ClickUtil.click(submit, view -> {

            SpUtil.clear();
            HttpHeaders httpHeaders = OkGo.getInstance().getCommonHeaders();
            OkGo.getInstance().getCommonParams().clear();
            httpHeaders.clear();

            OkGo.getInstance().addCommonHeaders(httpHeaders);
            userViewModel.clear();

            AppManager.getInstance().finishAllLogin();
            ARouter.getInstance().build(RoutePath.LOGIN_LOGIN).navigation();
        });
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }


    public void checkVersion(HttpResult<VersionBean> result) {
        if (result != null) {
            new VersionUpdateDialog(this, result.getData()).show();
        }
    }


}

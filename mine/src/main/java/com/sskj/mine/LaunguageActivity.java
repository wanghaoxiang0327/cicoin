package com.sskj.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.allen.library.SuperTextView;
import com.sskj.common.App;
import com.sskj.common.base.BaseActivity;
import com.sskj.common.language.LanguageSPUtil;
import com.sskj.common.language.LocalManageUtil;
import com.sskj.common.rxbus.BusCode;
import com.sskj.common.rxbus.RxBus;
import com.sskj.common.utils.ClickUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Hey
 * Create at  2019/09/05 20:32:18
 */
public class LaunguageActivity extends BaseActivity<LaunguagePresenter> {


    @BindView(R2.id.zh)
    SuperTextView zh;
    @BindView(R2.id.en)
    SuperTextView en;

    @Override
    public int getLayoutId() {
        return R.layout.mine_activity_launguage;
    }

    @Override
    public LaunguagePresenter getPresenter() {
        return new LaunguagePresenter();
    }

    @Override
    public void initView() {

        switch (LocalManageUtil.getLanguage(App.INSTANCE)) {
            case "zh":
                zh.getRightIconIV().setVisibility(View.VISIBLE);
                en.getRightIconIV().setVisibility(View.GONE);
                break;
            case "en":
                zh.getRightIconIV().setVisibility(View.GONE);
                en.getRightIconIV().setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
        ClickUtil.click(zh, view -> switchLa(1));
        ClickUtil.click(en, view -> switchLa(3));
    }

    private void switchLa(int id) {
        LanguageSPUtil.getInstance(App.INSTANCE).saveLanguage(id);
        LocalManageUtil.setApplicationLanguage(App.INSTANCE);
        RxBus.getDefault().send(BusCode.CHANGE_LANGUAGE);
    }

    @Override
    public void initData() {

    }

    public static void start(Context context) {
        Intent intent = new Intent(context, LaunguageActivity.class);
        context.startActivity(intent);
    }


}

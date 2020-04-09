package com.sskj.mine;

import android.content.Context;
import android.content.Intent;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.sskj.common.base.BaseActivity;
import com.sskj.common.rxbus.RxBus;
import com.sskj.common.utils.SpUtil;

import butterknife.BindView;

/**
 * @author Hey
 * Create at  2019/06/24
 */
public class ChangeSkipActivity extends BaseActivity<LanguagePresenter> {


    @BindView(R2.id.light)
    RadioButton light;
    @BindView(R2.id.night)
    RadioButton night;

    @BindView(R2.id.languageGroup)
    RadioGroup languageGroup;

    @Override
    public int getLayoutId() {
        return R.layout.mine_activity_skip;
    }

    @Override
    public LanguagePresenter getPresenter() {
        return new LanguagePresenter();
    }

    @Override
    public void initView() {
        switch (SpUtil.getInt("skip", 2)) {
            case 1:
                languageGroup.check(R.id.light);
                break;
            case 2:
                languageGroup.check(R.id.night);
                break;
            default:
                break;
        }

    }

    @Override
    public void initData() {
    }




    public static void start(Context context) {
        Intent intent = new Intent(context, ChangeSkipActivity.class);
        context.startActivity(intent);
    }


}

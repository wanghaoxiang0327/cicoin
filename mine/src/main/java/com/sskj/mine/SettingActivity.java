package com.sskj.mine;

import com.sskj.common.base.BaseActivity;
import com.sskj.mine.SettingPresenter;
import android.content.Context;
import android.content.Intent;
import com.sskj.mine.R;
/**
 * @author Hey
 * Create at  2019/09/05 13:37:06
 */
public class SettingActivity extends BaseActivity<SettingPresenter> {



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

       }

    @Override
    public void initData() {

    }

    public static void start(Context context){
        Intent intent=new Intent(context,SettingActivity.class);
        context.startActivity(intent);
    }

}
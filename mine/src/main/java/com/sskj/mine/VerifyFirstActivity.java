package com.sskj.mine;

import com.sskj.common.base.BaseActivity;
import com.sskj.mine.VerifyFirstPresenter;
import android.content.Context;
import android.content.Intent;
import com.sskj.mine.R;
/**
 * @author Hey
 * Create at  2019/09/04 21:11:04
 */
public class VerifyFirstActivity extends BaseActivity<VerifyFirstPresenter> {



    @Override
    public int getLayoutId() {
        return R.layout.mine_activity_verify_first;
    }

    @Override
    public VerifyFirstPresenter getPresenter() {
        return new VerifyFirstPresenter();
    }

    @Override
    public void initView() {

       }

    @Override
    public void initData() {

    }

    public static void start(Context context){
        Intent intent=new Intent(context,VerifyFirstActivity.class);
        context.startActivity(intent);
    }

}

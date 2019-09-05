package com.sskj.mine;

import com.sskj.common.base.BaseActivity;
import com.sskj.mine.VerifySecondPresenter;
import android.content.Context;
import android.content.Intent;
import com.sskj.mine.R;
/**
 * @author Hey
 * Create at  2019/09/04 21:11:12
 */
public class VerifySecondActivity extends BaseActivity<VerifySecondPresenter> {



    @Override
    public int getLayoutId() {
        return R.layout.mine_activity_verify_second;
    }

    @Override
    public VerifySecondPresenter getPresenter() {
        return new VerifySecondPresenter();
    }

    @Override
    public void initView() {

       }

    @Override
    public void initData() {

    }

    public static void start(Context context){
        Intent intent=new Intent(context,VerifySecondActivity.class);
        context.startActivity(intent);
    }

}

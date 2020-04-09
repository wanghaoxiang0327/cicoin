package com.sskj.mine;

import com.sskj.common.base.BaseActivity;
import com.sskj.mine.GogleVetifyPresenter;
import android.content.Context;
import android.content.Intent;
import com.sskj.mine.R;
/**
 * @author Hey
 * Create at  2019/09/05 21:13:42
 */
public class GogleVetifyActivity extends BaseActivity<GogleVetifyPresenter> {



    @Override
    public int getLayoutId() {
        return R.layout.mine_activity_gogle_vetify;
    }

    @Override
    public GogleVetifyPresenter getPresenter() {
        return new GogleVetifyPresenter();
    }

    @Override
    public void initView() {

       }

    @Override
    public void initData() {

    }

    public static void start(Context context){
        Intent intent=new Intent(context,GogleVetifyActivity.class);
        context.startActivity(intent);
    }

}

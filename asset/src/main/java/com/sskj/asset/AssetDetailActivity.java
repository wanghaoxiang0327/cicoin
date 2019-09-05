package com.sskj.asset;

import com.sskj.common.base.BaseActivity;
import com.sskj.asset.AssetDetailPresenter;
import android.content.Context;
import android.content.Intent;
import com.sskj.asset.R;
/**
 * @author Hey
 * Create at  2019/09/05 18:50:37
 */
public class AssetDetailActivity extends BaseActivity<AssetDetailPresenter> {

    @Override
    public int getLayoutId() {
        return R.layout.asset_activity_asset_detail;
    }

    @Override
    public AssetDetailPresenter getPresenter() {
        return new AssetDetailPresenter();
    }

    @Override
    public void initView() {

       }

    @Override
    public void initData() {

    }

    public static void start(Context context){
        Intent intent=new Intent(context,AssetDetailActivity.class);
        context.startActivity(intent);
    }

}

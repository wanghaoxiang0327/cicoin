// Generated code from Butter Knife. Do not modify!
package com.sskj.asset;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AssetActivity_ViewBinding implements Unbinder {
  private AssetActivity target;

  @UiThread
  public AssetActivity_ViewBinding(AssetActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AssetActivity_ViewBinding(AssetActivity target, View source) {
    this.target = target;

    target.totalAssetTv = Utils.findRequiredViewAsType(source, R.id.total_asset_tv, "field 'totalAssetTv'", TextView.class);
    target.hideAssetImg = Utils.findRequiredViewAsType(source, R.id.hide_asset_img, "field 'hideAssetImg'", ImageView.class);
    target.cnyAssetTv = Utils.findRequiredViewAsType(source, R.id.cny_asset_tv, "field 'cnyAssetTv'", TextView.class);
    target.assetList = Utils.findRequiredViewAsType(source, R.id.asset_list, "field 'assetList'", RecyclerView.class);
    target.contentLayout = Utils.findRequiredViewAsType(source, R.id.content_layout, "field 'contentLayout'", NestedScrollView.class);
    target.viewPager = Utils.findRequiredViewAsType(source, R.id.viewPager, "field 'viewPager'", ViewPager.class);
    target.ll = Utils.findRequiredViewAsType(source, R.id.ll, "field 'll'", LinearLayout.class);
    target.recharge = Utils.findRequiredViewAsType(source, R.id.recharge, "field 'recharge'", TextView.class);
    target.cashOut = Utils.findRequiredViewAsType(source, R.id.cashOut, "field 'cashOut'", TextView.class);
    target.transfer2 = Utils.findRequiredViewAsType(source, R.id.transfer2, "field 'transfer2'", TextView.class);
    target.transfer = Utils.findRequiredViewAsType(source, R.id.transfer, "field 'transfer'", TextView.class);
    target.llNolever = Utils.findRequiredViewAsType(source, R.id.ll_nolever, "field 'llNolever'", LinearLayout.class);
    target.transferGang = Utils.findRequiredViewAsType(source, R.id.transfer_gang, "field 'transferGang'", TextView.class);
    target.gassetList = Utils.findRequiredViewAsType(source, R.id.gasset_list, "field 'gassetList'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AssetActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.totalAssetTv = null;
    target.hideAssetImg = null;
    target.cnyAssetTv = null;
    target.assetList = null;
    target.contentLayout = null;
    target.viewPager = null;
    target.ll = null;
    target.recharge = null;
    target.cashOut = null;
    target.transfer2 = null;
    target.transfer = null;
    target.llNolever = null;
    target.transferGang = null;
    target.gassetList = null;
  }
}

// Generated code from Butter Knife. Do not modify!
package com.sskj.mine;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CommissionActivity_ViewBinding implements Unbinder {
  private CommissionActivity target;

  @UiThread
  public CommissionActivity_ViewBinding(CommissionActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CommissionActivity_ViewBinding(CommissionActivity target, View source) {
    this.target = target;

    target.commissionTopList = Utils.findRequiredViewAsType(source, R.id.commission_top_list, "field 'commissionTopList'", RecyclerView.class);
    target.detailList = Utils.findRequiredViewAsType(source, R.id.detail_list, "field 'detailList'", RecyclerView.class);
    target.contentLayout = Utils.findRequiredViewAsType(source, R.id.content_layout, "field 'contentLayout'", LinearLayout.class);
    target.totalAssetTv = Utils.findRequiredViewAsType(source, R.id.total_asset_tv, "field 'totalAssetTv'", TextView.class);
    target.hideAssetImg = Utils.findRequiredViewAsType(source, R.id.hide_asset_img, "field 'hideAssetImg'", ImageView.class);
    target.cnyAssetTv = Utils.findRequiredViewAsType(source, R.id.cny_asset_tv, "field 'cnyAssetTv'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CommissionActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.commissionTopList = null;
    target.detailList = null;
    target.contentLayout = null;
    target.totalAssetTv = null;
    target.hideAssetImg = null;
    target.cnyAssetTv = null;
  }
}

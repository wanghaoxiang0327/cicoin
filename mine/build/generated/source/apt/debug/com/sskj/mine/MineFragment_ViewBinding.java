// Generated code from Butter Knife. Do not modify!
package com.sskj.mine;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.constraint.Barrier;
import android.support.constraint.Group;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MineFragment_ViewBinding implements Unbinder {
  private MineFragment target;

  @UiThread
  public MineFragment_ViewBinding(MineFragment target, View source) {
    this.target = target;

    target.viewTop = Utils.findRequiredView(source, R.id.view_top, "field 'viewTop'");
    target.viewBarrier = Utils.findRequiredViewAsType(source, R.id.view_barrier, "field 'viewBarrier'", Barrier.class);
    target.groupUnLogin = Utils.findRequiredViewAsType(source, R.id.group_unLogin, "field 'groupUnLogin'", Group.class);
    target.tvLogin = Utils.findRequiredViewAsType(source, R.id.tv_login, "field 'tvLogin'", TextView.class);
    target.tvTips = Utils.findRequiredViewAsType(source, R.id.tv_tips, "field 'tvTips'", TextView.class);
    target.groupLogin = Utils.findRequiredViewAsType(source, R.id.group_login, "field 'groupLogin'", Group.class);
    target.viewInfoCenter = Utils.findRequiredView(source, R.id.view_info_center, "field 'viewInfoCenter'");
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.tvQd = Utils.findRequiredViewAsType(source, R.id.tv_qd, "field 'tvQd'", TextView.class);
    target.imgQd = Utils.findRequiredViewAsType(source, R.id.img_qd, "field 'imgQd'", ImageView.class);
    target.tvUid = Utils.findRequiredViewAsType(source, R.id.tv_uid, "field 'tvUid'", TextView.class);
    target.tvZc = Utils.findRequiredViewAsType(source, R.id.tv_zc, "field 'tvZc'", TextView.class);
    target.tvZcInfo = Utils.findRequiredViewAsType(source, R.id.tv_zc_info, "field 'tvZcInfo'", TextView.class);
    target.imgKj = Utils.findRequiredViewAsType(source, R.id.img_kj, "field 'imgKj'", ImageView.class);
    target.tvPrice = Utils.findRequiredViewAsType(source, R.id.tv_price, "field 'tvPrice'", TextView.class);
    target.tvCny = Utils.findRequiredViewAsType(source, R.id.tv_cny, "field 'tvCny'", TextView.class);
    target.rlContent = Utils.findRequiredViewAsType(source, R.id.rl_content, "field 'rlContent'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MineFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.viewTop = null;
    target.viewBarrier = null;
    target.groupUnLogin = null;
    target.tvLogin = null;
    target.tvTips = null;
    target.groupLogin = null;
    target.viewInfoCenter = null;
    target.tvName = null;
    target.tvQd = null;
    target.imgQd = null;
    target.tvUid = null;
    target.tvZc = null;
    target.tvZcInfo = null;
    target.imgKj = null;
    target.tvPrice = null;
    target.tvCny = null;
    target.rlContent = null;
  }
}

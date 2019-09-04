// Generated code from Butter Knife. Do not modify!
package com.sskj.mine;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class InviteActivity_ViewBinding implements Unbinder {
  private InviteActivity target;

  @UiThread
  public InviteActivity_ViewBinding(InviteActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public InviteActivity_ViewBinding(InviteActivity target, View source) {
    this.target = target;

    target.copyTv = Utils.findRequiredViewAsType(source, R.id.copy_tv, "field 'copyTv'", TextView.class);
    target.inviteCode = Utils.findRequiredViewAsType(source, R.id.invite_code, "field 'inviteCode'", TextView.class);
    target.mineConstraintlayout = Utils.findRequiredViewAsType(source, R.id.mine_constraintlayout, "field 'mineConstraintlayout'", ConstraintLayout.class);
    target.shareImg = Utils.findRequiredViewAsType(source, R.id.share_img, "field 'shareImg'", TextView.class);
    target.myTeam = Utils.findRequiredViewAsType(source, R.id.my_team, "field 'myTeam'", TextView.class);
    target.commissionDetail = Utils.findRequiredViewAsType(source, R.id.commission_detail, "field 'commissionDetail'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    InviteActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.copyTv = null;
    target.inviteCode = null;
    target.mineConstraintlayout = null;
    target.shareImg = null;
    target.myTeam = null;
    target.commissionDetail = null;
  }
}

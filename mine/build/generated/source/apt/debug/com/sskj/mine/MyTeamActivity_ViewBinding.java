// Generated code from Butter Knife. Do not modify!
package com.sskj.mine;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.constraint.Guideline;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyTeamActivity_ViewBinding implements Unbinder {
  private MyTeamActivity target;

  @UiThread
  public MyTeamActivity_ViewBinding(MyTeamActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MyTeamActivity_ViewBinding(MyTeamActivity target, View source) {
    this.target = target;

    target.teamCount = Utils.findRequiredViewAsType(source, R.id.team_count, "field 'teamCount'", TextView.class);
    target.mineGuideline = Utils.findRequiredViewAsType(source, R.id.mine_guideline, "field 'mineGuideline'", Guideline.class);
    target.increaseCount = Utils.findRequiredViewAsType(source, R.id.increase_count, "field 'increaseCount'", TextView.class);
    target.teamList = Utils.findRequiredViewAsType(source, R.id.team_list, "field 'teamList'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MyTeamActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.teamCount = null;
    target.mineGuideline = null;
    target.increaseCount = null;
    target.teamList = null;
  }
}

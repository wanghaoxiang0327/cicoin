// Generated code from Butter Knife. Do not modify!
package com.sskj.mine;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DirectorActivity_ViewBinding implements Unbinder {
  private DirectorActivity target;

  @UiThread
  public DirectorActivity_ViewBinding(DirectorActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DirectorActivity_ViewBinding(DirectorActivity target, View source) {
    this.target = target;

    target.dividendList = Utils.findRequiredViewAsType(source, R.id.dividend_list, "field 'dividendList'", RecyclerView.class);
    target.topList = Utils.findRequiredViewAsType(source, R.id.topList, "field 'topList'", RecyclerView.class);
    target.contentLayout = Utils.findRequiredViewAsType(source, R.id.content_layout, "field 'contentLayout'", NestedScrollView.class);
    target.tvContract = Utils.findRequiredViewAsType(source, R.id.tv_contract, "field 'tvContract'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DirectorActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.dividendList = null;
    target.topList = null;
    target.contentLayout = null;
    target.tvContract = null;
  }
}

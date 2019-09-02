// Generated code from Butter Knife. Do not modify!
package com.sskj.common.dialog;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.sskj.common.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TipAboutUsDialog_ViewBinding implements Unbinder {
  private TipAboutUsDialog target;

  @UiThread
  public TipAboutUsDialog_ViewBinding(TipAboutUsDialog target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TipAboutUsDialog_ViewBinding(TipAboutUsDialog target, View source) {
    this.target = target;

    target.tvEmail = Utils.findRequiredViewAsType(source, R.id.tvEmail, "field 'tvEmail'", TextView.class);
    target.tvCancel = Utils.findRequiredViewAsType(source, R.id.tvCancel, "field 'tvCancel'", TextView.class);
    target.tvCopy = Utils.findRequiredViewAsType(source, R.id.tvCopy, "field 'tvCopy'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TipAboutUsDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvEmail = null;
    target.tvCancel = null;
    target.tvCopy = null;
  }
}

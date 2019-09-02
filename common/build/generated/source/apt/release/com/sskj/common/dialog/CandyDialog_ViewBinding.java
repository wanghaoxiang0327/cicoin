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

public class CandyDialog_ViewBinding implements Unbinder {
  private CandyDialog target;

  @UiThread
  public CandyDialog_ViewBinding(CandyDialog target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CandyDialog_ViewBinding(CandyDialog target, View source) {
    this.target = target;

    target.tvDialogContent = Utils.findRequiredViewAsType(source, R.id.tv_dialog_content, "field 'tvDialogContent'", TextView.class);
    target.tvDialogCancel = Utils.findRequiredViewAsType(source, R.id.tv_dialog_cancel, "field 'tvDialogCancel'", TextView.class);
    target.tvDialogConfirm = Utils.findRequiredViewAsType(source, R.id.tv_dialog_confirm, "field 'tvDialogConfirm'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CandyDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvDialogContent = null;
    target.tvDialogCancel = null;
    target.tvDialogConfirm = null;
  }
}

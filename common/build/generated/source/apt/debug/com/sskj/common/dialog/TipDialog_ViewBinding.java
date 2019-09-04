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

public class TipDialog_ViewBinding implements Unbinder {
  private TipDialog target;

  @UiThread
  public TipDialog_ViewBinding(TipDialog target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TipDialog_ViewBinding(TipDialog target, View source) {
    this.target = target;

    target.dialogTitle = Utils.findRequiredViewAsType(source, R.id.dialog_title, "field 'dialogTitle'", TextView.class);
    target.dialogContent = Utils.findRequiredViewAsType(source, R.id.dialog_content, "field 'dialogContent'", TextView.class);
    target.cancelBtn = Utils.findRequiredViewAsType(source, R.id.cancel_btn, "field 'cancelBtn'", TextView.class);
    target.confirmBtn = Utils.findRequiredViewAsType(source, R.id.confirm_btn, "field 'confirmBtn'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TipDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.dialogTitle = null;
    target.dialogContent = null;
    target.cancelBtn = null;
    target.confirmBtn = null;
  }
}

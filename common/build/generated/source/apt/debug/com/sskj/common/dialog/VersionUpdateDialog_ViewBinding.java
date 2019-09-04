// Generated code from Butter Knife. Do not modify!
package com.sskj.common.dialog;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.sskj.common.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class VersionUpdateDialog_ViewBinding implements Unbinder {
  private VersionUpdateDialog target;

  @UiThread
  public VersionUpdateDialog_ViewBinding(VersionUpdateDialog target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public VersionUpdateDialog_ViewBinding(VersionUpdateDialog target, View source) {
    this.target = target;

    target.updateTip = Utils.findRequiredViewAsType(source, R.id.update_tip, "field 'updateTip'", TextView.class);
    target.updateBtn = Utils.findRequiredViewAsType(source, R.id.update_btn, "field 'updateBtn'", Button.class);
    target.cancelBtn = Utils.findRequiredViewAsType(source, R.id.cancel_btn, "field 'cancelBtn'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    VersionUpdateDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.updateTip = null;
    target.updateBtn = null;
    target.cancelBtn = null;
  }
}

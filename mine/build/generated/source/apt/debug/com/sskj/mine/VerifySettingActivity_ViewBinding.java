// Generated code from Butter Knife. Do not modify!
package com.sskj.mine;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.sskj.common.view.CheckButton;
import java.lang.IllegalStateException;
import java.lang.Override;

public class VerifySettingActivity_ViewBinding implements Unbinder {
  private VerifySettingActivity target;

  @UiThread
  public VerifySettingActivity_ViewBinding(VerifySettingActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public VerifySettingActivity_ViewBinding(VerifySettingActivity target, View source) {
    this.target = target;

    target.verifyName = Utils.findRequiredViewAsType(source, R.id.verify_name, "field 'verifyName'", TextView.class);
    target.verifyAccount = Utils.findRequiredViewAsType(source, R.id.verify_account, "field 'verifyAccount'", TextView.class);
    target.verifyStatus = Utils.findRequiredViewAsType(source, R.id.verify_status, "field 'verifyStatus'", TextView.class);
    target.verifyCheck = Utils.findRequiredViewAsType(source, R.id.verify_check, "field 'verifyCheck'", CheckButton.class);
    target.verifyCheckText = Utils.findRequiredViewAsType(source, R.id.verify_check_text, "field 'verifyCheckText'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    VerifySettingActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.verifyName = null;
    target.verifyAccount = null;
    target.verifyStatus = null;
    target.verifyCheck = null;
    target.verifyCheckText = null;
  }
}

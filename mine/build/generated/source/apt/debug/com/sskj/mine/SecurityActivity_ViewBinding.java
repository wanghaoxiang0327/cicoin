// Generated code from Butter Knife. Do not modify!
package com.sskj.mine;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.allen.library.SuperTextView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SecurityActivity_ViewBinding implements Unbinder {
  private SecurityActivity target;

  @UiThread
  public SecurityActivity_ViewBinding(SecurityActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SecurityActivity_ViewBinding(SecurityActivity target, View source) {
    this.target = target;

    target.menuSmsVerify = Utils.findRequiredViewAsType(source, R.id.menu_sms_verify, "field 'menuSmsVerify'", SuperTextView.class);
    target.menuGoogleVerify = Utils.findRequiredViewAsType(source, R.id.menu_google_verify, "field 'menuGoogleVerify'", SuperTextView.class);
    target.menuEmailVerify = Utils.findRequiredViewAsType(source, R.id.menu_email_verify, "field 'menuEmailVerify'", SuperTextView.class);
    target.menuLoginPs = Utils.findRequiredViewAsType(source, R.id.menu_login_ps, "field 'menuLoginPs'", SuperTextView.class);
    target.menuPayPs = Utils.findRequiredViewAsType(source, R.id.menu_pay_ps, "field 'menuPayPs'", SuperTextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SecurityActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.menuSmsVerify = null;
    target.menuGoogleVerify = null;
    target.menuEmailVerify = null;
    target.menuLoginPs = null;
    target.menuPayPs = null;
  }
}

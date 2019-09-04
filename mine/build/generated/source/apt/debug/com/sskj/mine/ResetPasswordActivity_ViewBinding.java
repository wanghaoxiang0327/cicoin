// Generated code from Butter Knife. Do not modify!
package com.sskj.mine;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ResetPasswordActivity_ViewBinding implements Unbinder {
  private ResetPasswordActivity target;

  @UiThread
  public ResetPasswordActivity_ViewBinding(ResetPasswordActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ResetPasswordActivity_ViewBinding(ResetPasswordActivity target, View source) {
    this.target = target;

    target.psEdt = Utils.findRequiredViewAsType(source, R.id.ps_edt, "field 'psEdt'", EditText.class);
    target.newPsEdt = Utils.findRequiredViewAsType(source, R.id.new_ps_edt, "field 'newPsEdt'", EditText.class);
    target.showNewPsImg = Utils.findRequiredViewAsType(source, R.id.show_new_ps_img, "field 'showNewPsImg'", ImageView.class);
    target.psRepeatEdt = Utils.findRequiredViewAsType(source, R.id.ps_repeat_edt, "field 'psRepeatEdt'", EditText.class);
    target.showRepeatPsImg = Utils.findRequiredViewAsType(source, R.id.show_repeat_ps_img, "field 'showRepeatPsImg'", ImageView.class);
    target.submit = Utils.findRequiredViewAsType(source, R.id.submit, "field 'submit'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ResetPasswordActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.psEdt = null;
    target.newPsEdt = null;
    target.showNewPsImg = null;
    target.psRepeatEdt = null;
    target.showRepeatPsImg = null;
    target.submit = null;
  }
}

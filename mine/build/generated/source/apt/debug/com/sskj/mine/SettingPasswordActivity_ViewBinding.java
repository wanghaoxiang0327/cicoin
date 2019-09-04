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

public class SettingPasswordActivity_ViewBinding implements Unbinder {
  private SettingPasswordActivity target;

  @UiThread
  public SettingPasswordActivity_ViewBinding(SettingPasswordActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SettingPasswordActivity_ViewBinding(SettingPasswordActivity target, View source) {
    this.target = target;

    target.psEdt = Utils.findRequiredViewAsType(source, R.id.ps_edt, "field 'psEdt'", EditText.class);
    target.showPsImg = Utils.findRequiredViewAsType(source, R.id.show_ps_img, "field 'showPsImg'", ImageView.class);
    target.psRepeatEdt = Utils.findRequiredViewAsType(source, R.id.ps_repeat_edt, "field 'psRepeatEdt'", EditText.class);
    target.showRepeatPsImg = Utils.findRequiredViewAsType(source, R.id.show_repeat_ps_img, "field 'showRepeatPsImg'", ImageView.class);
    target.submit = Utils.findRequiredViewAsType(source, R.id.submit, "field 'submit'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SettingPasswordActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.psEdt = null;
    target.showPsImg = null;
    target.psRepeatEdt = null;
    target.showRepeatPsImg = null;
    target.submit = null;
  }
}

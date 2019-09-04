// Generated code from Butter Knife. Do not modify!
package com.sskj.mine;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BindMobileOrEmailActivity_ViewBinding implements Unbinder {
  private BindMobileOrEmailActivity target;

  @UiThread
  public BindMobileOrEmailActivity_ViewBinding(BindMobileOrEmailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public BindMobileOrEmailActivity_ViewBinding(BindMobileOrEmailActivity target, View source) {
    this.target = target;

    target.verifyName = Utils.findRequiredViewAsType(source, R.id.verify_name, "field 'verifyName'", TextView.class);
    target.verifyAccountEdt = Utils.findRequiredViewAsType(source, R.id.verify_account_edt, "field 'verifyAccountEdt'", EditText.class);
    target.verifyCodeName = Utils.findRequiredViewAsType(source, R.id.verify_code_name, "field 'verifyCodeName'", TextView.class);
    target.getCode = Utils.findRequiredViewAsType(source, R.id.get_code_tv, "field 'getCode'", TextView.class);
    target.edtVerifyCode = Utils.findRequiredViewAsType(source, R.id.edt_verify_code, "field 'edtVerifyCode'", EditText.class);
    target.submit = Utils.findRequiredViewAsType(source, R.id.submit, "field 'submit'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    BindMobileOrEmailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.verifyName = null;
    target.verifyAccountEdt = null;
    target.verifyCodeName = null;
    target.getCode = null;
    target.edtVerifyCode = null;
    target.submit = null;
  }
}

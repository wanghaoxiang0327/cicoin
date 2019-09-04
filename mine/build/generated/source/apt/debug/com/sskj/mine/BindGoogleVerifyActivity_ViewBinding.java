// Generated code from Butter Knife. Do not modify!
package com.sskj.mine;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BindGoogleVerifyActivity_ViewBinding implements Unbinder {
  private BindGoogleVerifyActivity target;

  @UiThread
  public BindGoogleVerifyActivity_ViewBinding(BindGoogleVerifyActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public BindGoogleVerifyActivity_ViewBinding(BindGoogleVerifyActivity target, View source) {
    this.target = target;

    target.verifyName = Utils.findRequiredViewAsType(source, R.id.verify_name, "field 'verifyName'", TextView.class);
    target.googleCode = Utils.findRequiredViewAsType(source, R.id.google_code, "field 'googleCode'", TextView.class);
    target.verifyCodeName = Utils.findRequiredViewAsType(source, R.id.verify_code_name, "field 'verifyCodeName'", TextView.class);
    target.edtVerifyCode = Utils.findRequiredViewAsType(source, R.id.edt_verify_code, "field 'edtVerifyCode'", EditText.class);
    target.qrCodeImg = Utils.findRequiredViewAsType(source, R.id.qr_code_img, "field 'qrCodeImg'", ImageView.class);
    target.copyTv = Utils.findRequiredViewAsType(source, R.id.copy_tv, "field 'copyTv'", TextView.class);
    target.submit = Utils.findRequiredViewAsType(source, R.id.submit, "field 'submit'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    BindGoogleVerifyActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.verifyName = null;
    target.googleCode = null;
    target.verifyCodeName = null;
    target.edtVerifyCode = null;
    target.qrCodeImg = null;
    target.copyTv = null;
    target.submit = null;
  }
}

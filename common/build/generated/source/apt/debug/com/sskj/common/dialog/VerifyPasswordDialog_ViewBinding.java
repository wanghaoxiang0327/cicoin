// Generated code from Butter Knife. Do not modify!
package com.sskj.common.dialog;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.sskj.common.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class VerifyPasswordDialog_ViewBinding implements Unbinder {
  private VerifyPasswordDialog target;

  @UiThread
  public VerifyPasswordDialog_ViewBinding(VerifyPasswordDialog target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public VerifyPasswordDialog_ViewBinding(VerifyPasswordDialog target, View source) {
    this.target = target;

    target.titleTv = Utils.findRequiredViewAsType(source, R.id.title_tv, "field 'titleTv'", TextView.class);
    target.cancelTv = Utils.findRequiredViewAsType(source, R.id.cancel_tv, "field 'cancelTv'", TextView.class);
    target.psEdt = Utils.findRequiredViewAsType(source, R.id.ps_edt, "field 'psEdt'", EditText.class);
    target.psLayout = Utils.findRequiredViewAsType(source, R.id.ps_layout, "field 'psLayout'", LinearLayout.class);
    target.smsCodeEdt = Utils.findRequiredViewAsType(source, R.id.sms_code_edt, "field 'smsCodeEdt'", EditText.class);
    target.getCodeTv = Utils.findRequiredViewAsType(source, R.id.get_code_tv, "field 'getCodeTv'", TextView.class);
    target.smsLayout = Utils.findRequiredViewAsType(source, R.id.sms_layout, "field 'smsLayout'", LinearLayout.class);
    target.googleCodeEdt = Utils.findRequiredViewAsType(source, R.id.google_code_edt, "field 'googleCodeEdt'", EditText.class);
    target.pastTv = Utils.findRequiredViewAsType(source, R.id.past_tv, "field 'pastTv'", TextView.class);
    target.googleLayout = Utils.findRequiredViewAsType(source, R.id.google_layout, "field 'googleLayout'", LinearLayout.class);
    target.submit = Utils.findRequiredViewAsType(source, R.id.submit, "field 'submit'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    VerifyPasswordDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.titleTv = null;
    target.cancelTv = null;
    target.psEdt = null;
    target.psLayout = null;
    target.smsCodeEdt = null;
    target.getCodeTv = null;
    target.smsLayout = null;
    target.googleCodeEdt = null;
    target.pastTv = null;
    target.googleLayout = null;
    target.submit = null;
  }
}

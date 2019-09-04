// Generated code from Butter Knife. Do not modify!
package com.sskj.contract.login;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.allen.library.SuperButton;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ForgetPsActivity_ViewBinding implements Unbinder {
  private ForgetPsActivity target;

  @UiThread
  public ForgetPsActivity_ViewBinding(ForgetPsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ForgetPsActivity_ViewBinding(ForgetPsActivity target, View source) {
    this.target = target;

    target.back = Utils.findRequiredViewAsType(source, R.id.back, "field 'back'", ImageView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
    target.mobile = Utils.findRequiredViewAsType(source, R.id.mobile, "field 'mobile'", TextView.class);
    target.mobileLine = Utils.findRequiredView(source, R.id.mobile_line, "field 'mobileLine'");
    target.email = Utils.findRequiredViewAsType(source, R.id.email, "field 'email'", TextView.class);
    target.emailLine = Utils.findRequiredView(source, R.id.email_line, "field 'emailLine'");
    target.t1 = Utils.findRequiredViewAsType(source, R.id.t1, "field 't1'", TextView.class);
    target.etNum = Utils.findRequiredViewAsType(source, R.id.etNum, "field 'etNum'", EditText.class);
    target.ivClose = Utils.findRequiredViewAsType(source, R.id.iv_close, "field 'ivClose'", ImageView.class);
    target.t2 = Utils.findRequiredViewAsType(source, R.id.t2, "field 't2'", TextView.class);
    target.etCode = Utils.findRequiredViewAsType(source, R.id.etCode, "field 'etCode'", EditText.class);
    target.tvCode = Utils.findRequiredViewAsType(source, R.id.tvCode, "field 'tvCode'", TextView.class);
    target.t3 = Utils.findRequiredViewAsType(source, R.id.t3, "field 't3'", TextView.class);
    target.etPwd1 = Utils.findRequiredViewAsType(source, R.id.etPwd1, "field 'etPwd1'", EditText.class);
    target.ivPwd1 = Utils.findRequiredViewAsType(source, R.id.iv_pwd1, "field 'ivPwd1'", ImageView.class);
    target.t4 = Utils.findRequiredViewAsType(source, R.id.t4, "field 't4'", TextView.class);
    target.etPwd2 = Utils.findRequiredViewAsType(source, R.id.etPwd2, "field 'etPwd2'", EditText.class);
    target.ivPwd2 = Utils.findRequiredViewAsType(source, R.id.iv_pwd2, "field 'ivPwd2'", ImageView.class);
    target.register = Utils.findRequiredViewAsType(source, R.id.register, "field 'register'", SuperButton.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ForgetPsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.back = null;
    target.title = null;
    target.mobile = null;
    target.mobileLine = null;
    target.email = null;
    target.emailLine = null;
    target.t1 = null;
    target.etNum = null;
    target.ivClose = null;
    target.t2 = null;
    target.etCode = null;
    target.tvCode = null;
    target.t3 = null;
    target.etPwd1 = null;
    target.ivPwd1 = null;
    target.t4 = null;
    target.etPwd2 = null;
    target.ivPwd2 = null;
    target.register = null;
  }
}

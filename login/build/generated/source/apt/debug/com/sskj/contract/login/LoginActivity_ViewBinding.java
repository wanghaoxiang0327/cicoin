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

public class LoginActivity_ViewBinding implements Unbinder {
  private LoginActivity target;

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target, View source) {
    this.target = target;

    target.back = Utils.findRequiredViewAsType(source, R.id.back, "field 'back'", ImageView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
    target.etNum = Utils.findRequiredViewAsType(source, R.id.etNum, "field 'etNum'", EditText.class);
    target.ivClose = Utils.findRequiredViewAsType(source, R.id.iv_close, "field 'ivClose'", ImageView.class);
    target.etPwd = Utils.findRequiredViewAsType(source, R.id.etPwd, "field 'etPwd'", EditText.class);
    target.ivPwd = Utils.findRequiredViewAsType(source, R.id.iv_pwd, "field 'ivPwd'", ImageView.class);
    target.login = Utils.findRequiredViewAsType(source, R.id.login, "field 'login'", SuperButton.class);
    target.forget = Utils.findRequiredViewAsType(source, R.id.forget, "field 'forget'", TextView.class);
    target.tips = Utils.findRequiredViewAsType(source, R.id.tips, "field 'tips'", TextView.class);
    target.register = Utils.findRequiredViewAsType(source, R.id.register, "field 'register'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.back = null;
    target.title = null;
    target.etNum = null;
    target.ivClose = null;
    target.etPwd = null;
    target.ivPwd = null;
    target.login = null;
    target.forget = null;
    target.tips = null;
    target.register = null;
  }
}

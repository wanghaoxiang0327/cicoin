// Generated code from Butter Knife. Do not modify!
package com.sskj.asset;

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

public class ZoomActivity_ViewBinding implements Unbinder {
  private ZoomActivity target;

  @UiThread
  public ZoomActivity_ViewBinding(ZoomActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ZoomActivity_ViewBinding(ZoomActivity target, View source) {
    this.target = target;

    target.acount1 = Utils.findRequiredViewAsType(source, R.id.acount1, "field 'acount1'", TextView.class);
    target.acount2 = Utils.findRequiredViewAsType(source, R.id.acount2, "field 'acount2'", TextView.class);
    target.ivSwitch = Utils.findRequiredViewAsType(source, R.id.iv_switch, "field 'ivSwitch'", ImageView.class);
    target.tvNumber = Utils.findRequiredViewAsType(source, R.id.tv_number, "field 'tvNumber'", TextView.class);
    target.edtInputNumber = Utils.findRequiredViewAsType(source, R.id.edt_input_number, "field 'edtInputNumber'", EditText.class);
    target.tvAll = Utils.findRequiredViewAsType(source, R.id.tv_all, "field 'tvAll'", TextView.class);
    target.submit = Utils.findRequiredViewAsType(source, R.id.submit, "field 'submit'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ZoomActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.acount1 = null;
    target.acount2 = null;
    target.ivSwitch = null;
    target.tvNumber = null;
    target.edtInputNumber = null;
    target.tvAll = null;
    target.submit = null;
  }
}

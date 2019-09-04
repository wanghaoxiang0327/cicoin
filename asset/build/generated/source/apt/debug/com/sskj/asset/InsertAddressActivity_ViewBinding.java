// Generated code from Butter Knife. Do not modify!
package com.sskj.asset;

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

public class InsertAddressActivity_ViewBinding implements Unbinder {
  private InsertAddressActivity target;

  @UiThread
  public InsertAddressActivity_ViewBinding(InsertAddressActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public InsertAddressActivity_ViewBinding(InsertAddressActivity target, View source) {
    this.target = target;

    target.addressEdt = Utils.findRequiredViewAsType(source, R.id.address_edt, "field 'addressEdt'", EditText.class);
    target.selectAddress = Utils.findRequiredViewAsType(source, R.id.select_address, "field 'selectAddress'", ImageView.class);
    target.remarkEdt = Utils.findRequiredViewAsType(source, R.id.remark_edt, "field 'remarkEdt'", EditText.class);
    target.submit = Utils.findRequiredViewAsType(source, R.id.submit, "field 'submit'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    InsertAddressActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.addressEdt = null;
    target.selectAddress = null;
    target.remarkEdt = null;
    target.submit = null;
  }
}

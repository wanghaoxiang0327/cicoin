// Generated code from Butter Knife. Do not modify!
package com.sskj.asset;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.allen.library.SuperTextView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TransferActivity_ViewBinding implements Unbinder {
  private TransferActivity target;

  @UiThread
  public TransferActivity_ViewBinding(TransferActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TransferActivity_ViewBinding(TransferActivity target, View source) {
    this.target = target;

    target.accountEdt = Utils.findRequiredViewAsType(source, R.id.account_edt, "field 'accountEdt'", EditText.class);
    target.selectCoin = Utils.findRequiredViewAsType(source, R.id.select_coin, "field 'selectCoin'", SuperTextView.class);
    target.usefulTv = Utils.findRequiredViewAsType(source, R.id.useful_tv, "field 'usefulTv'", TextView.class);
    target.feeTv = Utils.findRequiredViewAsType(source, R.id.fee_tv, "field 'feeTv'", TextView.class);
    target.countEdt = Utils.findRequiredViewAsType(source, R.id.count_edt, "field 'countEdt'", EditText.class);
    target.submit = Utils.findRequiredViewAsType(source, R.id.submit, "field 'submit'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TransferActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.accountEdt = null;
    target.selectCoin = null;
    target.usefulTv = null;
    target.feeTv = null;
    target.countEdt = null;
    target.submit = null;
  }
}

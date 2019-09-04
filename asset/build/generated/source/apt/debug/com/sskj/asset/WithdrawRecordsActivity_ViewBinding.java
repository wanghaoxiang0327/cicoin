// Generated code from Butter Knife. Do not modify!
package com.sskj.asset;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.allen.library.SuperTextView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WithdrawRecordsActivity_ViewBinding implements Unbinder {
  private WithdrawRecordsActivity target;

  @UiThread
  public WithdrawRecordsActivity_ViewBinding(WithdrawRecordsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public WithdrawRecordsActivity_ViewBinding(WithdrawRecordsActivity target, View source) {
    this.target = target;

    target.selectCoin = Utils.findRequiredViewAsType(source, R.id.select_coin, "field 'selectCoin'", SuperTextView.class);
    target.withdrawRecords = Utils.findRequiredViewAsType(source, R.id.withdraw_records, "field 'withdrawRecords'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    WithdrawRecordsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.selectCoin = null;
    target.withdrawRecords = null;
  }
}

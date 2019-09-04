// Generated code from Butter Knife. Do not modify!
package com.sskj.asset;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.sskj.common.view.ToolBarLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TransferRecordsActivity_ViewBinding implements Unbinder {
  private TransferRecordsActivity target;

  @UiThread
  public TransferRecordsActivity_ViewBinding(TransferRecordsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TransferRecordsActivity_ViewBinding(TransferRecordsActivity target, View source) {
    this.target = target;

    target.recordsList = Utils.findRequiredViewAsType(source, R.id.records_list, "field 'recordsList'", RecyclerView.class);
    target.tbl = Utils.findRequiredViewAsType(source, R.id.tbl, "field 'tbl'", ToolBarLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TransferRecordsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recordsList = null;
    target.tbl = null;
  }
}

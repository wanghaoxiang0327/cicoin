// Generated code from Butter Knife. Do not modify!
package com.sskj.common.dialog;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.sskj.common.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SelectCoinDialog_ViewBinding implements Unbinder {
  private SelectCoinDialog target;

  @UiThread
  public SelectCoinDialog_ViewBinding(SelectCoinDialog target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SelectCoinDialog_ViewBinding(SelectCoinDialog target, View source) {
    this.target = target;

    target.coinList = Utils.findRequiredViewAsType(source, R.id.coin_list, "field 'coinList'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SelectCoinDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.coinList = null;
  }
}

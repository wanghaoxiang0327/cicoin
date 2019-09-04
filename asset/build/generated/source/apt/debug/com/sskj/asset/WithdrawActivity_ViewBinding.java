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
import com.allen.library.SuperTextView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WithdrawActivity_ViewBinding implements Unbinder {
  private WithdrawActivity target;

  @UiThread
  public WithdrawActivity_ViewBinding(WithdrawActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public WithdrawActivity_ViewBinding(WithdrawActivity target, View source) {
    this.target = target;

    target.selectCoin = Utils.findRequiredViewAsType(source, R.id.select_coin, "field 'selectCoin'", SuperTextView.class);
    target.usefulTv = Utils.findRequiredViewAsType(source, R.id.useful_tv, "field 'usefulTv'", TextView.class);
    target.addressEdt = Utils.findRequiredViewAsType(source, R.id.address_edt, "field 'addressEdt'", EditText.class);
    target.selectAddress = Utils.findRequiredViewAsType(source, R.id.select_address, "field 'selectAddress'", ImageView.class);
    target.countEdt = Utils.findRequiredViewAsType(source, R.id.count_edt, "field 'countEdt'", EditText.class);
    target.coinName = Utils.findRequiredViewAsType(source, R.id.coin_name, "field 'coinName'", TextView.class);
    target.all = Utils.findRequiredViewAsType(source, R.id.all, "field 'all'", TextView.class);
    target.feeTv = Utils.findRequiredViewAsType(source, R.id.fee_tv, "field 'feeTv'", TextView.class);
    target.arriveCount = Utils.findRequiredViewAsType(source, R.id.arrive_count, "field 'arriveCount'", TextView.class);
    target.submit = Utils.findRequiredViewAsType(source, R.id.submit, "field 'submit'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    WithdrawActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.selectCoin = null;
    target.usefulTv = null;
    target.addressEdt = null;
    target.selectAddress = null;
    target.countEdt = null;
    target.coinName = null;
    target.all = null;
    target.feeTv = null;
    target.arriveCount = null;
    target.submit = null;
  }
}

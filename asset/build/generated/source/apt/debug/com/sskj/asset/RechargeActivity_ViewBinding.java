// Generated code from Butter Knife. Do not modify!
package com.sskj.asset;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.allen.library.SuperTextView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RechargeActivity_ViewBinding implements Unbinder {
  private RechargeActivity target;

  @UiThread
  public RechargeActivity_ViewBinding(RechargeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RechargeActivity_ViewBinding(RechargeActivity target, View source) {
    this.target = target;

    target.selectCoin = Utils.findRequiredViewAsType(source, R.id.select_coin, "field 'selectCoin'", SuperTextView.class);
    target.qrCodeImg = Utils.findRequiredViewAsType(source, R.id.qr_code_img, "field 'qrCodeImg'", ImageView.class);
    target.rechargeAddressTv = Utils.findRequiredViewAsType(source, R.id.recharge_address_tv, "field 'rechargeAddressTv'", TextView.class);
    target.rechargeTip = Utils.findRequiredViewAsType(source, R.id.recharge_tip, "field 'rechargeTip'", TextView.class);
    target.copy = Utils.findRequiredViewAsType(source, R.id.copy, "field 'copy'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RechargeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.selectCoin = null;
    target.qrCodeImg = null;
    target.rechargeAddressTv = null;
    target.rechargeTip = null;
    target.copy = null;
  }
}

// Generated code from Butter Knife. Do not modify!
package com.sskj.asset;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddressListActivity_ViewBinding implements Unbinder {
  private AddressListActivity target;

  @UiThread
  public AddressListActivity_ViewBinding(AddressListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AddressListActivity_ViewBinding(AddressListActivity target, View source) {
    this.target = target;

    target.addBtcAddress = Utils.findRequiredViewAsType(source, R.id.add_btc_address, "field 'addBtcAddress'", ImageView.class);
    target.btcAddressList = Utils.findRequiredViewAsType(source, R.id.btc_address_list, "field 'btcAddressList'", RecyclerView.class);
    target.addEthAddress = Utils.findRequiredViewAsType(source, R.id.add_eth_address, "field 'addEthAddress'", ImageView.class);
    target.ethAddressList = Utils.findRequiredViewAsType(source, R.id.eth_address_list, "field 'ethAddressList'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AddressListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.addBtcAddress = null;
    target.btcAddressList = null;
    target.addEthAddress = null;
    target.ethAddressList = null;
  }
}

// Generated code from Butter Knife. Do not modify!
package com.sskj.asset;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AssetRecordsActivity_ViewBinding implements Unbinder {
  private AssetRecordsActivity target;

  @UiThread
  public AssetRecordsActivity_ViewBinding(AssetRecordsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AssetRecordsActivity_ViewBinding(AssetRecordsActivity target, View source) {
    this.target = target;

    target.selectCoinName = Utils.findRequiredViewAsType(source, R.id.select_coin_name, "field 'selectCoinName'", TextView.class);
    target.selectTypeName = Utils.findRequiredViewAsType(source, R.id.select_type_name, "field 'selectTypeName'", TextView.class);
    target.assetRecordsList = Utils.findRequiredViewAsType(source, R.id.asset_records_list, "field 'assetRecordsList'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AssetRecordsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.selectCoinName = null;
    target.selectTypeName = null;
    target.assetRecordsList = null;
  }
}

// Generated code from Butter Knife. Do not modify!
package com.sskj.miner.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.sskj.common.view.WaterView;
import com.sskj.miner.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MinerFragment_ViewBinding implements Unbinder {
  private MinerFragment target;

  @UiThread
  public MinerFragment_ViewBinding(MinerFragment target, View source) {
    this.target = target;

    target.tvRuleMiner = Utils.findRequiredViewAsType(source, R.id.tv_rule_miner, "field 'tvRuleMiner'", TextView.class);
    target.tvMsgMiner = Utils.findRequiredViewAsType(source, R.id.tv_msg_miner, "field 'tvMsgMiner'", TextView.class);
    target.viewWaterMiner = Utils.findRequiredViewAsType(source, R.id.view_water_miner, "field 'viewWaterMiner'", WaterView.class);
    target.tvJyMiner = Utils.findRequiredViewAsType(source, R.id.tv_jy_miner, "field 'tvJyMiner'", TextView.class);
    target.tvDetailsMiner = Utils.findRequiredViewAsType(source, R.id.tv_details_miner, "field 'tvDetailsMiner'", TextView.class);
    target.tvMoneyMiner = Utils.findRequiredViewAsType(source, R.id.tv_money_miner, "field 'tvMoneyMiner'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MinerFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvRuleMiner = null;
    target.tvMsgMiner = null;
    target.viewWaterMiner = null;
    target.tvJyMiner = null;
    target.tvDetailsMiner = null;
    target.tvMoneyMiner = null;
  }
}

// Generated code from Butter Knife. Do not modify!
package com.sskj.mine;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ShareImgActivity_ViewBinding implements Unbinder {
  private ShareImgActivity target;

  @UiThread
  public ShareImgActivity_ViewBinding(ShareImgActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ShareImgActivity_ViewBinding(ShareImgActivity target, View source) {
    this.target = target;

    target.qrCodeImg = Utils.findRequiredViewAsType(source, R.id.qr_code_img, "field 'qrCodeImg'", ImageView.class);
    target.shareLayout = Utils.findRequiredViewAsType(source, R.id.share_layout, "field 'shareLayout'", FrameLayout.class);
    target.rvImg = Utils.findRequiredViewAsType(source, R.id.rv_img, "field 'rvImg'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ShareImgActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.qrCodeImg = null;
    target.shareLayout = null;
    target.rvImg = null;
  }
}

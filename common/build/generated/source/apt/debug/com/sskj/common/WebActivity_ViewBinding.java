// Generated code from Butter Knife. Do not modify!
package com.sskj.common;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WebActivity_ViewBinding implements Unbinder {
  private WebActivity target;

  @UiThread
  public WebActivity_ViewBinding(WebActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public WebActivity_ViewBinding(WebActivity target, View source) {
    this.target = target;

    target.text = Utils.findRequiredViewAsType(source, R.id.text, "field 'text'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    WebActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.text = null;
  }
}

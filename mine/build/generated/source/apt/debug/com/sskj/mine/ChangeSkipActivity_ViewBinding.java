// Generated code from Butter Knife. Do not modify!
package com.sskj.mine;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ChangeSkipActivity_ViewBinding implements Unbinder {
  private ChangeSkipActivity target;

  @UiThread
  public ChangeSkipActivity_ViewBinding(ChangeSkipActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ChangeSkipActivity_ViewBinding(ChangeSkipActivity target, View source) {
    this.target = target;

    target.light = Utils.findRequiredViewAsType(source, R.id.light, "field 'light'", RadioButton.class);
    target.night = Utils.findRequiredViewAsType(source, R.id.night, "field 'night'", RadioButton.class);
    target.languageGroup = Utils.findRequiredViewAsType(source, R.id.languageGroup, "field 'languageGroup'", RadioGroup.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ChangeSkipActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.light = null;
    target.night = null;
    target.languageGroup = null;
  }
}

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

public class LanguageActivity_ViewBinding implements Unbinder {
  private LanguageActivity target;

  @UiThread
  public LanguageActivity_ViewBinding(LanguageActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LanguageActivity_ViewBinding(LanguageActivity target, View source) {
    this.target = target;

    target.chinese = Utils.findRequiredViewAsType(source, R.id.chinese, "field 'chinese'", RadioButton.class);
    target.chineseP = Utils.findRequiredViewAsType(source, R.id.chinese_p, "field 'chineseP'", RadioButton.class);
    target.english = Utils.findRequiredViewAsType(source, R.id.english, "field 'english'", RadioButton.class);
    target.korean = Utils.findRequiredViewAsType(source, R.id.korean, "field 'korean'", RadioButton.class);
    target.languageGroup = Utils.findRequiredViewAsType(source, R.id.languageGroup, "field 'languageGroup'", RadioGroup.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LanguageActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.chinese = null;
    target.chineseP = null;
    target.english = null;
    target.korean = null;
    target.languageGroup = null;
  }
}

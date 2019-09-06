package com.sskj.common.helper;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

import com.github.nukc.stateview.AnimatorProvider;

public class FadeAnimatorProvider implements AnimatorProvider {
    @Override
    public Animator showAnimation(View view) {

        return ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
    }

    @Override
    public Animator hideAnimation(View view) {
        return ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);
    }
}

package com.sskj.common.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;

import com.sskj.common.App;
import com.sskj.common.utils.ScreenUtil;

public class MaxHeightScrollView extends NestedScrollView {
    public MaxHeightScrollView(@NonNull Context context) {
        super(context);
    }

    public MaxHeightScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MaxHeightScrollView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = ScreenUtil.getScreenHeight(App.INSTANCE);
        heightMeasureSpec=MeasureSpec.makeMeasureSpec(height/4,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}

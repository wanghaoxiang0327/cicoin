package com.sskj.common.tab;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;
import android.widget.FrameLayout;

public class TabView extends FrameLayout implements Checkable {

    private boolean mChecked;

    public TabView(Context context) {
        super(context);
        initView();
    }

    public TabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public TabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
       View view= View.inflate(getContext(), com.flyco.tablayout.R.layout.layout_tab_top, null);
       addView(view);
    }

    @Override
    public void setChecked(boolean checked) {
        mChecked=checked;
        refreshDrawableState();

    }

    @Override
    public boolean isChecked() {
        return mChecked;
    }

    @Override
    public void toggle() {
        setChecked(!mChecked);
    }
}

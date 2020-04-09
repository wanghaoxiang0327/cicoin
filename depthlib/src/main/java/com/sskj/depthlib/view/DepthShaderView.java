package com.sskj.depthlib.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.sskj.depthlib.R;


public class DepthShaderView extends LinearLayout {
    private float maxProgress = 100;
    private float progress = 80;
    private float depth;
    private int width;
    private int height;
    private Paint mPaint;
    private int shadowColor = Color.BLUE;
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    private int direction = LEFT;

    public DepthShaderView(Context context) {
        this(context, null);
    }

    public DepthShaderView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DepthShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray array = getContext().obtainStyledAttributes(attributeSet, R.styleable.DepthShaderView);
            shadowColor = array.getColor(R.styleable.DepthShaderView_depth_sv_color, 0);
            maxProgress = array.getFloat(R.styleable.DepthShaderView_depth_sv_max_progress, 100);
            progress = array.getFloat(R.styleable.DepthShaderView_depth_sv_progress, 10);
            direction = array.getInt(R.styleable.DepthShaderView_depth_sv_direction, LEFT);
            array.recycle();
        }
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(shadowColor);
        if (direction == LEFT) {
            canvas.drawRect(0, 0, depth * progress, height, mPaint);
        } else {
            canvas.drawRect(width - depth * progress, 0, width, height, mPaint);
        }
        super.onDraw(canvas);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        mPaint.setStrokeWidth(height);
        calculateItem();
    }

    public void calculateItem() {
        depth = width / maxProgress;
    }

    public float getMaxProgress() {
        return maxProgress;
    }

    public void setMaxProgress(float maxProgress) {
        this.maxProgress = maxProgress;
        invalidate();
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
        invalidate();
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setShadowColor(int shadowColor) {
        this.shadowColor = shadowColor;
    }

}

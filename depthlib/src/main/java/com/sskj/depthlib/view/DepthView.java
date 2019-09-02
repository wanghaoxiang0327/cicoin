package com.sskj.depthlib.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


import com.sskj.depthlib.data.IDepthData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DepthView extends View {

    private List<IDepthData> mData = new ArrayList<>();

    private int mWidth;
    private int mHeight;

    private double itemHeight;
    private double itemWidth;

    private double maxVolume;
    private double minVolume;

    //购买绘制宽度
    private int mBuyWidth;
    //出售绘制宽度
    private int mSellWidth;
    //绘制高度
    private int mDrawHeight;
    private int mPriceHeight = 50;

    private int buyColor = Color.parseColor("#eee111");
    private int sellColor;

    private Paint mPaint;
    private Path mPath;


    public DepthView(Context context) {
        this(context, null);
    }

    public DepthView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DepthView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setAntiAlias(true);
        mPath = new Path();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mBuyWidth = mWidth / 2;
        mDrawHeight = mHeight - mPriceHeight;
        itemWidth = mBuyWidth / (double)mData.size();
        itemHeight = mDrawHeight / (maxVolume * 1.2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        drawBuy(canvas);
        canvas.restore();
    }

    private void drawBuy(Canvas canvas) {
        mPath.reset();
        mPaint.setColor(buyColor);
        float lastX, lastY;
        float x, y;
        for (int i = 0; i < mData.size(); i++) {
            if (i == 0) {
                mPath.moveTo(0, (float) getYByIndex(i));
            } else {
                lastX = (float) ((i-1) * itemWidth);
                lastY = (float) getYByIndex(i-1);
                x = (float) ((i) * itemWidth);
                y = (float) getYByIndex(i);
                mPath.quadTo(lastX/2,lastY/2,x, y);
            }

        }
        canvas.drawPath(mPath, mPaint);
    }


    public void setData(List<IDepthData> data) {
        if (data == null) {
            data = new ArrayList<>();
        }
        mData = data;
        calculateMaxVolume();
        invalidate();
    }

    private double getYByIndex(int i) {
        return mDrawHeight-mData.get(i).getVolume() * itemHeight;
    }


    private void calculateMaxVolume() {
        Collections.sort(mData, new Comparator<IDepthData>() {
            @Override
            public int compare(IDepthData o1, IDepthData o2) {
                if (o1.getVolume() == o2.getVolume()) {
                    return 0;
                }
                return o1.getVolume() < o2.getVolume() ? 1 : -1;
            }
        });
        maxVolume = mData.get(0).getVolume();
    }

}

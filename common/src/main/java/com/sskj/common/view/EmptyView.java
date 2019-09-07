package com.sskj.common.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sskj.common.R;

/**
 * 项目包名：com.sskj.common.view
 * 项目所属模块：
 * 作者：布兜兜不打豆豆
 * 创建时间：2019年09月07日
 * 类描述：
 * 备注：
 */
public class EmptyView extends LinearLayout {


    public EmptyView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.common_empty_view, this, false);
        TextView emptytext = (TextView) view.findViewById(R.id.empty_text);
        this.addView(view);
    }

}

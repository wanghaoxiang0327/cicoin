package com.sskj.common.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.library.SuperButton;
import com.sskj.common.R;
import com.sskj.common.R2;
import com.sskj.common.utils.ScreenUtil;
import com.zzhoujay.richtext.RichText;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 项目包名：com.sskj.common.dialog
 * 项目所属模块：
 * 作者：布兜兜不打豆豆
 * 创建时间：2019年09月04日
 * 类描述：
 * 备注：
 */
public class TipsNewDialog extends AlertDialog {


    @BindView(R2.id.title)
    TextView title;
    @BindView(R2.id.content)
    TextView content;
    @BindView(R2.id.ensure)
    SuperButton ensure;
    @BindView(R2.id.close)
    ImageView close;
    @BindView(R2.id.tv_close)
    TextView tvClose;

    private View view;


    public TipsNewDialog(@NonNull Context context) {
        super(context, R.style.common_custom_dialog);
        view = LayoutInflater.from(context).inflate(R.layout.common_dialog, null);
        setView(view);
        ButterKnife.bind(this, view);
        initView();
    }

    private void initView() {

        ensure.setOnClickListener(view -> {
            if (confirmListener != null) {
                confirmListener.onConfirm(this);
            } else {
                dismiss();
            }
        });

        close.setOnClickListener(view -> {
            if (cancelListener != null) {
                cancelListener.onCancel(this);
            } else {
                dismiss();
            }
        });

        tvClose.setOnClickListener(view -> {
            if (cancelListener != null) {
                cancelListener.onCancel(this);
            } else {
                dismiss();
            }
        });


    }

    @Override
    public void show() {
        super.show();
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = (int) (ScreenUtil.getScreenWidth(getContext()) * 0.8);
        getWindow().setAttributes(params);
    }

    public TipsNewDialog setTitle(String name) {
        title.setText(name);
        return this;
    }

    public TipsNewDialog setContent(String content) {
        RichText.from(content)
                .into(this.content);
        return this;
    }

    public TipsNewDialog setCancelVisible(int visible) {
        tvClose.setVisibility(visible);
        close.setVisibility(visible);
        return this;
    }


    public TipsNewDialog setConfirmText(String text) {
        ensure.setText(text);
        return this;
    }

    public TipsNewDialog setCancelText(String text) {
        tvClose.setText(text);
        return this;
    }

    public interface OnConfirmListener {
        void onConfirm(TipsNewDialog dialog);
    }


    private OnConfirmListener confirmListener;
    private OnCancelListener cancelListener;


    public TipsNewDialog setConfirmListener(OnConfirmListener confirmListener) {
        this.confirmListener = confirmListener;
        return this;
    }


    public TipsNewDialog setCancelListener(OnCancelListener cancelListener) {
        this.cancelListener = cancelListener;
        return this;
    }

}


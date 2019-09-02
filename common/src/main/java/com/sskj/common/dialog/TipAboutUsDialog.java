package com.sskj.common.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.sskj.common.R;
import com.sskj.common.R2;
import com.sskj.common.utils.ScreenUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TipAboutUsDialog extends AlertDialog {


    @BindView(R2.id.tvEmail)
    TextView tvEmail;
    @BindView(R2.id.tvCancel)
    TextView tvCancel;
    @BindView(R2.id.tvCopy)
    TextView tvCopy;
    private View view;


    public TipAboutUsDialog(@NonNull Context context) {
        super(context, R.style.common_custom_dialog);
        view = LayoutInflater.from(context).inflate(R.layout.common_tip_dialog_about_us, null);
        setView(view);
        ButterKnife.bind(this, view);
        initView();
    }

    private void initView() {

        tvCopy.setOnClickListener(view -> {
            if (confirmListener != null) {
                confirmListener.onConfirm(this);
            } else {
                dismiss();
            }
        });

        tvCancel.setOnClickListener(view -> {
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



    public TipAboutUsDialog setContent(String content) {
        tvEmail.setText(content);
        return this;
    }

    public TipAboutUsDialog setCancelVisible(int visible) {
        tvCancel.setVisibility(visible);
        return this;
    }


    public interface OnConfirmListener {
        void onConfirm(TipAboutUsDialog dialog);
    }


    private OnConfirmListener confirmListener;
    private OnCancelListener cancelListener;


    public TipAboutUsDialog setConfirmListener(OnConfirmListener confirmListener) {
        this.confirmListener = confirmListener;
        return this;
    }


    public TipAboutUsDialog setCancelListener(OnCancelListener cancelListener) {
        this.cancelListener = cancelListener;
        return this;
    }

}

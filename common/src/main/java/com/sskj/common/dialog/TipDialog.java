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

public class TipDialog extends AlertDialog {

    @BindView(R2.id.dialog_title)
   public TextView dialogTitle;
    @BindView(R2.id.dialog_content)
    public   TextView dialogContent;
    @BindView(R2.id.cancel_btn)
    public  TextView cancelBtn;
    @BindView(R2.id.confirm_btn)
    public  TextView confirmBtn;

    private View view;


    public TipDialog(@NonNull Context context) {
        super(context, R.style.common_custom_dialog);
        view = LayoutInflater.from(context).inflate(R.layout.common_tip_dialog, null);
        setView(view);
        ButterKnife.bind(this, view);
        initView();
    }

    private void initView() {

        confirmBtn.setOnClickListener(view -> {
            if (confirmListener != null) {
                confirmListener.onConfirm(this);
            } else {
                dismiss();
            }
        });

        cancelBtn.setOnClickListener(view -> {
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

    public TipDialog setTitle(String title) {
        dialogTitle.setText(title);
        return this;
    }

    public TipDialog setContent(String content) {
        dialogContent.setText(content);
        return this;
    }

    public TipDialog setCancelVisible(int visible) {
        cancelBtn.setVisibility(visible);
        return this;
    }


    public TipDialog setConfirmText(String text){
        confirmBtn.setText(text);
        return this;
    }
    public TipDialog setCancelText(String text){
        cancelBtn.setText(text);
        return this;
    }

    public interface OnConfirmListener {
        void onConfirm(TipDialog dialog);
    }


    private OnConfirmListener confirmListener;
    private OnCancelListener cancelListener;


    public TipDialog setConfirmListener(OnConfirmListener confirmListener) {
        this.confirmListener = confirmListener;
        return this;
    }


    public TipDialog setCancelListener(OnCancelListener cancelListener) {
        this.cancelListener = cancelListener;
        return this;
    }

}

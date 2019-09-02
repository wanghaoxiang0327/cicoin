package com.sskj.common.dialog;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.sskj.common.R;
import com.sskj.common.R2;
import com.sskj.common.utils.ScreenUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CandyDialog extends AlertDialog {
    @BindView(R2.id.tv_dialog_content)
    TextView tvDialogContent;
    @BindView(R2.id.tv_dialog_cancel)
    TextView tvDialogCancel;
    @BindView(R2.id.tv_dialog_confirm)
    TextView tvDialogConfirm;
    private View view;

    public CandyDialog(@NonNull Context context) {
        super(context, R.style.common_custom_dialog);
        view = LayoutInflater.from(context).inflate(R.layout.common_candy, null);
        setView(view);
        ButterKnife.bind(this, view);
        initView();
    }

    private void initView() {
        tvDialogConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onDialogClickListener!=null){
                    onDialogClickListener.onConfirm();
                }
                dismiss();
            }
        });
        tvDialogCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onDialogClickListener!=null){
                    onDialogClickListener.onCancel();
                }
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

    public CandyDialog setContent(String content) {
        tvDialogContent.setText(content);
        return this;
    }

    public CandyDialog setCancelText(String cancelText) {
        tvDialogCancel.setText(cancelText);
        return this;
    }

    public CandyDialog setConfrimText(String confrimText) {
        tvDialogConfirm.setText(confrimText);
        return this;
    }

    public interface OnDialogClickListener {
        void onConfirm();
        void onCancel();
    }


    private OnDialogClickListener onDialogClickListener;

    public CandyDialog setOnDialogClickListener(OnDialogClickListener onDialogClickListener) {
        this.onDialogClickListener = onDialogClickListener;
        return this;
    }
}

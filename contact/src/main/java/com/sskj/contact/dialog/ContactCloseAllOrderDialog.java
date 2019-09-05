package com.sskj.contact.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.sskj.common.utils.ClickUtil;
import com.sskj.common.utils.ScreenUtil;
import com.sskj.contact.R;
import com.sskj.contact.R2;
import com.sskj.contact.data.HoldOrder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ContactCloseAllOrderDialog extends DialogFragment {
    @BindView(R2.id.tv_type)
    TextView tvType;
    @BindView(R2.id.tv_price)
    TextView tvPrice;
    @BindView(R2.id.tv_num)
    TextView tvNum;
    @BindView(R2.id.btn_cancel)
    Button btnCancel;
    @BindView(R2.id.btn_confirm)
    Button btnConfirm;
    Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.common_custom_dialog);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contact_dialog_close_all_order, container, false);
        unbinder = ButterKnife.bind(this, view);
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        window.getDecorView().setPadding(0, 0, 0, 0);
        attributes.width = (int) (ScreenUtil.getScreenWidth(getContext()) * 0.9f);
        attributes.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(attributes);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        initView();
        return view;
    }

    private void initView() {
        btnCancel.setOnClickListener(v -> {
            getDialog().dismiss();
        });
        ClickUtil.click(btnConfirm, view -> {
            if (confirmListener != null) {
                confirmListener.onConfirm();
            }
            getDialog().dismiss();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    public interface OnConfirmListener {
        void onConfirm();
    }


    public ContactCloseAllOrderDialog setConfirmListener(OnConfirmListener confirmListener) {
        this.confirmListener = confirmListener;
        return this;
    }

    OnConfirmListener confirmListener;


    public static ContactCloseAllOrderDialog getInstance() {
        ContactCloseAllOrderDialog dialog = new ContactCloseAllOrderDialog();
        return dialog;
    }

}

package com.sskj.contact.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hjq.toast.ToastUtils;
import com.sskj.common.utils.ClickUtil;
import com.sskj.common.utils.DigitUtils;
import com.sskj.common.utils.MoneyValueFilter;
import com.sskj.common.utils.NumberUtils;
import com.sskj.common.utils.ScreenUtil;
import com.sskj.contact.R;
import com.sskj.contact.R2;
import com.sskj.contact.data.HoldOrder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ContactCloseOrderDialog extends DialogFragment {
    @BindView(R2.id.tv_type)
    TextView tvType;
    @BindView(R2.id.tv_price)
    TextView tvPrice;
    @BindView(R2.id.tv_num)
    TextView tvNum;
    @BindView(R2.id.tv_add)
    TextView tvAdd;
    @BindView(R2.id.tv_less)
    TextView tvLess;
    @BindView(R2.id.edt_num)
    EditText edtNum;
    @BindView(R2.id.btn_cancel)
    Button btnCancel;
    @BindView(R2.id.btn_confirm)
    Button btnConfirm;
    Unbinder unbinder;
    private HoldOrder orderData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.common_custom_dialog);
        if (getArguments() != null) {
            orderData = (HoldOrder) getArguments().getSerializable("order");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contact_dialog_close_order, container, false);
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
        tvType.setText("1".equals(orderData.getOtype()) ? getString(R.string.common_make_more) : getString(R.string.common_make_empty));
        tvType.setTextColor("1".equals(orderData.getOtype()) ? getResources().getColor(R.color.common_red) : getResources().getColor(R.color.common_green));
        tvPrice.setText(NumberUtils.keepMaxDown(orderData.getNewprice(), DigitUtils.getDigit(orderData.getCode())));
        tvNum.setText(orderData.getBuynum());
        edtNum.setFilters(new InputFilter[]{new MoneyValueFilter(2)});
        edtNum.setText(NumberUtils.keep(orderData.getBuynum(), 2));
        btnCancel.setOnClickListener(v -> {
            getDialog().dismiss();
        });
        ClickUtil.click(btnConfirm, view -> {
            if (TextUtils.isEmpty(edtNum.getText())) {
                ToastUtils.show(getString(R.string.contact_contactCloseOrderDialog3));
                return;
            }
            double totalNum = Double.parseDouble(orderData.getBuynum());
            double closeNum = Double.parseDouble(edtNum.getText().toString());
            if (closeNum > totalNum) {
                ToastUtils.show(getString(R.string.contact_contactCloseOrderDialog4) + orderData.getBuynum());
            }
            if (confirmListener != null) {
                confirmListener.onConfirm(edtNum.getText().toString(), orderData.getHold_id());
            }
            getDialog().dismiss();
        });
        ClickUtil.click(tvAdd, view -> {
            changeNum(edtNum, true);
        });
        ClickUtil.click(tvLess, view -> {
            changeNum(edtNum, false);
        });
    }

    public void changeNum(EditText editText, boolean increase) {
        String text = editText.getText().toString();
        int digit = 0;
        double num;
        double minChange = 0.1;
        int digitIndex = 0;
        if (TextUtils.isEmpty(text)) {
            num = 0;
        } else {
            num = Double.parseDouble(text);
        }
        if (text.contains(".")) {
            digitIndex = editText.getText().toString().indexOf(".");
            int length = text.length() - digitIndex;
            digitIndex = length - 1;
            for (int i = 0; i < digitIndex - 1; i++) {
                minChange = 0.1 * minChange;
            }
            digit = digitIndex;
        } else {
            minChange = 1;
        }
        if (increase) {
            num = num + minChange;
        } else {
            num = num - minChange;
        }
        if (num < 0) {
            num = 0;
        }
        editText.setText(NumberUtils.keep(num, digit));
        editText.setSelection(editText.getText().length());
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    public interface OnConfirmListener {
        void onConfirm(String num, String id);
    }


    public ContactCloseOrderDialog setConfirmListener(OnConfirmListener confirmListener) {
        this.confirmListener = confirmListener;
        return this;
    }

    OnConfirmListener confirmListener;


    public static ContactCloseOrderDialog getInstance(HoldOrder holdOrder) {
        ContactCloseOrderDialog dialog = new ContactCloseOrderDialog();
        Bundle bundle = new Bundle();
        bundle.putSerializable("order", holdOrder);
        dialog.setArguments(bundle);
        return dialog;
    }

}

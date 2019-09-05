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
import com.sskj.common.base.BaseDialogFragment;
import com.sskj.common.base.BasePresenter;
import com.sskj.common.http.HttpResult;
import com.sskj.common.utils.ClickUtil;
import com.sskj.common.utils.DigitUtils;
import com.sskj.common.utils.MoneyValueFilter;
import com.sskj.common.utils.NumberUtils;
import com.sskj.common.utils.ScreenUtil;
import com.sskj.contact.R;
import com.sskj.contact.R2;
import com.sskj.contact.data.HoldOrder;
import com.sskj.contact.data.PointInfo;
import com.sskj.contact.type.Price;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 设置止盈止损
 */
public class ContactOrderSettingDialog extends BaseDialogFragment<OrderSettingDialogPresenter> {

    @BindView(R2.id.tv_type)
    TextView tvType;
    @BindView(R2.id.tv_price)
    TextView tvPrice;
    @BindView(R2.id.edt_win)
    EditText edtWin;
    @BindView(R2.id.tv_win_min)
    TextView tvWinMin;
    @BindView(R2.id.edt_loss)
    EditText edtLoss;
    @BindView(R2.id.tv_loss_max)
    TextView tvLossMax;
    @BindView(R2.id.btn_cancel)
    Button btnCancel;
    @BindView(R2.id.btn_confirm)
    Button btnConfirm;

    Unbinder unbinder;

    private HoldOrder orderData;
    private PointInfo pointInfo;


    private double minZy;
    private double maxZs;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.common_custom_dialog);
        if (getArguments() != null) {
            orderData = (HoldOrder) getArguments().getSerializable("order");
            pointInfo = (PointInfo) getArguments().getSerializable("point");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.contact_dialog_setting_order, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        window.getDecorView().setPadding(0, 0, 0, 0);
        attributes.width = (int) (ScreenUtil.getScreenWidth(getContext()) * 0.9f);
        attributes.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(attributes);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        initView();
        return rootView;
    }

    @Override
    public void initView() {
        edtLoss.setFilters(new InputFilter[]{new MoneyValueFilter(DigitUtils.getDigit(orderData.getCode()))});
        edtWin.setFilters(new InputFilter[]{new MoneyValueFilter(DigitUtils.getDigit(orderData.getCode()))});
        tvType.setText(orderData.getType() == 1 ? getString(R.string.common_make_more) : getString(R.string.common_make_empty));
        tvPrice.setText(NumberUtils.keepMaxDown(orderData.getNewprice(), 4));
        btnCancel.setOnClickListener(v -> {
            getDialog().dismiss();
        });

        ClickUtil.click(btnConfirm, view -> {
            if (isEmptyShow(edtWin)) {
                return;
            }
            if (isEmptyShow(edtLoss)) {
                return;
            }
            double win = Double.parseDouble(getText(edtWin));
            double loss = Double.parseDouble(getText(edtLoss));

            if (win < minZy) {
                ToastUtils.show(getString(R.string.contact_contactOrderSettingDialog3) + minZy);
                return;
            }

            if (loss > maxZs) {
                ToastUtils.show(getString(R.string.contact_contactOrderSettingDialog4) + maxZs);
                return;
            }

            if (confirmListener != null) {
                mPresenter.setPoint(orderData.getHold_id(), getText(edtWin), getText(edtLoss));
            }
        });

    }

    @Override
    public void initData() {
        setPointInfo(pointInfo);
    }

    @Override
    public void loadData() {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected OrderSettingDialogPresenter getPresenter() {
        return new OrderSettingDialogPresenter();
    }

    public void setPointInfo(PointInfo result) {
        Double price = Double.parseDouble(orderData.getNewprice());
        if (orderData.getType() == 1) {
            minZy = price + Double.parseDouble(result.getMin_zy());
            maxZs = price - Double.parseDouble(result.getMin_zs());
        } else {
            minZy = price - Double.parseDouble(result.getMin_zy());
            maxZs = price + Double.parseDouble(result.getMin_zs());
        }
        tvWinMin.setText("≥" + NumberUtils.keepMaxDown(minZy, 4));
        tvLossMax.setText("≤" + NumberUtils.keepMaxDown(maxZs, 4));
    }

    public void setPointSuccess() {
        getDialog().dismiss();
        if (confirmListener != null) {
            confirmListener.onConfirm();
        }
    }


    public interface OnConfirmListener {
        void onConfirm();
    }


    public ContactOrderSettingDialog setConfirmListener(OnConfirmListener confirmListener) {
        this.confirmListener = confirmListener;
        return this;
    }

    OnConfirmListener confirmListener;


    public static ContactOrderSettingDialog getInstance(HoldOrder holdOrder, PointInfo pointInfo) {
        ContactOrderSettingDialog dialog = new ContactOrderSettingDialog();
        Bundle bundle = new Bundle();
        bundle.putSerializable("order", holdOrder);
        bundle.putSerializable("point", pointInfo);
        dialog.setArguments(bundle);
        return dialog;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

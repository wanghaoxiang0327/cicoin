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
import com.sskj.common.data.CoinBean;
import com.sskj.common.http.HttpResult;
import com.sskj.common.rxbus.RxBus;
import com.sskj.common.rxbus.Subscribe;
import com.sskj.common.rxbus.ThreadMode;
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

import java.math.BigDecimal;

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
    @BindView(R2.id.tv_zy_decrease_price)
    TextView tvZyDecreaseNum;
    @BindView(R2.id.tv_zy_increase_price)
    TextView tvZyINDecreaseNum;
    @BindView(R2.id.tv_zs_decrease_price)
    TextView tvZsDecreaseNum;
    @BindView(R2.id.tv_zs_increase_price)
    TextView tvZsINDecreaseNum;
    Unbinder unbinder;

    private HoldOrder orderData;
    private PointInfo pointInfo;


    private double minZy;
    private double minZs;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RxBus.getDefault().register(this);
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
        edtWin.setText(NumberUtils.keep(orderData.getPoit_win(), DigitUtils.getDigit(orderData.getCode())));
        edtLoss.setText(NumberUtils.keep(orderData.getPoit_loss(), DigitUtils.getDigit(orderData.getCode())));
        tvType.setText("1".equals(orderData.getOtype()) ? getString(R.string.common_make_more) : getString(R.string.common_make_empty));
        tvPrice.setText(NumberUtils.keepMaxDown(orderData.getNewprice(), DigitUtils.getDigit(orderData.getCode())));
        btnCancel.setOnClickListener(v -> {
            getDialog().dismiss();
        });
        ClickUtil.click(tvZyDecreaseNum, view -> {
            changeNum(edtWin, false);
        });
        ClickUtil.click(tvZyINDecreaseNum, view -> {
            changeNum(edtWin, true);
        });
        ClickUtil.click(tvZsDecreaseNum, view -> {
            changeNum(edtLoss, false);
        });
        ClickUtil.click(tvZsINDecreaseNum, view -> {
            changeNum(edtLoss, true);
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


            if ("1".equals(orderData.getOtype())) {
                if (win < minZy && win != 0) {
                    ToastUtils.show(getString(R.string.contact_contactOrderSettingDialog3) + minZy);
                    return;
                }
                if (loss > minZs && loss != 0) {
                    ToastUtils.show(getString(R.string.contact_contactOrderSettingDialog4) + minZs);
                    return;
                }
            } else {
                if (win > minZy && win != 0) {
                    ToastUtils.show(getString(R.string.contact_contactOrderSettingDialog30) + minZy);
                    return;
                }
                if (loss < minZs && loss != 0) {
                    ToastUtils.show(getString(R.string.contact_contactOrderSettingDialog40) + minZs);
                    return;
                }
            }


            if (confirmListener != null) {
                mPresenter.setPoint(orderData.getHold_id(), getText(edtWin), getText(edtLoss));
            }
        });

    }


    public void changeNum(EditText editText, boolean increase) {
        String text = editText.getText().toString();
        int digit = 0;
        BigDecimal num;
        BigDecimal minChange = new BigDecimal("0.1");
        int digitIndex = 0;
        if (TextUtils.isEmpty(text)) {
            num = new BigDecimal("0");
        } else {
            num = new BigDecimal(text);
        }
        if (text.contains(".")) {
            digitIndex = editText.getText().toString().indexOf(".");
            int length = text.length() - digitIndex;
            digitIndex = length - 1;
            for (int i = 0; i < digitIndex - 1; i++) {
                minChange = minChange.multiply(new BigDecimal("0.1"));
            }
            digit = digitIndex;
        } else {
            minChange = new BigDecimal("1");
        }
        if (increase) {
            num = num.add(minChange);
        } else {
            num = num.subtract(minChange);
        }
        if (num.doubleValue() < 0) {
            num = new BigDecimal("0");
        }
        editText.setText(NumberUtils.keep(num, digit));
        editText.setSelection(editText.getText().length());
    }

    @Override
    public void initData() {
        setPointInfo(pointInfo, orderData.getNewprice());
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

    public void setPointInfo(PointInfo result, String newPrice) {
        Double price = Double.parseDouble(newPrice);
        if ("1".equals(orderData.getOtype())) {
            minZy = price + Double.parseDouble(result.getMin_zy());
            minZs = price - Double.parseDouble(result.getMin_zs());
            tvWinMin.setText("≥" + NumberUtils.keepMaxDown(minZy, DigitUtils.getDigit(orderData.getCode())));
            tvLossMax.setText("≤" + NumberUtils.keepMaxDown(minZs, DigitUtils.getDigit(orderData.getCode())));
        } else {
            minZy = price - Double.parseDouble(result.getMin_zy());
            minZs = price + Double.parseDouble(result.getMin_zs());
            tvWinMin.setText("≤" + NumberUtils.keepMaxDown(minZy, DigitUtils.getDigit(orderData.getCode())));
            tvLossMax.setText("≥" + NumberUtils.keepMaxDown(minZs, DigitUtils.getDigit(orderData.getCode())));
        }
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
        RxBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateCoin(CoinBean coinBean) {
        if (orderData != null) {
            if (orderData.getCode().equals(coinBean.getCode())) {
                tvPrice.setText(NumberUtils.keepMaxDown(coinBean.getPrice(), DigitUtils.getDigit(orderData.getCode())));
                setPointInfo(pointInfo, coinBean.getPrice() + "");
            }
        }
    }
}

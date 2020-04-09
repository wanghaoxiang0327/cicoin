package com.sskj.contact.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.sskj.common.base.BaseDialogFragment;
import com.sskj.common.rxbus.RxBus;
import com.sskj.common.utils.ClickUtil;
import com.sskj.common.utils.NumberUtils;
import com.sskj.common.utils.ScreenUtil;
import com.sskj.contact.R;
import com.sskj.contact.R2;
import com.sskj.contact.data.CreateOrder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ContactCreateDialog extends BaseDialogFragment<CreateDialogPresenter> {

    @BindView(R2.id.tv_trade_type)
    TextView tvTradeType;
    @BindView(R2.id.tv_price_type)
    TextView tvPriceType;
    @BindView(R2.id.tv_price)
    TextView tvPrice;
    @BindView(R2.id.tv_num)
    TextView tvNum;
    @BindView(R2.id.tv_total)
    TextView tvTotal;
    @BindView(R2.id.tv_lever)
    TextView tvLever;
    @BindView(R2.id.tv_service_fee)
    TextView tvServiceFee;
    @BindView(R2.id.btn_cancel)
    Button btnCancel;
    @BindView(R2.id.btn_confirm)
    Button btnConfirm;

    Unbinder unbinder;

    CreateOrder orderBean;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.common_custom_dialog);
        if (getArguments() != null) {
            orderBean = (CreateOrder) getArguments().getSerializable("data");
        } else {
            throw new RuntimeException(getString(R.string.contact_contactCreateDialog1));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.contact_dialog_create_order, container, false);
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
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected CreateDialogPresenter getPresenter() {
        return new CreateDialogPresenter();
    }

    @Override
    public void initView() {
        //做多
        if (1 == orderBean.getTradeType().value) {
            tvTradeType.setTextColor(getResources().getColor(R.color.common_red));
            tvTradeType.setText(getString(R.string.common_make_more));
        } else {//做空
            tvTradeType.setTextColor(getResources().getColor(R.color.common_green));
            tvTradeType.setText(getString(R.string.common_make_empty));
        }
        tvPriceType.setText(orderBean.getPriceType().name);
        tvTradeType.setText(orderBean.getTradeType().name);
        tvPrice.setText(orderBean.getPrice());
        tvNum.setText(orderBean.getNum());
        tvServiceFee.setText(NumberUtils.keepMaxDown(orderBean.getFee(), 4));
        tvTotal.setText(orderBean.getTotal());
        tvLever.setText(orderBean.getLever());
    }

    @Override
    public void initData() {
        btnCancel.setOnClickListener(v -> {
            getDialog().dismiss();
        });
        ClickUtil.click(btnConfirm, view -> {
            mPresenter.createOrder(orderBean.getPrice(),
                    orderBean.getPriceType().value,
                    orderBean.getTradeType().value,
                    orderBean.getNum(),
                    orderBean.getCode(),
                    orderBean.getLever());
        });
    }

    @Override
    public void loadData() {

    }

    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * 创建订单成功
     */
    public void createOrderSuccess() {
        RxBus.getDefault().post("LimitPriceSuccess");
        RxBus.getDefault().post("makeOrderSuccess");
        getDialog().dismiss();
    }


}

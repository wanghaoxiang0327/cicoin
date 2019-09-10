package com.sskj.contact;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.binaryfork.spanny.Spanny;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.hey.lib.HeySpinner;
import com.hjq.toast.ToastUtils;
import com.sskj.common.App;
import com.sskj.common.base.BaseFragment;
import com.sskj.common.data.CoinBean;
import com.sskj.common.dialog.TipsNewDialog;
import com.sskj.common.event.ContactChangeCoin;
import com.sskj.common.router.RoutePath;
import com.sskj.common.rxbus.RxBus;
import com.sskj.common.rxbus.Subscribe;
import com.sskj.common.rxbus.ThreadMode;
import com.sskj.common.simple.SimpleTextWatcher;
import com.sskj.common.user.data.UserBean;
import com.sskj.common.utils.BigDecimalUtils;
import com.sskj.common.utils.ClickUtil;
import com.sskj.common.utils.DigitUtils;
import com.sskj.common.utils.MoneyValueFilter;
import com.sskj.common.utils.NumberUtils;
import com.sskj.contact.data.BalanceInfo;
import com.sskj.contact.data.CoinInfo;
import com.sskj.contact.data.CreateOrder;
import com.sskj.contact.dialog.ContactCreateDialog;
import com.sskj.contact.event.EventContact;
import com.sskj.contact.type.Price;
import com.sskj.contact.type.Trade;
import com.sskj.contact.view.NoTopCornerTabLayout;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;

/**
 * @author Hey
 * Create at  2019/08/22 17:44:22
 */
public class ContractLeftFragment extends BaseFragment<ContractLeftPresenter> {
    @BindView(R2.id.radio_up)
    RadioButton radioUp;
    @BindView(R2.id.radio_down)
    RadioButton radioDown;
    @BindView(R2.id.trade_type_group)
    RadioGroup tradeTypeGroup;
    @BindView(R2.id.price_type_spinner)
    HeySpinner priceTypeSpinner;
    @BindView(R2.id.decrease_price)
    Button decreasePrice;
    @BindView(R2.id.edt_price)
    EditText edtPrice;
    @BindView(R2.id.increase_price)
    Button increasePrice;
    @BindView(R2.id.limit_price_layout)
    LinearLayout limitPriceLayout;
    @BindView(R2.id.tv_market_price)
    TextView tvMarketPrice;
    @BindView(R2.id.tv_usable_usdt)
    TextView tvUsableUsdt;
    @BindView(R2.id.tv_unit)
    TextView tvUnit;
    @BindView(R2.id.edt_num)
    EditText edtNum;
    @BindView(R2.id.point_tabLayout)
    NoTopCornerTabLayout pointTabLayout;
    @BindView(R2.id.level_spinner)
    HeySpinner levelSpinner;
    @BindView(R2.id.tv_total_money)
    TextView tvTotalMoney;
    @BindView(R2.id.tv_total_unit)
    TextView tvTotalUnit;
    @BindView(R2.id.btn_submit)
    Button btnSubmit;
    private Price priceType = Price.MARKET;
    private Trade tradeType = Trade.UP;

    private ArrayList<String> priceItems = new ArrayList<>();
    private String[] levers;

    /**
     * 数量
     */
    private BigDecimal num = new BigDecimal(0);
    /**
     * 杠杆
     */
    private BigDecimal lever = new BigDecimal(1);
    /**
     * 价格
     */
    private BigDecimal price = new BigDecimal(0);
    /**
     * 点差
     */
    private BigDecimal spread = new BigDecimal(0);
    /**
     * 最小变动价
     */
    private BigDecimal minChangePrice = new BigDecimal(0);

    /**
     * 合约面值
     */
    private BigDecimal unitNum = new BigDecimal(1);

    /**
     * 手续费
     */
    private BigDecimal fee = new BigDecimal(0);

    /**
     * 余额
     */
    private BigDecimal balance = new BigDecimal(0);

    /**
     * 最大交易量
     */
    private BigDecimal maxNum = new BigDecimal(0);

    //是否选择仓位
    private boolean changeByPosition;
    private UserBean mUserInfo;
    private String code;
    private NumberFormat percentFormat = NumberFormat.getPercentInstance();
    private BigDecimal feeMoney;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getArguments() != null) {
            code = getArguments().getString("code");
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.contact_fragment_contract_left;
    }

    @Override
    public ContractLeftPresenter getPresenter() {
        return new ContractLeftPresenter();
    }

    @Override
    public void initView() {
        RxBus.getDefault().register(this);
        initPriceType();
        initTradeType();
        initPoint();
        userViewModel.getUser().observe(this, new Observer<UserBean>() {
            @Override
            public void onChanged(@Nullable UserBean userBean) {
                mUserInfo = userBean;
            }
        });
        tvUnit.setText(code.split("_")[0].toUpperCase());
        edtPrice.setFilters(new InputFilter[]{new MoneyValueFilter(4)});
        edtNum.setFilters(new InputFilter[]{new MoneyValueFilter(2)});
        userViewModel.getUser().observe(this, new Observer<UserBean>() {
            @Override
            public void onChanged(@Nullable UserBean userBean) {
                if (userBean == null) {
                    btnSubmit.setText(getString(R.string.contact_please_login));
                    btnSubmit.setBackgroundResource(R.drawable.contact_red_bg_50);
                } else {
                    btnSubmit.setText(getString(R.string.common_make_more));
                    btnSubmit.setBackgroundResource(R.drawable.contact_red_bg_50);
                }
            }
        });
        ClickUtil.click(btnSubmit, view -> {
            if (mUserInfo == null) {
                ARouter.getInstance().build(RoutePath.LOGIN_LOGIN).navigation();
                return;
            }
            if (mUserInfo.getStatus() == 1) {
                new TipsNewDialog(getContext()).setContent(getString(R.string.contact_verifyHomeActivity4)).setConfirmText(getString(R.string.common_common_tip_dialog80)).setConfirmListener(new TipsNewDialog.OnConfirmListener() {
                    @Override
                    public void onConfirm(TipsNewDialog dialog) {
                        ARouter.getInstance().build(RoutePath.VERIFYFIRST).navigation();
                        dialog.dismiss();
                    }
                }).show();
                return;
            }
            if (priceType == Price.LIMIT) {
                if (isEmpty(edtPrice)) {
                    ToastUtils.show(getString(R.string.contact_contractLeftFragment1));
                    return;
                }
            }
            if (isEmpty(edtNum)) {
                ToastUtils.show(getString(R.string.contact_contractLeftFragment2));
                return;
            }

            if (num.floatValue() == 0) {
                ToastUtils.show(getString(R.string.contact_contractLeftFragment3));
                return;
            }
            if (num.floatValue() == 0) {
                ToastUtils.show(getString(R.string.contact_contractLeftFragment4));
                return;
            }
            CreateOrder orderBean = new CreateOrder();
            orderBean.setCode(code);
            orderBean.setLever(lever.toString());
            orderBean.setFee(feeMoney + "");
            orderBean.setNum(num.toString());
            if (priceType == Price.LIMIT) {//限价
                orderBean.setPrice(getText(edtPrice));
            } else {
                orderBean.setPrice(NumberUtils.keepDown(price, DigitUtils.getDigit(code)));
            }
            orderBean.setPriceType(priceType);
            orderBean.setTradeType(tradeType);
            orderBean.setTotal(getText(tvTotalMoney));
            ContactCreateDialog createDialog = new ContactCreateDialog();
            Bundle bundle = new Bundle();
            bundle.putSerializable("data", orderBean);
            createDialog.setArguments(bundle);
            createDialog.show(getFragmentManager(), "CreateOrder");
        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void makeOrder(String makeOrder) {
        if ("makeOrderSuccess".equals(makeOrder)) { //下单成功
            loadData();
            edtNum.getText().clear();
        } else if ("clickMakeMore".equals(makeOrder)) { //做多
            radioUp.performClick();
        } else if ("clickMakeEmpty".equals(makeOrder)) {//做空
            radioDown.performClick();
        }
    }

    private void initPoint() {
        String[] pointTabs = new String[]{"25%", "50%", "75%", "100%"};
        pointTabLayout.setTabData(pointTabs);
        pointTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (position != -1) {
                    try {
                        calculateMaxTrade();
                        changeByPosition = true;
                        BigDecimal p = new BigDecimal(percentFormat.parse(pointTabs[position]).floatValue());
                        edtNum.setText(NumberUtils.keepDown(maxNum.multiply(p), 0));
                        edtNum.setSelection(edtNum.getText().length());
                        changeByPosition = false;
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        pointTabLayout.setCurrentTab(-1);
    }


    private void initPriceType() {
        priceItems.add(getString(R.string.contact_contractLeftFragment6));
        priceItems.add(getString(R.string.contact_contractLeftFragment5));
        priceTypeSpinner.setOnSelectListener(position -> {
            if (position == 0) {
                priceType = Price.LIMIT;
                limitPriceLayout.setVisibility(View.VISIBLE);
                edtPrice.setText(NumberUtils.keep(price, 4));
                tvMarketPrice.setVisibility(View.GONE);
            } else {
                priceType = Price.MARKET;
                limitPriceLayout.setVisibility(View.GONE);
                tvMarketPrice.setVisibility(View.VISIBLE);
            }
        });
        priceTypeSpinner.attachData(priceItems);
    }

    private void initTradeType() {
        tradeTypeGroup.setOnCheckedChangeListener((group, checkedId) -> {
            userViewModel.getUser().observe(this, new Observer<UserBean>() {
                @Override
                public void onChanged(@Nullable UserBean userBean) {
                    if (userBean == null) {
                        btnSubmit.setText(getString(R.string.contact_please_login));
                        if (checkedId == R.id.radio_up) {
                            btnSubmit.setBackgroundResource(R.drawable.contact_red_bg_50);
                        } else {
                            btnSubmit.setBackgroundResource(R.drawable.contact_green_bg_50);
                        }
                    } else {
                        if (checkedId == R.id.radio_up) {
                            tradeType = Trade.UP;
                            btnSubmit.setBackgroundResource(R.drawable.contact_red_bg_50);
                            pointTabLayout.setIndicatorColor(getResources().getColor(R.color.common_red));
                        } else {
                            tradeType = Trade.DOWN;
                            btnSubmit.setBackgroundResource(R.drawable.contact_green_bg_50);
                            pointTabLayout.setIndicatorColor(getResources().getColor(R.color.common_green));
                        }
                        btnSubmit.setText(tradeType.name);
                    }
                }
            });

        });
    }

    private void initLevel() {
        if (levers != null) {
            levelSpinner.setOnSelectListener(position -> {
                lever = new BigDecimal(levers[position]);
                Spanny spanny = new Spanny(getString(R.string.contact_contractLeftFragment7))
                        .append(" " + levers[position], new ForegroundColorSpan(color(R.color.common_text)));
                calculateTotalMoney();
                calculateMaxTrade();
                levelSpinner.setText(spanny);
            });
            levelSpinner.attachData(Arrays.asList(levers));
        }
    }

    @Override
    public void initData() {
        /**
         * 数量变动监听
         */
        edtNum.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    num = new BigDecimal("0");
                } else {
                    num = new BigDecimal(s.toString());
                }
                if (!changeByPosition) {
                    pointTabLayout.setCurrentTab(-1);
                }
                calculateTotalMoney();
            }
        });

        /**
         * 价格变动监听
         */
        edtPrice.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    price = new BigDecimal("0");
                } else {
                    price = new BigDecimal(s.toString());
                }
                calculateTotalMoney();
                calculateMaxTrade();
            }
        });
        increasePrice.setOnClickListener(v -> {
            changeNum(edtPrice, true);
        });
        decreasePrice.setOnClickListener(v -> {
            changeNum(edtPrice, false);
        });
    }

    @Override
    public void loadData() {
        userViewModel.getUser().observe(this, new Observer<UserBean>() {
            @Override
            public void onChanged(@Nullable UserBean userBean) {
                if (userBean != null) {
                    mPresenter.getBalance();
                }
            }
        });
    }

    /**
     * 计算保证金
     * 市价做多：建仓价=买入价*（1+点差比例）*合约面值
     * 市价做空：建仓价=买入价*（1-点差比例）*合约面值
     * 保证金=建仓价*买入手数*合约面值/杠杆
     * 手续费=建仓价*买入手数*合约面值*手续费比例
     * 交易金额=保证金+手续费
     */
    public void calculateTotalMoney() {
        BigDecimal createPrice;
        BigDecimal spreadPrice = spread.multiply(minChangePrice);
        if (tradeType == Trade.UP) {
            createPrice = price.add(price.multiply(spreadPrice));
        } else {
            createPrice = price.subtract(price.multiply(spreadPrice));
        }
        feeMoney = createPrice.multiply(num).multiply(unitNum).multiply(fee);
        BigDecimal total = num.multiply(createPrice).multiply(unitNum).divide(lever, 8, RoundingMode.DOWN).add(feeMoney);
        setText(tvTotalMoney, NumberUtils.keep(total, 4));
    }


    /**
     * 计算最大交易额
     * 交易额= （价格/杠杆+手续费*价格）*数量*面值
     */
    public void calculateMaxTrade() {
        BigDecimal createPrice;
        BigDecimal spreadPrice = spread.multiply(minChangePrice);
        if (tradeType == Trade.UP) {
            createPrice = price.add(price.multiply(spreadPrice));
        } else {
            createPrice = price.subtract(price.multiply(spreadPrice));
        }
        BigDecimal realPrice = createPrice.divide(lever, 8, RoundingMode.DOWN).add(fee.multiply(createPrice));
        if (realPrice.floatValue() != 0) {
            maxNum = balance.divide(realPrice.multiply(unitNum), 8, RoundingMode.DOWN);
        }
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


    public static ContractLeftFragment newInstance(String code) {
        ContractLeftFragment fragment = new ContractLeftFragment();
        Bundle bundle = new Bundle();
        bundle.putString("code", code);
        fragment.setArguments(bundle);
        return fragment;
    }


    public void setCoinInfo(CoinInfo data) {
        if (data != null) {
            if (data.getLeverage().contains(",")) {
                levers = data.getLeverage().split(",");
            } else {
                levers = new String[]{data.getLeverage()};
            }
            spread = new BigDecimal(data.getSpread());
            minChangePrice = new BigDecimal(data.getVar_price());
            if (!TextUtils.isEmpty(data.getPcs_price())) {
                unitNum = new BigDecimal(data.getPcs_price());
            }
            if (!data.getTrans_fee().startsWith("%")) {
                fee = new BigDecimal(Float.parseFloat(data.getTrans_fee().substring(0, data.getTrans_fee().indexOf("%"))) / 100);
            }
            tvUnit.setText(data.getCode().split("_")[0].toUpperCase());
            initLevel();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updatePrice(CoinBean coinBean) {
        if (code.equals(coinBean.getCode())) {
            price = new BigDecimal(coinBean.getPrice());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updatePrice(CoinInfo coinInfo) {
        if (coinInfo != null) {
            setCoinInfo(coinInfo);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updatePrice(EventContact eventContact) {
        if (eventContact.code == 0) {
            edtPrice.getText().clear();
            edtPrice.setText(NumberUtils.keep(eventContact.content, 4));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeCoin(ContactChangeCoin coinBean) {
        code = coinBean.getCode();
        edtNum.getText().clear();
        pointTabLayout.setCurrentTab(-1);

    }


    public void setBalance(BalanceInfo data) {
        if (data != null) {
            balance = new BigDecimal(data.balance);
            tvUsableUsdt.setText(data.balance + "USDT");
        }
    }
}

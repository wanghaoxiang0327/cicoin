package com.sskj.asset;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.allen.library.SuperTextView;
import com.hjq.toast.ToastUtils;
import com.sskj.asset.data.TransferInfo;
import com.sskj.asset.data.WithdrawInfo;
import com.sskj.common.BaseApplication;
import com.sskj.common.base.BaseActivity;
import com.sskj.common.data.CoinAsset;
import com.sskj.common.data.WithdrawCoinInfo;
import com.sskj.common.dialog.Coin;
import com.sskj.common.dialog.SelectCoinDialog;
import com.sskj.common.dialog.VerifyPasswordDialog;
import com.sskj.common.router.RoutePath;
import com.sskj.common.simple.SimpleTextWatcher;
import com.sskj.common.utils.ClickUtil;
import com.sskj.common.utils.DigitUtils;
import com.sskj.common.utils.MoneyValueFilter;
import com.sskj.common.utils.NumberUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Flowable;

/**
 * 提币
 *
 * @author Hey
 * Create at  2019/06/26
 */
public class WithdrawActivity extends BaseActivity<WithdrawPresenter> {
    @BindView(R2.id.select_coin)
    SuperTextView selectCoin;
    @BindView(R2.id.useful_tv)
    TextView usefulTv;
    @BindView(R2.id.address_edt)
    EditText addressEdt;
    @BindView(R2.id.select_address)
    ImageView selectAddress;
    @BindView(R2.id.count_edt)
    EditText countEdt;
    @BindView(R2.id.coin_name)
    TextView coinName;
    @BindView(R2.id.all)
    TextView all;
    @BindView(R2.id.fee_tv)
    TextView feeTv;
    @BindView(R2.id.arrive_count)
    TextView arriveCount;
    @BindView(R2.id.submit)
    Button submit;
    private final int SELECT_ADDRESS = 1003;
    private String unit;
    private SelectCoinDialog selectCoinDialog;
    private List<CoinAsset> coinList;
    private String pid;
    private String code;
    double minCount;;
    double fee;
    double useful;
    private String codeOrigin;
    private boolean hasTbpwd;

    @Override
    public int getLayoutId() {
        return R.layout.asset_activity_withdraw;
    }

    @Override
    public WithdrawPresenter getPresenter() {
        return new WithdrawPresenter();
    }

    @Override
    public void initView() {
        mToolBarLayout.setRightButtonOnClickListener(view -> {
            WithdrawRecordsActivity.start(this, "cash");
        });

        userViewModel.getUser().observe(this, userBean -> {
            if (userBean != null) {
                hasTbpwd= !TextUtils.isEmpty(userBean.getTpwd());
            }
        });
    }

    @Override
    public void initData() {
        ClickUtil.click(selectAddress, view -> {
            Intent intent = new Intent(this, AddressListActivity.class);
            intent.putExtra("select", true);
            startActivityForResult(intent, SELECT_ADDRESS);
        });
        //选择币种
        ClickUtil.click(selectCoin, view -> {
            if (coinList == null) {
                mPresenter.getCoinAsset(true);
            } else {
                showCoinDialog(coinList);
            }
        });
        ClickUtil.click(submit, view -> {
            if (!hasTbpwd){
                ARouter.getInstance().build(RoutePath.TPWD).navigation();
                ToastUtils.show("请先设置支付密码");
            }
            if (isEmpty(countEdt)) {
                ToastUtils.show(getString(R.string.asset_transferActivity1));
                return;
            }
            double count = Double.parseDouble(getText(countEdt));
            if (count < minCount) {
                ToastUtils.show(getString(R.string.asset_transferActivity2) + minCount);
                return;
            }

            if (count > useful) {
                ToastUtils.show(getString(R.string.asset_withdrawActivity3));
                return;
            }
            new VerifyPasswordDialog(this, true, false, true, 5)
                    .setOnConfirmListener((dialog, ps, sms, google) -> {
                        dialog.dismiss();
                        mPresenter.withdraw(getText(addressEdt), pid, NumberUtils.keepDown(getText(countEdt), 4), ps, sms, BaseApplication.getMobile());
                    }).show();
        });

        countEdt.setFilters(new InputFilter[]{new MoneyValueFilter(DigitUtils.getAssetDigit(code))});
        countEdt.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                computeArrive();
            }
        });
        all.setOnClickListener(view -> {
            countEdt.setText(useful + "");
        });
    }

    @Override
    public void loadData() {
        mPresenter.getCoinAsset(false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_ADDRESS) {
                String address = data.getStringExtra("address");
                if (address != null) {
                    addressEdt.setText(address);
                    addressEdt.setSelection(address.length());
                }
            }
        }
    }

    public void changeCoin(CoinAsset coin) {
        selectCoin.setRightString(coin.getPname());
        coinName.setText(coin.getPname());
        unit = coin.getPname();
        pid = coin.getPid();
        code = coin.getPname();
        mPresenter.getWithdrawInfo(pid);
        countEdt.getText().clear();
    }


    public void setCoinList(List<CoinAsset> data) {
        if (data != null && !data.isEmpty()) {
            coinList = data;
            codeOrigin = data.get(0).getPname();
            Flowable.fromIterable(data)
                    .filter(coinAsset -> coinAsset.getPname().equals(codeOrigin))
                    .subscribe(coinAsset -> {
                        changeCoin(coinAsset);
                    });
        }

    }

    public void showCoinDialog(List<CoinAsset> data) {
        if (selectCoinDialog == null) {
            selectCoinDialog = new SelectCoinDialog(this, (dialog, coin, position) -> {
                changeCoin(coin);
                dialog.dismiss();
            });
        }
        selectCoinDialog.setData(data);
        selectCoinDialog.show();
    }


    public static void start(Context context) {
        Intent intent = new Intent(context, WithdrawActivity.class);
        context.startActivity(intent);
    }


    public void setWithDrawInfo(WithdrawInfo data) {
        usefulTv.setText(NumberUtils.keepDown(data.balance, DigitUtils.getAssetDigit(code)) + " " + unit);
        countEdt.setHint(getString(R.string.asset_withdrawActivity4) + data.tb_minum);
        feeTv.setText(getString(R.string.asset_transferActivity4) + data.tb_fee + " " + unit + getString(R.string.asset_transferActivity5));
        fee = Double.valueOf(data.tb_fee);
        useful = Double.valueOf(data.balance);
    }

    /**
     * 计算到账数量
     */
    public void computeArrive() {
        double count;
        if (isEmpty(countEdt)) {
            count = 0;
        } else {
            count = Double.parseDouble(getText(countEdt));
        }
        double arriveNum = count - fee;
        if (arriveNum < 0) {
            arriveNum = 0;
        }
        setText(arriveCount, getString(R.string.asset_withdrawActivity7) + NumberUtils.keepDown(arriveNum, DigitUtils.getAssetDigit(code)) + " " + unit);
    }

    public void withdrawSuccess() {
        mPresenter.getWithdrawInfo(pid);
        countEdt.getText().clear();
    }
}

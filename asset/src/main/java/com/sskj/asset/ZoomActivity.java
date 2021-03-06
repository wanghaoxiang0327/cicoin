package com.sskj.asset;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hjq.toast.ToastUtils;
import com.jakewharton.rxbinding3.view.RxView;
import com.jakewharton.rxbinding3.widget.RxTextView;
import com.jakewharton.rxbinding3.widget.TextViewAfterTextChangeEvent;
import com.sskj.asset.data.TransferInfoBean;
import com.sskj.common.App;
import com.sskj.common.base.BaseActivity;
import com.sskj.common.data.CoinAsset;
import com.sskj.common.data.CoinListEntity;
import com.sskj.common.dialog.SelectCoinDialog;
import com.sskj.common.router.RoutePath;
import com.sskj.common.utils.BigDecimalUtils;
import com.sskj.common.utils.ClickUtil;
import com.sskj.common.utils.DigitUtils;
import com.sskj.common.utils.MoneyValueFilter;
import com.sskj.common.utils.NumberUtils;
import com.sskj.common.view.ToolBarLayout;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * @author Hey
 * Create at  2019/08/23 14:57:15
 */
@Route(path = RoutePath.ZOOM)
public class ZoomActivity extends BaseActivity<ZoomPresenter> {
    @BindView(R2.id.toolbar)
    ToolBarLayout toolbar;
    @BindView(R2.id.tv_old_coin)
    TextView tvOldCoin;
    @BindView(R2.id.tv_old_amount)
    TextView tvOldAmount;
    @BindView(R2.id.iv_exchange)
    ImageView ivExchange;
    @BindView(R2.id.tv_new_coin)
    TextView tvNewCoin;
    @BindView(R2.id.tv_new_amount)
    TextView tvNewAmount;
    @BindView(R2.id.et_input_exchange_num)
    EditText etInputExchangeNum;
    @BindView(R2.id.et_arrival_num)
    EditText etArrivalNum;
    @BindView(R2.id.et_asset_pwd)
    EditText etAssetPwd;
    @BindView(R2.id.submit)
    Button submit;


    List<CoinListEntity> data = new ArrayList<>();
    List<TransferInfoBean> rightData = new ArrayList<>();
    @BindView(R2.id.left)
    LinearLayout left;
    @BindView(R2.id.right)
    LinearLayout right;
    @BindView(R2.id.zhang)
    ImageView zhang;
    @BindView(R2.id.die)
    ImageView die;
    @BindView(R2.id.ky)
    TextView ky;
    @BindView(R2.id.sxf)
    TextView sxf;

    private String leftCode = "", rightCode = "";
    //左边数据
    List<CoinAsset> leftCoin = new ArrayList<>();
    //右边数据
    List<CoinAsset> rightCoin = new ArrayList<>();
    private double currentX = 0, currentY = 0;

    //默认左边展示第一条
    int lSelect = 0;
    //默认右边展示第一条
    int rSelect = 0;
    private Disposable inter;

    private double maxCount;
    private String pid;

    @Override
    public int getLayoutId() {
        return R.layout.asset_activity_zoom;
    }

    @Override
    public ZoomPresenter getPresenter() {
        return new ZoomPresenter();
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
        mPresenter.getExchangeInfo();
        etInputExchangeNum.setFilters(new InputFilter[]{
                new MoneyValueFilter().setDigits(6)
        });
        ClickUtil.click(left, view -> {
            SelectCoinDialog ss = new SelectCoinDialog(this, (dialog, coin, position) -> {
                tvOldCoin.setText(coin.getPname());
                tvOldAmount.setText(DigitUtils.getDigits(coin.getMark(), Double.parseDouble(coin.getPrice())));
                pid = coin.getPid();
                lSelect = position;
                leftCode = coin.getMark();
                mPresenter.Transfer(leftCode);
                rSelect = 0;
                dialog.dismiss();
            }).setData(leftCoin);

            ss.show();
        });
        ClickUtil.click(right, view -> {
            SelectCoinDialog s = new SelectCoinDialog(this, (dialog, coin, position) -> {
                tvNewCoin.setText(coin.getPname());
                tvNewAmount.setText(DigitUtils.getDigits(coin.getMark(), Double.parseDouble(coin.getPrice())));
                rSelect = position;
                rightCode = coin.getMark();
                dialog.dismiss();
            }).setData(rightCoin);
            s.show();

        });
        RxTextView.textChanges(etInputExchangeNum)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(a -> {
                    String sss = a.toString();
                    Double dd = TextUtils.isEmpty(sss) ? 0d : Double.parseDouble(sss);
                    if (dd > maxCount) {
                        etInputExchangeNum.setText(maxCount + "");
                        etInputExchangeNum.setSelection(getText(etInputExchangeNum).length());
                        dd = maxCount;
                    }
                    Double ee = (isEmpty(tvOldAmount) ? 0d : Double.parseDouble(getText(tvOldAmount))) * dd /
                            (isEmpty(tvNewAmount) ? 1 : Double.parseDouble(getText(tvNewAmount)));

                    etArrivalNum.setText(NumberUtils.keepDown(ee, 6));

                });


        ClickUtil.click(submit, view -> {
            if (TextUtils.isEmpty(getText(etInputExchangeNum))) {
                ToastUtils.show(etInputExchangeNum.getHint().toString());
                return;
            }
            if (TextUtils.isEmpty(getText(etAssetPwd))) {
                ToastUtils.show(etAssetPwd.getHint().toString());
                return;
            }
            mPresenter.getTransfer(leftCoin.get(lSelect).getMark(), rightCoin.get(rSelect).getMark(), getText(etInputExchangeNum), getText(etAssetPwd));
        });
    }

    @Override
    public void initEvent() {
        super.initEvent();
        toolbar.setRightButtonOnClickListener(v -> ExchangeDetailActivity.start(ZoomActivity.this));
    }

    @Override
    public void loadData() {
        super.loadData();
        //请求左边数据
        mPresenter.getExchangeInfo();

        inter = Flowable.interval(2, TimeUnit.SECONDS)
                .subscribe(aLong ->
                        mPresenter.getExchangeInfo());
    }

    public static void start(Context context, int type) {
        Intent intent = new Intent(context, ZoomActivity.class);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }


    public void setCoinList(List<CoinListEntity> data) {
        if (data != null && !data.isEmpty()) {
            this.data.clear();
            this.data = data;
            tvOldCoin.setText(data.get(lSelect).getPname());
            leftCode = data.get(lSelect).getCode();
            if (Double.parseDouble(data.get(lSelect).getActprice()) >= currentX) {
                //张
                tvOldAmount.setTextColor(ContextCompat.getColor(App.INSTANCE, R.color.common_red));
                zhang.setImageResource(R.mipmap.asset_zhang);
                zhang.setVisibility(View.VISIBLE);
            } else if (Double.parseDouble(data.get(rSelect).getActprice()) < currentX) {
                //die
                tvOldAmount.setTextColor(ContextCompat.getColor(App.INSTANCE, R.color.common_green));
                zhang.setImageResource(R.mipmap.asset_die);
                zhang.setVisibility(View.VISIBLE);
            } else {
                tvOldAmount.setTextColor(ContextCompat.getColor(App.INSTANCE, R.color.common_text));
                zhang.setVisibility(View.GONE);
            }
            currentX = Double.parseDouble(data.get(lSelect).getActprice());
            tvOldAmount.setText(DigitUtils.getDigits(leftCode, currentX));
            Flowable.fromIterable(data)
                    .map(coinListEntity -> new CoinAsset(coinListEntity.getPid(), coinListEntity.getPname(),
                            coinListEntity.getActprice(), coinListEntity.getCode())).toList()
                    .subscribe(coinAssets -> {
                        leftCoin = coinAssets;
                    });
            mPresenter.Transfer(leftCode);
            mPresenter.getMax(data.get(lSelect).getPid());
        }
    }

    public void setRCoinList(List<TransferInfoBean> da) {
        right.setEnabled(true);
        die.setVisibility(View.VISIBLE);
        tvNewAmount.setVisibility(View.VISIBLE);
        rightData.clear();
        rightData = da;
        rightCode = da.get(rSelect).getEx_coin();
        tvNewCoin.setText(da.get(rSelect).getEx_name());


        if (Double.parseDouble(da.get(rSelect).getEx_coin_actprice()) >= currentY) {
            //张
            tvNewAmount.setTextColor(ContextCompat.getColor(App.INSTANCE, R.color.common_red));
            die.setImageResource(R.mipmap.asset_zhang);
            die.setVisibility(View.VISIBLE);
        } else if (Double.parseDouble(da.get(rSelect).getEx_coin_actprice()) < currentY) {
            //die
            tvNewAmount.setTextColor(ContextCompat.getColor(App.INSTANCE, R.color.common_green));
            die.setImageResource(R.mipmap.asset_die);
            die.setVisibility(View.VISIBLE);
        } else {
            tvNewAmount.setTextColor(ContextCompat.getColor(App.INSTANCE, R.color.common_text));
            die.setVisibility(View.GONE);
        }
        currentY = Double.parseDouble(da.get(rSelect).getEx_coin_actprice());
        tvNewAmount.setText(DigitUtils.getDigits(rightCode, currentY));
        Flowable.fromIterable(da)
                .map(coinListEntity -> new CoinAsset(coinListEntity.getEx_pid(), coinListEntity.getEx_name(),
                        coinListEntity.getEx_coin_actprice(), coinListEntity.getEx_coin())).toList()
                .subscribe(coinAssets -> {
                    rightCoin = coinAssets;
                });
    }

    public void setError() {
        tvNewCoin.setText("暂未开通");
        tvNewAmount.setVisibility(View.GONE);
        tvNewAmount.setText("");
        die.setVisibility(View.GONE);
        right.setEnabled(false);
    }

    public void max(double max, String fee) {
        maxCount = max;
        ky.setText(String.format(getString(R.string.asset_ky), max + ""));
        sxf.setText(String.format(getString(R.string.asset_sxf), fee));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (inter != null) {
            inter.dispose();
        }
    }

    public void transeSuceesss() {
        finish();
    }
}

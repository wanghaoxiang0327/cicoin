package com.sskj.asset;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.sskj.asset.data.TransferInfoBean;
import com.sskj.common.base.BaseActivity;
import com.sskj.common.data.CoinAsset;
import com.sskj.common.data.CoinListEntity;
import com.sskj.common.dialog.SelectCoinDialog;
import com.sskj.common.router.RoutePath;
import com.sskj.common.utils.ClickUtil;
import com.sskj.common.view.ToolBarLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Flowable;
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


    List<CoinListEntity> data;
    List<TransferInfoBean> rightData;
    @BindView(R2.id.left)
    LinearLayout left;
    @BindView(R2.id.right)
    LinearLayout right;

    private String leftCode = "", rightCode = "";
    List<CoinAsset> leftCoin = new ArrayList<>();
    List<CoinAsset> rightCoin = new ArrayList<>();

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


        ClickUtil.click(left, view -> {
           SelectCoinDialog ss= new SelectCoinDialog(this, (dialog, coin, position) -> {
                tvOldCoin.setText(coin.getPname());
                leftCode = coin.getPid();
                mPresenter.Transfer(leftCode);
                dialog.dismiss();
            }).setData(leftCoin);

           ss.show();
        });
        ClickUtil.click(right, view -> {
            SelectCoinDialog  s=new SelectCoinDialog(this, (dialog, coin, position) -> {
                tvNewCoin.setText(coin.getPname());
                leftCode = coin.getPid();
                dialog.dismiss();
            }).setData(rightCoin);
            s.show();

        });
    }

    @Override
    public void initEvent() {
        super.initEvent();
        toolbar.setRightButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExchangeDetailActivity.start(ZoomActivity.this);
            }
        });
    }

    @Override
    public void loadData() {
        super.loadData();
        mPresenter.getExchangeInfo();
    }

    public static void start(Context context, int type) {
        Intent intent = new Intent(context, ZoomActivity.class);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }


    public void setCoinList(List<CoinListEntity> data) {
        if (data != null && !data.isEmpty()) {
            this.data = data;
            tvOldCoin.setText(data.get(0).getPname());
            leftCode = data.get(0).getCode();
            Flowable.fromIterable(data)
                    .map(coinListEntity -> new CoinAsset(coinListEntity.getCode(), coinListEntity.getPname())).toList()
                    .subscribe(coinAssets -> {
                        leftCoin = coinAssets;
                    });
            mPresenter.Transfer(leftCode);
        }
    }

    public void setRCoinList(List<TransferInfoBean> da) {
        right.setEnabled(true);
        rightData = da;
        rightCode = da.get(0).getEx_coin();
        tvNewCoin.setText(da.get(0).getEx_name());
        Flowable.fromIterable(da)
                .map(coinListEntity -> new CoinAsset(coinListEntity.getEx_coin(), coinListEntity.getEx_name())).toList()
                .subscribe(coinAssets -> {
                    rightCoin = coinAssets;
                });
    }

    public void setError(){
        tvNewCoin.setText("暂未开通");
        right.setEnabled(false);
    }


}

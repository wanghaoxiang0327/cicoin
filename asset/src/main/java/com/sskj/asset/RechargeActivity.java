package com.sskj.asset;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.allen.library.SuperTextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.sskj.common.base.BaseActivity;
import com.sskj.common.data.CoinAsset;
import com.sskj.common.dialog.SelectCoinDialog;
import com.sskj.common.http.BaseHttpConfig;
import com.sskj.common.router.RoutePath;
import com.sskj.common.utils.ClickUtil;
import com.sskj.common.utils.CopyUtils;
import com.sskj.common.view.ToolBarLayout;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import io.reactivex.Flowable;

/**
 * 充币
 *
 * @author Hey
 * Create at  2019/06/26
 */
@Route(path = RoutePath.CB)
public class RechargeActivity extends BaseActivity<RechargePresenter> {

    @BindView(R2.id.select_coin)
    SuperTextView selectCoin;
    @BindView(R2.id.qr_code_img)
    ImageView qrCodeImg;
    @BindView(R2.id.recharge_address_tv)
    TextView rechargeAddressTv;
    @BindView(R2.id.recharge_tip)
    TextView rechargeTip;
    @BindView(R2.id.copy)
    Button copy;
    private SelectCoinDialog selectCoinDialog;
    private List<CoinAsset> coinList;
    private String pid;
    private String codeOrigin;

    @Override
    public int getLayoutId() {
        return R.layout.asset_activity_recharge;
    }

    @Override
    public RechargePresenter getPresenter() {
        return new RechargePresenter();
    }

    @Override
    public void initView() {
        selectCoin.setOnClickListener(view -> {
            if (coinList == null) {
                mPresenter.getCoinAsset(true);
            } else {
                showCoinDialog(coinList);
            }
        });
    }

    @Override
    public void initData() {
        mPresenter.getCoinAsset(false);
        ClickUtil.click(copy, view -> CopyUtils.copy(this, getText(rechargeAddressTv)));

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
        Intent intent = new Intent(context, RechargeActivity.class);
        context.startActivity(intent);
    }


    public void setCoinList(List<CoinAsset> data) {
        if (data != null && !data.isEmpty()) {
            coinList = data;
            codeOrigin = data.get(0).getPname();
            Flowable.fromIterable(data)
                    .filter(coinAsset -> coinAsset.getPname().equals(codeOrigin))
                    .map(coinAsset -> {
                        String name;
                        if (coinAsset.getPname().contains("_")) {
                            name = coinAsset.getPname().substring(0, coinAsset.getPname().indexOf("_"));
                        } else {
                            name = coinAsset.getPname();
                        }
                        coinAsset.setPname(name);
                        return coinAsset;
                    })
                    .subscribe(coinAsset -> {
                        changeCoin(coinAsset);
                    });
        }

    }

    public void changeCoin(CoinAsset coin) {
        pid = coin.getPid();
        selectCoin.setRightString(coin.getPname());
        mPresenter.getRechargeInfo(pid);
        rechargeTip.setText(getString(R.string.asset_rechargeActivity1) + coin.getPname() + getString(R.string.asset_rechargeActivity2));
    }


    public void setRechargeInfo(Map<String, String> result) {
        Glide.with(this)
//                .skipMemoryCache(true) // 不使用内存缓存
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .load(BaseHttpConfig.BASE_URL + "/" + result.get("qrc"))
                .apply(new RequestOptions()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE))
                .into(qrCodeImg);
        rechargeAddressTv.setText(result.get("url"));
    }
}

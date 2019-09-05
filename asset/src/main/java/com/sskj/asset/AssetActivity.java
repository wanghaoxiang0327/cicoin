package com.sskj.asset;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.hjq.toast.ToastUtils;
import com.sskj.common.adapter.BaseAdapter;
import com.sskj.common.adapter.ViewHolder;
import com.sskj.common.base.BaseActivity;
import com.sskj.common.dialog.TipDialog;
import com.sskj.common.router.RoutePath;
import com.sskj.common.utils.ClickUtil;
import com.sskj.common.utils.CoinIcon;
import com.sskj.common.utils.NumberUtils;

import butterknife.BindView;

/**
 * 资产
 *
 * @author Hey
 * Create at  2019/06/26
 */
@Route(path = RoutePath.ASSET)
public class AssetActivity extends BaseActivity<AssetPresenter> {
    @BindView(R2.id.asset_list)
    RecyclerView assetList;
    BaseAdapter<AllAssetEntity.Asset> assetAdapter;
    @BindView(R2.id.ll)
    LinearLayout ll;
    @BindView(R2.id.ll_recharge)
    LinearLayout llRecharge;
    @BindView(R2.id.tv_all_asset)
    TextView tvAllAsset;
    @BindView(R2.id.tv_asset_rnb)
    TextView tvAssetRnb;
    @BindView(R2.id.ll_cashOut)
    LinearLayout llCashOut;
    @BindView(R2.id.ll_transfer)
    LinearLayout llTransfer;
    @BindView(R2.id.iv_eye)
    ImageView ivEye;
    @BindView(R2.id.ll_nolever)
    LinearLayout llNolever;
    //是否设置支付密码
    private boolean setPs;
    private boolean isOpen = true;
    AllAssetEntity data;
    //初级认证
    boolean isFirstCheck = false;
    //高级认证
    boolean isSecondCheck = false;

    @Override
    public int getLayoutId() {
        return R.layout.asset_fragment_asset;
    }

    @Override
    public AssetPresenter getPresenter() {
        return new AssetPresenter();
    }

    @Override
    public void initView() {
        userViewModel.getUser().observe(this, userBean -> {
            if (userBean != null) {
                setPs = !TextUtils.isEmpty(userBean.getTpwd());
                isFirstCheck = userBean.getStatus() == 3;
                isSecondCheck = userBean.getAuth_status() == 3;
            }
        });
        assetList.setLayoutManager(new LinearLayoutManager(this));
        assetAdapter = new BaseAdapter<AllAssetEntity.Asset>(R.layout.asset_item_asset, null, assetList) {
            @Override
            public void bind(ViewHolder holder, AllAssetEntity.Asset item) {
                holder.setText(R.id.asset_useful, NumberUtils.keepMaxDown(item.usable, 4)).setText(R.id.asset_frost, NumberUtils.keepMaxDown(item.frost, 4))
                        .setText(R.id.coin_name, item.pname)
                        .setText(R.id.tv_asset_equivalent, NumberUtils.keep2(item.cny));
                holder.setImageResource(R.id.coin_icon, CoinIcon.getIcon(item.mark));
                holder.itemView.setOnClickListener(v -> BillDetailActivity.start(AssetActivity.this, item.pid));
            }
        };
        ClickUtil.click(llRecharge, view -> {
            if (isFirstCheck) {
                RechargeActivity.start(this);
            } else {
                ARouter.getInstance().build(RoutePath.VERIFY_HOME).navigation();
                ToastUtils.show("请先完成初级验证");
            }
        });
        ClickUtil.click(ivEye, view -> {
            if (isOpen) {
                tvAllAsset.setText(NumberUtils.keepMaxDown(data.ttl_money, 4));
                tvAssetRnb.setText("≈" + NumberUtils.keep2(data.ttl_cnymoney) + "CNY");
                isOpen = false;
            } else {
                tvAllAsset.setText("****");
                tvAssetRnb.setText("≈****CNY");
                isOpen = true;
            }
        });
        ClickUtil.click(llCashOut, view -> {
            if (!setPs) {
                new TipDialog(this)
                        .setContent(getString(R.string.asset_assetFragment2))
                        .setCancelVisible(View.GONE)
                        .setConfirmListener(dialog -> {
                            dialog.dismiss();
                            ARouter.getInstance().build(RoutePath.SECURITY).navigation();
                        })
                        .show();
                return;
            }
            if (!isSecondCheck) {
                ARouter.getInstance().build(RoutePath.VERIFY_HOME).navigation();
                ToastUtils.show("请先完成高级验证");
                return;
            }
//
            WithdrawActivity.start(this);
        });
        ClickUtil.click(llTransfer, view -> {
            ZoomActivity.start(this, 1);
        });
    }

    @Override
    public void initData() {
        //资产明细
        mToolBarLayout.setRightButtonOnClickListener(view -> {
            AssetRecordsActivity.start(this);
        });
    }

    @Override
    public void loadData() {
        mPresenter.getAllAsset();
    }

    public void getAllAsset(AllAssetEntity data) {
        this.data = data;
        tvAllAsset.setText(NumberUtils.keepMaxDown(data.ttl_money, 4));
        tvAssetRnb.setText("≈" + NumberUtils.keep2(data.ttl_cnymoney) + "CNY");
        if (data.asset != null && data.asset.size() > 0) {
            assetAdapter.setNewData(data.asset);
        }
    }
}

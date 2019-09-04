package com.sskj.asset;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.sskj.asset.data.AssetData;
import com.sskj.asset.data.GAssetBean;
import com.sskj.asset.viewpagercard.CardItem;
import com.sskj.asset.viewpagercard.CardPagerAdapter;
import com.sskj.asset.viewpagercard.ShadowTransformer;
import com.sskj.common.CommonConfig;
import com.sskj.common.adapter.BaseAdapter;
import com.sskj.common.adapter.ViewHolder;
import com.sskj.common.base.BaseActivity;
import com.sskj.common.dialog.TipDialog;
import com.sskj.common.router.RoutePath;
import com.sskj.common.rxbus.Subscribe;
import com.sskj.common.rxbus.ThreadMode;
import com.sskj.common.utils.ClickUtil;
import com.sskj.common.utils.CoinIcon;
import com.sskj.common.utils.DigitUtils;
import com.sskj.common.utils.NumberUtils;
import com.sskj.common.utils.SpUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 资产
 *
 * @author Hey
 * Create at  2019/06/26
 */
@Route(path = RoutePath.ASSET)
public class AssetActivity extends BaseActivity<AssetPresenter> {


    @BindView(R2.id.total_asset_tv)
    TextView totalAssetTv;
    @BindView(R2.id.hide_asset_img)
    ImageView hideAssetImg;
    @BindView(R2.id.cny_asset_tv)
    TextView cnyAssetTv;
    @BindView(R2.id.asset_list)
    RecyclerView assetList;
    @BindView(R2.id.content_layout)
    NestedScrollView contentLayout;

    BaseAdapter<AssetData.ResBean.AssetBean> assetAdapter;
    BaseAdapter<GAssetBean.ResBean.AssetBean> gassetAdapter;
    @BindView(R2.id.viewPager)
    ViewPager viewPager;
    @BindView(R2.id.ll)
    LinearLayout ll;
    @BindView(R2.id.recharge)
    TextView recharge;
    @BindView(R2.id.cashOut)
    TextView cashOut;
    @BindView(R2.id.transfer2)
    TextView transfer2;
    @BindView(R2.id.transfer)
    TextView transfer;
    @BindView(R2.id.ll_nolever)
    LinearLayout llNolever;
    @BindView(R2.id.transfer_gang)
    TextView transferGang;
    @BindView(R2.id.gasset_list)
    RecyclerView gassetList;
    private boolean checkSms;
    private boolean checkGoogle;
    //是否是经纪人
    private boolean isBroker;
    //是否设置支付密码
    private boolean setPs;

    private boolean showAsset;
    private String total;
    private String cny;


    private ShadowTransformer mCardShadowTransformer;
    private CardItem item1;
    private CardItem item2;
    private int curtpageitem = 0;

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

        wrapRefresh(contentLayout);
        setEnableLoadMore(false);
        showAsset = SpUtil.getBoolean(CommonConfig.SHOWASSET, true);
        userViewModel.getUser().observe(this, userBean -> {
            if (userBean != null) {
                checkSms = userBean.getIsStartSms() == 1;
                checkGoogle = userBean.getIsStartGoogle() == 1;
                isBroker = userBean.getRank() != 0;
                setPs = !TextUtils.isEmpty(userBean.getTpwd());
            }
        });

        assetList.setLayoutManager(new LinearLayoutManager(this));
        assetAdapter = new BaseAdapter<AssetData.ResBean.AssetBean>(R.layout.asset_item_asset, null, assetList) {
            @Override
            public void bind(ViewHolder holder, AssetData.ResBean.AssetBean item) {
                holder.setText(R.id.coin_name, item.getPname());
                holder.setImageResource(R.id.coin_icon, CoinIcon.getIcon(item.getMark()));
                if (showAsset) {
                    holder.setText(R.id.asset_useful, NumberUtils.keepDown(item.getUsable(), DigitUtils.getAssetDigit(item.getPname())));
                    holder.setText(R.id.asset_frost, NumberUtils.keepDown(item.getFrost(), DigitUtils.getAssetDigit(item.getPname())));
                }else{
                    holder.setText(R.id.asset_useful, "****");
                    holder.setText(R.id.asset_frost,"****");
                }
            }
        };
        gassetList.setLayoutManager(new LinearLayoutManager(this));
        gassetAdapter = new BaseAdapter<GAssetBean.ResBean.AssetBean>(R.layout.asset_item_asset, null, gassetList) {
            @Override
            public void bind(ViewHolder holder, GAssetBean.ResBean.AssetBean item) {
                holder.setText(R.id.coin_name, item.getPname());
                holder.setImageResource(R.id.coin_icon, CoinIcon.getIcon(item.getMark()));
                if (showAsset) {
                    holder.setText(R.id.asset_useful, NumberUtils.keepDown(item.getUsable(), DigitUtils.getAssetDigit(item.getPname())));
                    holder.setText(R.id.asset_frost, NumberUtils.keepDown(item.getFrost(), DigitUtils.getAssetDigit(item.getPname())));
                }else{
                    holder.setText(R.id.asset_useful, "****");
                    holder.setText(R.id.asset_frost,"****");
                }
            }
        };



        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                curtpageitem = position;
                if (position == 0) {
                    llNolever.setVisibility(View.VISIBLE);
                    transferGang.setVisibility(View.GONE);
                    assetList.setVisibility(View.VISIBLE);
                    gassetList.setVisibility(View.GONE);
                } else {
                    llNolever.setVisibility(View.GONE);
                    transferGang.setVisibility(View.VISIBLE);
                    gassetList.setVisibility(View.VISIBLE);
                    assetList.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        ClickUtil.click(recharge, view -> {
            RechargeActivity.start(this);
        });
        ClickUtil.click(transfer, view -> {
            if (!isBroker) {
                new TipDialog(this)
                        .setContent(getString(R.string.asset_assetFragment101))
                        .setCancelVisible(View.GONE)
                        .setConfirmListener(dialog -> {
                            dialog.dismiss();
                        })
                        .show();
                return;
            }

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

            if (!checkSms && !checkGoogle) {
                new TipDialog(this)
                        .setContent(getString(R.string.asset_assetFragment1))
                        .setCancelVisible(View.GONE)
                        .setConfirmListener(dialog -> {
                            dialog.dismiss();
                            ARouter.getInstance().build(RoutePath.SECURITY).navigation();
                        })
                        .show();
            } else {
                TransferActivity.start(this);
            }

        });
        ClickUtil.click(cashOut, view -> {

            if (!setPs) {
                new TipDialog(this)
                        .setContent(getString(R.string.asset_assetFragment2))
                        .setCancelVisible(View.GONE)
                        .setConfirmListener(dialog -> {
                            dialog.dismiss();
//                                    ARouter.getInstance().build(RoutePath.SECURITY).navigation();
                        })
                        .show();
                return;
            }
//
            if (!checkSms && !checkGoogle) {
                new TipDialog(this)
                        .setContent(getString(R.string.asset_assetFragment1))
                        .setCancelVisible(View.GONE)
                        .setConfirmListener(dialog -> {
                            dialog.dismiss();
//                                    ARouter.getInstance().build(RoutePath.SECURITY).navigation();
                        })
                        .show();
                return;
            }
            WithdrawActivity.start(this);
        });
        ClickUtil.click(transfer2, view -> {
            ZoomActivity.start(this, 1);
        });
        ClickUtil.click(transferGang, view -> {
            ZoomActivity.start(this, 2);
        });
    }


    @Override
    public void initData() {
        //资产明细
        mToolBarLayout.setRightButtonOnClickListener(view -> {
            AssetRecordsActivity.start(this);
        });
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updatePager(String str){
        if (str!= null){
            if (str.equals("isShowAsset")){
                showAsset = SpUtil.getBoolean(CommonConfig.SHOWASSET, true);
                CardPagerAdapter mCardAdapter = new CardPagerAdapter();
                mCardAdapter.addCardItem(item1);
                mCardAdapter.addCardItem(item2);
                mCardShadowTransformer = new ShadowTransformer(viewPager, mCardAdapter);
                mCardShadowTransformer.enableScaling(true);
                viewPager.setAdapter(mCardAdapter);
                viewPager.setCurrentItem(curtpageitem);
                viewPager.setPageTransformer(false, mCardShadowTransformer);
                assetAdapter.notifyDataSetChanged();
                gassetAdapter.notifyDataSetChanged();
            }
        }
    }
    @Override
    public void loadData() {
//        mPresenter.getAsset();
    }


    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        mPresenter.getAsset();
    }


//    public static AssetActivity newInstance() {
//        AssetActivity fragment = new AssetActivity();
//        Bundle bundle = new Bundle();
//        fragment.setArguments(bundle);
//        return fragment;
//    }


    public void setAsset(AssetData data) {
        total = NumberUtils.keepDown(data.getRes().getTotal().getTtl_money(), 4);
        cny = NumberUtils.keepDown(data.getRes().getTotal().getTtl_cnymoney(), 2);
        assetAdapter.setNewData(data.getRes().getAsset());
        item1 = new CardItem(getString(R.string.asset_fragment_assetaccount1), total, cny);
        mPresenter.getGAsset();
    }

    public void setGAsset(GAssetBean data) {
        total = NumberUtils.keepDown(data.getRes().getTotal().getTtl_money(), 4);
        cny = NumberUtils.keepDown(data.getRes().getTotal().getTtl_cnymoney(), 2);
        gassetAdapter.setNewData(data.getRes().getAsset());
        item2 = new CardItem(getString(R.string.asset_fragment_assetaccount2), total, cny);
        CardPagerAdapter mCardAdapter = new CardPagerAdapter();
        mCardAdapter.addCardItem(item1);
        mCardAdapter.addCardItem(item2);
        mCardShadowTransformer = new ShadowTransformer(viewPager, mCardAdapter);
        mCardShadowTransformer.enableScaling(true);
        viewPager.setAdapter(mCardAdapter);
        viewPager.setPageTransformer(false, mCardShadowTransformer);
        viewPager.setOffscreenPageLimit(1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getAsset();
    }
}

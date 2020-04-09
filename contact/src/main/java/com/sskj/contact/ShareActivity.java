package com.sskj.contact;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.azhon.appupdate.utils.SharePreUtil;
import com.bumptech.glide.Glide;
import com.gyf.barlibrary.ImmersionBar;
import com.sskj.common.base.BaseActivity;
import com.sskj.common.utils.ImgUtil;
import com.sskj.common.utils.NumberUtils;
import com.sskj.common.view.ToolBarLayout;
import com.sskj.contact.data.DealOrder;
import com.sskj.contact.data.ShareInfo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Hey
 * Create at  2019/09/05 08:56:35
 */
public class ShareActivity extends BaseActivity<SharePresenter> {
    DealOrder dealOrder;
    @BindView(R2.id.tv_market_more_or_empty)
    TextView tvMarketMoreOrEmpty;
    @BindView(R2.id.tv_code)
    TextView tvCode;
    @BindView(R2.id.toolbar)
    ToolBarLayout toolbar;
    @BindView(R2.id.tv_coin_name)
    TextView tvCoinName;
    @BindView(R2.id.tv_coin_count)
    TextView tvCoinCount;
    @BindView(R2.id.tv_open_price)
    TextView tvOpenPrice;
    @BindView(R2.id.tv_coin_name_des)
    TextView tvCoinNameDes;
    @BindView(R2.id.tv_coin_count_des)
    TextView tvCoinCountDes;
    @BindView(R2.id.tv_price_des)
    TextView tvPriceDes;
    @BindView(R2.id.iv_share_code)
    ImageView ivShareCode;
    @BindView(R2.id.rl_share)
    RelativeLayout rlShare;

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this)
                .transparentStatusBar()
                .fitsSystemWindows(false)
                //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .statusBarDarkFont(false, 0.2f)
                .init();
    }

    @Override
    public int getLayoutId() {
        return R.layout.contact_activity_share;
    }

    @Override
    public SharePresenter getPresenter() {
        return new SharePresenter();
    }

    @Override
    public void initView() {
        toolbar.mLeftButton.setCompoundDrawableTintList(ColorStateList.valueOf(color(R.color.common_white)));
        dealOrder = (DealOrder) getIntent().getSerializableExtra("dealOrder");
        if (dealOrder != null) {
            tvMarketMoreOrEmpty.setText(dealOrder.getOtype() == 1 ? getString(R.string.common_make_more) : getString(R.string.common_make_empty));
            tvMarketMoreOrEmpty.setBackgroundResource(dealOrder.getOtype() == 1 ? R.drawable.common_red_bg_5 : R.drawable.common_green_bg_5);
            tvCode.setText(NumberUtils.keepMaxDown(dealOrder.getProfit(), 4) + "USDT");
            if (dealOrder.getProfit().contains("-")) {
                tvCode.setTextColor(color(R.color.common_green));
                tvCoinNameDes.setTextColor(color(R.color.common_green));
                tvCoinCountDes.setTextColor(color(R.color.common_green));
                tvPriceDes.setTextColor(color(R.color.common_green));
            } else {
                tvCode.setTextColor(color(R.color.common_red));
                tvCoinNameDes.setTextColor(color(R.color.common_red));
                tvCoinCountDes.setTextColor(color(R.color.common_red));
                tvPriceDes.setTextColor(color(R.color.common_red));
            }
            tvCoinName.setText(dealOrder.getPname());
            tvCoinCount.setText(NumberUtils.keepMaxDown(dealOrder.getBuynum(), 4));
            tvOpenPrice.setText(NumberUtils.keepMaxDown(dealOrder.getSellprice(), 4));
        }

        toolbar.setRightButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImgUtil.saveImageToGallery(ShareActivity.this, rlShare);
            }
        });
    }

    @Override
    public void initData() {
        mPresenter.getShareInfo();
    }

    public void setShareInfo(ShareInfo shareInfo) {
        if (shareInfo != null) {
            Glide.with(this)
                    .load(shareInfo.getQrc())
                    .into(ivShareCode);
        }
    }

    public static void start(Context context, DealOrder dealOrder) {
        Intent intent = new Intent(context, ShareActivity.class);
        intent.putExtra("dealOrder", dealOrder);
        context.startActivity(intent);
    }
}

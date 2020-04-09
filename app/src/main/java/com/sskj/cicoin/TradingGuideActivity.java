package com.sskj.cicoin;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.sskj.common.base.BaseActivity;
import com.sskj.common.tab.TabItem;
import com.sskj.common.tab.TabLayout;
import com.sskj.common.utils.ClickUtil;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @author Hey
 * Create at  2019/09/03 09:17:24
 * 交易指南
 */
public class TradingGuideActivity extends BaseActivity<TradingGuidePresenter> {
    @BindView(R2.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R2.id.news_content)
    FrameLayout newsContent;
    ArrayList<TabItem> tabs = new ArrayList<>();
    ArrayList<Fragment> fragments = new ArrayList<>();
    @BindView(R2.id.left_img)
    ImageView leftImg;


    @Override
    public int getLayoutId() {
        return R.layout.app_activity_trading_guide;
    }

    @Override
    public TradingGuidePresenter getPresenter() {
        return new TradingGuidePresenter();
    }

    @Override
    public void initView() {
        tabs.add(new TabItem(getString(R.string.app_industry_information)));
        tabs.add(new TabItem(getString(R.string.app_trading_guide)));
        fragments.add(InformationFragment.newInstance());
        fragments.add(TransGuideFragment.newInstance());
        tabLayout.setTextSelectColor(getResources().getColor(R.color.common_tip));
        tabLayout.setTabData(tabs, getSupportFragmentManager(), R.id.news_content, fragments);
        ClickUtil.click(leftImg, view -> {
            finish();
        });
    }

    @Override
    public void initData() {

    }

    public static void start(Context context) {
        Intent intent = new Intent(context, TradingGuideActivity.class);
        context.startActivity(intent);
    }

}

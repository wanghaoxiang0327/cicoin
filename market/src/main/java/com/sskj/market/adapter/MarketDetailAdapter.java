package com.sskj.market.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sskj.common.App;
import com.sskj.common.tab.TabItem;
import com.sskj.common.tab.TabLayout;
import com.sskj.common.utils.ClickUtil;
import com.sskj.common.utils.NumberUtils;
import com.sskj.market.ChartFragment;
import com.sskj.market.DepthFragment;
import com.sskj.market.KChartPop;
import com.sskj.market.R;
import com.sskj.market.SummaryFragment;
import com.sskj.market.TradeFragment;
import com.sskj.market.data.MarketDetail;

import java.util.ArrayList;
import java.util.List;

public class MarketDetailAdapter extends BaseMultiItemQuickAdapter<MarketDetail, BaseViewHolder> {


    private String code;

    private FragmentManager mFragmentManager;


    ArrayList<TabItem> chartTabs = new ArrayList<>();

    private ArrayList<Fragment> fragmentList = new ArrayList<>();

    String[] goodsType = {"minute", "minute", "minute5", "minute15", "minute60", "hour4", "day"};

    //ChartTab
    TabLayout chartTabLayout;

    //底部Tab
    TabLayout bottomTabLayout;

    //指标弹窗
    private KChartPop kChartPop;

    private ArrayList<Fragment> bottomFragments = new ArrayList<>();

    private ArrayList<TabItem> bottomTabs = new ArrayList<>();

    private LinearLayout chartNorm;

    //指标
    private boolean isUpToggle = true;

    private boolean isDownToggle = true;

    public MarketDetailAdapter(List<MarketDetail> data, FragmentManager fragmentManager) {
        super(data);
        mFragmentManager = fragmentManager;
        addItemType(MarketDetail.TOP, R.layout.market_detail_top);
        addItemType(MarketDetail.CHART, R.layout.market_detail_chart);
        addItemType(MarketDetail.BOTTOM, R.layout.market_detail_bottom);
    }


    @Override
    protected void convert(BaseViewHolder helper, MarketDetail item) {
        switch (helper.getItemViewType()) {
            case MarketDetail.TOP:
                code = item.getTopData().getCode();
                helper.setText(R.id.tv_price, item.getTopData().getPrice() + "")
                        .setText(R.id.tv_the_amount, NumberUtils.keepDown(item.getTopData().getVolume(), 2))
                        .setTextColor(R.id.tv_price, item.getTopData().getChangeRate().contains("-") ? mContext.getResources().getColor(R.color.market_green) : mContext.getResources().getColor(R.color.market_red))
                        .setText(R.id.tv_change_rate, item.getTopData().getChangeRate())
                        .setTextColor(R.id.tv_change_rate, item.getTopData().getChangeRate().contains("-") ? mContext.getResources().getColor(R.color.market_green) : mContext.getResources().getColor(R.color.market_red))
                        .setImageResource(R.id.iv_status, item.getTopData().getChangeRate().contains("-") ? R.mipmap.market_fall : R.mipmap.market_rise)
                        .setText(R.id.tvCny, "≈" + NumberUtils.keepDown(item.getTopData().getCnyPrice(), 2))
                        .setText(R.id.tv_high, item.getTopData().getHigh() + "")
                        .setText(R.id.tv_low, item.getTopData().getLow() + "");
                break;
            case MarketDetail.CHART:
                if (chartTabLayout == null) {
                    initTab();
                    chartNorm = helper.getView(R.id.chart_norm);
                    chartTabLayout = helper.getView(R.id.chart_tabLayout);
                    chartTabLayout.setTabData(chartTabs, mFragmentManager, R.id.chart_content, fragmentList);
                    initPoint();
                }
                break;
            case MarketDetail.BOTTOM:
                if (bottomTabLayout == null) {
                    initBottomTab();
                    bottomTabLayout = helper.getView(R.id.bootmTabLyout);
                    bottomTabLayout.setTabData(bottomTabs, mFragmentManager, R.id.order_content, bottomFragments);
                }
                break;
            default:
                break;
        }
    }


    private void initTab() {
        chartTabs.add(new TabItem(App.INSTANCE.getString(R.string.market_time), 0, 0));
        chartTabs.add(new TabItem("1M", 0, 0));
        chartTabs.add(new TabItem("5M", 0, 0));
        chartTabs.add(new TabItem("15M", 0, 0));
        chartTabs.add(new TabItem("1H", 0, 0));
        chartTabs.add(new TabItem("4H", 0, 0));
        chartTabs.add(new TabItem(App.INSTANCE.getString(R.string.market_day), 0, 0));
        fragmentList.add(ChartFragment.newInstance(code, goodsType[0], true));
        fragmentList.add(ChartFragment.newInstance(code, goodsType[1], false));
        fragmentList.add(ChartFragment.newInstance(code, goodsType[2], false));
        fragmentList.add(ChartFragment.newInstance(code, goodsType[3], false));
        fragmentList.add(ChartFragment.newInstance(code, goodsType[4], false));
        fragmentList.add(ChartFragment.newInstance(code, goodsType[5], false));
        fragmentList.add(ChartFragment.newInstance(code, goodsType[6], false));
    }

    private void initBottomTab() {
        bottomTabs.add(new TabItem("深度"));
        bottomTabs.add(new TabItem("成交"));
        bottomTabs.add(new TabItem("简介"));
        bottomFragments.add(DepthFragment.newInstance(code, true));
        bottomFragments.add(TradeFragment.newInstance(code));
        bottomFragments.add(SummaryFragment.newInstance(code));
    }


    private void initPoint() {
        ClickUtil.click(chartNorm, v -> {
            if (kChartPop == null) {
                kChartPop = new KChartPop(mContext);
                View contentView = kChartPop.getContentView();
                TextView tvMa = contentView.findViewById(R.id.tvMa);
                TextView tvBoll = contentView.findViewById(R.id.tvBoll);
                TextView tvMacd = contentView.findViewById(R.id.tvMacd);
                TextView tvKdj = contentView.findViewById(R.id.tvKdj);
                TextView tvRsi = contentView.findViewById(R.id.tvRsi);
                TextView tvWr = contentView.findViewById(R.id.tvWr);
                ImageView ivUpToggle = contentView.findViewById(R.id.ivUpToggle);
                ImageView ivDownToggle = contentView.findViewById(R.id.ivDownToggle);
                ivUpToggle.setImageResource(R.mipmap.market_icon_show);
                ivDownToggle.setImageResource(R.mipmap.market_icon_show);
                ClickUtil.click(tvMa, view -> {
                    ((ChartFragment) fragmentList.get(chartTabLayout.getCurrentTab())).getKChartView().setMainDrawMaShow();
                    ivUpToggle.setImageResource(R.mipmap.market_icon_show);
                    kChartPop.dismiss();
                });
                ClickUtil.click(tvBoll, view -> {
                    ((ChartFragment) fragmentList.get(chartTabLayout.getCurrentTab())).getKChartView().setMainDrawBollShow();
                    ivUpToggle.setImageResource(R.mipmap.market_icon_show);
                    kChartPop.dismiss();

                });
                ClickUtil.click(ivUpToggle, view -> {
                    if (isUpToggle) {
                        isUpToggle = false;
                        ivUpToggle.setImageResource(R.mipmap.market_icon_hide);

                        ((ChartFragment) fragmentList.get(chartTabLayout.getCurrentTab())).getKChartView().setMainDrawNoneShow();
                    } else {
                        isUpToggle = true;
                        ivUpToggle.setImageResource(R.mipmap.market_icon_show);
                        ((ChartFragment) fragmentList.get(chartTabLayout.getCurrentTab())).getKChartView().setMainDrawMaShow();
                    }
                    kChartPop.dismiss();

                });
                ClickUtil.click(tvMacd, view -> {
                    ((ChartFragment) fragmentList.get(chartTabLayout.getCurrentTab())).getKChartView().changeMACD();
                    kChartPop.dismiss();

                });
                ClickUtil.click(tvKdj, view -> {
                    ((ChartFragment) fragmentList.get(chartTabLayout.getCurrentTab())).getKChartView().changeKDJ();
                    kChartPop.dismiss();

                });
                ClickUtil.click(tvRsi, view -> {
                    ((ChartFragment) fragmentList.get(chartTabLayout.getCurrentTab())).getKChartView().changeRSI();
                    kChartPop.dismiss();

                });
                ClickUtil.click(tvWr, view -> {
                    ((ChartFragment) fragmentList.get(chartTabLayout.getCurrentTab())).getKChartView().changeWR();
                    kChartPop.dismiss();

                });
                ClickUtil.click(ivDownToggle, view -> {
                    if (isDownToggle) {
                        isDownToggle = false;
                        ivDownToggle.setImageResource(R.mipmap.market_icon_hide);
                        ((ChartFragment) fragmentList.get(chartTabLayout.getCurrentTab())).getKChartView().setDrawDown(isDownToggle);
                    } else {
                        isDownToggle = true;
                        ivDownToggle.setImageResource(R.mipmap.market_icon_show);

                        ((ChartFragment) fragmentList.get(chartTabLayout.getCurrentTab())).getKChartView().setDrawDown(isDownToggle);
                    }
                    kChartPop.dismiss();

                });
                kChartPop.setBackground(0);
            }
            if (kChartPop.isShowing()) {
                kChartPop.dismiss();
            } else {
                kChartPop.showPopupWindow(chartTabLayout);
            }
        });
    }

}

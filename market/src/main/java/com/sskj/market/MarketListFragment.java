package com.sskj.market;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sskj.common.adapter.BaseAdapter;
import com.sskj.common.adapter.ViewHolder;
import com.sskj.common.base.BaseFragment;
import com.sskj.common.data.CoinBean;
import com.sskj.common.helper.DataSource;
import com.sskj.common.helper.SmartRefreshHelper;
import com.sskj.common.rxbus.RxBus;
import com.sskj.common.rxbus.Subscribe;
import com.sskj.common.rxbus.ThreadMode;
import com.sskj.common.utils.ClickUtil;
import com.sskj.common.utils.CoinIcon;
import com.sskj.common.utils.DigitUtils;
import com.sskj.common.utils.NumberUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Flowable;

/**
 * @author Hey
 * Create at  2019/05/29
 */
public class MarketListFragment extends BaseFragment<MarketListPresenter> {
    @BindView(R2.id.market_list)
    RecyclerView marketList;
    BaseAdapter<CoinBean> adapter;
    SmartRefreshHelper<CoinBean> smartRefreshHelper;
    List<CoinBean> topList = new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.market_fragment_market_list;
    }

    @Override
    public MarketListPresenter getPresenter() {
        return new MarketListPresenter();
    }

    @Override
    public void initView() {
        RxBus.getDefault().register(this);
        marketList.setLayoutManager(new LinearLayoutManager(getContext()));
        marketList.getItemAnimator().setChangeDuration(0);
        adapter = new BaseAdapter<CoinBean>(R.layout.market_item_coin_list, null, marketList) {
            @Override
            public void bind(ViewHolder holder, CoinBean item) {
                if (item.getName().contains("_")) {
                    holder.setText(R.id.coin_name, item.getName().split("_")[0]);
                } else {
                    holder.setText(R.id.coin_name, item.getName());
                }
                holder.setText(R.id.coin_cny_price, "≈" + NumberUtils.keepDown(item.getCnyPrice(), 2) + "CNY");
                holder.setText(R.id.coin_price, NumberUtils.keepDown(item.getPrice(), DigitUtils.getDigit(item.getCode())))
                        .setText(R.id.coin_change_rate, item.getChange() > 0 ? "+" + item.getChangeRate() : item.getChangeRate());
                if (!item.isUp()) {
                    holder.setTextColor(R.id.coin_price, color(R.color.market_green));
                    holder.setBackgroundRes(R.id.coin_change_rate, R.drawable.market_green_bg_50);
                } else {
                    holder.setTextColor(R.id.coin_price, color(R.color.market_red));
                    holder.setBackgroundRes(R.id.coin_change_rate, R.drawable.market_red_bg_50);
                }
                holder.setImageResource(R.id.coin_img, CoinIcon.getIcon(item.getCode()));
                ClickUtil.click(holder.itemView, view -> {
                    NewMarketDetailActivity.start(getContext(), item);
                });
            }
        };
    }


    @Override
    public void initData() {
        smartRefreshHelper = new SmartRefreshHelper<>(getContext(), adapter);
        smartRefreshHelper.setEnableLoadMore(false);
        smartRefreshHelper.setDataSource(new DataSource<CoinBean>() {
            @Override
            public Flowable<List<CoinBean>> loadData(int page) {
                mPresenter.hideLoading();
                return mPresenter.getMarketList("");
            }
        });
    }

    @Override
    public void loadData() {

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateCoin(CoinBean coinBean) {
        if (adapter != null) {
            for (int i = 0; i < adapter.getData().size(); i++) {
                if (adapter.getData().get(i).getCode().equals(coinBean.getCode())) {
                    coinBean.setPid(adapter.getData().get(i).getPid());
                    coinBean.setCode(adapter.getData().get(i).getCode());
                    adapter.getData().set(i, coinBean);
                    adapter.notifyItemChanged(i);
                }
            }
        }
    }


    public static MarketListFragment newInstance() {
        MarketListFragment fragment = new MarketListFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }


    public void setData(List<CoinBean> result) {
        topList.clear();
        for (CoinBean coinBean : result) {
            if (coinBean.getCode().equals("btc_usdt") || coinBean.getCode().equals("eth_usdt") || coinBean.getCode().equals("xrp_usdt")) {
                topList.add(coinBean);
            }
        }
        adapter.setNewData(topList);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBus.getDefault().unregister(this);
    }
}

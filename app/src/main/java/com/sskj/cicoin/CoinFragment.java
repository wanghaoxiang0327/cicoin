package com.sskj.cicoin;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sskj.common.adapter.BaseAdapter;
import com.sskj.common.adapter.ViewHolder;
import com.sskj.common.base.BaseFragment;
import com.sskj.common.data.CoinBean;
import com.sskj.common.rxbus.RxBus;
import com.sskj.common.rxbus.Subscribe;
import com.sskj.common.rxbus.ThreadMode;
import com.sskj.common.utils.DigitUtils;
import com.sskj.common.utils.NumberUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author Hey
 * Create at  2019/09/04 10:14:24
 */
public class CoinFragment extends BaseFragment<CoinPresenter> {
    int page = 1;
    @BindView(R.id.recyclerView)
    RecyclerView topCoinRecyclerView;
    private BaseAdapter<CoinBean> topAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.app_fragment_coin;
    }

    @Override
    public CoinPresenter getPresenter() {
        return new CoinPresenter();
    }

    @Override
    public void initView() {
        RxBus.getDefault().register(this);
        page = getArguments().getInt("page");
        initAdapter();
    }


    private void initAdapter() {
        topCoinRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        topCoinRecyclerView.getItemAnimator().setChangeDuration(0);
        topAdapter = new BaseAdapter<CoinBean>(R.layout.market_item_top_coin, null, topCoinRecyclerView) {
            @Override
            public void bind(ViewHolder holder, CoinBean item) {
                if (holder.getPosition() < 3) {
                    holder.setText(R.id.coin_name, item.getCode().replace("_", "/").toUpperCase())
                            .setText(R.id.coin_price, NumberUtils.keepDown(item.getPrice(), DigitUtils.getDigit(item.getCode())))
                            .setText(R.id.coin_cny_price, "≈" + item.getCnyPrice() + " CNY")
                            .setText(R.id.coin_change_rate, item.getChange() > 0 ? "+" + item.getChangeRate() : item.getChangeRate());
                    if (!item.isUp()) {
                        holder.setTextColor(R.id.coin_price, color(R.color.market_green));
                        holder.setTextColor(R.id.coin_change_rate, color(R.color.market_green));
                    } else {
                        holder.setTextColor(R.id.coin_price, color(R.color.market_red));
                        holder.setTextColor(R.id.coin_change_rate, color(R.color.market_red));
                    }
                }

//                ClickUtil.click(holder.itemView, view -> {
//                    ARouter.getInstance()
//                            .build(RoutePath.MARKET_DETAIL)
//                            .withSerializable("coinBean", item)
//                            .navigation();
//                });

            }
        };
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateCoin(CoinBean coinBean) {
        if (topAdapter != null) {
            for (int i = 0; i < topAdapter.getData().size(); i++) {
                if (topAdapter.getData().get(i).getCode().equals(coinBean.getCode())) {
                    coinBean.setPid(topAdapter.getData().get(i).getPid());
                    topAdapter.getData().set(i, coinBean);
                    topAdapter.notifyItemChanged(i);
                }
            }
        }
    }


    @Override
    public void initData() {

    }

    @Override
    public void loadData() {
        mPresenter.getMarketList();
    }

    public static CoinFragment newInstance(int page) {
        CoinFragment fragment = new CoinFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("page", page);
        fragment.setArguments(bundle);
        return fragment;
    }

    public void setData(List<CoinBean> data) {
        if (page == 1) {
            topAdapter.setNewData(data.subList(0, 3));
        } else if (page == 2) {
            topAdapter.setNewData(data.subList(3, data.size()));
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBus.getDefault().unregister(this);
    }
}

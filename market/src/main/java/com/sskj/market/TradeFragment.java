package com.sskj.market;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sskj.common.adapter.BaseAdapter;
import com.sskj.common.adapter.ViewHolder;
import com.sskj.common.base.BaseFragment;
import com.sskj.common.utils.NumberUtils;
import com.sskj.common.utils.TimeFormatUtil;
import com.sskj.market.data.Trade;
import com.sskj.market.data.TradeData;

import butterknife.BindView;

/**
 * 成交订单
 *
 * @author Hey
 * Create at  2019/08/01 16:57:04
 */
public class TradeFragment extends BaseFragment<TradePresenter> {


    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;

    private String code;

    private BaseAdapter<Trade> adapter;


    @Override
    public int getLayoutId() {
        return R.layout.market_fragment_deal_order;
    }

    @Override
    public TradePresenter getPresenter() {
        return new TradePresenter();
    }

    @Override
    public void initView() {
        if (getArguments() != null) {
            code = getArguments().getString("code");
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new BaseAdapter<Trade>(R.layout.market_item_trade, null, recyclerView) {
            @Override
            public void bind(ViewHolder holder, Trade item) {
                holder.setText(R.id.tvTime, TimeFormatUtil.SF_FORMAT_H.format(item.getCreateTime()))
                        .setText(R.id.tvPrice, NumberUtils.keepDown(item.getPrice(), 4))
                        .setText(R.id.tvNum, item.getAmount());
                if (item.getType().equals("buy")) {
                    holder.setText(R.id.tvDirection, "买入");
                } else {
                    holder.setText(R.id.tvDirection, "卖出");
                }

            }
        };
    }


    @Override
    public void initData() {

    }

    @Override
    public void loadData() {


    }

    @Override
    public void lazyLoad() {
        mPresenter.getOrder(code);
    }

    public static TradeFragment newInstance(String code) {
        TradeFragment fragment = new TradeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("code", code);
        fragment.setArguments(bundle);
        return fragment;
    }


    public void setData(TradeData data) {
        adapter.setNewData(data.getData());
    }
}

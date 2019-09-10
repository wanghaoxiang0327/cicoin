package com.sskj.market;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.sskj.common.adapter.BaseAdapter;
import com.sskj.common.adapter.ViewHolder;
import com.sskj.common.base.BaseFragment;
import com.sskj.common.data.PankouPushData;
import com.sskj.common.event.ContactChangeCoin;
import com.sskj.common.helper.DataSource;
import com.sskj.common.helper.SmartRefreshHelper;
import com.sskj.common.http.HttpConfig;
import com.sskj.common.rxbus.RxBus;
import com.sskj.common.rxbus.Subscribe;
import com.sskj.common.rxbus.ThreadMode;
import com.sskj.common.socket.WebSocket;
import com.sskj.common.socket.WebSocketObserver;
import com.sskj.common.utils.DigitUtils;
import com.sskj.common.utils.NumberUtils;
import com.sskj.common.utils.TimeFormatUtil;
import com.sskj.market.data.BuySellData;
import com.sskj.market.data.DealOrder;
import com.sskj.market.data.Trade;
import com.sskj.market.data.TradeData;
import com.sskj.market.data.TradePushBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 成交订单
 *
 * @author Hey
 * Create at  2019/08/01 16:57:04
 */
public class TradeFragment extends BaseFragment<TradePresenter> {
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R2.id.tvNum)
    TextView tvNum;
    private String code;
    private BaseAdapter<TradePushBean.TradePush> adapter;
    WebSocket webSocket;

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
        RxBus.getDefault().register(this);
        if (getArguments() != null) {
            code = getArguments().getString("code");
        }
        if (!TextUtils.isEmpty(code)) {
            if (code.contains("_")) {
                tvNum.setText(String.format(getString(R.string.market_count), code.split("_")[0].toUpperCase()));
            } else {
                tvNum.setText(String.format(getString(R.string.market_count), code.toUpperCase()));
            }
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new BaseAdapter<TradePushBean.TradePush>(R.layout.market_item_trade, null, recyclerView) {
            @Override
            public void bind(ViewHolder holder, TradePushBean.TradePush item) {
                holder.setText(R.id.tvTime, TimeFormatUtil.SF_FORMAT_H.format(item.dt))
                        .setText(R.id.tvPrice, NumberUtils.keepDown(item.price, 4))
                        .setText(R.id.tvNum, item.amount + "");
                if ("buy".equals(item.dc)) {
                    holder.setText(R.id.tvDirection, getString(R.string.market_chengjiao_buy));
                    holder.setTextColor(R.id.tvDirection, getResources().getColor(R.color.common_red));
                } else {
                    holder.setText(R.id.tvDirection, getString(R.string.market_chengjiao_sell));
                    holder.setTextColor(R.id.tvDirection, getResources().getColor(R.color.common_green));
                }
            }
        };
    }


    @Override
    public void initData() {

    }

    @Override
    public void onVisible() {
        super.onVisible();
        setSocketListener();
    }

    @Override
    public void onInVisible() {
        super.onInVisible();
//        if (webSocket != null) {
//            webSocket.disposeByTag(getClass().getSimpleName());
//        }
    }

    @Override
    public void loadData() {
        startWebSocket();
    }

    /**
     * 切换币种
     *
     * @param coinBean
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeCoin(ContactChangeCoin coinBean) {
        code = coinBean.getCode();
        startWebSocket();
    }

    public void startWebSocket() {
        JSONObject message = new JSONObject();
        message.put("code", code);
        webSocket = new WebSocket(HttpConfig.WS_TRANSACTION, "transaction", message.toString());
        setSocketListener();
    }

    public void setSocketListener() {
        if (webSocket != null) {
            webSocket.setListener(new WebSocket.MarketWebSocketListener() {
                @Override
                public void onMessage(String message) {
                    Flowable.just(message).observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io()).subscribe(new Consumer<String>() {
                        @Override
                        public void accept(String s) throws Exception {
                            try {
                                TradePushBean tradePushBean = JSONObject.parseObject(s, TradePushBean.class);
                                if (tradePushBean != null && tradePushBean.data != null && tradePushBean.data.size() > 0) {
                                    adapter.setNewData(tradePushBean.data);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        }
    }

    @Override
    public void lazyLoad() {
    }

    public static TradeFragment newInstance(String code) {
        TradeFragment fragment = new TradeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("code", code);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBus.getDefault().unregister(this);
    }
}

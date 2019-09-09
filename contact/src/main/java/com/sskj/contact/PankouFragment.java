package com.sskj.contact;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.sskj.common.adapter.BaseAdapter;
import com.sskj.common.adapter.ViewHolder;
import com.sskj.common.base.BaseFragment;
import com.sskj.common.data.BuySellData;
import com.sskj.common.data.CoinBean;
import com.sskj.common.data.DepthData;
import com.sskj.common.event.ContactChangeCoin;
import com.sskj.common.http.HttpConfig;
import com.sskj.common.rxbus.RxBus;
import com.sskj.common.rxbus.Subscribe;
import com.sskj.common.rxbus.ThreadMode;
import com.sskj.common.socket.WebSocket;
import com.sskj.common.utils.DigitUtils;
import com.sskj.common.utils.NumberUtils;
import com.sskj.contact.data.Pankou;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 盘口
 *
 * @author Hey
 * Create at  2019/08/26 14:36:33
 */
public class PankouFragment extends BaseFragment<PankouPresenter> {
    @BindView(R2.id.sell_list)
    RecyclerView sellList;
    @BindView(R2.id.buy_list)
    RecyclerView buyList;
    @BindView(R2.id.tv_price)
    TextView tvPrice;
    @BindView(R2.id.tv_cny_price)
    TextView tvCnyPrice;
    private BaseAdapter<Pankou> sellAdapter;
    private BaseAdapter<Pankou> buyAdapter;
    private String code;
    private int pankouSize = 7;
    private WebSocket webSocket;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getArguments() != null) {
            code = getArguments().getString("code");
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.contact_fragment_pankou;
    }

    @Override
    public PankouPresenter getPresenter() {
        return new PankouPresenter();
    }

    @Override
    public void initView() {
        sellList.setLayoutManager(sellLayoutManager);
        buyList.setLayoutManager(buyLayoutManager);
    }

    @Override
    public void initData() {
        sellAdapter = new BaseAdapter<Pankou>(R.layout.contact_item_pankou, null, sellList) {
            @Override
            public void bind(ViewHolder holder, Pankou item) {
                holder.setTextColor(R.id.tv_price, color(R.color.common_green))
                        .setText(R.id.tv_no, holder.getLayoutPosition() + 1 + "")
                        .setProgress(R.id.progress, item.getProgressRate())
                        .setText(R.id.tv_price, NumberUtils.keep(item.getPrice(), DigitUtils.getDigit(code)))
                        .setText(R.id.tv_num, item.getTotalSize());
            }
        };
        buyAdapter = new BaseAdapter<Pankou>(R.layout.contact_buy_item_pankou, null, buyList) {
            @Override
            public void bind(ViewHolder holder, Pankou item) {
                holder.setTextColor(R.id.tv_price, color(R.color.common_red))
                        .setProgress(R.id.progress, item.getProgressRate())
                        .setText(R.id.tv_no, holder.getLayoutPosition() + 1 + "")
                        .setText(R.id.tv_price, NumberUtils.keep(item.getPrice(), DigitUtils.getDigit(code)))
                        .setText(R.id.tv_num, item.getTotalSize());
            }
        };

    }

    @Override
    public void loadData() {
        mPresenter.getPankouData(code);
    }


    LinearLayoutManager sellLayoutManager = new LinearLayoutManager(getContext()) {
        @Override
        public boolean canScrollVertically() {
            return false;
        }

        @Override
        public boolean canScrollHorizontally() {
            return false;
        }
    };

    LinearLayoutManager buyLayoutManager = new LinearLayoutManager(getContext()) {
        @Override
        public boolean canScrollVertically() {
            return false;
        }

        @Override
        public boolean canScrollHorizontally() {
            return false;
        }
    };

    public static PankouFragment newInstance(String code) {
        PankouFragment fragment = new PankouFragment();
        Bundle bundle = new Bundle();
        bundle.putString("code", code);
        fragment.setArguments(bundle);
        return fragment;
    }

    public void setPankouData(BuySellData buySellData) {
        if (buySellData != null) {
            sellAdapter.setNewData(formatList(buySellData.getAsks()));
            buyAdapter.setNewData(formatList(buySellData.getBids()));
        }
    }

    public List<Pankou> formatList(List<DepthData> data) {
        List<Pankou> list = new ArrayList<>();
        if (data == null) {
            data = new ArrayList<>();
        }
        while (data.size() < pankouSize) {
            data.add(new DepthData());
        }
        for (int i = 0; i < pankouSize; i++) {
            list.add(new Pankou(String.valueOf(data.get(i).getPrice()), String.valueOf(data.get(i).getTotalSize())));
        }
        Double full = 0d;
        for (Pankou bid : list) {
            if (!bid.getTotalSize().equals("--")) {
                Double aDouble = Double.valueOf(bid.getTotalSize());
                if (aDouble > full) {
                    full = aDouble;
                }
            }
        }
        for (Pankou bid : list) {
            if (!bid.getTotalSize().equals("--")) {
                Double aDouble = Double.valueOf(bid.getTotalSize());
                bid.setRate((int) (aDouble / full * 100d));
            } else {
                bid.setRate(0);
            }
        }
        Collections.sort(list, (o1, o2) -> {
            try {
                return new BigDecimal(o1.getPrice()).doubleValue() > new BigDecimal(o2.getPrice()).doubleValue() ? -1 : 1;
            } catch (Exception e) {
                return 1;
            }
        });
        return list;
    }

    public void startWebSocket() {
        if (webSocket != null) {
            webSocket.closeWebSocket();
        }
        JSONObject messageObj = new JSONObject();
        messageObj.put("code", code);
        webSocket = new WebSocket(HttpConfig.WS_PANKOU, "pankou", messageObj.toJSONString());
        webSocket.setListener(message -> {
            Flowable.just(message)
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(s -> {
                        return JSONObject.parseObject(message, BuySellData.class);
                    })
                    .subscribeOn(Schedulers.io())
                    .subscribe(buySellData -> {
                        if (buySellData != null) {
                            setPankouData(buySellData);
                        }
                    });
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        webSocket.closeWebSocket();
        RxBus.getDefault().unregister(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        startWebSocket();
        RxBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updatePrice(CoinBean coinBean) {
        if (code.equals(coinBean.getCode())) {
            tvPrice.setText(NumberUtils.keepDown(coinBean.getPrice(), DigitUtils.getDigit(code)));
            tvCnyPrice.setText("≈" + NumberUtils.keepDown(coinBean.getCnyPrice(), 2) + "CNY");
        }
    }


    /**
     * 切换币种
     *
     * @param coinBean
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeCoin(ContactChangeCoin coinBean) {
        code = coinBean.getCode();
        mPresenter.getPankouData(code);
        tvPrice.setText(NumberUtils.keepDown(coinBean.getPrice(), DigitUtils.getDigit(code)));
        tvCnyPrice.setText("≈" + NumberUtils.keepDown(coinBean.getCnyPrice(), 2) + "CNY");
        startWebSocket();
    }


}

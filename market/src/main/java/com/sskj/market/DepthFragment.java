package com.sskj.market;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.fastjson.JSONObject;
import com.sskj.common.adapter.BaseAdapter;
import com.sskj.common.adapter.ViewHolder;
import com.sskj.common.base.BaseFragment;
import com.sskj.common.http.HttpConfig;
import com.sskj.common.router.RoutePath;
import com.sskj.common.socket.WebSocket;
import com.sskj.common.socket.WebSocketObserver;
import com.sskj.common.utils.DigitUtils;
import com.sskj.common.utils.NumberUtils;
import com.sskj.common.utils.ScreenUtil;
import com.sskj.depthlib.data.DepthData;
import com.sskj.depthlib.view.DepthMapView;
import com.sskj.market.data.BuySellData;
import com.sskj.market.data.DeepData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Hey
 * Create at  2019/07/31 10:25:20
 */
@Route(path = RoutePath.DEPTH_FRAGMENT)
public class DepthFragment extends BaseFragment<DeepthPresenter> {
    @BindView(R2.id.depthMapView)
    DepthMapView depthMapView;
    @BindView(R2.id.deepRecyclerView)
    RecyclerView deepRecyclerView;
    @BindView(R2.id.depthMapTip)
    LinearLayout depthMapTip;
    @BindView(R2.id.ll_depth)
    LinearLayout llDepth;
    @BindView(R2.id.frameLayout)
    FrameLayout frameLayout;
    @BindView(R2.id.tv_buy_count_des)
    TextView tvBuycountDes;
    @BindView(R2.id.tv_sale_count_des)
    TextView tvSaleCoutDes;
    private BaseAdapter<DeepData> deepListAdapter;
    private String code;
    private Disposable disposable;
    private WebSocket webSocket;
    private WebSocket pankouWebSocket;
    private boolean isBig;

    @Override
    public int getLayoutId() {
        return R.layout.market_fragment_deepth;
    }

    @Override
    public DeepthPresenter getPresenter() {
        return new DeepthPresenter();
    }

    @Override
    public void initView() {
        if (getArguments() != null) {
            code = getArguments().getString("code");
            isBig = getArguments().getBoolean("isBig");
        }
        if (!TextUtils.isEmpty(code)) {
            if (code.contains("_")) {
                tvBuycountDes.setText(String.format(getString(R.string.market_count), code.split("_")[0].toUpperCase()));
                tvSaleCoutDes.setText(String.format(getString(R.string.market_count), code.split("_")[0].toUpperCase()));
            } else {
                tvBuycountDes.setText(String.format(getString(R.string.market_count), code.toUpperCase()));
                tvSaleCoutDes.setText(String.format(getString(R.string.market_count), code.toUpperCase()));
            }
        }
        if (isBig) {
            llDepth.setBackgroundResource(R.color.common_background_dark);
            frameLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ScreenUtil.sp2px(getContext(), 200)));
            depthMapView.isBig = true;
            depthMapView.mBackgroundColor = R.color.common_background_dark;
        } else {
            llDepth.setBackgroundResource(R.color.common_white);
            depthMapView.isBig = false;
            depthMapView.setShader(false, color(R.color.common_depth_buy_top), color(R.color.common_depth_buy_middle), color(R.color.common_depth_buy_bottom), 1000);
            depthMapView.setShader(true, color(R.color.common_depth_sell_top), color(R.color.common_depth_sell_middle), color(R.color.common_depth_sell_bottom), 1000);
        }
        depthMapView.setOnPointShowListener(show -> {
            if (show) {
                depthMapTip.setVisibility(View.GONE);
            } else {
                depthMapTip.setVisibility(View.VISIBLE);
            }
        });
        deepRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        deepListAdapter = new BaseAdapter<DeepData>(R.layout.market_item_deep_list, null, deepRecyclerView) {
            @Override
            public void bind(ViewHolder holder, DeepData item) {
                holder.setText(R.id.buy_position, holder.getLayoutPosition() + 1 + "")
                        .setText(R.id.sell_position, holder.getLayoutPosition() + 1 + "")
                        .setText(R.id.buy_count, NumberUtils.keepMaxDown(item.getBuyCount(), 4))
                        .setText(R.id.buy_price, NumberUtils.keepDown(item.getBuyPrice(), DigitUtils.getDigit(code)))
                        .setText(R.id.sell_count, NumberUtils.keepMaxDown(item.getSellCount(),4))
                        .setText(R.id.sell_price, NumberUtils.keepDown(item.getSellPrice(),  DigitUtils.getDigit(code)));
            }
        };
    }


    @Override
    public void initData() {
        startWebSocket();
    }

    @Override
    public void loadData() {
        mPresenter.getDeepData(code);
        mPresenter.getPankouData(code);
    }

    @Override
    public void lazyLoad() {

    }

    public static DepthFragment newInstance(String code, boolean isBig) {
        DepthFragment fragment = new DepthFragment();
        Bundle bundle = new Bundle();
        bundle.putString("code", code);
        bundle.putBoolean("isBig", isBig);
        fragment.setArguments(bundle);
        return fragment;
    }


    /**
     * 设置深度列表数据
     */
    public void setDeepData(BuySellData buySellData) {
        disposable = Flowable.just(buySellData)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(buySellData1 -> {
                    if (buySellData1.getAsks() != null && buySellData1.getBids() != null) {
                        List<DeepData> data = new ArrayList<>();
                        //使买盘和买盘数据量保持一致
                        while (buySellData1.getAsks().size() > buySellData1.getBids().size()) {
                            buySellData1.getBids().add(new DepthData());
                        }
                        while (buySellData1.getAsks().size() < buySellData1.getBids().size()) {
                            buySellData1.getAsks().add(new DepthData());
                        }
                        //组合卖盘数据
                        for (DepthData item : buySellData1.getAsks()) {
                            DeepData itemData = new DeepData();
                            itemData.setSellCount(item.getTotalSize());
                            itemData.setSellPrice(item.getPrice());
                            data.add(itemData);
                        }
                        //组合买盘数据
                        for (int i = 0; i < buySellData1.getBids().size(); i++) {
                            DepthData item = buySellData1.getBids().get(i);
                            data.get(i).setBuyCount(item.getTotalSize());
                            data.get(i).setBuyPrice(item.getPrice());
                        }
                        return data;
                    }

                    return null;
                })
                .subscribe(data -> {
                    deepListAdapter.setNewData(data);
                }, Throwable::printStackTrace);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposable != null) {
            disposable.dispose();
        }
        if (webSocket != null) {
            webSocket.closeWebSocket();
        }
        if (pankouWebSocket != null) {
            pankouWebSocket.closeWebSocket();
        }
    }

    //设置深度图数据
    public void setDeepMap(BuySellData data) {
        depthMapView.setData(data.getBids(), data.getAsks());
        depthMapTip.setVisibility(View.VISIBLE);
    }

    public void startWebSocket() {
        JSONObject message = new JSONObject();
        try {
            message.put("code", code);
            if (webSocket == null) {
                webSocket = new WebSocket(HttpConfig.WS_DEPTH, "depth", message.toString());
                setSocketListener();
            } else {
                webSocket.sendMessage(message.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (pankouWebSocket == null) {
            pankouWebSocket = new WebSocket(HttpConfig.WS_PANKOU, "pankou", message.toString());
            setPankouSocketListener();
        } else {
            pankouWebSocket.sendMessage(message.toString());
        }
    }

    public void setSocketListener() {
        if (webSocket != null) {
            webSocket.observer(getClass().getSimpleName(), new WebSocketObserver() {
                @Override
                public void onNext(String s) {
                    try {
                        BuySellData data = JSONObject.parseObject(s, BuySellData.class);
                        setDeepMap(data);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public void setPankouSocketListener() {
        if (pankouWebSocket != null) {
            pankouWebSocket.observer(getClass().getSimpleName(), new WebSocketObserver() {
                @Override
                public void onNext(String s) {
                    try {
                        BuySellData data = JSONObject.parseObject(s, BuySellData.class);
                        setDeepData(data);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}

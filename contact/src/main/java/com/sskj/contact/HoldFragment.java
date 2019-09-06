package com.sskj.contact;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sskj.common.DividerLineItemDecoration;
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
import com.sskj.common.utils.NumberUtils;
import com.sskj.common.utils.ScreenUtil;
import com.sskj.common.utils.TimeFormatUtil;
import com.sskj.contact.data.HoldOrder;
import com.sskj.contact.data.PointInfo;
import com.sskj.contact.dialog.ContactCloseOrderDialog;
import com.sskj.contact.dialog.ContactOrderSettingDialog;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 持仓订单
 *
 * @author Hey
 * Create at  2019/08/26 16:57:34
 */
public class HoldFragment extends BaseFragment<HoldPresenter> {
    @BindView(R2.id.order_list)
    RecyclerView orderList;
    SmartRefreshHelper<HoldOrder> smartRefreshHelper;
    BaseAdapter<HoldOrder> orderAdapter;
    String code;
    int size = 10;
    Disposable disposable;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getArguments() != null) {
            code = getArguments().getString("code");
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.contact_fragment_hold;
    }

    @Override
    public HoldPresenter getPresenter() {
        return new HoldPresenter();
    }

    @Override
    public void initView() {
        RxBus.getDefault().register(this);
        orderList.setLayoutManager(new LinearLayoutManager(getContext()));
        orderList.addItemDecoration(new DividerLineItemDecoration(getContext())
                .setDividerColor(color(R.color.common_background))
                .setPaintWidth(ScreenUtil.dp2px(getContext(), 3))
        );
        orderAdapter = new BaseAdapter<HoldOrder>(R.layout.contact_item_hold, null, orderList) {
            @Override
            public void bind(ViewHolder holder, HoldOrder item) {
                holder.setText(R.id.tv_order_type, item.getType() == 1 ? getString(R.string.common_make_more) : getString(R.string.common_make_empty))
                        .setTextColor(R.id.tv_order_type, item.getType() == 1 ? color(R.color.common_red) : color(R.color.common_green))
                        .setText(R.id.tv_price_type, item.getOtype() == 1 ? getString(R.string.contact_dealFragment7) : getString(R.string.contact_dealFragment8))
                        .setText(R.id.tv_coin_name, item.getCode().replace("_", "/").toUpperCase())
                        .setText(R.id.tv_create_time, TimeFormatUtil.SF_FORMAT_J.format(item.getAddtime() * 1000))
                        .setText(R.id.tv_order_lever, item.getLeverage())
                        .setText(R.id.tv_hold_price, NumberUtils.keepMaxDown(item.getBuyprice(), 4))
                        .setText(R.id.tv_new_price, NumberUtils.keepMaxDown(item.getNewprice(), 4))
                        .setText(R.id.tv_hold_num, item.getBuynum())
                        .setText(R.id.tv_total_money, NumberUtils.keepMaxDown(item.getTotalprice(), 4))
                        .setText(R.id.tv_fee, NumberUtils.keepMaxDown(item.getSxfee(), 4))
//                        .setText(R.id.tv_night_fee, item.getDayfee())
                        .setText(R.id.tv_win_price, NumberUtils.keepMaxDown(item.getPoit_win(), 4))
                        .setText(R.id.tv_loss_price, NumberUtils.keepMaxDown(item.getPoit_loss(), 4))
                        .setText(R.id.tv_profit, getString(R.string.contact_holdFragment5) + item.getFdyk());
                if (item.getFdyk().contains("-")) {
                    holder.setBackgroundRes(R.id.tv_profit, R.drawable.common_green_bg_5);
                } else {
                    holder.setBackgroundRes(R.id.tv_profit, R.drawable.common_red_bg_5);
                }
                ClickUtil.click(holder.getView(R.id.btn_close), view -> {
                    ContactCloseOrderDialog.getInstance(item)
                            .setConfirmListener((num, id) -> {
                                mPresenter.closeOrder(num, id);
                            }).show(getChildFragmentManager(), "ContactCloseOrderDialog");

                });
                ClickUtil.click(holder.getView(R.id.btn_reset), view -> {
                    mPresenter.getPointInfo(item);
                });
            }
        };
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateCoin(CoinBean coinBean) {
        if (orderAdapter != null) {
            for (int i = 0; i < orderAdapter.getData().size(); i++) {
                if (orderAdapter.getData().get(i).getCode().equals(coinBean.getCode())) {
                    HoldOrder holdOrder = orderAdapter.getData().get(i);
                    holdOrder.setNewprice(coinBean.getPrice() + "");
                    orderAdapter.getData().set(i, holdOrder);
                    orderAdapter.notifyItemChanged(i);
                }
            }
        }
    }

    @Override
    public void initData() {
        smartRefreshHelper = new SmartRefreshHelper<>(getContext(), orderAdapter);
    }

    @Override
    public void loadData() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshOrder(String refresh) {
        if (refresh.equals("refreshDealOrder")) {
            smartRefreshHelper.refresh();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBus.getDefault().unregister(this);
    }

    @Override
    public void lazyLoad() {
        smartRefreshHelper.setDataSource(new DataSource<HoldOrder>() {
            @Override
            public Flowable<List<HoldOrder>> loadData(int page) {
                return mPresenter.getHoldOrder(code, page, size);
            }
        });
        disposable = Flowable.interval(2, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                smartRefreshHelper.loadData(false);
            }
        });
    }

    public static HoldFragment newInstance(String code) {
        HoldFragment fragment = new HoldFragment();
        Bundle bundle = new Bundle();
        bundle.putString("code", code);
        fragment.setArguments(bundle);
        return fragment;
    }

    public void closeOrderSuccess() {
        smartRefreshHelper.loadData();
    }

    public void setPointInfo(PointInfo data, HoldOrder order) {
        ContactOrderSettingDialog.getInstance(order, data)
                .setConfirmListener(() -> {
                    smartRefreshHelper.refresh();
                }).show(getChildFragmentManager(), "ContactOrderSettingDialog");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (disposable != null) {
            disposable.dispose();
        }
    }
}

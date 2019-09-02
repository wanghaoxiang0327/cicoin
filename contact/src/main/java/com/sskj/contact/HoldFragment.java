package com.sskj.contact;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sskj.common.DividerLineItemDecoration;
import com.sskj.common.adapter.BaseAdapter;
import com.sskj.common.adapter.ViewHolder;
import com.sskj.common.base.BaseFragment;
import com.sskj.common.helper.DataSource;
import com.sskj.common.helper.SmartRefreshHelper;
import com.sskj.common.utils.ClickUtil;
import com.sskj.common.utils.ScreenUtil;
import com.sskj.common.utils.TimeFormatUtil;
import com.sskj.contact.data.HoldOrder;
import com.sskj.contact.data.PointInfo;
import com.sskj.contact.dialog.ContactCloseOrderDialog;
import com.sskj.contact.dialog.ContactOrderSettingDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Flowable;

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
        orderList.setLayoutManager(new LinearLayoutManager(getContext()));
        orderList.addItemDecoration(new DividerLineItemDecoration(getContext())
                .setDividerColor(color(R.color.common_dark))
                .setPaintWidth(ScreenUtil.dp2px(getContext(), 5))
        );
        orderAdapter = new BaseAdapter<HoldOrder>(R.layout.contact_item_hold, null, orderList) {
            @Override
            public void bind(ViewHolder holder, HoldOrder item) {
                holder.setText(R.id.tv_order_type, item.getType() == 1 ? getString(R.string.contact_dealFragment5) : getString(R.string.contact_dealFragment6))
                        .setText(R.id.tv_price_type, item.getOtype() == 1 ? getString(R.string.contact_dealFragment7) : getString(R.string.contact_dealFragment8))
                        .setText(R.id.tv_coin_name, item.getCode())
                        .setText(R.id.tv_create_time, TimeFormatUtil.SF_FORMAT_J.format(item.getAddtime() * 1000))
                        .setText(R.id.tv_order_lever, item.getLeverage())
                        .setText(R.id.tv_hold_price, item.getBuyprice())
                        .setText(R.id.tv_new_price, item.getNewprice())
                        .setText(R.id.tv_hold_num, item.getBuynum())
                        .setText(R.id.tv_total_money, item.getTotalprice())
                        .setText(R.id.tv_fee, item.getSxfee())
                        .setText(R.id.tv_night_fee, item.getDayfee())
                        .setText(R.id.tv_win_price, item.getPoit_win())
                        .setText(R.id.tv_loss_price, item.getPoit_loss())
                        .setText(R.id.tv_profit, getString(R.string.contact_holdFragment5) + item.getFdyk());
                if (item.getFdyk().contains("-")) {
                    holder.setBackgroundRes(R.id.tv_profit, R.color.common_red);
                } else {
                    holder.setBackgroundRes(R.id.tv_profit, R.color.common_green);
                }
                holder.setTextColor(R.id.tv_order_type, item.getType() == 1 ? color(R.color.common_green) : color(R.color.common_red));

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


    @Override
    public void initData() {
        smartRefreshHelper = new SmartRefreshHelper<>(getContext(), orderAdapter);
    }

    @Override
    public void loadData() {

    }

    @Override
    public void lazyLoad() {
        smartRefreshHelper.setDataSource(new DataSource<HoldOrder>() {
            @Override
            public Flowable<List<HoldOrder>> loadData(int page) {
                return mPresenter.getHoldOrder(code, page, size);
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
        ContactOrderSettingDialog.getInstance(order,data)
                .setConfirmListener(() -> {
                    smartRefreshHelper.refresh();
                }).show(getChildFragmentManager(), "ContactOrderSettingDialog");

    }
}

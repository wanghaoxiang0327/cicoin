package com.sskj.contact;

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
import com.sskj.common.utils.NumberUtils;
import com.sskj.common.utils.ScreenUtil;
import com.sskj.common.utils.TimeFormatUtil;
import com.sskj.contact.R;
import com.sskj.contact.data.DealOrder;
import com.sskj.contact.data.EntrustOrder;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Flowable;

/**
 * @author Hey
 * Create at  2019/08/28 13:40:10
 */
public class DealFragment extends BaseFragment<DealPresenter> {


    @BindView(R2.id.order_list)
    RecyclerView orderList;

    BaseAdapter<DealOrder> adapter;

    int size = 10;

    private SmartRefreshHelper<DealOrder> smartRefreshHelper;

    String code;
    private HashMap<Integer, String> typeMap = new HashMap<>();

    @Override
    public int getLayoutId() {
        return R.layout.contact_fragment_deal;
    }

    @Override
    public DealPresenter getPresenter() {
        return new DealPresenter();
    }

    @Override
    public void initView() {
        RxBus.getDefault().register(this);
        if (getArguments() != null) {
            code = getArguments().getString("code");
        }
        typeMap.put(1, getString(R.string.contact_dealFragment1));
        typeMap.put(2, getString(R.string.contact_dealFragment2));
        typeMap.put(3, getString(R.string.contact_dealFragment3));
        typeMap.put(4, getString(R.string.contact_dealFragment4));

        orderList.setLayoutManager(new LinearLayoutManager(getContext()));
        orderList.addItemDecoration(new DividerLineItemDecoration(getContext())
                .setDividerColor(color(R.color.common_background))
                .setPaintWidth(ScreenUtil.dp2px(getContext(), 5))
        );

        adapter = new BaseAdapter<DealOrder>(R.layout.contact_item_deal, null, orderList) {
            @Override
            public void bind(ViewHolder holder, DealOrder item) {
                holder.setText(R.id.tv_order_type, item.getType() == 1 ? getString(R.string.common_make_more) : getString(R.string.common_make_empty))
                        .setText(R.id.tv_opening_time, TimeFormatUtil.SF_FORMAT_J.format(item.getAddtime() * 1000))
                        .setText(R.id.tv_coin_name, item.getPname().replace("_", "/").toUpperCase())
                        .setText(R.id.tv_create_time, TimeFormatUtil.SF_FORMAT_J.format(item.getAddtime() * 1000))
                        .setText(R.id.tv_order_lever, TimeFormatUtil.SF_FORMAT_J.format(item.getSelltime() * 1000))
                        .setText(R.id.tv_hold_price, NumberUtils.keepMaxDown(item.getBuyprice(), 4))
                        .setText(R.id.tv_close_price, NumberUtils.keepMaxDown(item.getSellprice(), 4))
                        .setText(R.id.tv_hold_num, NumberUtils.keepMaxDown(item.getBuynum(), 4))
                        .setText(R.id.tv_total_money, NumberUtils.keepMaxDown(item.getTotalprice(), 4))
                        .setText(R.id.tv_fee, NumberUtils.keepMaxDown(item.getSxfee(), 4))
                        .setText(R.id.tv_night_fee, item.getLeverage())
                        .setText(R.id.tv_win_price, NumberUtils.keepMaxDown(item.getPoit_win(), 4))
                        .setText(R.id.tv_loss_price, NumberUtils.keepMaxDown(item.getPoit_loss(), 4))
                        .setText(R.id.tv_profit, getString(R.string.contact_dealFragment9) + NumberUtils.keepMaxDown(item.getProfit(), 4))
                        .setText(R.id.tv_state, getString(R.string.contact_contact_dialog_close_order80) + typeMap.get(item.getPc_type()));
                if (item.getProfit().contains("-")) {
                    holder.setBackgroundRes(R.id.tv_profit, R.drawable.common_green_bg_5);
                    holder.setVisible(R.id.tv_share, false);
                } else {
                    holder.setVisible(R.id.tv_share, true);
                    holder.setBackgroundRes(R.id.tv_profit, R.drawable.common_red_bg_5);
                }
                holder.setOnClickListener(R.id.tv_share, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ShareActivity.start(getContext(), item);
                    }
                });
            }
        };
    }


    @Override
    public void initData() {
        smartRefreshHelper = new SmartRefreshHelper<>(getContext(), adapter);
    }

    @Override
    public void loadData() {

    }

    @Override
    public void lazyLoad() {
        smartRefreshHelper.setDataSource(new DataSource<DealOrder>() {
            @Override
            public Flowable<List<DealOrder>> loadData(int page) {
                return mPresenter.getDealOrder(code, page, size);
            }
        });
    }

    public static DealFragment newInstance(String code) {
        DealFragment fragment = new DealFragment();
        Bundle bundle = new Bundle();
        bundle.putString("code", code);
        fragment.setArguments(bundle);
        return fragment;
    }

}

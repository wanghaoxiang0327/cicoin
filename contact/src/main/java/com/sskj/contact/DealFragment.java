package com.sskj.contact;

import com.sskj.common.DividerLineItemDecoration;
import com.sskj.common.adapter.BaseAdapter;
import com.sskj.common.adapter.ViewHolder;
import com.sskj.common.base.BaseFragment;

import com.sskj.common.helper.DataSource;
import com.sskj.common.helper.SmartRefreshHelper;
import com.sskj.common.utils.ScreenUtil;
import com.sskj.common.utils.TimeFormatUtil;
import com.sskj.contact.R;
import com.sskj.contact.data.DealOrder;
import com.sskj.contact.data.EntrustOrder;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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


    private HashMap<Integer,String> typeMap=new HashMap<>();

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
        typeMap.put(1, getString(R.string.contact_dealFragment1));
        typeMap.put(2, getString(R.string.contact_dealFragment2));
        typeMap.put(3, getString(R.string.contact_dealFragment3));
        typeMap.put(4, getString(R.string.contact_dealFragment4));

        orderList.setLayoutManager(new LinearLayoutManager(getContext()));
        orderList.addItemDecoration(new DividerLineItemDecoration(getContext())
                .setDividerColor(color(R.color.common_dark))
                .setPaintWidth(ScreenUtil.dp2px(getContext(), 5))
        );

        adapter = new BaseAdapter<DealOrder>(R.layout.contact_item_deal, null, orderList) {
            @Override
            public void bind(ViewHolder holder, DealOrder item) {
                holder.setText(R.id.tv_order_type, item.getType() == 1 ? getString(R.string.contact_dealFragment5) : getString(R.string.contact_dealFragment6))
                        .setText(R.id.tv_price_type, item.getOtype() == 1 ? getString(R.string.contact_dealFragment7) : getString(R.string.contact_dealFragment8))
                        .setText(R.id.tv_coin_name, item.getPname())
                        .setText(R.id.tv_create_time, TimeFormatUtil.SF_FORMAT_J.format(item.getSelltime() * 1000))
                        .setText(R.id.tv_order_lever, item.getLeverage())
                        .setText(R.id.tv_hold_price, item.getBuyprice())
                        .setText(R.id.tv_close_price, item.getSellprice())
                        .setText(R.id.tv_hold_num, item.getBuynum())
                        .setText(R.id.tv_total_money, item.getTotalprice())
                        .setText(R.id.tv_fee, item.getSxfee())
                        .setText(R.id.tv_night_fee, item.getDayfee())
                        .setText(R.id.tv_win_price, item.getPoit_win())
                        .setText(R.id.tv_loss_price, item.getPoit_loss())
                        .setText(R.id.tv_profit, getString(R.string.contact_dealFragment9) + item.getProfit())
                        .setText(R.id.tv_state, typeMap.get(item.getPc_type()));

                if (item.getProfit().contains("-")){
                    holder.setBackgroundRes(R.id.tv_profit, R.color.common_red);
                }else {
                    holder.setBackgroundRes(R.id.tv_profit, R.color.common_green);
                }

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
                return mPresenter.getDealOrder(page, size);
            }
        });
    }

    public static DealFragment newInstance() {
        DealFragment fragment = new DealFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }


}

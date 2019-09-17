package com.sskj.contact;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.sskj.common.DividerLineItemDecoration;
import com.sskj.common.adapter.BaseAdapter;
import com.sskj.common.adapter.ViewHolder;
import com.sskj.common.base.BaseFragment;
import com.sskj.common.helper.DataSource;
import com.sskj.common.helper.SmartRefreshHelper;
import com.sskj.common.rxbus.RxBus;
import com.sskj.common.rxbus.Subscribe;
import com.sskj.common.rxbus.ThreadMode;
import com.sskj.common.user.data.UserBean;
import com.sskj.common.utils.ClickUtil;
import com.sskj.common.utils.DigitUtils;
import com.sskj.common.utils.NumberUtils;
import com.sskj.common.utils.ScreenUtil;
import com.sskj.common.utils.TimeFormatUtil;
import com.sskj.contact.data.EntrustOrder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 委托订单
 *
 * @author Hey
 * Create at  2019/08/26 16:58:03
 */
public class EntrustFragment extends BaseFragment<EntrustPresenter> {
    @BindView(R2.id.order_list)
    RecyclerView orderList;
    BaseAdapter<EntrustOrder> adapter;
    Disposable disposable;
    int size = 10;
    String code;
    private SmartRefreshHelper<EntrustOrder> smartRefreshHelper;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.contact_fragment_entrust;
    }

    @Override
    public EntrustPresenter getPresenter() {
        return new EntrustPresenter();
    }

    @Override
    public void initView() {
        if (getArguments() != null) {
            code = getArguments().getString("code");
        }
        RxBus.getDefault().register(this);
        orderList.setLayoutManager(new LinearLayoutManager(getContext()));
        orderList.addItemDecoration(new DividerLineItemDecoration(getContext())
                .setDividerColor(color(R.color.common_background))
                .setPaintWidth(ScreenUtil.dp2px(getContext(), 5))
        );
    }


    @Override
    public void initData() {
        adapter = new BaseAdapter<EntrustOrder>(R.layout.contact_item_entrust, null, orderList) {
            @Override
            public void bind(ViewHolder holder, EntrustOrder item) {
                holder.setText(R.id.tv_order_type, item.getOtype() == 1 ? getString(R.string.common_make_more) : getString(R.string.common_make_empty))
                        .setTextColor(R.id.tv_order_type, item.getOtype() == 1 ? color(R.color.common_red) : color(R.color.common_green))
                        .setText(R.id.tv_coin_name, item.getPname().replace("_", "/").toUpperCase())
                        .setText(R.id.tv_create_time, TimeFormatUtil.SF_FORMAT_J.format(item.getAddtime() * 1000))
                        .setText(R.id.tv_order_num, NumberUtils.keepMaxDown(item.getBuynum(), 4))
                        .setText(R.id.tv_create_price, NumberUtils.keepMaxDown(item.getBuyprice(), DigitUtils.getDigit(item.getPname())))
                        .setText(R.id.tv_leverage_multiple, item.getLeverage())
                        .setText(R.id.tv_total_money, NumberUtils.keepMaxDown(item.getTotalprice(), 4))
                        .setText(R.id.tv_fee, NumberUtils.keepMaxDown(item.getSxfee(), 4));
                ClickUtil.click(holder.getView(R.id.btn_cancel), view -> {
                    mPresenter.cancelOrder(item.getEn_id());
                });
            }
        };
        smartRefreshHelper = new SmartRefreshHelper<>(getContext(), adapter);
    }

    @Override
    public void loadData() {
        userViewModel.getUser().observe(this, new Observer<UserBean>() {
            @Override
            public void onChanged(@Nullable UserBean userBean) {
                if (userBean != null) {
                    smartRefreshHelper.setDataSource(new DataSource<EntrustOrder>() {
                        @Override
                        public Flowable<List<EntrustOrder>> loadData(int page) {
                            if (TextUtils.isEmpty(code)) {
                                return mPresenter.getNoCodeEntrustOrder(page, size);
                            } else {
                                return mPresenter.getEntrustOrder(page, size, code);
                            }
                        }
                    });
                    disposable = Flowable.interval(2, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(Long aLong) throws Exception {
                            if (smartRefreshHelper != null) {
                                smartRefreshHelper.loadData(false);
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    public void lazyLoad() {
    }

    public static EntrustFragment newInstance(String code) {
        EntrustFragment fragment = new EntrustFragment();
        Bundle bundle = new Bundle();
        bundle.putString("code", code);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void limitPriceAddOrderSuccess(String des) {
        if (des.equals("LimitPriceSuccess")) {
            smartRefreshHelper.loadData();
        }
    }

    /**
     * 取消订单成功
     */
    public void cancelOrderSuccess() {
        smartRefreshHelper.loadData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBus.getDefault().unregister(this);
        if (disposable != null) {
            disposable.dispose();
        }
    }
}

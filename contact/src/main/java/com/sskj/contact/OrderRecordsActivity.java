package com.sskj.contact;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sskj.common.base.BaseActivity;
import com.sskj.common.rxbus.RxBus;
import com.sskj.common.tab.TabItem;
import com.sskj.common.tab.TabLayout;
import com.sskj.common.tab.TabSelectListener;
import com.sskj.common.utils.ClickUtil;
import com.sskj.common.utils.NumberUtils;
import com.sskj.contact.data.DetailOrder;
import com.sskj.contact.dialog.ContactCloseAllOrderDialog;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * @author Hey
 * Create at  2019/08/28 09:24:57
 */
public class OrderRecordsActivity extends BaseActivity<OrderRecordsPresenter> {
    @BindView(R2.id.tv_total)
    TextView tvTotal;
    @BindView(R2.id.tv_useful)
    TextView tvUseful;
    @BindView(R2.id.tv_freeze)
    TextView tvFreeze;
    @BindView(R2.id.tv_profit)
    TextView tvProfit;
    @BindView(R2.id.tv_burst_rate)
    TextView tvBurstRate;
    @BindView(R2.id.tv_burst_rate_des)
    TextView tvBurstRateDes;
    @BindView(R2.id.layout_profit)
    LinearLayout layoutProfit;
    @BindView(R2.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R2.id.tv_close_all)
    TextView tvCloseAll;
    String code;
    Disposable disposable;

    private ArrayList<TabItem> tabItems = new ArrayList<>();
    private ArrayList<Fragment> fragments = new ArrayList<>();


    @Override
    public int getLayoutId() {
        return R.layout.contact_activity_order_records;
    }

    @Override
    public OrderRecordsPresenter getPresenter() {
        return new OrderRecordsPresenter();
    }

    @Override
    public void initView() {
        code = getIntent().getStringExtra("code");
        tabItems.add(new TabItem(getString(R.string.contact_orderRecordsActivity1)));
        tabItems.add(new TabItem(getString(R.string.contact_orderRecordsActivity2)));
        tabItems.add(new TabItem(getString(R.string.contact_orderRecordsActivity3)));
        fragments.add(HoldFragment.newInstance(code));
        fragments.add(EntrustFragment.newInstance(""));
        fragments.add(DealFragment.newInstance(code));
        tabLayout.setTabData(tabItems, getSupportFragmentManager(), R.id.order_content, fragments);
        ClickUtil.click(tvCloseAll, view -> {
            ContactCloseAllOrderDialog.getInstance()
                    .setConfirmListener(() -> {
                        mPresenter.closeAllOrder();
                    }).show(getSupportFragmentManager(), "");
        });
    }

    @Override
    public void initEvent() {
        super.initEvent();
        tabLayout.setOnTabSelectListener(new TabSelectListener() {
            @Override
            public boolean onTabSelect(int position) {
                if (position == 0) {
                    tvCloseAll.setVisibility(View.VISIBLE);
                } else {
                    tvCloseAll.setVisibility(View.GONE);
                }
                return true;
            }

            @Override
            public boolean onTabReselect(int position) {
                return false;
            }
        });
    }

    public void closeAllOrderSuccess() {
        RxBus.getDefault().post("refreshDealOrder");
    }

    @Override
    public void initData() {

    }

    @Override
    public void loadData() {
        disposable = Flowable.interval(0, 2, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                mPresenter.getDetailInfo(code);
            }
        });
    }

    public static void start(Context context, String code) {
        Intent intent = new Intent(context, OrderRecordsActivity.class);
        intent.putExtra("code", code);
        context.startActivity(intent);
    }

    public void setDetailInfo(DetailOrder data) {
        setText(tvTotal, NumberUtils.keepMaxDown(data.getTotalusdt(), 4));
        setText(tvUseful, NumberUtils.keepMaxDown(data.getKeyong_price(), 4));
        setText(tvProfit, NumberUtils.keepMaxDown(data.getYingkui(), 4));
        setText(tvFreeze, NumberUtils.keepMaxDown(data.getTotaldeposit(), 4));
        setText(tvBurstRate, data.getRisk());
        if (data.getYingkui().contains("-")) {
            layoutProfit.setBackgroundResource(R.drawable.common_green_bg_5);
            tvBurstRate.setTextColor(color(R.color.common_green));
            tvBurstRateDes.setTextColor(color(R.color.common_green));
        } else {
            layoutProfit.setBackgroundResource(R.drawable.common_red_bg_5);
            tvBurstRate.setTextColor(color(R.color.common_red));
            tvBurstRateDes.setTextColor(color(R.color.common_red));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}

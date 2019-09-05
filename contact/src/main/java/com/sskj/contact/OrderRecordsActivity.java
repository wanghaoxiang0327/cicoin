package com.sskj.contact;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.sskj.common.base.BaseActivity;
import com.sskj.common.tab.TabItem;
import com.sskj.common.tab.TabLayout;
import com.sskj.common.utils.NumberUtils;
import com.sskj.contact.data.DetailOrder;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    @BindView(R2.id.layout_profit)
    LinearLayout layoutProfit;
    @BindView(R2.id.tabLayout)
    TabLayout tabLayout;

    String code;


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
        fragments.add(EntrustFragment.newInstance());
        fragments.add(DealFragment.newInstance(code));
        tabLayout.setTabData(tabItems, getSupportFragmentManager(), R.id.order_content, fragments);

    }

    @Override
    public void initData() {

    }

    @Override
    public void loadData() {
        mPresenter.getDetailInfo(code);
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
        } else {
            layoutProfit.setBackgroundResource(R.drawable.common_red_bg_5);
        }

    }
}

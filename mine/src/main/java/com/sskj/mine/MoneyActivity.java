package com.sskj.mine;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.sskj.common.DividerLineItemDecoration;
import com.sskj.common.adapter.BaseAdapter;
import com.sskj.common.adapter.ViewHolder;
import com.sskj.common.base.BaseActivity;
import com.sskj.common.utils.NumberUtils;
import com.sskj.common.utils.TimeFormatUtil;
import com.sskj.mine.MoneyPresenter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sskj.mine.R;
import com.sskj.mine.data.CommissionBean;
import com.sskj.mine.data.CommissionDetailBean;

import butterknife.BindView;

/**
 * @author Hey
 * Create at  2019/09/05 09:37:33
 */
public class MoneyActivity extends BaseActivity<MoneyPresenter> {


    @BindView(R2.id.list)
    RecyclerView detailList;
    BaseAdapter<CommissionDetailBean> commissionDetailAdapter;

    private int size = 15;
    private int page = 1;


    @Override
    public int getLayoutId() {
        return R.layout.mine_activity_money;
    }

    @Override
    public MoneyPresenter getPresenter() {
        return new MoneyPresenter();
    }

    @Override
    public void initView() {
        detailList.addItemDecoration(new DividerLineItemDecoration(this)
                .setDividerColor(color(R.color.common_divider))
                .setLastDraw(false)
                .setFirstDraw(false));
        detailList.setLayoutManager(new LinearLayoutManager(this));
        commissionDetailAdapter = new BaseAdapter<CommissionDetailBean>(R.layout.mine_item_comission_detail, null, detailList) {
            @Override
            public void bind(ViewHolder holder, CommissionDetailBean item) {
                holder.setText(R.id.user_id, item.getDown_account())
                        .setText(R.id.count, item.getFee() + item.getPname())
                        .setText(R.id.time, TimeFormatUtil.SF_FORMAT_E.format(item.getAddtime() * 1000));
            }
        };
    }


    @Override
    public void initData() {
        wrapRefresh(detailList);
    }

    @Override
    public void loadData() {
        mPresenter.getCommsion(page, size);
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, MoneyActivity.class);
        context.startActivity(intent);
    }


    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        page = 1;
        mPresenter.getCommsion(page, size);
    }

    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        page++;
        mPresenter.getCommsion(page, size);
    }


    public void setCommission(CommissionBean data) {
        if (page == 1) {
            commissionDetailAdapter.setNewData(data.getData().getList());
        } else {
            commissionDetailAdapter.addData(data.getData().getList());
        }

        if (data.getData().getList() == null || data.getData().getList().isEmpty()) {
            mRefreshLayout.setNoMoreData(true);
        }
    }

}

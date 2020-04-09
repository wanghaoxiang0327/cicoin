package com.sskj.miner.ui.activity;

import com.allen.library.SuperTextView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.sskj.common.DividerLineItemDecoration;
import com.sskj.common.adapter.BaseAdapter;
import com.sskj.common.adapter.ViewHolder;
import com.sskj.common.base.BaseActivity;
import com.sskj.common.view.EmptyView;
import com.sskj.miner.R2;
import com.sskj.miner.bean.UsdtBean;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sskj.miner.R;

import butterknife.BindView;

/**
 * @author Hey
 * Create at  2019/09/05 18:20:34
 */
public class SyActivity extends BaseActivity<SyPresenter> {


    @BindView(R2.id.recycle)
    RecyclerView teamList;
    BaseAdapter<UsdtBean.DataBean> teamAdapter;

    private int page = 1;
    private int size = 10;

    @Override
    public int getLayoutId() {
        return R.layout.miner_activity_sy;
    }

    @Override
    public SyPresenter getPresenter() {
        return new SyPresenter();
    }


    @Override
    public void initView() {
        teamList.addItemDecoration(new DividerLineItemDecoration(this)
                .setFirstDraw(false)
                .setLastDraw(false)
                .setDividerColor(color(R.color.common_divider))
        );
        teamList.setLayoutManager(new LinearLayoutManager(this));
        teamAdapter = new BaseAdapter<UsdtBean.DataBean>(R.layout.miner_item_usdt, null, teamList) {
            @Override
            public void bind(ViewHolder holder, UsdtBean.DataBean item) {
                SuperTextView sp = holder.getView(R.id.usdt);
                sp.setLeftString(item.getNum())
                        .setRightString(item.getTime());
            }
        };
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, SyActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void initData() {
        wrapRefresh(teamList);
    }

    @Override
    public void loadData() {
        mPresenter.getUsdt(page, size);
    }

    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        page++;
        mPresenter.getUsdt(page, size);
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        page = 1;
        mPresenter.getUsdt(page, size);
    }


    public void setUsdt(UsdtBean data) {

        mRefreshLayout.finishLoadMore();
        mRefreshLayout.finishRefresh();
        if (page == 1) {
            teamAdapter.setNewData(data.getData());
            teamAdapter.notifyDataSetChanged();
        } else {
            teamAdapter.addData(data.getData());
            teamAdapter.notifyDataSetChanged();
        }

        if (data.getData() == null || data.getData().isEmpty()) {
            mRefreshLayout.setNoMoreData(true);
            teamAdapter.setEmptyView(new EmptyView(this));
            teamAdapter.notifyDataSetChanged();
        }

    }

}

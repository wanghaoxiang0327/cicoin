package com.sskj.miner.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.sskj.common.DividerLineItemDecoration;
import com.sskj.common.adapter.BaseAdapter;
import com.sskj.common.adapter.ViewHolder;
import com.sskj.common.base.BaseActivity;
import com.sskj.common.utils.TimeFormatUtil;
import com.sskj.common.view.EmptyView;
import com.sskj.miner.R;
import com.sskj.miner.R2;
import com.sskj.miner.bean.ForceBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Hey
 * Create at  2019/09/05 18:20:14
 */
public class ForceActivity extends BaseActivity<ForcePresenter> {


    @BindView(R2.id.recycle)
    RecyclerView teamList;
    BaseAdapter<ForceBean.DataBean> teamAdapter;

    private int page = 1;
    private int size = 10;

    @Override
    public int getLayoutId() {
        return R.layout.miner_activity_force;
    }

    @Override
    public ForcePresenter getPresenter() {
        return new ForcePresenter();
    }

    @Override
    public void initView() {
        teamList.addItemDecoration(new DividerLineItemDecoration(this)
                .setFirstDraw(false)
                .setLastDraw(false)
                .setDividerColor(color(R.color.common_divider))
        );
        teamList.setLayoutManager(new LinearLayoutManager(this));
        teamAdapter = new BaseAdapter<ForceBean.DataBean>(R.layout.miner_item_force, null, teamList) {
            @Override
            public void bind(ViewHolder holder, ForceBean.DataBean item) {
                String name = "";
                switch (item.getType()) {
                    case "0":
                        name = "签到";
                        break;
                    case "1":
                        name = "交易";
                        break;
                    case "2":
                        name = "撤单";
                        break;
                    case "3":
                        name = "提交";
                        break;
                    default:
                        name = "签到";
                        break;
                }
                holder.setText(R.id.name, name)
                        .setText(R.id.account, item.getNums())
                        .setText(R.id.time, item.getTime());
            }
        };
    }


    public static void start(Context context) {
        Intent intent = new Intent(context, ForceActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void initData() {
        wrapRefresh(teamList);
    }

    @Override
    public void loadData() {
        mPresenter.getforce(page, size);
    }

    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        page++;
        mPresenter.getforce(page, size);
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        page = 1;
        mPresenter.getforce(page, size);
    }


    public void setForce(ForceBean data) {
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadMore();
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

package com.sskj.cicoin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.sskj.common.adapter.BaseAdapter;
import com.sskj.common.adapter.ViewHolder;
import com.sskj.common.base.BaseActivity;
import com.sskj.common.http.Page;
import com.sskj.common.utils.ClickUtil;
import com.sskj.common.utils.TimeFormatUtil;
import com.sskj.common.view.ToolBarLayout;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Hey
 * Create at  2019/09/03 15:27:17
 */
public class NoticeListActivity extends BaseActivity<NoticeListPresenter> {
    @BindView(R.id.toolbar)
    ToolBarLayout toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private int page = 1;
    BaseAdapter<NoticeBean> teamAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.app_activity_notice_list;
    }

    @Override
    public NoticeListPresenter getPresenter() {
        return new NoticeListPresenter();
    }

    @Override
    public void initView() {
        toolbar.setTitle(getResources().getString(R.string.app_platform_announcement));
        wrapRefresh(recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        teamAdapter = new BaseAdapter<NoticeBean>(R.layout.app_item_notice_list, null, recyclerView) {
            @Override
            public void bind(ViewHolder holder, NoticeBean item) {
                holder.setText(R.id.tv_title, item.title)
                        .setText(R.id.tv_create_time, TimeFormatUtil.SF_FORMAT_E.format(Long.valueOf(item.create_time) * 1000));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        NewsBean newsBean = new NewsBean();
                        newsBean.setId(item.id);
                        newsBean.setTitle(item.title);
                        NewsDetailActivity.start(mContext, newsBean, 1);
                    }
                });
            }
        };
    }

    @Override
    public void initData() {
        mPresenter.getNoticeList(page + "");
    }

    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        page++;
        mPresenter.getNoticeList(page + "");
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        page = 1;
        mPresenter.getNoticeList(page + "");
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, NoticeListActivity.class);
        context.startActivity(intent);
    }

    public void setNotice(Page<NoticeBean> data) {
        if (data != null) {
            if (page == 1) {
                teamAdapter.setNewData(data.getRes());
            } else {
                teamAdapter.addData(data.getRes());
            }
            if (data.getRes() == null || data.getRes().isEmpty()) {
                mRefreshLayout.setNoMoreData(true);
            }
        }
    }
}

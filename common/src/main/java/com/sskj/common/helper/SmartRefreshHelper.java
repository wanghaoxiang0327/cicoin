package com.sskj.common.helper;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.github.nukc.stateview.StateView;
import com.hjq.toast.ToastUtils;
import com.lzy.okgo.exception.HttpException;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * 实现自动下拉刷新，加载更多
 * @author Hey
 * @param <T>
 */
public class SmartRefreshHelper<T> extends BaseHelper<T> implements LoadListener<T>, IRefreshView<T> {

    private SmartRefreshLayout mRefreshLayout;

    private IDataSoucre<T> dataSource;

    private IAdapter<T> adapter;

    private Context mContext;

    private boolean enableRefresh = true;

    private boolean enableLoadMore = true;

    private StateView stateView;


    public SmartRefreshHelper(Context context, IAdapter<T> adapter) {
        mContext = context;
        this.adapter = adapter;
        wrapRefresh(adapter.getContentView());
        stateView = StateView.inject(mRefreshLayout);
        stateView.setOnRetryClickListener(() -> {
            loadData();
        });
    }

    @Override
    public void onSuccess(List<T> data, boolean refresh) {
        if (refresh) {
            adapter.setRefreshData(data);
            mRefreshLayout.finishRefresh();
        } else {
            adapter.addMoreData(data);
            mRefreshLayout.finishLoadMore();
        }
        stateView.showContent();
        mRefreshLayout.setNoMoreData(!dataSource.hasMore());
    }

    @Override
    public void onFailure(Throwable e, boolean refresh) {
        if (refresh) {
            mRefreshLayout.finishRefresh();
        } else {
            mRefreshLayout.finishLoadMore();
        }

        if (e instanceof UnknownHostException || e instanceof TimeoutException) {
            if (adapter.getCount() == 0) {
                stateView.showRetry();
            } else {
                ToastUtils.show("网络好像有点问题~");
            }
        }else if (e instanceof HttpException){
            if (adapter.getCount() == 0) {
                stateView.showRetry();
            } else {
                ToastUtils.show("服务器好像有点问题~");
            }
        }
    }

    @Override
    public void setAdapter(IAdapter<T> adapter) {
        this.adapter = adapter;
        wrapRefresh(adapter.getContentView());
    }

    @Override
    public void setDataSource(IDataSoucre<T> dataSource) {
        this.dataSource = dataSource;
        loadData();
    }


    @Override
    public void refresh() {
        mRefreshLayout.autoRefresh();
    }

    @Override
    public void loadMore() {
        mRefreshLayout.autoLoadMore();
    }

    @Override
    public void loadData() {
        dataSource.refresh(this);
    }


    /**
     * 包裹模式 在view的外层包裹上RefreshLayout
     *
     * @param view
     */
    public void wrapRefresh(View view) {
        if (view != null && view.getParent() != null) {
            initRefreshLayout();
            ViewGroup parentView = (ViewGroup) view.getParent();
            parentView.removeView(view);
            mRefreshLayout.addView(view);
            parentView.addView(mRefreshLayout);
        }
    }

    /**
     * 初始化RefreshLayout
     */
    public void initRefreshLayout() {
        if (mRefreshLayout == null) {
            mRefreshLayout = new SmartRefreshLayout(mContext);
            mRefreshLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            mRefreshLayout.setEnableRefresh(enableRefresh);
            mRefreshLayout.setEnableLoadMore(enableLoadMore);
            mRefreshLayout.setOnRefreshListener(refreshLayout -> {
                dataSource.refresh(this);
            });
            mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
                dataSource.loadMore(this);
            });
        }
    }


    public void setEnableRefresh(boolean enableRefresh) {
        this.enableRefresh = enableRefresh;
        if (mRefreshLayout != null) {
            mRefreshLayout.setEnableRefresh(enableRefresh);
        }
    }

    public void setEnableLoadMore(boolean enableLoadMore) {
        this.enableLoadMore = enableLoadMore;
        if (mRefreshLayout != null) {
            mRefreshLayout.setEnableLoadMore(enableLoadMore);
        }
    }
}

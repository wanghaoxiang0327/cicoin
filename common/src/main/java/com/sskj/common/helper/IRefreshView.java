package com.sskj.common.helper;

public interface IRefreshView<T> {

    /**
     * 下拉刷新
     */
    void refresh();

    /**
     * 加载更多
     */
    void loadMore();

    /**
     * 加载数据
     */
    void loadData();

}

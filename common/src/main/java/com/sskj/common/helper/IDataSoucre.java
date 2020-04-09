package com.sskj.common.helper;

public interface IDataSoucre<T> {

    void refresh(LoadListener<T> loadListener);

    void loadMore(LoadListener<T> loadListener);

    boolean hasMore();
}

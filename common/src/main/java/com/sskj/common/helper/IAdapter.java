package com.sskj.common.helper;

import android.support.v7.widget.RecyclerView;

import java.util.Collection;
import java.util.List;

public interface IAdapter<T> {

    void setRefreshData(List<T> data);

    void addMoreData(Collection<? extends T> data);

    RecyclerView getContentView();

    int getCount();
}

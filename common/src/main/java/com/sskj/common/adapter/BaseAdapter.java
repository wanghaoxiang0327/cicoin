package com.sskj.common.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sskj.common.helper.IAdapter;

import java.util.Collection;
import java.util.List;

public abstract class BaseAdapter<T> extends BaseQuickAdapter<T, ViewHolder> implements IAdapter<T> {


    private RecyclerView mRecyclerView;

    public BaseAdapter(int layoutResId, @Nullable List<T> data, RecyclerView recyclerView) {
        super(layoutResId, data);
        if (recyclerView != null) {
            mRecyclerView = recyclerView;
            this.bindToRecyclerView(recyclerView);
        }
    }

    public BaseAdapter(int layoutResId, @Nullable List<T> data, RecyclerView recyclerView, boolean showEmpty) {
        this(layoutResId, data, recyclerView);
        if (showEmpty) {
            mRecyclerView = recyclerView;
        }
    }

    @Override
    protected void convert(ViewHolder helper, T item) {
        bind(helper, item);
    }

    public abstract void bind(ViewHolder holder, T item);


    @Override
    public void addData(Collection<? extends T> newData) {
        if (newData != null) {
            super.addData(newData);
        }
    }

    @Override
    public void addMoreData(Collection<? extends T> data) {
        addData(data);
    }

    @Override
    public void setRefreshData(List<T> data) {
        setNewData(data);
    }


    @Override
    public RecyclerView getContentView() {
        return mRecyclerView;
    }

    @Override
    public int getCount() {
        return getData().size();
    }
}

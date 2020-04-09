package com.sskj.common.helper;

/**
 *
 * @author Hey
 * @param <T>
 */
public abstract class BaseHelper<T> {
    /**
     * 设置数据适配器
     * @param adapter
     */
    abstract public void setAdapter(IAdapter<T> adapter);

    /**
     * 设置数据源
     * @param dataSource
     */
    abstract public void setDataSource(IDataSoucre<T> dataSource);

}

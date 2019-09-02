package com.sskj.common.helper;

import java.util.List;

/**
 * 数据加载监听
 * @author Hey
 */
public interface LoadListener<T> {

    /**
     * 获取成功
     * @param data
     * @param refresh 是否是刷新
     */
     void onSuccess(List<T> data, boolean refresh);

    /**
     * 获取失败
     * @param e
     * @param refresh 是否是刷新
     */
     void onFailure(Throwable e, boolean refresh);

}

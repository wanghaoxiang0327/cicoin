package com.sskj.market.data;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.sskj.common.data.CoinBean;

public class MarketDetail implements MultiItemEntity {


    public static final int TOP = 0;
    public static final int CHART = 1;
    public static final int BOTTOM = 2;

    private int itemType;

    private CoinBean topData;


    public MarketDetail(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public CoinBean getTopData() {
        return topData;
    }

    public void setTopData(CoinBean topData) {
        this.topData = topData;
    }
}

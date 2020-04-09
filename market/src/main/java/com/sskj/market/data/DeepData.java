package com.sskj.market.data;

import java.util.List;

/**
 * 深度列表
 *
 * @author Hey
 */
public class DeepData {

    private float buyPrice;
    private float sellPrice;
    private float buyCount;
    private float sellCount;

    public float getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(float buyPrice) {
        this.buyPrice = buyPrice;
    }

    public float getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(float sellPrice) {
        this.sellPrice = sellPrice;
    }

    public float getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(float buyCount) {
        this.buyCount = buyCount;
    }

    public float getSellCount() {
        return sellCount;
    }

    public void setSellCount(float sellCount) {
        this.sellCount = sellCount;
    }
}

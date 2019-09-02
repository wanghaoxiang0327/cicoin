package com.sskj.market.data;

import com.sskj.depthlib.data.DepthData;
import com.sskj.market.data.DeepData;

import java.util.List;

public class BuySellData {

    private String name;
    private String code;
    private List<DepthData> bids;
    private List<DepthData> asks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public List<DepthData> getBids() {
        return bids;
    }

    public void setBids(List<DepthData> bids) {
        this.bids = bids;
    }

    public List<DepthData> getAsks() {
        return asks;
    }

    public void setAsks(List<DepthData> asks) {
        this.asks = asks;
    }
}

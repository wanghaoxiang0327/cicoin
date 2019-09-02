package com.sskj.market.data;

import com.alibaba.fastjson.annotation.JSONField;

public class Trade {

    @JSONField(name = "dt")
    private long createTime;
    @JSONField(name = "dc")
    private String type;
    private String amount;
    private String price;

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}

package com.sskj.market.data;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class TradeData {

    private String code;
    private String name;
    private long timestamp;
    private List<Trade> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public List<Trade> getData() {
        return data;
    }

    public void setData(List<Trade> data) {
        this.data = data;
    }
}
